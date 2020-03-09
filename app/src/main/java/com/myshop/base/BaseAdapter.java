package com.myshop.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 *
 * 基类里面实现的点
 * 1. 数据公用--用泛型T
 * 2. 创建item条目 abstract方法
 * 3. 封装BaseViewHolder,实现动态获取item中的组件，并且优化获取组件的方式（空间换时间）
 * 4. 封装数据绑定到界面
 * 5. 封装数据的刷新
 *
 * @param <T>
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter {

    private List<T> mDatas;
    protected Context mContext;
    private ItemClickListener clickListener;
    //用来响应列表条目中的触发事件
    protected View.OnClickListener onClickListener;

    public BaseAdapter(Context context,List<T> mDatas){
        this.mContext = context;
        this.mDatas = mDatas;
    }

    /**
     * 创建item的view并且关联到ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(getLayout(),null);
        BaseViewHolder viewHolder = new BaseViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickListener != null){
                    clickListener.itemClick(viewHolder,viewHolder.getLayoutPosition());
                }
            }
        });
        return viewHolder;
    }

    /**
     * 绑定数据到item
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder h = (BaseViewHolder) holder;
        T data = mDatas.get(position);
        bindData(h,data);
    }

    //读取当前列表数据的数量
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    //定义一个获取item条目的抽象方法
    public abstract int getLayout();
    //定义一个绑定数据到item条目的方法
    public abstract void bindData(BaseViewHolder holder,T data);

    /**
     * 整体列表的刷新
     * @param list
     */
    public void updateList(List<T> list){
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 在列表的尾部添加数据
     * @param list
     */
    public void updateMoreList(List<T> list){
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * viewholder封装类
     */
    public static class BaseViewHolder extends RecyclerView.ViewHolder{

        SparseArray views;
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            views = new SparseArray();
        }

        /**
         * 获取item对应的View
         * @param id
         * @return
         */
        public View getViewById(int id){
            View view = (View) views.get(id);
            if(view == null){
                view = itemView.findViewById(id);
                views.append(id,view);
            }
            return view;
        }
    }

    /**
     * 添加接口回调
     * @param listner
     */
    public void addItemClickListener(ItemClickListener listner){
        clickListener = listner;
    }

    /**
     * 绑定条目中的组件触发事件
     * @param listener
     */
    public void addOnClickListener(View.OnClickListener listener){
        onClickListener = listener;
    }

    /**
     * 接口回调的定义
     */
    public interface ItemClickListener{
        void itemClick(BaseViewHolder holder,int position);
    }
}

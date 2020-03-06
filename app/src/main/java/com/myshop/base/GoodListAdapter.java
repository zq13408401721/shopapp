package com.myshop.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myshop.R;

import java.util.List;

public class GoodListAdapter extends RecyclerView.Adapter {

    private List<String> datas;

    private Context context;
    public GoodListAdapter(Context context,List<String> datas){
        this.context = context;
        this.datas = datas;
    }


    //用来创建列表条目
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //动态创建View
        //View view = View.inflate(context, R.layout.layout_item_good,parent);

        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_good,parent);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        String name = datas.get(position);
        TextView txtName = (TextView) myViewHolder.getViewById(R.id.txt_item_name);
        txtName.setText(name);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 创建一个列表条目关联的ViewHolder
     *
     * 如何用一个ViewHolder的类，去实现关联不同的item的xml布局的显示文件？？
     */
    class MyViewHolder extends RecyclerView.ViewHolder{


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public View getViewById(int id){
            View view = null;
            view = itemView.findViewById(id);
            return view;
        }
    }
}

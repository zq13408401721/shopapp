package com.myshop.ui.home;

import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myshop.R;
import com.myshop.base.BaseAdapter;
import com.myshop.base.BaseFragment;
import com.myshop.interfaces.home.HomeConstract;
import com.myshop.model.bean.HomeBean;
import com.myshop.presenter.home.HomePresenter;
import com.myshop.ui.home.adapter.BrandAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {

    @BindView(R.id.txt_brand_title)
    TextView txtBrandTitle;
    @BindView(R.id.recy_brand)
    RecyclerView recyBrand;
    @BindView(R.id.txt_topic_title)
    TextView txtTopicTitle;
    @BindView(R.id.recy_topic)
    RecyclerView recyTopic;

    //brand列表
    List<HomeBean.DataBean.BrandListBean> brandList;
    BrandAdapter brandAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeConstract.Presenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        brandList = new ArrayList<>();
        brandAdapter = new BrandAdapter(context,brandList);
        recyBrand.setLayoutManager(new GridLayoutManager(context,2));
        recyBrand.setAdapter(brandAdapter);
        //设置Brand列表的接口回调
        brandAdapter.addItemClickListener(new BaseAdapter.ItemClickListener() {
            @Override
            public void itemClick(BaseAdapter.BaseViewHolder holder, int position) {
                //接收列表条目的接口回调
                Toast.makeText(context, "click pos "+position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    public void getHomeDataReturn(HomeBean result) {

        updateBrand(result.getData().getBrandList());

        updateTopic(result.getData().getTopicList());
    }

    /**
     * 刷新品牌制造商的列表
     * @param brandList
     */
    private void updateBrand(List<HomeBean.DataBean.BrandListBean> brandList){
        brandAdapter.updateList(brandList);
    }

    /**
     * 刷新专题的列表
     * @param topicList
     */
    private void updateTopic(List<HomeBean.DataBean.TopicListBean> topicList){

    }


}
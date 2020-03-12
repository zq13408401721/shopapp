package com.myshop.ui.home;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.myshop.R;
import com.myshop.base.BaseFragment;
import com.myshop.common.GlideImageLoader;
import com.myshop.interfaces.home.HomeConstract;
import com.myshop.model.bean.HomeBean;
import com.myshop.presenter.home.HomePresenter;
import com.myshop.ui.home.adapter.HomeAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewHomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<HomeBean.HomeListBean> list;
    HomeAdapter homeAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_new_home;
    }

    @Override
    protected HomeConstract.Presenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        homeAdapter = new HomeAdapter(list);

        //监听计算当前条目占用的列表
        homeAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int i) {
                int type = list.get(i).getItemType();
                switch (type){
                    case HomeBean.HomeListBean.TYPE_BANNER:
                    case HomeBean.HomeListBean.TYPE_CHANNEL:
                    case HomeBean.HomeListBean.TYPE_TITLE:
                    case HomeBean.HomeListBean.TYPE_HOTGOOD:
                    case HomeBean.HomeListBean.TYPE_TOPIC:
                    case HomeBean.HomeListBean.TYPE_VIEW_LINE:
                        return 2;
                    case HomeBean.HomeListBean.TYPE_BRAND:
                    case HomeBean.HomeListBean.TYPE_NEWGOOD:
                    case HomeBean.HomeListBean.TYPE_CATEGORY:
                        return 1;
                }
                return 0;
            }
        });
        homeAdapter.bindToRecyclerView(recyclerView);
    }

    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    public void getHomeDataReturn(List<HomeBean.HomeListBean> result) {
        addHeader(result.remove(0));
        list.addAll(result);
        homeAdapter.notifyDataSetChanged();

    }

    private void addHeader(HomeBean.HomeListBean head){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item_banner,null);
        Banner banner = view.findViewById(R.id.banner);
            List<String> imgs = new ArrayList<>();
            for (HomeBean.DataBean.BannerBean item : (List<HomeBean.DataBean.BannerBean>) head.data) {
                imgs.add(item.getImage_url());
            }
            banner.tag = "true";
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(imgs);
            banner.start();

        homeAdapter.addHeaderView(view);
    }

    @Override
    public void getIndexDataReturn(HomeBean result) {

    }
}

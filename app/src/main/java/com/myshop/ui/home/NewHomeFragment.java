package com.myshop.ui.home;

import com.myshop.R;
import com.myshop.base.BaseFragment;
import com.myshop.interfaces.IBasePresenter;
import com.myshop.interfaces.home.HomeConstract;
import com.myshop.model.bean.HomeBean;
import com.myshop.presenter.home.HomePresenter;

import java.util.ArrayList;
import java.util.List;

public class NewHomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {

    List<HomeBean.BaseData> list;

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
    }

    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    public void getHomeDataReturn(HomeBean result) {
        HomeBean.BaseData banner = new HomeBean.BaseData();
        banner.setType(1);
        banner.setData(result.getData().getBanner());
        list.add(banner);
        HomeBean.BaseData brand = new HomeBean.BaseData();
        brand.setType(2);
        brand.setData(result.getData().getBrandList());
        list.add(brand);
    }

    @Override
    public void getIndexDataReturn(HomeBean result) {

    }
}

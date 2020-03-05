package com.myshop.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.myshop.R;
import com.myshop.base.BaseFragment;
import com.myshop.interfaces.IBasePresenter;
import com.myshop.interfaces.home.HomeConstract;
import com.myshop.model.bean.HomeBean;
import com.myshop.presenter.home.HomePresenter;

public class HomeFragment extends BaseFragment<HomeConstract.Presenter> implements HomeConstract.View {

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

    }

    @Override
    protected void initData() {
        presenter.getHomeData();
    }

    @Override
    public void getHomeDataReturn(HomeBean result) {
        System.out.print(result);
    }
}
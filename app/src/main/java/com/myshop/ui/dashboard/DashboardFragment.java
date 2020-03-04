package com.myshop.ui.dashboard;

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
import com.myshop.interfaces.topic.TopicConstract;
import com.myshop.model.bean.TopicBean;
import com.myshop.presenter.topic.TopicPresenter;

public class DashboardFragment extends BaseFragment<TopicConstract.Presenter> implements TopicConstract.View {

    int page = 1;
    int size = 100;

    @Override
    protected int getLayout() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected TopicConstract.Presenter createPresenter() {
        return new TopicPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presenter.getTopicData(page,size);
    }

    @Override
    public void getTopicDataReturn(TopicBean result) {

    }
}
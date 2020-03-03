package com.myshop.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.myshop.R;
import com.myshop.interfaces.IBasePresenter;
import com.myshop.interfaces.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 抽取V层实现基类
 */
public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    //如果P层的类型 使用IBasePresenter可以，为什么会用P泛型？
    protected P presenter; //定义P泛型的变量
    //butterknife对象
    Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        presenter = createPresenter();
        unbinder = ButterKnife.bind(this);
        //关联View
        if(presenter != null){
            presenter.attachView(this);
        }
        initView();
        initData();
    }

    //定义获取当前界面的布局ID
    protected abstract int getLayout();
    //创建P层实例
    protected abstract P createPresenter();
    //定义初始化界面
    protected abstract void initView();
    //定义初始化数据
    protected abstract void initData();

    //信息提示
    @Override
    public void showTips(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.dettachView();
        }
        if(unbinder != null){
            unbinder.unbind();
        }
    }
}

package com.myshop.base;

import com.myshop.interfaces.IBasePresenter;
import com.myshop.interfaces.IBaseView;

import java.lang.ref.WeakReference;

/**
 * 抽取P层的基类
 * @param <V>
 */
public abstract class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {

    protected V mView; //当前关联进来的V层的类
    WeakReference<V> weakReference; //采用弱引用关联View

    //关联View
    @Override
    public void attachView(V view) {
        weakReference = new WeakReference(view);
        mView = weakReference.get();
    }

    //取消View关联
    @Override
    public void dettachView() {
        mView = null;
    }
}

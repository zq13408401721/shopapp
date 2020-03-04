package com.myshop.common;

import com.myshop.interfaces.IBaseView;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 抽取请求返回的处理
 * @param <T>
 */
public abstract class ResponseSubscriber<T> extends ResourceSubscriber<T>{

    IBaseView view;

    public ResponseSubscriber(IBaseView view) {
        this.view = view;
    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable t) {
        if(view != null){
            view.showTips(t.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }
}

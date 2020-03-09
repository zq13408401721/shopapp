package com.myshop.presenter.test;

import com.myshop.base.BasePresenter;
import com.myshop.common.ResponseSubscriber;
import com.myshop.interfaces.test.TestConstract;
import com.myshop.model.HttpManager;
import com.myshop.model.bean.AppLoginBean;
import com.myshop.utils.RxUtils;

import okhttp3.RequestBody;

public class TestPresenter extends BasePresenter<TestConstract.View> implements TestConstract.Presenter {
    @Override
    public void appLogin(String user, String pw) {
        addSubscribe(HttpManager.getInstance().getMyServer().appLogin(user,pw)
        .compose(RxUtils.rxScheduler())
        .subscribeWith(new ResponseSubscriber<AppLoginBean>(mView){
            @Override
            public void onNext(AppLoginBean appLoginBean) {
                mView.appLoginReturn(appLoginBean);
            }
        }));
    }

    @Override
    public void appLogin(RequestBody body) {
        addSubscribe(HttpManager.getInstance().getMyServer().appLoginJson(body)
                .compose(RxUtils.rxScheduler())
                .subscribeWith(new ResponseSubscriber<AppLoginBean>(mView){
                    @Override
                    public void onNext(AppLoginBean appLoginBean) {
                        mView.appLoginJsonReturn(appLoginBean);
                    }
                }));
    }
}

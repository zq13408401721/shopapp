package com.myshop.presenter.home;

import android.util.Log;

import com.myshop.base.BasePresenter;
import com.myshop.common.ResponseSubscriber;
import com.myshop.interfaces.home.HomeConstract;
import com.myshop.model.HttpManager;
import com.myshop.model.bean.HomeBean;
import com.myshop.utils.RxUtils;

import io.reactivex.functions.Function;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Response;

public class HomePresenter extends BasePresenter<HomeConstract.View> implements HomeConstract.Presenter {
    //请求主页数据
    @Override
    public void getHomeData() {
        addSubscribe(HttpManager.getInstance().getMyServer().getHome()
                .compose(RxUtils.<HomeBean>rxScheduler())
                .subscribeWith(new ResponseSubscriber<HomeBean>(mView) {
                    @Override
                    public void onNext(HomeBean result) {
                        if (result.getErrno() == 0) {
                            mView.getHomeDataReturn(result);
                        } else {
                            super.onNext(result);
                        }
                    }
                }));
    }

    /**
     *
     */
    @Override
    public void getIndexData() {
        addSubscribe(
                HttpManager.getInstance().getMyServer().getIndex()
                        .compose(RxUtils.rxScheduler())
                        .map(new Function<Response, HomeBean>() {
                            @Override
                            public HomeBean apply(Response response) throws Exception {

                                return null;
                            }
                        })
                        .subscribeWith(new ResponseSubscriber<HomeBean>(mView) {
                            @Override
                            public void onNext(HomeBean homeBean) {
                                mView.getIndexDataReturn(homeBean);
                            }
                        })
        );
    }
}

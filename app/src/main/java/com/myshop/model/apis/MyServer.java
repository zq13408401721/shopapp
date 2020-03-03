package com.myshop.model.apis;

import com.myshop.model.bean.HomeBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface MyServer {

    @GET("index")
    Flowable<HomeBean> getHome();

}

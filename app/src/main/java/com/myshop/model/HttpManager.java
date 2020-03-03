package com.myshop.model;

import com.myshop.common.Constant;
import com.myshop.model.apis.MyServer;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装网络请求的类型
 * Retrofit+Rxjava
 *
 */
public class HttpManager {

    private static volatile HttpManager instance;
    public static HttpManager getInstance(){
        if(instance == null){
            synchronized (HttpManager.class){
                if(instance == null){
                    instance = new HttpManager();
                }
            }
        }
        return instance;
    }

    //网络接口类
    private MyServer myServer;

    /**
     * 获取一个Retrofit对象
     * @return
     */
    private Retrofit getRetrofit(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient())
                .build();
        return retrofit;
    }

    /**
     * 封装网络请求的Okhttpclient对象
     * @return
     */
    private OkHttpClient okHttpClient(){
        File file = new File(Constant.PATH_CACHE);
        Cache cache = new Cache(file,100*1024*1024);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        return client;
    }

    /**
     * 获取MyServer网络接口类
     * @return
     */
    public MyServer getMyServer(){
        if(myServer == null){
            synchronized (MyServer.class){
                if(myServer == null){
                    myServer = getRetrofit(Constant.BASE_SHOP_URL).create(MyServer.class);
                }
            }
        }
        return myServer;
    }




}

package com.myshop.model;

import com.myshop.common.Constant;
import com.myshop.model.apis.MyServer;

import java.io.File;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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
        OkHttpClient client = okHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .callFactory(new CallFactoryProxy(client) {
                    @Override
                    protected HttpUrl getNowUrl(String curUrl, Request request) {
                        String oldUrl = request.url().toString();
                        String newUrl = "";
                        HttpUrl httpUrl = null;
                        for(int i=0; i<CallFactoryProxy.URLS.length; i++){
                            int index = oldUrl.indexOf(CallFactoryProxy.URLS[i]);
                            if(index >= 0){
                                //用当前的基础地址替换原来得请求地址
                                newUrl = oldUrl.replace(CallFactoryProxy.URLS[i],curUrl);
                                httpUrl = HttpUrl.get(newUrl);
                                break;
                            }
                        }
                        //如果没有新的地址替换，直接用原来得URL
                        if(httpUrl == null) httpUrl = request.url();
                        return httpUrl;
                    }
                })
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

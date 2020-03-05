package com.myshop.model;

import android.util.Log;

import com.myshop.common.Constant;
import com.myshop.model.apis.MyServer;
import com.myshop.utils.SpUtils;
import com.myshop.utils.SystemUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
                .addInterceptor(new LoggingInterceptor())
                .addInterceptor(new HeadersInterceptor())
                .addNetworkInterceptor(new NetWorkInterceptor())
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

    /**
     * 日志拦截器，打印请求接口的报文信息
     * 提供日志信息帮组优化代码
     */
    static class LoggingInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            //通过系统时间的差打印接口请求的信息
            long time = System.nanoTime();
            Request request = chain.request();
            Log.i("Request:",String.format("Sending request %s on %s%n%s",request.url(),chain.connection(),request.headers()));
            Response response = chain.proceed(request);
            long now = System.nanoTime();
            Log.i("Received:",String.format("Received response for %s in %.1fms%n%s",response.request().url(),(now-time)/1e6d,response.headers()));
            return response;
        }
    }

    /**
     * 请求的修改设置
     */
    static class HeadersInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Client-Type","ANDROID")
                    .addHeader("X-Nideshop-Token", SpUtils.getInstance().getString("token"))
                    .build();
            return chain.proceed(request);
        }
    }

    /**
     * 网络拦截器
     */
    static class NetWorkInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if(!SystemUtils.checkNetWork()){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            //通过判断网络连接是否存在获取本地或者服务器的数据
            if(!SystemUtils.checkNetWork()){
                int maxAge = 0;
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public ,max-age="+maxAge).build();
            }else{
                int maxStale = 60*60*24*28; //设置缓存数据的保存时间
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control","public, onlyif-cached, max-stale="+maxStale).build();
            }
        }
    }




}

package com.myshop.model.apis;

import com.myshop.model.bean.AppLoginBean;
import com.myshop.model.bean.HomeBean;
import com.myshop.model.bean.LoginBean;
import com.myshop.model.bean.TopicBean;

import butterknife.BindView;
import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface MyServer {

    @GET("index")
    Flowable<HomeBean> getHome();

    @Streaming
    @GET("index")
    @Headers("Data-Type:probuffer")
    Flowable<Response> getIndex();

    //专题数据请求接口
    @GET("topic/list")
    Flowable<TopicBean> getTopic(@Query("page") int page, @Query("size") int size);

    //登录接口
    @POST("auth/login")
    Flowable<LoginBean> login(@Field("nickname") String nickname, @Field("password") String pw);

    //必须用json格式
    @POST("auth/applogin")
    @FormUrlEncoded
    Flowable<AppLoginBean> appLogin(@Field("user") String user,@Field("pw") String pw);

    @POST("auth/applogin")
    Flowable<AppLoginBean> appLoginJson(@Body RequestBody body);


}

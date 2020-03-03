package com.myshop.common;

import com.myshop.apps.MyApp;

import java.io.File;

public class Constant {

    public static final String PATH_DATA = MyApp.myApp.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/shopapp";

    //商城的基础地址
    public static final String BASE_SHOP_URL = "https://cdwan.cn/api/";

}

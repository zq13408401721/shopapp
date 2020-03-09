package com.myshop.interfaces.test;

import com.myshop.interfaces.IBasePresenter;
import com.myshop.interfaces.IBaseView;
import com.myshop.model.bean.AppLoginBean;

import okhttp3.RequestBody;

public interface TestConstract {

    interface View extends IBaseView{
        void appLoginReturn(AppLoginBean result);

        void appLoginJsonReturn(AppLoginBean result);
    }

    interface Presenter extends IBasePresenter<View>{
        void appLogin(String user,String pw);

        void appLogin(RequestBody body);
    }

}

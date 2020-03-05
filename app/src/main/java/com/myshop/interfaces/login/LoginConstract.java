package com.myshop.interfaces.login;

import com.myshop.interfaces.IBasePresenter;
import com.myshop.interfaces.IBaseView;
import com.myshop.model.bean.LoginBean;

/**
 * 登录业务的契约类
 */
public interface LoginConstract {

    interface View extends IBaseView{
        void loginReturn(LoginBean result);
    }

    interface Presenter extends IBasePresenter<View>{
        void login(String nickname,String pw);
    }

}

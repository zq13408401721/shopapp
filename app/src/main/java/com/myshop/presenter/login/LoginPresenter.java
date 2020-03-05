package com.myshop.presenter.login;

import androidx.appcompat.view.menu.MenuView;

import com.myshop.base.BasePresenter;
import com.myshop.common.ResponseSubscriber;
import com.myshop.interfaces.login.LoginConstract;
import com.myshop.model.HttpManager;
import com.myshop.model.bean.LoginBean;
import com.myshop.utils.RxUtils;

public class LoginPresenter extends BasePresenter<LoginConstract.View> implements LoginConstract.Presenter {

    @Override
    public void login(String nickname, String pw) {
        addSubscribe(HttpManager.getInstance().getMyServer().login(nickname,pw)
        .compose(RxUtils.<LoginBean>rxScheduler())
        .subscribeWith(new ResponseSubscriber<LoginBean>(mView){
            @Override
            public void onNext(LoginBean loginBean) {
                if(loginBean.getErrno() == 0){
                    mView.loginReturn(loginBean);
                }else{
                    super.onNext(loginBean);
                }
            }
        }));
    }
}

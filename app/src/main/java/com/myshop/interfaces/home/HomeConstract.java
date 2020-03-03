package com.myshop.interfaces.home;

import com.myshop.interfaces.IBasePresenter;
import com.myshop.interfaces.IBaseView;
import com.myshop.model.bean.HomeBean;

public interface HomeConstract {

    interface View extends IBaseView{
        void getHomeDataReturn(HomeBean result);
    }

    interface Presenter extends IBasePresenter<View>{
        void getHomeData();
    }

}

package com.myshop.interfaces.home;

import com.myshop.interfaces.IBasePresenter;
import com.myshop.interfaces.IBaseView;
import com.myshop.model.bean.HomeBean;

/**
 * 契约类  解决高类聚 低耦合
 */
public interface HomeConstract {

    interface View extends IBaseView{
        void getHomeDataReturn(HomeBean result);

        void getIndexDataReturn(HomeBean result);
    }

    interface Presenter extends IBasePresenter<View>{
        void getHomeData();

        void getIndexData();
    }

}

package com.myshop.interfaces.topic;

import com.myshop.interfaces.IBasePresenter;
import com.myshop.interfaces.IBaseView;
import com.myshop.model.bean.TopicBean;

/**
 * 专题业务相关v，p层接口的定义
 */
public interface TopicConstract {

    //定义V层接口，方法给P层的实现类调用
    interface View extends IBaseView{
        void getTopicDataReturn(TopicBean result);
    }

    //定义专题业务的P层接口，方法给对应的V层的实现类调用
    interface Presenter extends IBasePresenter<View>{
        void getTopicData(int page,int size);
    }

}

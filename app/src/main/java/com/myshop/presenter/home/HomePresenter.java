package com.myshop.presenter.home;

import android.content.ClipData;

import com.myshop.base.BasePresenter;
import com.myshop.common.ResponseSubscriber;
import com.myshop.interfaces.home.HomeConstract;
import com.myshop.model.HttpManager;
import com.myshop.model.bean.HomeBean;
import com.myshop.utils.RxUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;
import retrofit2.Response;

public class HomePresenter extends BasePresenter<HomeConstract.View> implements HomeConstract.Presenter {
    //请求主页数据
    @Override
    public void getHomeData() {
        addSubscribe(HttpManager.getInstance().getMyServer().getHome()
                .compose(RxUtils.<HomeBean>rxScheduler())
                .map(new Function<HomeBean, List<HomeBean.HomeListBean>>() {
                    //把服务器返回的数据转换成列表数据
                    @Override
                    public List<HomeBean.HomeListBean> apply(HomeBean bean) throws Exception {
                        List<HomeBean.HomeListBean> list = new ArrayList<>();
                        //banner
                        HomeBean.HomeListBean banner = new HomeBean.HomeListBean();
                        banner.currentType = HomeBean.HomeListBean.TYPE_BANNER;
                        banner.data = bean.getData().getBanner();
                        list.add(banner);
                        //channel
                        HomeBean.HomeListBean channel = new HomeBean.HomeListBean();
                        channel.currentType = HomeBean.HomeListBean.TYPE_CHANNEL;
                        channel.data = bean.getData().getBanner();
                        list.add(channel);
                        //分割线
                        HomeBean.HomeListBean lineBrand = new HomeBean.HomeListBean();
                        lineBrand.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
                        list.add(lineBrand);
                        //title
                        HomeBean.HomeListBean brandTitle = new HomeBean.HomeListBean();
                        brandTitle.currentType = HomeBean.HomeListBean.TYPE_TITLE;
                        brandTitle.title ="品牌商直供";
                        list.add(brandTitle);
                        //brand
                        for(HomeBean.DataBean.BrandListBean item:bean.getData().getBrandList()){
                            HomeBean.HomeListBean brand = new HomeBean.HomeListBean();
                            brand.currentType = HomeBean.HomeListBean.TYPE_BRAND;
                            brand.data = item;
                            list.add(brand);
                        }
                        //newgood title
                        HomeBean.HomeListBean newGoodTitle = new HomeBean.HomeListBean();
                        newGoodTitle.currentType = HomeBean.HomeListBean.TYPE_TITLE;
                        newGoodTitle.title = "周一周四 · 新品首发";
                        list.add(newGoodTitle);
                        //newgood
                        for(HomeBean.DataBean.NewGoodsListBean item:bean.getData().getNewGoodsList()) {
                            HomeBean.HomeListBean newGood = new HomeBean.HomeListBean();
                            newGood.currentType = HomeBean.HomeListBean.TYPE_NEWGOOD;
                            newGood.data = item;
                            list.add(newGood);
                        }
                        //分割线
                        HomeBean.HomeListBean lineHot = new HomeBean.HomeListBean();
                        lineHot.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
                        list.add(lineHot);
                        //hottitle
                        HomeBean.HomeListBean hotTitle = new HomeBean.HomeListBean();
                        hotTitle.currentType = HomeBean.HomeListBean.TYPE_TITLE;
                        hotTitle.title = "人气推荐";
                        list.add(hotTitle);
                        //hot
                        for(HomeBean.DataBean.HotGoodsListBean item:bean.getData().getHotGoodsList()) {
                            HomeBean.HomeListBean hot = new HomeBean.HomeListBean();
                            hot.currentType = HomeBean.HomeListBean.TYPE_HOTGOOD;
                            hot.data = item;
                            list.add(hot);
                        }
                        //分割线
                        HomeBean.HomeListBean lineTopic = new HomeBean.HomeListBean();
                        lineTopic.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
                        list.add(lineTopic);
                        //topictitle
                        HomeBean.HomeListBean topicTitle = new HomeBean.HomeListBean();
                        topicTitle.currentType = HomeBean.HomeListBean.TYPE_TITLE;
                        topicTitle.title = "专题精选";
                        list.add(topicTitle);
                        //topic
                        HomeBean.HomeListBean topic = new HomeBean.HomeListBean();
                        topic.currentType = HomeBean.HomeListBean.TYPE_TOPIC;
                        topic.data = bean.getData().getTopicList();
                        list.add(topic);
                        //分割线
                        HomeBean.HomeListBean lineCategory = new HomeBean.HomeListBean();
                        lineCategory.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
                        list.add(lineCategory);
                        //category
                        for(HomeBean.DataBean.CategoryListBean item:bean.getData().getCategoryList()) {
                            HomeBean.HomeListBean category = new HomeBean.HomeListBean();
                            category.currentType = HomeBean.HomeListBean.TYPE_TITLE;
                            category.title = item.getName();
                            list.add(category);
                            for(HomeBean.DataBean.CategoryListBean.GoodsListBean data:item.getGoodsList()){
                                HomeBean.HomeListBean good = new HomeBean.HomeListBean();
                                good.currentType = HomeBean.HomeListBean.TYPE_CATEGORY;
                                good.data = data;
                                list.add(good);
                            }
                            //分割线
                            HomeBean.HomeListBean line = new HomeBean.HomeListBean();
                            line.currentType = HomeBean.HomeListBean.TYPE_VIEW_LINE;
                            list.add(line);
                        }
                        return list;
                    }
                })
                .subscribeWith(new ResponseSubscriber<List<HomeBean.HomeListBean>>(mView) {
                    @Override
                    public void onNext(List<HomeBean.HomeListBean> result) {
                        mView.getHomeDataReturn(result);
                    }
                }));
    }

    /**
     *
     */
    @Override
    public void getIndexData() {
        addSubscribe(
                HttpManager.getInstance().getMyServer().getIndex()
                        .compose(RxUtils.rxScheduler())
                        .map(new Function<Response, HomeBean>() {
                            @Override
                            public HomeBean apply(Response response) throws Exception {

                                return null;
                            }
                        })
                        .subscribeWith(new ResponseSubscriber<HomeBean>(mView) {
                            @Override
                            public void onNext(HomeBean homeBean) {
                                mView.getIndexDataReturn(homeBean);
                            }
                        })
        );
    }
}

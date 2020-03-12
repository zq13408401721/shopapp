package com.myshop.ui.home.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.myshop.R;
import com.myshop.common.GlideImageLoader;
import com.myshop.model.bean.HomeBean;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeBean.HomeListBean, BaseViewHolder> {

    public static final int TYPE_BANNER = 100;
    public static final int TYPE_BRANDGOOD = 200;
    public static final int TYPE_NEWGOOD = 300;
    public static final int TYPE_GRID_ITEM_TITLE = 400; //grid标题

    List<HomeBean.HomeListBean> list;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public HomeAdapter(List<HomeBean.HomeListBean> data) {
        super(data);
        this.list = data;

        addItemType(HomeBean.HomeListBean.TYPE_BANNER,R.layout.layout_item_banner);
        addItemType(HomeBean.HomeListBean.TYPE_CHANNEL,R.layout.layout_item_channel);
        addItemType(HomeBean.HomeListBean.TYPE_TITLE,R.layout.layout_item_title);
        addItemType(HomeBean.HomeListBean.TYPE_BRAND,R.layout.layout_item_brand);
        //addItemType(HomeBean.HomeListBean.TYPE_TITLE,R.layout.layout_item_title);
        addItemType(HomeBean.HomeListBean.TYPE_NEWGOOD,R.layout.layout_item_newgood);
        //addItemType(HomeBean.HomeListBean.TYPE_TITLE,R.layout.layout_item_title);
        addItemType(HomeBean.HomeListBean.TYPE_HOTGOOD,R.layout.layout_item_hotgood);
        //addItemType(HomeBean.HomeListBean.TYPE_TITLE,R.layout.layout_item_title);
        addItemType(HomeBean.HomeListBean.TYPE_TOPIC,R.layout.layout_topic_recy);
        addItemType(HomeBean.HomeListBean.TYPE_CATEGORY,R.layout.layout_item_category);
        addItemType(HomeBean.HomeListBean.TYPE_VIEW_LINE,R.layout.layout_view_line);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.HomeListBean item) {
        switch (item.getItemType()){
            case HomeBean.HomeListBean.TYPE_BANNER:
                //banner放入到条目中会出现显示不出来
                //initBanner(helper,item);
                break;
            case HomeBean.HomeListBean.TYPE_CHANNEL:
                initChannel(helper,item);
                break;
            case HomeBean.HomeListBean.TYPE_BRAND:
                initBrand(helper, (HomeBean.DataBean.BrandListBean) item.data);
                break;
            case HomeBean.HomeListBean.TYPE_TITLE:
                initTitle(helper,item);
                break;
            case HomeBean.HomeListBean.TYPE_NEWGOOD:
                initNewGood(helper, (HomeBean.DataBean.NewGoodsListBean) item.data);
                break;
            case HomeBean.HomeListBean.TYPE_HOTGOOD:
                initHotGood(helper, (HomeBean.DataBean.HotGoodsListBean) item.data);
                break;
            case HomeBean.HomeListBean.TYPE_TOPIC:
                initTopic(helper, (List<HomeBean.DataBean.TopicListBean>) item.data);
                break;
            case HomeBean.HomeListBean.TYPE_CATEGORY:
                initCategory(helper, (HomeBean.DataBean.CategoryListBean.GoodsListBean) item.data);
                break;
            case HomeBean.HomeListBean.TYPE_VIEW_LINE:

                break;
        }
    }

    //初始化banner
    private void initBanner(BaseViewHolder helper, HomeBean.HomeListBean data){
        Banner banner = helper.getView(R.id.banner);
        if(TextUtils.isEmpty(banner.tag)) {
            List<String> imgs = new ArrayList<>();
            for (HomeBean.DataBean.BannerBean item : (List<HomeBean.DataBean.BannerBean>) data.data) {
                imgs.add(item.getImage_url());
            }
            banner.tag = "true";
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(imgs);
            banner.start();
        }
    }

    //初始化channel
    private void initChannel(BaseViewHolder helper, HomeBean.HomeListBean data){

    }

    //初始化title
    private void initTitle(BaseViewHolder helper, HomeBean.HomeListBean data){
        helper.setText(R.id.txt_title,data.title);
    }

    //初始化品牌
    private void initBrand(BaseViewHolder helper, HomeBean.DataBean.BrandListBean data){
        Glide.with(mContext).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.img_brand));
        helper.setText(R.id.txt_name,data.getName());
        helper.setText(R.id.txt_price,"￥"+data.getFloor_price());
    }

    //初始化新商品数据
    private void initNewGood(BaseViewHolder helper, HomeBean.DataBean.NewGoodsListBean data){
        Glide.with(mContext).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.img_newGood));
        helper.setText(R.id.txt_name,data.getName());
        helper.setText(R.id.txt_price,"￥"+data.getRetail_price());
    }

    //初始化热门商品
    private void initHotGood(BaseViewHolder helper, HomeBean.DataBean.HotGoodsListBean data) {
        Glide.with(mContext).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.img_hot));
        helper.setText(R.id.txt_name,data.getName());
        helper.setText(R.id.txt_des,data.getGoods_brief());
        helper.setText(R.id.txt_price,"￥"+data.getRetail_price());
    }

    //初始化专题
    private void initTopic(BaseViewHolder helper, List<HomeBean.DataBean.TopicListBean> data) {
        RecyclerView recyclerView = helper.getView(R.id.recy_topic);
        if(recyclerView.getAdapter() == null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            TopicAdapter topicAdapter = new TopicAdapter(R.layout.layout_item_topic, data);
            topicAdapter.bindToRecyclerView(recyclerView);
        }
    }

    //初始化category
    private void initCategory(BaseViewHolder helper, HomeBean.DataBean.CategoryListBean.GoodsListBean data) {
        Glide.with(mContext).load(data.getList_pic_url()).into((ImageView) helper.getView(R.id.img_category));
        helper.setText(R.id.txt_name,data.getName());
        helper.setText(R.id.txt_price,"￥"+data.getRetail_price());
    }

    /*************************adapter继承MultiItemQuickAdapter************/

    /*public HomeAdapter(){
        super(null);
        finishInitialize();
    }*/

    /*@Override
    protected int getViewType(HomeBean.BaseData bean) {
        int type = bean.getType();
        if(type == 1){
            return TYPE_GRID_ITEM_TITLE;
        }else if(type == 2){
            return TYPE_BRANDGOOD;
        }else if(type == 3){
            return TYPE_GRID_ITEM_TITLE;
        }else if(type == 4){
            return TYPE_NEWGOOD;
        }
        return 0;
    }
*/
    /*@Override
    public void registerItemProvider() {

        mProviderDelegate.registerProvider(new GridItemTitleProvider());
        mProviderDelegate.registerProvider(new BrandGoodItemProvider());
        mProviderDelegate.registerProvider(new GridItemTitleProvider());
        mProviderDelegate.registerProvider(new NewGoodItemProvider());
    }*/
}

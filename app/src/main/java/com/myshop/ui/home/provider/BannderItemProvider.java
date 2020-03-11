package com.myshop.ui.home.provider;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.myshop.R;
import com.myshop.model.bean.HomeBean;
import com.myshop.ui.home.adapter.HomeAdapter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class BannderItemProvider extends BaseItemProvider {
    @Override
    public int viewType() {
        return HomeAdapter.TYPE_BANNER;
    }

    @Override
    public int layout() {
        return R.layout.layout_item_banner;
    }

    @Override
    public void convert(BaseViewHolder helper, Object data, int position) {
        List imgs = new ArrayList();
        List<HomeBean.DataBean.BannerBean> banners = (List<HomeBean.DataBean.BannerBean>) data;
        for(HomeBean.DataBean.BannerBean item:banners){
            imgs.add(item.getImage_url());
        }
        Banner banner = helper.getView(R.id.banner);
        banner.setImages(imgs);
    }
}

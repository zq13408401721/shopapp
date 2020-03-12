package com.myshop.ui.home.provider;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.myshop.R;
import com.myshop.model.bean.HomeBean;
import com.myshop.ui.home.adapter.HomeAdapter;

public class GridItemTitleProvider extends BaseItemProvider<HomeBean.BaseData,BaseViewHolder> {
    @Override
    public int viewType() {
        return HomeAdapter.TYPE_GRID_ITEM_TITLE;
    }

    @Override
    public int layout() {
        return R.layout.layout_item_title;
    }

    @Override
    public void convert(BaseViewHolder helper, HomeBean.BaseData data, int position) {
        helper.setText(R.id.txt_title,data.currentType);
    }
}

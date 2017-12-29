package mst.com.baserecyclerviewadapterhelper.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.bean.HomeItem;

/**
 * Created by IT小蔡 on 2017-12-23.
 */

public class MainAdapter extends BaseQuickAdapter<HomeItem,BaseViewHolder>{

    public MainAdapter(@LayoutRes int layoutResId, @Nullable List<HomeItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {

        helper.setText(R.id.text,item.getTitle());
    }
}

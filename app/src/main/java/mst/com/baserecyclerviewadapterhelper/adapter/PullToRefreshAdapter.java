package mst.com.baserecyclerviewadapterhelper.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;

/**
 * Created by IT小蔡 on 2017-12-23.
 */

public class PullToRefreshAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public PullToRefreshAdapter(@Nullable List<String> data) {
        super(R.layout.item_img_text_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv,item);
    }
}

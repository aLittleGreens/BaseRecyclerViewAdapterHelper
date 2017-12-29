package mst.com.baserecyclerviewadapterhelper.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;

/**
 * Created by IT小蔡 on 2017-12-27.
 */

public class QuickAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public QuickAdapter(List<String> data) {
        super(R.layout.item_img_text_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}

package mst.com.baserecyclerviewadapterhelper.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;

/**
 * Created by IT小蔡 on 2017-12-23.
 */

public class HeaderAndFooterAdapter extends BaseQuickAdapter<String,BaseViewHolder>{

    public HeaderAndFooterAdapter(@Nullable List<String> data) {
        super(R.layout.item_header_and_footer, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}

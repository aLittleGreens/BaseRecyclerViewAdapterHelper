package mst.com.baserecyclerviewadapterhelper.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.bean.MultipleItem;

/**
 * Created by IT小蔡 on 2017-12-23.
 */

public class MultipleAdapter extends BaseMultiItemQuickAdapter<MultipleItem,BaseViewHolder> {


    public MultipleAdapter(@Nullable List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.TEXT, R.layout.item_text_view);
        addItemType(MultipleItem.IMG, R.layout.item_image_view);
        addItemType(MultipleItem.IMG_TEXT, R.layout.item_img_text_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {

        switch (helper.getItemViewType()){
            case MultipleItem.TEXT:
                helper.setText(R.id.tv,item.getContent());
                break;

            case MultipleItem.IMG:
                helper.setImageResource(R.id.iv,item.getResId());
                break;

            case MultipleItem.IMG_TEXT:

                helper.setText(R.id.tv,item.getContent());
                helper.setImageResource(R.id.iv,item.getResId());
                break;
        }

    }
}

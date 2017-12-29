package mst.com.baserecyclerviewadapterhelper.adapter;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.bean.AppBean;
import mst.com.baserecyclerviewadapterhelper.bean.TopSectionEntity;

/**
 * Created by IT小蔡 on 2017-12-25.
 */

public class SectionAdapter extends BaseSectionQuickAdapter<TopSectionEntity, BaseViewHolder> {

    private Context context;

    public SectionAdapter(List<TopSectionEntity> data) {
        super(R.layout.applistitem_normal, R.layout.applistitem_titlecard, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, TopSectionEntity item) {
        helper.setText(R.id.ItemTitle, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
    }

    @Override
    protected void convert(BaseViewHolder helper, TopSectionEntity item) {
        AppBean appBean = item.t;
        helper.setText(R.id.appSerial, appBean.getAliasName());
        Glide.with(mContext).load(appBean.getIcon()).into((ImageView) helper.getView(R.id.appicon));
        helper.setText(R.id.ItemTitle, appBean.getName());
        helper.setText(R.id.ItemText_star, appBean.getSizeDesc());
        helper.setText(R.id.memo, appBean.getMemo());
    }
}

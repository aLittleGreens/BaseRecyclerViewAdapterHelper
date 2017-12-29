package mst.com.baserecyclerviewadapterhelper.adapter;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oushangfeng.pinnedsectionitemdecoration.utils.FullSpanUtil;

import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.bean.AppBean;
import mst.com.baserecyclerviewadapterhelper.bean.TopSectionEntity;

/**
 * Created by IT小蔡 on 2017-12-29.
 */

public class StickAdapter extends BaseMultiItemQuickAdapter<TopSectionEntity, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public StickAdapter(List<TopSectionEntity> data) {
        super(data);

        addItemType(TopSectionEntity.TYPE_HEADER,R.layout.applistitem_titlecard);
        addItemType(TopSectionEntity.TYPE_DATA,R.layout.applistitem_normal);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, TopSectionEntity.TYPE_HEADER);
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        FullSpanUtil.onViewAttachedToWindow(holder, this, TopSectionEntity.TYPE_HEADER);
    }


    @Override
    protected void convert(BaseViewHolder helper, TopSectionEntity item) {
            switch (helper.getItemViewType()){

                case TopSectionEntity.TYPE_HEADER:
                    helper.setText(R.id.ItemTitle, item.header);
                    helper.setVisible(R.id.more, item.isMore());
                    helper.addOnClickListener(R.id.more);
                    break;

                case TopSectionEntity.TYPE_DATA:
                    AppBean appBean = item.t;
                    helper.setText(R.id.appSerial, appBean.getAliasName());
                    Glide.with(mContext).load(appBean.getIcon()).into((ImageView) helper.getView(R.id.appicon));
                    helper.setText(R.id.ItemTitle, appBean.getName());
                    helper.setText(R.id.ItemText_star, appBean.getSizeDesc());
                    helper.setText(R.id.memo, appBean.getMemo());
                    break;

            }
    }
}

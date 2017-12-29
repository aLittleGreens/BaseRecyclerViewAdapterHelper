package mst.com.baserecyclerviewadapterhelper.loadmore;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

import mst.com.baserecyclerviewadapterhelper.R;

/**
 * Created by IT小蔡 on 2017-12-25.
 */

public class CustomLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}

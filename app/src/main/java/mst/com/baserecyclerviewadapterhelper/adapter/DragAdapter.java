package mst.com.baserecyclerviewadapterhelper.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.bean.Book;

/**
 * Created by IT小蔡 on 2017-12-29.
 */

public class DragAdapter extends BaseItemDraggableAdapter<Book,BaseViewHolder> {

    public DragAdapter(@LayoutRes int layoutResId, @Nullable List<Book> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Book item) {
        helper.setText(R.id.tv,item.getName());
    }
}

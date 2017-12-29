package mst.com.baserecyclerviewadapterhelper.adapter;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.bean.Book;

/**
 * Created by IT小蔡 on 2017-12-28.
 */

public class SwipeAdapter extends BaseItemDraggableAdapter<Book, BaseViewHolder> {


    public SwipeAdapter(int layoutResId, List<Book> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Book item) {

        helper.addOnClickListener(R.id.delete)
                .addOnClickListener(R.id.collect)
                .addOnClickListener(R.id.content)
                .addOnClickListener(R.id.share);

        helper.setText(R.id.nameText, item.getName());

        if (item.isCollect()) {
            helper.setText(R.id.collect, "以收藏");
        } else {
            helper.setText(R.id.collect, "收藏");
        }
    }
}

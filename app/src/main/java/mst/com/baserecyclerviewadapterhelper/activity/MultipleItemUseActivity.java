package mst.com.baserecyclerviewadapterhelper.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.adapter.MultipleAdapter;
import mst.com.baserecyclerviewadapterhelper.base.BaseActivity;
import mst.com.baserecyclerviewadapterhelper.bean.MultipleItem;
import mst.com.baserecyclerviewadapterhelper.util.ToastUtils;

public class MultipleItemUseActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private List<MultipleItem> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_item_use);
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        initAdapter();
    }

    private void initAdapter() {
        MultipleAdapter mulitiAdapter = new MultipleAdapter(listItems);
        View topView = getLayoutInflater().inflate(R.layout.top_view,(ViewGroup) recyclerView.getParent(),false);
        topView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showLongToast("header");
            }
        });
        mulitiAdapter.addHeaderView(topView);
        mulitiAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return listItems.get(position).getSpanSize();
            }
        });
        recyclerView.setAdapter(mulitiAdapter);

        mulitiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showLongToast(position+"");
            }
        });
    }

    @Override
    protected void initData() {
        listItems = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            listItems.add(new MultipleItem(MultipleItem.IMG, MultipleItem.IMG_SPAN_SIZE,R.mipmap.ic_launcher));
            listItems.add(new MultipleItem(MultipleItem.TEXT,MultipleItem.TEXT_SPAN_SIZE,getString(R.string.app_name)));
            listItems.add(new MultipleItem(MultipleItem.IMG_TEXT,MultipleItem.IMG_TEXT_SPAN_SIZE,getString(R.string.app_name),R.mipmap.top_background));
            listItems.add(new MultipleItem(MultipleItem.IMG_TEXT,MultipleItem.IMG_TEXT_SPAN_SIZE_MIN,getString(R.string.app_name),R.mipmap.top_background));
            listItems.add(new MultipleItem(MultipleItem.IMG_TEXT,MultipleItem.IMG_TEXT_SPAN_SIZE_MIN,getString(R.string.app_name),R.mipmap.top_background));
        }
    }
}

package mst.com.baserecyclerviewadapterhelper.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.adapter.MainAdapter;
import mst.com.baserecyclerviewadapterhelper.base.BaseActivity;
import mst.com.baserecyclerviewadapterhelper.bean.HomeItem;

public class MainActivity extends BaseActivity {

    private static final Class<?>[] ACTIVITY = {MultipleItemUseActivity.class, HeaderAndFooterUseActivity.class, PullToRefreshUseActivity.class, SectionUseActivity.class, StickSectionActivity.class,EmptyViewUseActivity.class,ItemSwipeUseActivity.class,ItemDragActivity.class, ExpandableUseActivity.class,};
    private static final String[] TITLE ={"多类型item","添加头部尾部","PullToRefresh","Section","悬浮标题","异常占位图","侧滑","拖拽","多级展开菜单"};
    private RecyclerView recyclerView;
    private List<HomeItem> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        initAdapter();
    }

    private void initAdapter() {
        MainAdapter mainAdapter = new MainAdapter(R.layout.main_list_adapter,mDataList);
        View topView = getLayoutInflater().inflate(R.layout.top_view,(ViewGroup) recyclerView.getParent(),false);
        mainAdapter.addHeaderView(topView);
        mainAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MainActivity.this, ACTIVITY[position]);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    protected void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem(ACTIVITY[i],TITLE[i]);
            mDataList.add(item);
        }
    }

}

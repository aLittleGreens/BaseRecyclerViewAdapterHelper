package mst.com.baserecyclerviewadapterhelper.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.adapter.PullToRefreshAdapter;
import mst.com.baserecyclerviewadapterhelper.base.BaseActivity;
import mst.com.baserecyclerviewadapterhelper.loadmore.CustomLoadMoreView;
import mst.com.baserecyclerviewadapterhelper.util.ToastUtils;

public class PullToRefreshUseActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {

    private List<String> mDataList;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    private static final int TOTAL_COUNTER = 10;
    private int mCurrentCounter;
    private PullToRefreshAdapter pullToRefreshAdapter;
    private boolean error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_use);
    }

    @Override
    protected void initView() {
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        initListener();
    }

    private void initAdapter() {
        pullToRefreshAdapter = new PullToRefreshAdapter(mDataList);
        pullToRefreshAdapter.setOnLoadMoreListener(this, recyclerView);
        pullToRefreshAdapter.setLoadMoreView(new CustomLoadMoreView());
        mCurrentCounter = pullToRefreshAdapter.getData().size();
        recyclerView.setAdapter(pullToRefreshAdapter);

    }

    private void initListener() {

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefreshAdapter.setEnableLoadMore(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                        pullToRefreshAdapter.notifyDataSetChanged();
                        mCurrentCounter = pullToRefreshAdapter.getData().size();
                        refreshLayout.setRefreshing(false);
                        error = false;
                        pullToRefreshAdapter.setEnableLoadMore(true);
                    }
                }, 1500);
            }
        });


        pullToRefreshAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShortToast("" + position);
            }
        });
    }

    @Override
    protected void initData() {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        mDataList.clear();
        for (int i = 0; i < 8; i++) {
            mDataList.add("" + i);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        refreshLayout.setRefreshing(false);
        if (!error) {
            error = true;
            pullToRefreshAdapter.loadMoreFail();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mCurrentCounter < TOTAL_COUNTER) {
                        pullToRefreshAdapter.addData("more" + mCurrentCounter);
                        mCurrentCounter = pullToRefreshAdapter.getData().size();
                        pullToRefreshAdapter.loadMoreComplete();
                    } else {
                        pullToRefreshAdapter.loadMoreEnd();
                    }
                }
            }, 1000);

        }
        refreshLayout.setRefreshing(false);
    }
}

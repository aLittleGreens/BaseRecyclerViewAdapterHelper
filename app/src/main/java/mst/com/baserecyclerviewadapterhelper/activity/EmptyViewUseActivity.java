package mst.com.baserecyclerviewadapterhelper.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.adapter.QuickAdapter;
import mst.com.baserecyclerviewadapterhelper.base.BaseActivity;

public class EmptyViewUseActivity extends BaseActivity implements View.OnClickListener {


    private RecyclerView recyclerView;
    private View notDataView;
    private View errorView;

    private boolean mError = true;
    private boolean mNoData = true;
    private List<String> mDataList;
    private QuickAdapter quickAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_view_use);
    }

    @Override
    protected void initView() {
        findViewById(R.id.btn_reset).setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) recyclerView.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });

        initAdapter();
        onRefresh();
    }

    private void onRefresh() {
        quickAdapter.setEmptyView(R.layout.loading_view, (ViewGroup) recyclerView.getParent());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mError){
                    quickAdapter.setEmptyView(errorView);
                    mError = false;
                }else if(mNoData){
                    quickAdapter.setEmptyView(notDataView);
                    mNoData = false;
                }else{
                    initData();
                    quickAdapter.setNewData(mDataList);
                }
            }
        },1500);

    }

    private void initAdapter() {
        quickAdapter = new QuickAdapter(null);
        recyclerView.setAdapter(quickAdapter);
    }

    @Override
    protected void initData() {
        if(mDataList == null){
            mDataList = new ArrayList<>();
        }
        mDataList.clear();
        for (int i = 0; i < 5; i++) {
            mDataList.add("" + i);
        }
    }

    @Override
    public void onClick(View v) {
         mError = true;
         mNoData = true;

        quickAdapter.setNewData(null);
        onRefresh();
    }
}

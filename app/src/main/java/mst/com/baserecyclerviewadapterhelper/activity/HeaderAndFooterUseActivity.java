package mst.com.baserecyclerviewadapterhelper.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.adapter.HeaderAndFooterAdapter;
import mst.com.baserecyclerviewadapterhelper.base.BaseActivity;

public class HeaderAndFooterUseActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<String> mDataList;
    private HeaderAndFooterAdapter headerAndFooterAdapter;

    private int headerCount = 0;

    private int footerCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_and_footer_use);
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
    }

    private void initAdapter() {
        headerAndFooterAdapter = new HeaderAndFooterAdapter(mDataList);

        headerAndFooterAdapter.addHeaderView(getHeaderView(0, 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerCount++;
                headerAndFooterAdapter.addHeaderView(getHeaderView(1,headerCount, getRemoveHeaderListener()), 0);
            }
        }));

        headerAndFooterAdapter.addFooterView(getFooterView(0, 0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                footerCount++;
                headerAndFooterAdapter.addFooterView(getFooterView(1,footerCount,getRemoveFooterListener()),0);
            }
        }));

        recyclerView.setAdapter(headerAndFooterAdapter);

        headerAndFooterAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(HeaderAndFooterUseActivity.this, "" + Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mDataList.add("" + i);
        }
    }


    private View getHeaderView(int type, int index, View.OnClickListener listener) {

        View view = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) recyclerView.getParent(), false);
        if (type == 1) {
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
            TextView textView = (TextView) view.findViewById(R.id.tv);
            textView.setText("Header:" + index);
            imageView.setImageResource(R.mipmap.rm_icon);
        }
        view.setOnClickListener(listener);
        return view;
    }

    private View.OnClickListener getRemoveHeaderListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerCount--;
                headerAndFooterAdapter.removeHeaderView(v);
            }
        };
    }


    private View getFooterView(int type, int index, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.footer_view, (ViewGroup) recyclerView.getParent(), false);
        if (type == 1) {
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
            TextView textView = (TextView) view.findViewById(R.id.tv);
            textView.setText("Footer:" + index);
            imageView.setImageResource(R.mipmap.rm_icon);
        }
        view.setOnClickListener(listener);
        return view;
    }

    private View.OnClickListener getRemoveFooterListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                footerCount--;
                headerAndFooterAdapter.removeFooterView(v);
            }
        };
    }

}

package mst.com.baserecyclerviewadapterhelper.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.adapter.ExpandableAdapter;
import mst.com.baserecyclerviewadapterhelper.base.BaseActivity;
import mst.com.baserecyclerviewadapterhelper.bean.Level0Item;
import mst.com.baserecyclerviewadapterhelper.bean.Level1Item;
import mst.com.baserecyclerviewadapterhelper.bean.Person;

public class ExpandableUseActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private List<MultiItemEntity> mDatas;
    private ExpandableAdapter expandableAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_use);
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        gridLayoutManager = new GridLayoutManager(this, 3);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return expandableAdapter.getItemViewType(position) == ExpandableAdapter.TYPE_PERSON ? 1 : gridLayoutManager.getSpanCount();
            }
        });

        initAdapter();
    }

    private void initAdapter() {

        expandableAdapter = new ExpandableAdapter(mDatas);
        recyclerView.setAdapter(expandableAdapter);
        // important! setLayoutManager should be called after setAdapter
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void initData() {

        int lv0Count = 9;
        int lv1Count = 3;
        int personCount = 5;

        String[] nameList = {"Bob", "Andy", "Lily", "Brown", "Bruce"};
        Random random = new Random();

        mDatas = new ArrayList<>();
        for (int i = 0; i < lv0Count; i++) {
            Level0Item lv0 = new Level0Item("我的朋友" + i , "subtitle of " + i);
            for (int j = 0; j < lv1Count; j++) {
                Level1Item lv1 = new Level1Item("Level 1 item: " + j, "(no animation)");
                for (int k = 0; k < personCount; k++) {
                    lv1.addSubItem(new Person(nameList[k], random.nextInt(40)));
                }
                lv0.addSubItem(lv1);
            }
            mDatas.add(lv0);
        }
    }
}

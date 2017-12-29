package mst.com.baserecyclerviewadapterhelper.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration;
import com.oushangfeng.pinnedsectionitemdecoration.callback.OnHeaderClickAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.adapter.StickAdapter;
import mst.com.baserecyclerviewadapterhelper.base.BaseActivity;
import mst.com.baserecyclerviewadapterhelper.bean.TopSectionEntity;
import mst.com.baserecyclerviewadapterhelper.util.JsonParseUtils;
import mst.com.baserecyclerviewadapterhelper.util.ToastUtils;

public class StickSectionActivity extends BaseActivity {

    RecyclerView recyclerView;
    private List<TopSectionEntity> appBeanMap;

    private PinnedHeaderItemDecoration mHeaderItemDecoration;
    private StickAdapter stickAdapter;
    private GridLayoutManager gridLayoutManager;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stick_section);
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(gridLayoutManager);
        initAdapter();
    }

    private void initAdapter() {
        stickAdapter = new StickAdapter(appBeanMap);
        stickAdapter.onAttachedToRecyclerView(recyclerView);
        OnHeaderClickAdapter clickAdapter = new OnHeaderClickAdapter() {

            @Override
            public void onHeaderClick(View view, int id, int position) {
                switch (id) {
                    case R.id.fl:
                        // case OnItemTouchListener.HEADER_ID:
                        ToastUtils.showShortToast("click, tag: " + stickAdapter.getData().get(position).header);
                        break;
                    case R.id.more:
                        ToastUtils.showShortToast("click, tag: " + stickAdapter.getData().get(position).header + ": more");
                        break;
                }
            }

        };

        mHeaderItemDecoration = new PinnedHeaderItemDecoration.Builder(TopSectionEntity.TYPE_HEADER).
                setDividerId(R.drawable.divider).
                enableDivider(false) //开关分割线
                .setClickIds(R.id.more, R.id.fl).
                        disableHeaderClick(false).
                        setHeaderClickListener(clickAdapter)
                .create();
        recyclerView.addItemDecoration(mHeaderItemDecoration);

        recyclerView.setAdapter(stickAdapter);
        View topView = LayoutInflater.from(StickSectionActivity.this).inflate(R.layout.head_stick_switch_layout, recyclerView, false);
        Switch switchBtn = (Switch) topView.findViewById(R.id.switchBtn);
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    recyclerView.setLayoutManager(linearLayoutManager);
                    stickAdapter.notifyDataSetChanged();
                } else {
                    recyclerView.setLayoutManager(gridLayoutManager);
                    stickAdapter.onAttachedToRecyclerView(recyclerView);
                    stickAdapter.notifyDataSetChanged();
                }
            }
        });
        stickAdapter.addHeaderView(topView);
        // 因为添加了1个头部，他是不在clickAdapter.getData这个数据里面的，所以这里要设置数据的偏移值告知ItemDecoration真正的数据索引
        mHeaderItemDecoration.setDataPositionOffset(stickAdapter.getHeaderLayoutCount());

        stickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TopSectionEntity topSectionEntity = appBeanMap.get(position);
                if (topSectionEntity.isHeader)
                    Toast.makeText(StickSectionActivity.this, topSectionEntity.header + position, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(StickSectionActivity.this, topSectionEntity.t.getName() + position, Toast.LENGTH_LONG).show();
            }
        });

        stickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(StickSectionActivity.this, "onItemChildClick" + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void initData() {
        appBeanMap = JsonParseUtils.getAppBeanMap(getStrFromAssets("top_section.json"));
    }

    /**
     * @return Json数据（String）
     * @description 通过assets文件获取json数据，这里写的十分简单，没做循环判断。
     */
    private String getStrFromAssets(String name) {
        AssetManager assetManager = getAssets();
        try {
            InputStream is = assetManager.open(name);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

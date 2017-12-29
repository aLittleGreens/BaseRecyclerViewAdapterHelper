package mst.com.baserecyclerviewadapterhelper.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.adapter.SectionAdapter;
import mst.com.baserecyclerviewadapterhelper.base.BaseActivity;
import mst.com.baserecyclerviewadapterhelper.bean.TopSectionEntity;
import mst.com.baserecyclerviewadapterhelper.util.JsonParseUtils;

public class SectionUseActivity extends BaseActivity {

    RecyclerView recyclerView;
    private List<TopSectionEntity> appBeanMap;
    private SectionAdapter sectionAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_use);
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
/*        gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return sectionAdapter.getData().get(position).isHeader ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);*/
        initAdapter();
    }

    private void initAdapter() {
        sectionAdapter = new SectionAdapter(appBeanMap);

        sectionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TopSectionEntity topSectionEntity = appBeanMap.get(position);
                if (topSectionEntity.isHeader)
                    Toast.makeText(SectionUseActivity.this, topSectionEntity.header + position, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SectionUseActivity.this, topSectionEntity.t.getName() + position, Toast.LENGTH_LONG).show();
            }
        });

        sectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(SectionUseActivity.this, "onItemChildClick" + position, Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.setAdapter(sectionAdapter);
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

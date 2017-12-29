package mst.com.baserecyclerviewadapterhelper.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.guanaj.easyswipemenulibrary.EasySwipeMenuLayout;

import java.util.ArrayList;
import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.adapter.SwipeAdapter;
import mst.com.baserecyclerviewadapterhelper.base.BaseActivity;
import mst.com.baserecyclerviewadapterhelper.bean.Book;
import mst.com.baserecyclerviewadapterhelper.util.ToastUtils;

public class ItemSwipeUseActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private String [] bookNames = {"java","php","pyson","三国","水浒传","西游记","红楼梦","甄嬛传","数据结构","设计模式","源码分析"};
    private List<Book> mDatas;
    private SwipeAdapter dragAndSwipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_drag_and_swipe_use);
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
    }

    private void initAdapter() {
        dragAndSwipeAdapter = new SwipeAdapter(R.layout.dragandswipt_layout,mDatas);

        dragAndSwipeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShortToast("onItemClick:"+position);
            }
        });

        dragAndSwipeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {


                switch (view.getId()){

                    case R.id.share:
                        ToastUtils.showShortToast("分享:"+mDatas.get(position).getName());
                        EasySwipeMenuLayout swipeMenuLayout1 = EasySwipeMenuLayout.getViewCache();
                        if(swipeMenuLayout1 != null && swipeMenuLayout1.resetStatus()){
                        }
                        break;

                    case R.id.delete:

                        mDatas.remove(position);
                        dragAndSwipeAdapter.notifyItemRemoved(position);

                        break;

                    case R.id.collect:
                        EasySwipeMenuLayout easySwipeMenuLayout = EasySwipeMenuLayout.getViewCache();
                        if(easySwipeMenuLayout != null && easySwipeMenuLayout.resetStatus()){
                        }
                        mDatas.get(position).setCollect(!mDatas.get(position).isCollect());
                        dragAndSwipeAdapter.notifyItemChanged(position);
                        break;

                    case R.id.content:
                        EasySwipeMenuLayout swipeMenuLayout = (EasySwipeMenuLayout) view.getParent();
                        if(swipeMenuLayout.resetStatus()){
                            break;
                        }
                        ToastUtils.showShortToast("onItemChildClick:"+mDatas.get(position).getName());
                        break;
                }
            }
        });


        recyclerView.addItemDecoration(new DividerItemDecoration(this,RecyclerView.VERTICAL));
        recyclerView.setAdapter(dragAndSwipeAdapter);
    }

    @Override
    protected void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < bookNames.length; i++) {
            mDatas.add(new Book(bookNames[i],false));
        }
    }
}

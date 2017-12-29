package mst.com.baserecyclerviewadapterhelper.activity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;

import java.util.ArrayList;
import java.util.List;

import mst.com.baserecyclerviewadapterhelper.R;
import mst.com.baserecyclerviewadapterhelper.adapter.DragAdapter;
import mst.com.baserecyclerviewadapterhelper.base.BaseActivity;
import mst.com.baserecyclerviewadapterhelper.bean.Book;
import mst.com.baserecyclerviewadapterhelper.util.ToastUtils;

public class ItemDragActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private ItemTouchHelper mItemTouchHelper;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;

    private String [] bookNames = {"java","php","pyson","三国","水浒传","西游记","红楼梦","甄嬛传","数据结构","设计模式","源码分析"};
    private List<Book> mDatas;
    private DragAdapter dragAndSwipeAdapter;


    private Paint mPaint;
    private Rect bounds;
    final String testString = "滑动删除";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_drag);
    }

    @Override
    protected void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();

        mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setTextSize(40);
        mPaint.setColor(Color.RED);
        bounds = new Rect();
        mPaint.getTextBounds(testString, 0, testString.length(), bounds);
    }

    private void initAdapter() {
        dragAndSwipeAdapter = new DragAdapter(R.layout.item_expandable_lv2,mDatas);
        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(dragAndSwipeAdapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);

        dragAndSwipeAdapter.enableDragItem(mItemTouchHelper);// 开启拖拽
        dragAndSwipeAdapter.setOnItemDragListener(listener);

        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);
        dragAndSwipeAdapter.enableSwipeItem();// 开启滑动删除
        dragAndSwipeAdapter.setOnItemSwipeListener(onItemSwipeListener);


        recyclerView.setAdapter(dragAndSwipeAdapter);

        dragAndSwipeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showShortToast(mDatas.get(position).getName());
            }
        });
    }

    @Override
    protected void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < bookNames.length; i++) {
            mDatas.add(new Book(bookNames[i],false));
        }
    }


    OnItemDragListener listener = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.d(TAG, "drag start");
            BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.WHITE);
        }

        @Override
        public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
            Log.d(TAG, "move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());
        }

        @Override
        public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.d(TAG, "drag end"+mDatas);
            BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.BLACK);
        }
    };


    OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
        @Override
        public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.d(TAG, "view swiped start: " + pos);
            BaseViewHolder holder = ((BaseViewHolder) viewHolder);
            holder.setTextColor(R.id.tv, Color.WHITE);
        }

        @Override
        public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.d(TAG, "View reset: " + pos);
            BaseViewHolder holder = ((BaseViewHolder) viewHolder);
            holder.setTextColor(R.id.tv, Color.BLACK);
            if(pos == -1){
                Log.e(TAG,"data:"+mDatas);
            }
        }

        @Override
        public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
            Log.d(TAG, "View Swiped: " + pos);


        }

        @Override
        public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
//                Log.e(TAG,viewHolder.itemView.getMeasuredWidth()+"onItemSwipeMoving: DX:"+dX);
            canvas.drawColor(ContextCompat.getColor(ItemDragActivity.this, R.color.color_light_blue));
            canvas.drawText(testString, 0, viewHolder.itemView.getMeasuredHeight()/2 + bounds.height()/2, mPaint);
//                canvas.drawText("Just some text", 0, 40, mPaint);
        }
    };
}

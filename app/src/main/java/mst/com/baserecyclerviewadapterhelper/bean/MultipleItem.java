package mst.com.baserecyclerviewadapterhelper.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by IT小蔡 on 2017-12-23.
 */

public class MultipleItem implements MultiItemEntity {

    public static final int TEXT = 1;
    public static final int IMG = 2;
    public static final int IMG_TEXT = 3;
    public static final int TEXT_SPAN_SIZE = 3;
    public static final int IMG_SPAN_SIZE = 1;
    public static final int IMG_TEXT_SPAN_SIZE = 4;
    public static final int IMG_TEXT_SPAN_SIZE_MIN = 2;

    private int itemType;
    private int spanSize;
    private String content;
    private int resId;

    public MultipleItem(int itemType, int spanSize, String content, int resId) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.content = content;
        this.resId = resId;
    }

    public MultipleItem(int itemType, int spanSize, String content) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.content = content;
    }

    public MultipleItem(int itemType, int spanSize, int resId) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.resId = resId;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public int getSpanSize(){
        return spanSize;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}

package mst.com.baserecyclerviewadapterhelper.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by IT小蔡 on 2017-12-25.
 */

public class TopSectionEntity extends SectionEntity<AppBean> implements MultiItemEntity {

    private boolean isMore;

    public static final int TYPE_HEADER = 1;
    public static final int TYPE_DATA = 2;

    public TopSectionEntity(boolean isHeader, String header, boolean isMore) {
        super(isHeader, header);
        this.isMore = isMore;
    }

    public TopSectionEntity(AppBean appBean) {
        super(appBean);
    }

    public boolean isMore() {
        return isMore;
    }

    /**
     * 作为悬浮标题 才 implements MultiItemEntity，否则，不用复用
     * @return
     */
    @Override
    public int getItemType() {
        return isHeader ? TYPE_HEADER : TYPE_DATA;
    }
}

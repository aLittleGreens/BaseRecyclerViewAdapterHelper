package mst.com.baserecyclerviewadapterhelper.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import mst.com.baserecyclerviewadapterhelper.adapter.ExpandableAdapter;

/**
 * Created by IT小蔡 on 2017-12-28.
 */

public class Level0Item extends AbstractExpandableItem<Level1Item> implements MultiItemEntity {

    public String title;
    public String subTitle;

    public Level0Item(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    @Override
    public int getItemType() {
        return ExpandableAdapter.TYPE_LEVEL_0;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}

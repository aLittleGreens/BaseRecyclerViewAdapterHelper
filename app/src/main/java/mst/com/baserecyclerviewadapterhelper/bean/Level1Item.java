package mst.com.baserecyclerviewadapterhelper.bean;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import mst.com.baserecyclerviewadapterhelper.adapter.ExpandableAdapter;

/**
 * Created by IT小蔡 on 2017-12-28.
 */

public class Level1Item extends AbstractExpandableItem<Person> implements MultiItemEntity {
    public String title;
    public String subTitle;

    public Level1Item(String title, String subTitle) {
        this.subTitle = subTitle;
        this.title = title;
    }

    @Override
    public int getItemType() {
        return ExpandableAdapter.TYPE_LEVEL_1;
    }

    @Override
    public int getLevel() {
        return 1;
    }
}

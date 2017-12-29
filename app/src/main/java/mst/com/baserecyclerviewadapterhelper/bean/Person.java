package mst.com.baserecyclerviewadapterhelper.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import mst.com.baserecyclerviewadapterhelper.adapter.ExpandableAdapter;

/**
 * Created by luoxw on 2016/8/10.
 */

public class Person implements MultiItemEntity {
    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String name;
    public int age;

    @Override
    public int getItemType() {
        return ExpandableAdapter.TYPE_PERSON;
    }
}
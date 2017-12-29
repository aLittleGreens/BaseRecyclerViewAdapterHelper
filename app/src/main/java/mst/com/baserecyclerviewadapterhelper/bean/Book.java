package mst.com.baserecyclerviewadapterhelper.bean;

/**
 * Created by IT小蔡 on 2017-12-28.
 */

public class Book {

    private String name;

    private boolean isCollect;

    public Book() {
    }

    public Book(String name, boolean isCollect) {
        this.name = name;
        this.isCollect = isCollect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollect() {
        return isCollect;
    }

    public void setCollect(boolean collect) {
        isCollect = collect;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", isCollect=" + isCollect +
                '}';
    }
}

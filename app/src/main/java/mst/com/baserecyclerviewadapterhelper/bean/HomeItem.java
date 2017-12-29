package mst.com.baserecyclerviewadapterhelper.bean;

/**
 * Created by IT小蔡 on 2017-12-23.
 */

public class HomeItem {

    private Class<?> activity;
    private String title;



    public HomeItem(Class<?> activity, String title) {
        this.activity = activity;
        this.title = title;
    }

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity(Class<?> activity) {
        this.activity = activity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package mst.com.baserecyclerviewadapterhelper.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mst.com.baserecyclerviewadapterhelper.bean.AppBean;
import mst.com.baserecyclerviewadapterhelper.bean.TopSectionEntity;

/**
 * Created by IT小蔡 on 2017-12-25.
 */

public class JsonParseUtils {

    /*得到分类栏目和子项的数据*/
    public static List<TopSectionEntity> getAppBeanMap(String sjson) {
        JSONObject json = null;
        try {
            json = new JSONObject(sjson);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        List<TopSectionEntity> appList = new ArrayList<>();
        try {
            JSONArray data = json.getJSONArray("layoutData");
            for (int i = 0; i < data.length(); i++) {
                if (i == 4 || i == 6 || i == 8 || i == 10 | i == 12) {
                    String header = data.getJSONObject(i - 1).getJSONArray("dataList").getJSONObject(0).getString("name");
                    appList.add(new TopSectionEntity(true,header,true));
                    JSONArray appArray = data.getJSONObject(i).getJSONArray("dataList");
                    for (int m = 0; m < appArray.length(); m++) {
                        JSONObject jsonObject = appArray.getJSONObject(m);
                        AppBean appBean = parseAppBean(jsonObject.toString());
                        appList.add(new TopSectionEntity(appBean));
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return appList;
    }

    public static AppBean parseAppBean(String json) {
        AppBean appBean = null;
        String appId = "";
        String appVersionName = "";
        String downCountDesc = "";
        String downurl = "";
        String icon = "";
        String intro = "";
        String memo = "";
        String name = "";
        String packageName = "";
        String sizeDesc = "";
        String stars = "";
        String aliasName = "";
        String detailId = "";

        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("appid")) appId = jsonObject.getString("appid");
            if (jsonObject.has("appVersionName"))
                appVersionName = jsonObject.getString("appVersionName");
            if (jsonObject.has("downCountDesc"))
                downCountDesc = jsonObject.getString("downCountDesc");
            if (jsonObject.has("downurl")) downurl = jsonObject.getString("downurl");
            if (jsonObject.has("icon")) icon = jsonObject.getString("icon");
            if (jsonObject.has("intro")) intro = jsonObject.getString("intro");
            if (jsonObject.has("memo")) memo = jsonObject.getString("memo");
            if (jsonObject.has("name")) name = jsonObject.getString("name");
            if (jsonObject.has("package")) packageName = jsonObject.getString("package");
            if (jsonObject.has("sizeDesc")) sizeDesc = jsonObject.getString("sizeDesc");
            if (jsonObject.has("stars")) stars = jsonObject.getString("stars");
            if (jsonObject.has("aliasName"))
                aliasName = jsonObject.getString("aliasName").substring(0, 1);
            if (jsonObject.has("detailId"))
                detailId = jsonObject.getString("detailId").substring(5);

            appBean = new AppBean(appId, appVersionName, downCountDesc, downurl, icon, intro, memo, name, packageName, sizeDesc, stars, aliasName, detailId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return appBean;
    }

}

package com.youzipi.topbar_demo;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

class DataUtil {

    public static List<HashMap<String, Object>> getListData(String data) {
        List<HashMap<String, Object>> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("index", jsonObject.getString("index"));
                    map.put("title", jsonObject.getString("title"));
                    map.put("id", jsonObject.getString("id"));
                    map.put("pub", jsonObject.getString("pub"));

                    map.put("link", jsonObject.getString("link"));

                    list.add(map);
                }
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println();
        return list;

    }


    public static HashMap<String, Object> getdetailData(String data) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject jsonObject = new JSONObject(data);

            map.put("dt", jsonObject.getJSONArray("dt"));
            map.put("dd", jsonObject.getJSONArray("dd"));
            map.put("flag", jsonObject.getJSONArray("flag"));
            map.put("address", jsonObject.getJSONArray("address"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }


    public static HashMap<String, String> buildHtml(String data) {
        HashMap<String,String> map = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray dt = jsonObject.getJSONArray("dt");
            JSONArray dd = jsonObject.getJSONArray("dd");
            String detail = "";
            for(int i=0;i<dt.length();i++){
                detail += "<p><span>" + dt.getString(i) + "</span><span>" + dd.getString(i) + "</span></p>";
            }

            String address_info = "";
            JSONArray flag = jsonObject.getJSONArray("flag");
            JSONArray address = jsonObject.getJSONArray("address");
            for(int i=0;i<address.length();i++){
//                address_info += "<p><span>馆藏信息:&nbsp&nbsp</span><span>" + address.getString(i) + "</span><span>" + flag.getString(i) + "</span></p>";
                address_info += "<p><span>" + address.getString(i) + "</span><span>" + flag.getString(i) + "</span></p>";
            }
            map.put("detail",detail);
            map.put("address_info",address_info);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        String json = "[{\"id\":\"TP393.09\\/154\",\"title\":\"Ruby基础教程\",\"index\":\"1\",\"pub\":\"人民邮电出版社2014\",\"link\":\"http:\\/\\/lib2.nuist.edu.cn\\/opac\\/item.php?marc_no=0000536779\"}";
        List list = DataUtil.getListData(json);
        String json2 = "";
        System.out.println(list);
        HashMap map = buildHtml(json2);
        System.out.println(map);
    }

}
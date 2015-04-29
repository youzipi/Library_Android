package com.youzipi.topbar_demo;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class DataUtil{

    public static List<HashMap<String,Object>> getListData(String data)
    {
        List<HashMap<String,Object>> list = new ArrayList<>();
        try
        {
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject;
            for (int i = 0; i < jsonArray.length(); i++)
            {
                jsonObject = jsonArray.getJSONObject(i);
                {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("index", jsonObject.getString("index"));
                    map.put("title", jsonObject.getString("title"));
                    map.put("id", jsonObject.getString("id"));
                    map.put("pub", jsonObject.getString("pub"));
                    list.add(map);
                }
            }

        } catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println();
        return list;

    }

    public static void main(String[] args) {
        String json = "[{\"id\":\"TP393.09\\/154\",\"title\":\"Ruby基础教程\",\"index\":\"1\",\"pub\":\"人民邮电出版社2014\",\"link\":\"http:\\/\\/lib2.nuist.edu.cn\\/opac\\/item.php?marc_no=0000536779\"}";
        List list= DataUtil.getListData(json);
        System.out.println(list);
    }

}
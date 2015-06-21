package com.youzipi.topbar_demo;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by youzipi on 2015/4/27.
 */
public class DetailAction extends Thread{
    private List<NameValuePair> params = new ArrayList<NameValuePair>();
    private String id;
    private Handler handler;

    public DetailAction(Handler handler, String id) {
        this.handler = handler;
        this.id = id;
    }
    private JSONObject data;
    public void upload(){
        Log.i("status", "upload");
        String uri = "http://library-58635.coding.io/api/v1/detail/"+id;
        HttpGet httpRequest = new HttpGet(uri);
        Log.i("id", id);
        try {
            Log.i("keyword", String.valueOf(httpRequest.getRequestLine()));
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                httpResponse.getEntity();
                HttpEntity entity=httpResponse.getEntity();
                InputStream is=entity.getContent();
                Log.i("status", "entity.getContent(): " + is);
                //下面是读取数据的过程
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line=null;
                StringBuilder sb=new StringBuilder();
                while((line=br.readLine())!=null){
                    sb.append(line);
                }
                String result = sb.toString();
                Log.i("status", "result: " + result);
                data = new JSONObject(result);
                Log.i("status", "data: " + data);

                Message msg  = new Message();
                msg.obj = result;
                handler.sendMessage(msg);
            } else {
                Log.i("status", "Error Response" + httpResponse.getStatusLine().toString());
            }
        } catch (UnsupportedEncodingException e) {
            Log.i("status", "result: " + e.getMessage());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        upload();
    }
}

package com.youzipi.topbar_demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

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
public class SearchAction extends Thread{
    private List<NameValuePair> params = new ArrayList<NameValuePair>();
    private String keyword;
    private Handler handler;

    public SearchAction(Handler handler,String keyword) {
        this.handler = handler;
        this.keyword = keyword;
    }
    private JSONArray data;
    public void upload(){
        Log.i("status", "upload");
//        String uri = "http://library-58635.coding.io/q/";
        String uri = "https://mjlugs-8080-cujriy.box.myide.io/q/";
        HttpPost httpRequest = new HttpPost(uri);
        params.add(new BasicNameValuePair("keyword", keyword));
        Log.i("keyword", keyword);
        try {
            httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            Log.i("keyword", String.valueOf(httpRequest.getRequestLine()));
            Log.i("keyword", "request: " +EntityUtils.toString(httpRequest.getEntity()));
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                httpResponse.getEntity();
                HttpEntity entity=httpResponse.getEntity();
                InputStream is=entity.getContent();
                Log.i("status", "entity.getContent(): " + is);
                //下面是读取数据的过程
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                String line=null;
                StringBuffer sb=new StringBuffer();
                while((line=br.readLine())!=null){
                    sb.append(line);
                }
                String result = sb.toString();
                Log.i("status", "result: " + result);
                data = new JSONArray(result);
                Log.i("status", "data: " + data);

                Message msg  = new Message();
                msg.obj = result;
                handler.sendMessage(msg);
            } else {
                Log.i("status", "Error Response" + httpResponse.getStatusLine().toString());
            }
        } catch (UnsupportedEncodingException e) {
            Log.i("status", "result: " + e.getMessage());
        } catch (IOException e) {

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        upload();
    }
}

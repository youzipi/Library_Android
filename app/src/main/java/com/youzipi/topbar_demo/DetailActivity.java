package com.youzipi.topbar_demo;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class DetailActivity extends Activity {
    private TextView infoView;
    private TextView addressView;
    Handler myHandler;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initAll();
    }

    public void initAll() {

        myHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                HashMap<String,String> map = DataUtil.buildHtml(msg.obj.toString());
                Log.v("MyHandler", "msg.toString" + msg.obj.toString());
                Log.v("MyHandler", "map:" + map.toString());
//                ArrayAdapter arrayAdapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,list);
//                String[] keysOfMap = {"index", "title", "id", "pub"};

                infoView.setText(Html.fromHtml(map.get("detail")));
                addressView.setText(Html.fromHtml(map.get("address_info")));
            }
        };

        addressView = (TextView) findViewById(R.id.address);
        infoView = (TextView) findViewById(R.id.info);
        String id = getIntent().getStringExtra("id");
        Log.v("intent", "id:" + id);
        DetailAction detailAction = new DetailAction(myHandler,id);
        detailAction.start();


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

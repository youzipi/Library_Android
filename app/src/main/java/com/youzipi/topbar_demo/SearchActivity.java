package com.youzipi.topbar_demo;


import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SearchActivity extends Activity {

    private TextView describe;
    private ListView listView;
    Context context = this;
    private ArrayList<String> arrayList;
    Handler myHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initAll();
//        myHandler = new MyHandler(context, listView);
    }

    public void initAll(){
        describe = (TextView)findViewById(R.id.list_view_title);
        listView = (ListView)findViewById(R.id.list_view);
        describe.setText("Book_list");
        arrayList = new ArrayList<String>();
        arrayList.add("向之所欣");
        arrayList.add("俯仰之间");
        arrayList.add("已为陈迹");
        arrayList.add("犹不能不以之兴怀");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);

        final EditText editText = (EditText)findViewById(R.id.keywordText);
        Button searchButton = (Button)findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String keyword = editText.getText().toString();
                arrayList = new ArrayList<String>();
                SearchAction searchAction = new SearchAction(myHandler,keyword);
                searchAction.start();
                arrayList.add(keyword);
                ArrayAdapter arrayAdapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(arrayAdapter);

            }
        });

        myHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                List<HashMap<String,Object>> list = DataUtil.getListData(msg.obj.toString());
                Log.v("MyHandler", "msg.toString"+msg.obj.toString());
                Log.v("MyHandler", "list:"+list.toString());
                ArrayAdapter arrayAdapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,list);
                listView.setAdapter(arrayAdapter);
            }
        };
    }

    public ListView getListView() {
        return listView;
    }
}
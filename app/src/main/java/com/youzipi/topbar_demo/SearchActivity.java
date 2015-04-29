package com.youzipi.topbar_demo;


import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SearchActivity extends Activity implements OnItemClickListener {

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
        listView.setOnItemClickListener(this);


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
//                ArrayAdapter arrayAdapter = new ArrayAdapter(context,android.R.layout.simple_list_item_1,list);
                String[] keysOfMap = {"index","title","id","pub"};
                int [] keyOfLayout = {R.id.item_simple_adapter_index,R.id.item_simple_adapter_title,R.id.item_simple_adapter_id,R.id.item_simple_adapter_pub};
                SimpleAdapter simpleAdapter = new SimpleAdapter(context,list,R.layout.item_simple_adapter,keysOfMap,keyOfLayout);
                listView.setAdapter(simpleAdapter);
            }
        };
    }

    public ListView getListView() {
        return listView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = listView.getItemAtPosition(position).toString();
        Log.v("onclick", "item:"+item);
        Toast.makeText(context,item,Toast.LENGTH_LONG).show();

    }
}

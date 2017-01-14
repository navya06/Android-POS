package com.navyakaree.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        ListView listView = (ListView) findViewById(R.id.ListView);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Hi");
        arrayList.add("Hello");
        arrayList.add("Good Morning");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, arrayList);
        listView.setAdapter(arrayAdapter);
    }



}

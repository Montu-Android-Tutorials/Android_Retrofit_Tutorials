package com.app.android_retrofit_tutorials;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.android_retrofit_tutorials.app_base.Base_Activity;
import com.app.android_retrofit_tutorials.app_ui.app_activity.Open_Fragment_Activity;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends Base_Activity {

    private ListView mLvRecyclerOptions;
    private  ArrayList<String> optionString = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mLvRecyclerOptions = findViewById(R.id.lvRecyclerOptions);
        addRecyclerViewOptions();

        mLvRecyclerOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                openFragmentByOpr(optionString.get(position).toString().trim());
            }
        });

    }

    private void addRecyclerViewOptions() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, optionString);
        optionString.clear();
        optionString.add("GET METHOD");
        optionString.add("GET METHOD with PARAMETER");
        optionString.add("POST METHOD");
        optionString.add("PUT METHOD");
        optionString.add("DELETE METHOD");
        optionString.add("PATCH METHOD");
        optionString.add("MULTIPART METHOD");
        mLvRecyclerOptions.setAdapter(adapter);
    }



    private void openFragmentByOpr(String strOperations) {
        switch (strOperations) {
            case "GET METHOD":
                startActivity(new Intent(this, Open_Fragment_Activity.class)
                        .putExtra("fragmentKey", "GET_Method_Fragment")
                        .putExtra("restOpr", "SimpleGET"));
                break;
        }
    }


}
package com.app.android_retrofit_tutorials;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.android_retrofit_tutorials.app_base.Base_Activity;
import com.app.android_retrofit_tutorials.app_ui.app_activity.Open_Fragment_Activity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends Base_Activity implements View.OnClickListener {

    private MaterialButton mOpenRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.openRecyclerView:
                startActivity(new Intent(this, Open_Fragment_Activity.class)
                .putExtra("fragmentKey", "Notification_Fragment"));
                break;
        }

    }

    private void initView() {
        mOpenRecyclerView = findViewById(R.id.openRecyclerView);
        mOpenRecyclerView.setOnClickListener(this);
    }
}
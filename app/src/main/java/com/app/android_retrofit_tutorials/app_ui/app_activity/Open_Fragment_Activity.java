package com.app.android_retrofit_tutorials.app_ui.app_activity;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.app.android_retrofit_tutorials.R;
import com.app.android_retrofit_tutorials.app_base.Base_Activity;
import com.app.android_retrofit_tutorials.app_ui.app_fragment.GET_Method_Fragment;
import com.google.android.material.appbar.AppBarLayout;

public class Open_Fragment_Activity extends Base_Activity {
    private String TAG = Open_Fragment_Activity.class.getSimpleName();
    private String fragmentKey;
    private Fragment mFragment;


    private AppBarLayout mAppBar;
    private Toolbar mToolbar;
    private FrameLayout mFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_fragment);

        fragmentKey = getIntent().getStringExtra("fragmentKey");
        Log.d(TAG, "onCreate: fragmentKey--->" + fragmentKey);

        initView();

        openFragmentByKey(fragmentKey);

    }


    private void initView() {
        mAppBar = findViewById(R.id.app_bar);
        mToolbar = findViewById(R.id.toolbar);
        setUpTollBar(mToolbar);
        mFragmentContainer = findViewById(R.id.fragment_container);
    }



    private void openFragmentByKey(String fragmentKey) {
        Bundle bundle = new Bundle();

        switch (fragmentKey) {
            case "GET_Method_Fragment":
                /*mFragment = new GET_Method_Fragment();
                bundle.putString("productID", "productID");
                mFragment.setArguments(bundle);*/
                setFragment(R.id.fragment_container, new GET_Method_Fragment(), false);
                break;


        }
    }



    private void setUpTollBar(Toolbar mToolbar) {
        setSupportActionBar(mToolbar);
        // add back arrow to toolbar
        mToolbar.setTitleTextColor(getResources().getColor(R.color.black));
        mToolbar.setTitle("");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
//            getSupportActionBar().setDisplayShowTitleEnabled(true);
        }
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
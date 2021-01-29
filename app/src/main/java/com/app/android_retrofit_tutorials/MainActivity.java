package com.app.android_retrofit_tutorials;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.widget.AppCompatButton;

import com.app.android_retrofit_tutorials.app_base.Base_Activity;
import com.app.android_retrofit_tutorials.app_model.Resp_postUserJob;
import com.app.android_retrofit_tutorials.app_network_call.NetworkCall;
import com.app.android_retrofit_tutorials.app_network_call.RequestNotifier;
import com.app.android_retrofit_tutorials.app_ui.app_activity.Open_Fragment_Activity;
import com.app.android_retrofit_tutorials.app_utills.AppAlert;
import com.app.android_retrofit_tutorials.app_utills.ProgressView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Response;

public class MainActivity extends Base_Activity implements RequestNotifier {

    private String TAG = MainActivity.class.getSimpleName();

    private ListView mLvRecyclerOptions;
    private ArrayList<String> optionString = new ArrayList<>();

    private Context mContext;
    private  NetworkCall networkCall;


    //Todo Post Job
    private BottomSheetDialog postBottomSheetDialog;
    private TextInputEditText mEdtName;
    private TextInputEditText mEdtJob;
    private AppCompatButton mBtnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        networkCall = new NetworkCall(mContext, this);

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
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, optionString);
        optionString.clear();
        optionString.add("GET METHOD");
        optionString.add("GET With PARAM's");
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
                        .putExtra("fragmentKey", "GET_Method_Fragment"));
                break;

            case "GET With PARAM's":
                startActivity(new Intent(this, Open_Fragment_Activity.class)
                        .putExtra("fragmentKey", "GET_Method_Params_Fragment"));
                break;

            case "POST METHOD":
                _postJob(mContext);
                break;
        }
    }


    //Todo Post Job

    private void _postJob(Context context) {

        postBottomSheetDialog = new BottomSheetDialog(context, R.style.TransparentDialog);
        View dialogView = getLayoutInflater().inflate(R.layout.bottom_sheet_post_job, null);

        postBottomSheetDialog.setContentView(dialogView);
        postBottomSheetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                BottomSheetDialog d = (BottomSheetDialog) dialog;
                View bottomSheetInternal = d.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });


        mEdtName = dialogView.findViewById(R.id.edtName);
        mEdtJob = dialogView.findViewById(R.id.edtJob);
        mBtnPost = dialogView.findViewById(R.id.btnPost);

        mBtnPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isAllValid(mEdtName.getText().toString().trim(), mEdtJob.getText().toString().trim());
                }
            });

            postBottomSheetDialog.show();
        }

    private void isAllValid(String name, String job) {
        if (TextUtils.isEmpty(name))
            mEdtName.setError("Please Enter User Name");
        else if (TextUtils.isEmpty(job))
            mEdtJob.setError("Please Enter Job Title");
        else
        {

//            if (postBottomSheetDialog != null)
//                postBottomSheetDialog.dismiss();

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", name);
            jsonObject.addProperty("job", job);

            Log.d(TAG, "isAllValid:jsonObject--->"+jsonObject);
            ProgressView.show(mContext);
            networkCall.postUserJob(jsonObject);
        }

    }



    @Override
    public void notifySuccess(Response<?> response) {

        if (response.body() instanceof Resp_postUserJob) {
            ProgressView.dismiss();
            Resp_postUserJob resp_postUserJob = (Resp_postUserJob) response.body();
            if (resp_postUserJob.getId() != null) {

                if (postBottomSheetDialog != null)
                    postBottomSheetDialog.dismiss();

                AppAlert.callNotamlAlert(mContext, "Success","User Created Successfully:-"+resp_postUserJob.getId());

            } else {
                AppAlert.callNotamlAlert(mContext, "Error", "Something Went Wrong Please Try Again....!!!");
            }
        }
    }

    @Override
    public void notifyNoInternet() {
        ProgressView.dismiss();
        AppAlert._noInternetAlert(mContext);
    }

    @Override
    public void notifyError(Throwable throwable) {
        Log.d(TAG, "notifyError: throwable--->" + throwable.getMessage());
        ProgressView.dismiss();
        AppAlert.callError(mContext);
    }

    @Override
    public void notifyString(String s) {
        ProgressView.dismiss();
        AppAlert.callAlert(mContext, s);

    }


}
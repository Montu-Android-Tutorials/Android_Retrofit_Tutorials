package com.app.android_retrofit_tutorials.app_ui.app_fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.android_retrofit_tutorials.R;
import com.app.android_retrofit_tutorials.app_adapter.Adapter_GET_Method;
import com.app.android_retrofit_tutorials.app_adapter.Adapter_GET_Method_Params;
import com.app.android_retrofit_tutorials.app_base.Base_Fragment;
import com.app.android_retrofit_tutorials.app_model.Response_getUsers;
import com.app.android_retrofit_tutorials.app_network_call.NetworkCall;
import com.app.android_retrofit_tutorials.app_network_call.RequestNotifier;
import com.app.android_retrofit_tutorials.app_utills.AppAlert;
import com.app.android_retrofit_tutorials.app_utills.ProgressView;

import java.util.ArrayList;

import retrofit2.Response;

public class GET_Method_Params_Fragment extends Base_Fragment implements RequestNotifier, Adapter_GET_Method_Params.OpenUserInfo{


    private String TAG = GET_Method_Params_Fragment.class.getSimpleName();
    private NetworkCall networkCall;
    private Context mContext;

    private LinearLayout mMainLay;
    private RecyclerView mRecyclerView;
    private LinearLayout mLayError;
    private TextView mTxtDataMsg;

    private ArrayList<Response_getUsers.DataEntity> resultEntityArrayList = new ArrayList<>();
    private Adapter_GET_Method_Params adapter_get_method_params;


    public GET_Method_Params_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_method_params, container, false);
        initView(view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        networkCall = new NetworkCall(mContext, this);

        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null)
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("GET METHOD");

        adapter_get_method_params.getList().clear();
        callAPI(mContext);


    }

    private void initView(View view) {
        mMainLay = view.findViewById(R.id.main_lay);
        mRecyclerView = view.findViewById(R.id.RecyclerView);
        mLayError = view.findViewById(R.id.lay_error);
        mTxtDataMsg = view.findViewById(R.id.txt_data_msg);
        setUpRecycler();
    }

    private void setUpRecycler() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        adapter_get_method_params = new Adapter_GET_Method_Params(getContext(), resultEntityArrayList, this);
        mRecyclerView.setAdapter(adapter_get_method_params);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void callAPI(Context mContext) {
        ProgressView.show(getContext());
        networkCall.getUsers();
    }

    @Override
    public void notifySuccess(Response<?> response) {

        if (response.body() instanceof Response_getUsers) {
            Response_getUsers response_getUsers = (Response_getUsers) response.body();
            resultEntityArrayList.clear();
            ProgressView.dismiss();
            if (response_getUsers != null) {
                if (response_getUsers.getData() != null) {
                    if (response_getUsers.getData().size() > 0) {
                        if (!response_getUsers.getData().isEmpty()) {
                            for (Response_getUsers.DataEntity success : response_getUsers.getData()) {
                                success.setId(success.getId());
                                resultEntityArrayList.add(success);
                            }

                            mMainLay.setVisibility(View.VISIBLE);
                            mLayError.setVisibility(View.GONE);
                            adapter_get_method_params.setList(resultEntityArrayList);
                        }
                    } else {
                        mLayError.setVisibility(View.VISIBLE);
                        mTxtDataMsg.setText(getResources().getString(R.string.no_data_found));
                        mMainLay.setVisibility(View.GONE);
                    }
                } else {
                    mLayError.setVisibility(View.VISIBLE);
                    mTxtDataMsg.setText(getResources().getString(R.string.no_data_found));
                    mMainLay.setVisibility(View.GONE);
                }
            } else {
                mLayError.setVisibility(View.VISIBLE);
                mTxtDataMsg.setText(getResources().getString(R.string.no_data_found));
                mMainLay.setVisibility(View.GONE);
            }
        }


    }


    @Override
    public void notifyNoInternet() {
        ProgressView.dismiss();
        AppAlert._noInternetAlert(mContext);
        mLayError.setVisibility(View.VISIBLE);
        mTxtDataMsg.setText(getResources().getString(R.string.no_data_found));
        mMainLay.setVisibility(View.GONE);

    }

    @Override
    public void notifyError(Throwable throwable) {
        Log.d(TAG, "notifyError: throwable--->" + throwable.getMessage());
        ProgressView.dismiss();
        AppAlert.callError(mContext);
        mLayError.setVisibility(View.VISIBLE);
        mTxtDataMsg.setText(getResources().getString(R.string.no_data_found));
        mMainLay.setVisibility(View.GONE);
    }

    @Override
    public void notifyString(String s) {
        ProgressView.dismiss();
        mLayError.setVisibility(View.VISIBLE);
        mTxtDataMsg.setText(getResources().getString(R.string.no_data_found));
        mMainLay.setVisibility(View.GONE);
    }


    @Override
    public void _userInfo(Response_getUsers.DataEntity entity) {
        Log.d(TAG, "_userInfo: UserIIIDDD---->"+entity.getId());
    }
}
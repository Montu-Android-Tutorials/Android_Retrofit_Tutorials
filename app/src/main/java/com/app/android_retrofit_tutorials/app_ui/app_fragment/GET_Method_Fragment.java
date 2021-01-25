package com.app.android_retrofit_tutorials.app_ui.app_fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.android_retrofit_tutorials.R;
import com.app.android_retrofit_tutorials.app_adapter.Adapter_GET_Method;
import com.app.android_retrofit_tutorials.app_base.Base_Fragment;
import com.app.android_retrofit_tutorials.app_constants.Preference_Manager;
import com.app.android_retrofit_tutorials.app_model.Resp_get_All_Notification_for_EveryOne;
import com.app.android_retrofit_tutorials.app_network_call.NetworkCall;
import com.app.android_retrofit_tutorials.app_network_call.RequestNotifier;
import com.app.android_retrofit_tutorials.app_utills.AppAlert;
import com.app.android_retrofit_tutorials.app_utills.ProgressView;

import java.util.ArrayList;

import retrofit2.Response;


public class GET_Method_Fragment extends Base_Fragment implements RequestNotifier, Adapter_GET_Method.OpenNotificationId {


    private final int pageSize = 10;

    private String TAG = GET_Method_Fragment.class.getSimpleName();
    private int cardPosition;
    private Preference_Manager preference_manager;
    private NetworkCall networkCall;
    private Context mContext;
    private boolean mIsLoading = false;
    private boolean mIsLastPage = false;
    private int mCurrentPage = 0;
    private Boolean isFirstPage;
    private int loadPage = 10;
    private int skipPage = 0;
    //    private ShimmerFrameLayout mShimmerViewContainer;
    private LinearLayout mMainLay;
    private RecyclerView mRecyclerView;
    private LinearLayout mLayError;
    private TextView mTxtDataMsg;
    private LinearLayoutManager layoutManager;


    private ArrayList<Resp_get_All_Notification_for_EveryOne.ResultEntity> resultEntityArrayList = new ArrayList<>();
    private Adapter_GET_Method adapter_GETExample;


    public GET_Method_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_get_method, container, false);
        initView(view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        preference_manager = new Preference_Manager(mContext);
        networkCall = new NetworkCall(mContext, this);


        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null)
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("GET METHOD");


        mIsLoading = false;
        mIsLastPage = false;
        mCurrentPage = 0;

        /*Bundle bundle = this.getArguments();
        if (bundle != null) {
            apiKey = bundle.getString("key");
        }*/


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // number of visible items
                    int visibleItemCount = layoutManager.getChildCount();
                    // number of items in layout
                    int totalItemCount = layoutManager.getItemCount();
                    // the position of first visible item
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    boolean isNotLoadingAndNotLastPage = !mIsLoading && !mIsLastPage;
                    // flag if number of visible items is at the last
                    boolean isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount;
                    // validate non negative values
                    boolean isValidFirstItem = firstVisibleItemPosition >= 0;
                    // validate total items are more than possible visible items
                    boolean totalIsMoreThanVisible = totalItemCount >= pageSize;
                    // flag to know whether to load more
                    boolean shouldLoadMore = isValidFirstItem && isAtLastItem && totalIsMoreThanVisible && isNotLoadingAndNotLastPage;

                    if (shouldLoadMore) {
                        skipPage = skipPage + pageSize;
                        callAPI(false, skipPage);
                    }
                }
            }
        });


        // load the first page
        adapter_GETExample.getList().clear();
        callAPI(true, skipPage);


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
        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(layoutManager);

//        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 4));

        adapter_GETExample = new Adapter_GET_Method(getContext(), resultEntityArrayList, this);
        mRecyclerView.setAdapter(adapter_GETExample);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void callAPI(boolean isFirstPage, int skip) {
        this.isFirstPage = isFirstPage;

        // change loading state
        mIsLoading = true;
        networkCall.get_All_Notification_for_EveryOne("5f9cfb354fe2dc022c1137ed", "customer", loadPage, skip);
    }

    @Override
    public void notifySuccess(Response<?> response) {

        if (response.body() instanceof Resp_get_All_Notification_for_EveryOne) {
            Resp_get_All_Notification_for_EveryOne pendingOrdersForSeller = (Resp_get_All_Notification_for_EveryOne) response.body();
            resultEntityArrayList.clear();
            if (pendingOrdersForSeller != null) {
                if (pendingOrdersForSeller.getResult() != null) {
                    if (pendingOrdersForSeller.getResult().size() > 0) {
                        if (!pendingOrdersForSeller.getResult().isEmpty()) {

                            mMainLay.setVisibility(View.VISIBLE);
                            mLayError.setVisibility(View.GONE);

                            for (Resp_get_All_Notification_for_EveryOne.ResultEntity success : pendingOrdersForSeller.getResult()) {
                                success.setId(success.getId());
                                resultEntityArrayList.add(success);
                            }

                            if (isFirstPage) {
                                adapter_GETExample.setList(resultEntityArrayList);
                            } else {
                                adapter_GETExample.addAll(resultEntityArrayList);
                            }


                            Log.d(TAG, "notifySuccess: resultEntityArrayList--->" + resultEntityArrayList.size());

                            mIsLoading = false;
                            mIsLastPage = mCurrentPage == 100;
                            mCurrentPage = mCurrentPage + 1;

                        }
                    } else {

                        if (isFirstPage) {
                            mLayError.setVisibility(View.VISIBLE);
                            mTxtDataMsg.setText(getResources().getString(R.string.no_data_found));

                            mMainLay.setVisibility(View.GONE);
                        } else {
                            mMainLay.setVisibility(View.VISIBLE);
                            mLayError.setVisibility(View.GONE);
                        }
                    }
                } else {
                    if (isFirstPage) {
                        mLayError.setVisibility(View.VISIBLE);
                        mTxtDataMsg.setText(getResources().getString(R.string.no_data_found));

                        mMainLay.setVisibility(View.GONE);
                    } else {
                        mMainLay.setVisibility(View.VISIBLE);
                        mLayError.setVisibility(View.GONE);
                    }
                }
            } else {

                if (isFirstPage) {
                    mLayError.setVisibility(View.VISIBLE);
                    mTxtDataMsg.setText(getResources().getString(R.string.no_data_found));

                    mMainLay.setVisibility(View.GONE);
                } else {
                    mMainLay.setVisibility(View.VISIBLE);
                    mLayError.setVisibility(View.GONE);
                }
            }
        }


    }


    @Override
    public void notifyNoInternet() {
        ProgressView.dismiss();
        AppAlert._noInternetAlert(mContext);
        if (isFirstPage) {
            mLayError.setVisibility(View.VISIBLE);
            mTxtDataMsg.setText(getResources().getString(R.string.no_data_found));

            mMainLay.setVisibility(View.GONE);
        } else {
            mMainLay.setVisibility(View.VISIBLE);
            mLayError.setVisibility(View.GONE);
        }

    }

    @Override
    public void notifyError(Throwable throwable) {
        Log.d(TAG, "notifyError: throwable--->" + throwable.getMessage());
        ProgressView.dismiss();
        AppAlert.callError(mContext);
        if (isFirstPage) {
            mLayError.setVisibility(View.VISIBLE);
            mTxtDataMsg.setText("No Data Found....!");

            mMainLay.setVisibility(View.GONE);
        } else {
            mMainLay.setVisibility(View.VISIBLE);
            mLayError.setVisibility(View.GONE);
        }

    }

    @Override
    public void notifyString(String s) {
        ProgressView.dismiss();
        if (isFirstPage) {
            mLayError.setVisibility(View.VISIBLE);
            mTxtDataMsg.setText("No Data Found....!");

            mMainLay.setVisibility(View.GONE);
        } else {
            mMainLay.setVisibility(View.VISIBLE);
            mLayError.setVisibility(View.GONE);
        }
        AppAlert.callAlert(mContext, s);

    }


    @Override
    public void openNotificationId(Resp_get_All_Notification_for_EveryOne.ResultEntity entity) {

        Log.d(TAG, "openNotificationId: DATADTADAT-->" + entity.getId());

        /*startActivity(new Intent(mContext, Product_Order_InFo_Activity.class).
                putExtra("orderID", App_Utils.removeFirstChar(App_Utils.removeLastChar(entity.getValue())))
                .putExtra("notificationID", entity.getId()).
                        putExtra("isSeen", entity.isSeen()));*/
    }


}
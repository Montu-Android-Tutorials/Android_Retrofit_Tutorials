package com.app.android_retrofit_tutorials.app_adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.android_retrofit_tutorials.R;
import com.app.android_retrofit_tutorials.app_model.Resp_get_All_Notification_for_EveryOne;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class Adapter_GET_Method extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public String TAG = Adapter_GET_Method.class.getSimpleName();

    private List<Resp_get_All_Notification_for_EveryOne.ResultEntity> resultEntities;
    private Context mContext;

    int lastPosition = -1;

    OpenNotificationId openNotificationId;

    public Adapter_GET_Method(Context context, List<Resp_get_All_Notification_for_EveryOne.ResultEntity> resultEntities,
                              OpenNotificationId openNotificationId) {
        this.resultEntities = resultEntities;
        this.mContext = context;
        this.openNotificationId = openNotificationId;
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    @Override
    public int getItemCount() {
        return resultEntities.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_notifications, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder1, int position) {

        MyViewHolder holder = (MyViewHolder) holder1;
        Resp_get_All_Notification_for_EveryOne.ResultEntity entity = resultEntities.get(position);



        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;


        holder.mTxtNotifTitle.setText(entity.getTitle() + " " + entity.getKey());
        holder.mTxtMessage.setText(entity.getBody());

        if (entity.isSeen()) {
            holder.mMainCard.setBackgroundColor(Color.parseColor("#EEEEEE"));
        } else {
            holder.mMainCard.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

//        holder.mTxtDate.setText(App_Utils.convertDate(entity.getNotifytime()));


        holder.mMainCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.mMainCard.setBackgroundColor(Color.parseColor("#ECEFF1"));
                openNotificationId.openNotificationId(entity);

            }
        });

    }


    //ViewHolder Class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView mMainCard;
        private MaterialTextView mTxtNotifTitle;
        private MaterialTextView mTxtMessage;
        private MaterialTextView mTxtDate;


        public MyViewHolder(View view) {
            super(view);
            mMainCard = view.findViewById(R.id.mainCard);
            mTxtNotifTitle = view.findViewById(R.id.txt_notif_title);
            mTxtMessage = view.findViewById(R.id.txt_message);
            mTxtDate = view.findViewById(R.id.txt_date);

        }
    }


    public void addAll(List<Resp_get_All_Notification_for_EveryOne.ResultEntity> newList) {
        int lastIndex = resultEntities.size() /*- 1*/;
        resultEntities.addAll(newList);
        notifyItemRangeInserted(lastIndex, newList.size());
    }

    public List<Resp_get_All_Notification_for_EveryOne.ResultEntity> getList() {
        return this.resultEntities;
    }


    ///Todo Pagination

    public void setList(List<Resp_get_All_Notification_for_EveryOne.ResultEntity> list) {
        this.resultEntities = list;
        notifyDataSetChanged();
    }

    //Swipe to Remove
    public void removeItem(int position) {
        resultEntities.remove(position);
        notifyItemRemoved(position);
    }



    //Animate
    @Override
    public void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }




    public interface OpenNotificationId {
        public void  openNotificationId(Resp_get_All_Notification_for_EveryOne.ResultEntity entity);
    }

}

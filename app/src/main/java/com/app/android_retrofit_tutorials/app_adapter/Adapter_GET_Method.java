package com.app.android_retrofit_tutorials.app_adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.android_retrofit_tutorials.R;
import com.app.android_retrofit_tutorials.app_model.Response_getUsers;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class Adapter_GET_Method extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public String TAG = Adapter_GET_Method.class.getSimpleName();

    private List<Response_getUsers.DataEntity> resultEntities;
    private Context mContext;
    private OpenNotificationId openNotificationId;

    private int lastPosition = -1;


    public Adapter_GET_Method(Context context, List<Response_getUsers.DataEntity> resultEntities,
                              OpenNotificationId openNotificationId) {
        this.resultEntities = resultEntities;
        this.mContext = context;
        this.openNotificationId = openNotificationId;
    }


    @Override
    public int getItemCount() {
        return resultEntities.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_users_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder1, int position) {

        MyViewHolder holder = (MyViewHolder) holder1;
        Response_getUsers.DataEntity entity = resultEntities.get(position);

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > lastPosition) ? R.anim.up_from_bottom
                        : R.anim.down_from_top);
        holder.itemView.startAnimation(animation);
        lastPosition = position;


        holder.mTxtUserName.setText(entity.getFirstName() + " " + entity.getLastName());
        holder.mTxtUserEmail.setText(entity.getEmail());


        if (entity.getAvatar() != null) {
            Glide.with(mContext)
                    .load(entity.getAvatar())
                    .apply(new RequestOptions().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher))
                    .into(holder.mIvUserProfile);
        }
        holder.mMainCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNotificationId.openNotificationId(entity);
            }
        });

    }



    //ViewHolder Class
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CardView mMainCard;
        private ImageView mIvUserProfile;
        private MaterialTextView mTxtUserName;
        private MaterialTextView mTxtUserEmail;
        public MyViewHolder(View view) {
            super(view);
            mMainCard = view.findViewById(R.id.mainCard);
            mIvUserProfile = view.findViewById(R.id.ivUserProfile);
            mTxtUserName = view.findViewById(R.id.txtUserName);
            mTxtUserEmail = view.findViewById(R.id.txtUserEmail);
        }
    }


    public void addAll(List<Response_getUsers.DataEntity> newList) {
        int lastIndex = resultEntities.size() /*- 1*/;
        resultEntities.addAll(newList);
        notifyItemRangeInserted(lastIndex, newList.size());
    }

    public List<Response_getUsers.DataEntity> getList() {
        return this.resultEntities;
    }


    ///Todo Pagination

    public void setList(List<Response_getUsers.DataEntity> list) {
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
        public void openNotificationId(Response_getUsers.DataEntity entity);
    }

}

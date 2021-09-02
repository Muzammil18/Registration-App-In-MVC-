package com.example.registration.views.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registration.R;
import com.example.registration.listener.HomeListener;
import com.example.registration.models.UserDataModel;
import com.example.registration.views.fragments.UserProfileFragment;

import java.util.ArrayList;


public class UsersFragmentAdapter extends RecyclerView.Adapter<UsersFragmentAdapter.viewHolder> {
    Context context;
    ArrayList<UserDataModel> arrayList;
    HomeListener homeListener;
    public UsersFragmentAdapter(Context context,ArrayList<UserDataModel> arrayList,HomeListener homeListener) {
        this.context = context;
        this.arrayList=arrayList;
        this.homeListener=homeListener;
    }
    @Override
    public UsersFragmentAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_of_user, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public void onBindViewHolder(UsersFragmentAdapter.viewHolder holder, int position) {
        holder.img_User.setImageResource(arrayList.get(position).getUserIcon());
        holder.user_Name.setText(arrayList.get(position).getUserName());
        holder.user_Email.setText(arrayList.get(position).getUserEmail());
        holder.user_PhoneNo.setText(arrayList.get(position).getUserPhoneNo());
        holder.onItemClicked();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public final class viewHolder extends RecyclerView.ViewHolder {
        ImageView img_User;
        TextView user_Name,user_Email,user_PhoneNo;
        ConstraintLayout layout;

        public viewHolder(View itemView) {
            super(itemView);
            layout=(ConstraintLayout)itemView.findViewById(R.id.layout);
            img_User=(ImageView)itemView.findViewById(R.id.img_user) ;
            user_Name=(TextView) itemView.findViewById(R.id.text_user_name);
            user_Email=(TextView) itemView.findViewById(R.id.text_user_email);
            user_PhoneNo=(TextView) itemView.findViewById(R.id.text_user_phoneno);
        }
        void onItemClicked(){
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle=new Bundle();
                    bundle.putInt("icon",arrayList.get(getPosition()).getUserIcon());
                    bundle.putString("name",arrayList.get(getPosition()).getUserName());
                    bundle.putString("email",arrayList.get(getPosition()).getUserEmail());
                    bundle.putString("phone",arrayList.get(getPosition()).getUserPhoneNo());
                    homeListener.onFragmentChangeWithBundleListener(bundle,new UserProfileFragment());
                }
            });
        }


    }

}

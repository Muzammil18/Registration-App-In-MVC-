package com.example.registration.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.registration.R;
import com.example.registration.listener.HomeListener;

public class UserProfileFragment extends Fragment {
    ImageView img_Profile;
    TextView textName,textEmail,textPhoneNo;


    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_userprofile, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        find_View();
    }

    private void find_View() {
        img_Profile=(ImageView)getView().findViewById(R.id.img_user);
        img_Profile.setImageResource(getArguments().getInt("icon"));
        textName=getView().findViewById(R.id.textname);
        textName.setText(getArguments().getString("name"));
        textEmail=getView().findViewById(R.id.textemail);
        textEmail.setText(getArguments().getString("email"));
        textPhoneNo=getView().findViewById(R.id.textphoneno);
        textPhoneNo.setText(getArguments().getString("phone"));
    }
}

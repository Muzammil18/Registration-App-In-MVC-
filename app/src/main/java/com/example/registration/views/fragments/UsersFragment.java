package com.example.registration.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registration.R;
import com.example.registration.helpers.DatabaseHelper;
import com.example.registration.listener.HomeListener;
import com.example.registration.models.UserDataModel;
import com.example.registration.views.activities.RegistrationActivity;
import com.example.registration.views.adapters.UsersFragmentAdapter;

import java.util.ArrayList;

public class UsersFragment extends Fragment {
    Button btn_add_user;
    ArrayList<UserDataModel> arrayList;
    RecyclerView recyclerView;
    DatabaseHelper database_helper;
    protected Context mContext;
    protected HomeListener homeListener;
    public void onAttach( Context context) {
        super.onAttach(context);
        this.mContext = context;
        this.homeListener = (HomeListener)context;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //onBackPressed();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        find_View();
        init();
    }

    private void find_View() {
        btn_add_user = getView().findViewById(R.id.button_add_user);
        recyclerView = getView().findViewById(R.id.recyclerView);
    }

    private void init() {
        btn_add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), RegistrationActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        showAllUsers();

    }

    void showAllUsers(){
        database_helper=new DatabaseHelper(getContext());
        arrayList = new ArrayList<>(database_helper.getAllUsers());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        UsersFragmentAdapter adapter = new UsersFragmentAdapter(getContext(), arrayList,homeListener);
        recyclerView.setAdapter(adapter);
    }

}


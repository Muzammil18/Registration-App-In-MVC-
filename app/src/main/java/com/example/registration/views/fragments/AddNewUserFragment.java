package com.example.registration.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.registration.R;
import com.example.registration.listener.HomeListener;

public class AddNewUserFragment extends Fragment {
    EditText textName,textEmail,textPhoneNo;
    Button btn_save;
    protected Context mContext;
    protected HomeListener homeListener;
    public void onAttach( Context context) {
        super.onAttach(context);
        this.mContext = context;
        this.homeListener = (HomeListener)context;
    }

    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //onBackPressed();
    }
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_new_user, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        find_View();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validatingFields()){
                    Bundle bundle=new Bundle();
                    bundle.putString("name",textName.getText().toString());
                    bundle.putString("email",textEmail.getText().toString());
                    bundle.putString("phone",textPhoneNo.getText().toString());
                    homeListener.onFragmentChangeWithBundleListener(bundle,new AddUserCapchaFragment());
            }
            }
        });
    }

    private void find_View() {
        btn_save=getView().findViewById(R.id.btnSave);
        textName=getView().findViewById(R.id.txtName);
        textEmail=getView().findViewById(R.id.txtEmail);
        textPhoneNo=getView().findViewById(R.id.txtPhoneNo);

    }

    public final boolean validatingFields() {
        Boolean value = true;
        if(textName.length()==0){
            textName.setError("Please Enter Name");
            value=false;
            return value;
        }
        if(textEmail.length()==0){
            textEmail.setError("Please Enter Email");
            value=false;
            return value;
        }
        if(!textEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            textEmail.setError("Please Enter Correct Email");
            value=false;
            return value;
        }
        if(textPhoneNo.length()==0){
            textPhoneNo.setError("Please Enter Phone no");
            value=false;
            return value;
        }

        return value;
    }

}


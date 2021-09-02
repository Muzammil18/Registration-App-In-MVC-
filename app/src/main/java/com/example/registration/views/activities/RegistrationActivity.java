package com.example.registration.views.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.registration.R;
import com.example.registration.listener.HomeListener;
import com.example.registration.views.fragments.AddNewUserFragment;
import com.example.registration.views.fragments.UsersFragment;

public class RegistrationActivity extends FragmentActivity implements HomeListener {
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        onFragmentChange(new AddNewUserFragment());
    }


    protected final void onFragmentChange(Fragment fragment) {
        if (fragment != null) {
            String backStateName = fragment.getClass().getName();
            FragmentManager manager = this.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentlayout, fragment, backStateName);
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
        }

    }

    protected final void onFragmentChangeWithBundle(Bundle bundle, Fragment fragment) {
        if (fragment != null) {
            String backStateName = fragment.getClass().getName();
            FragmentManager manager = this.getSupportFragmentManager();
            fragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentlayout, fragment, backStateName);
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onFragmentChangeListener(Fragment fragment) {
        onFragmentChange(fragment);
    }

    @Override
    public void onFragmentChangeWithBundleListener(Bundle bundle, Fragment fragment) {
            onFragmentChangeWithBundle(bundle,fragment);
    }


    @Override
    public void onBackButtonPressed() {
        onBackPressed();

    }

}

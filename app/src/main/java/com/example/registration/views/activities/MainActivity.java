package com.example.registration.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.registration.R;
import com.example.registration.listener.HomeListener;
import com.example.registration.views.fragments.UserProfileFragment;
import com.example.registration.views.fragments.UsersFragment;

public class MainActivity extends FragmentActivity implements HomeListener {
    private boolean isTwoPane;
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        }
        else {
            getSupportFragmentManager().popBackStack();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragmentlayout1) != null) {
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
        onFragmentChangeListener(new UsersFragment());
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
            FragmentTransaction fragmentTransaction =  manager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentlayout, fragment, backStateName);
            fragmentTransaction.addToBackStack(backStateName);
            fragmentTransaction.commit();
        }

    }
    protected final void onFragmentChangeWithBundle(int layout, Bundle bundle, Fragment fragment) {
        if (fragment != null) {
            String backStateName = fragment.getClass().getName();
            FragmentManager manager = this.getSupportFragmentManager();
            fragment.setArguments(bundle);
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(layout, fragment, backStateName);
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
        if (isTwoPane&&(fragment instanceof UserProfileFragment)) {
            this.onFragmentChangeWithBundle(R.id.fragmentlayout1,bundle, fragment);
        }
        else {
            this.onFragmentChangeWithBundle(bundle, fragment);
        }
    }


    @Override
    public void onBackButtonPressed() {

    }

}
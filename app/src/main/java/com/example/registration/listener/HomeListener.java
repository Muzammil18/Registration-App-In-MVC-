package com.example.registration.listener;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public interface HomeListener {
    void onFragmentChangeListener(Fragment fragment);
    void onFragmentChangeWithBundleListener(Bundle bundle,  Fragment fragment);
    void onBackButtonPressed();
}

package com.example.registration.views.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.registration.R;
import com.example.registration.helpers.DatabaseHelper;
import com.example.registration.listener.HomeListener;
import com.example.registration.views.adapters.CapchaFragmentAdapter;

import java.util.Random;

public class AddUserCapchaFragment extends Fragment {
    DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    Button btn_Add_User;
    CapchaFragmentAdapter adapter;
    ImageView btn_refrash;
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
        return inflater.inflate(R.layout.fragment_add_user_capcha, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        find_View();
        setRecyclerViewData();
        init();
    }

    private void init() {
        btn_refrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefreshClicked();
            }
        });
        btn_Add_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showdialoage();            }
        });
    }

    private void find_View() {
        recyclerView=getView().findViewById(R.id.recyclerView);
        btn_refrash=getView().findViewById(R.id.btnRefresh);
        btn_Add_User=getView().findViewById(R.id.button_add_user);
        adapter=new CapchaFragmentAdapter();
    }
    void setRecyclerViewData(){
        recyclerView.setLayoutManager((RecyclerView.LayoutManager)(new GridLayoutManager(this.requireContext(), 3)));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter=new CapchaFragmentAdapter(getContext(),arrayIcons,arrayIconsKeys);
        recyclerView.setAdapter((RecyclerView.Adapter)(adapter));

    }

    public Integer[] arrayIcons= new Integer[]
            {R.drawable.img1, R.drawable.img2, R.drawable.img3,
                    R.drawable.img4,R.drawable.img5,R.drawable.img6,
                    R.drawable.img7,R.drawable.img8,R.drawable.img9};
    public Integer[] arrayIconsKeys=new Integer[]
            {0, 0, 0,
                    0, 1, 1,
                    1, 1, 1};
    public final void onRefreshClicked() {
        Integer[] rendomiconarray = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        Integer[] rendomkeyarray = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] rendom = (Integer[])this.shuffle((Comparable[])rendomkeyarray);
        int i = 0;

        for(int var5 = this.arrayIcons.length; i < var5; ++i) {
            int rendomindex = ((Number)rendom[i]).intValue();
            rendomiconarray[i] = this.arrayIcons[rendomindex];
            rendomkeyarray[i] = this.arrayIconsKeys[rendomindex];
        }

        this.arrayIcons = rendomiconarray;
        this.arrayIconsKeys = rendomkeyarray;
        setRecyclerViewData();
    }
    public final Comparable[] shuffle(Comparable[] items) {
        Random rg = new Random();


        for( int i = 0; i < items.length; i++) {
            int randomPosition = rg.nextInt(items.length);
            Comparable tmp = items[i];
            items[i] = items[randomPosition];
            items[randomPosition] = tmp;
        }

        return items;
    }

    public final void showdialoage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if (adapter.validcapcha() == 0 && adapter.numberOfClick() == 4) {
            builder.setMessage((CharSequence)"Verified");
            builder.setPositiveButton((CharSequence)"OK", (DialogInterface.OnClickListener)(new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int which) {
                    databaseHelper=new DatabaseHelper(getContext());
                    databaseHelper.insertData(
                            getArguments().getString("name"),
                            getArguments().getString("email"),
                            getArguments().getString("phone"),
                            String.valueOf(randomIcon())
                    );
                   homeListener.onBackButtonPressed();
                }
            }));
        } else {
            builder.setMessage((CharSequence)"Not Verified");
            builder.setPositiveButton((CharSequence)"OK", (DialogInterface.OnClickListener)(new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int which) {
                  homeListener.onBackButtonPressed();
                }
            }));
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    private static final Integer[] arrayProfile= new Integer[]{
            R.drawable.icon01_01, R.drawable.icon01_02, R.drawable.icon01_03,
            R.drawable.icon01_04, R.drawable.icon01_05, R.drawable.icon01_06,
            R.drawable.icon01_07, R.drawable.icon01_08, R.drawable.icon01_09,
            R.drawable.icon01_10, R.drawable.icon01_11, R.drawable.icon01_12,
            R.drawable.icon01_13, R.drawable.icon01_14, R.drawable.icon01_15,
            R.drawable.icon01_16, R.drawable.icon01_17, R.drawable.icon01_18,
            R.drawable.icon01_19, R.drawable.icon01_20, R.drawable.icon01_21,
            R.drawable.icon01_22, R.drawable.icon01_23, R.drawable.icon01_24,
            R.drawable.icon01_25, R.drawable.icon01_26, R.drawable.icon01_27,
            R.drawable.icon01_28, R.drawable.icon01_29, R.drawable.icon01_30,
    };
    public final int randomIcon() {
        int rendomindex = (new Random()).nextInt(arrayProfile.length);
        return ((Number)arrayProfile[rendomindex]).intValue();
    }

}


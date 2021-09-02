package com.example.registration.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.registration.R;
import com.example.registration.helpers.ValidationCapcha;


public class CapchaFragmentAdapter extends RecyclerView.Adapter<CapchaFragmentAdapter.viewHolder> implements ValidationCapcha {
    Context context;
    Integer[] arrayIcons;
    Integer[] arrayIconsKeys;
    private int validCapcha;
    private int numberOfClickInCapcha;

    public CapchaFragmentAdapter() {
    }

    public CapchaFragmentAdapter(Context context, Integer[] arrayIcons, Integer[] arrayIconsKeys) {
        this.context = context;
        this.arrayIcons=arrayIcons;
        this.arrayIconsKeys=arrayIconsKeys;
    }



    @Override
    public CapchaFragmentAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_of_add_user_capcha, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(CapchaFragmentAdapter.viewHolder holder, int position) {
        holder.selected_Item.setImageResource(arrayIcons[position]);
        holder.selected_Item();
        holder.unSelected_Item();
    }

    @Override
    public int getItemCount() {
        return arrayIcons.length;
    }

    @Override
    public int validcapcha() {
        return validCapcha;
    }

    @Override
    public int numberOfClick() {
        return numberOfClickInCapcha;
    }

    public final class viewHolder extends RecyclerView.ViewHolder {
        ImageView selected_Item;
        ImageView unSelected_Item;

        public viewHolder(View itemView) {
            super(itemView);
            selected_Item=(ImageView)itemView.findViewById(R.id.img_user) ;
            unSelected_Item=(ImageView) itemView.findViewById(R.id.img_user_selected);

        }

        public final void selected_Item() {
            selected_Item.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {
                    unSelected_Item.setVisibility(View.VISIBLE);
                    validCapcha+=arrayIconsKeys[getPosition()];
                    numberOfClickInCapcha+=1;
                }
            }));
        }

        public final void unSelected_Item() {
            unSelected_Item.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {
                    unSelected_Item.setVisibility(View.INVISIBLE);
                    validCapcha-=arrayIconsKeys[getPosition()];
                    numberOfClickInCapcha-=1;
                }
            }));
        }

    }

}

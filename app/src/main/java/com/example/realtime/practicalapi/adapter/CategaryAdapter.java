package com.example.realtime.practicalapi.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.realtime.practicalapi.OnItemClick;
import com.example.realtime.practicalapi.R;
import com.example.realtime.practicalapi.model.Categary;
import com.preference.PowerPreference;

import java.util.ArrayList;
import java.util.List;


public class CategaryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_DATA = 0;
    private Activity activity;
    List<Categary> categary;
    Context context;
    private OnItemClick mCallback;



    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imgPhoto;
        public TextView tvName;

        public ItemHolder(CategaryAdapter sP_Tool_UserAdapterUser, View view) {
            super(view);
            this.imgPhoto = (ImageView) view.findViewById(R.id.cimg);
            this.tvName = (TextView) view.findViewById(R.id.cName);
        }
    }

    public CategaryAdapter(Activity activity2,List<Categary> categary,OnItemClick listener) {
        this.activity = activity2;
        this.categary = categary;
        this.mCallback = listener;
    }

    @Override
    public int getItemCount() {
        return this.categary.size();
    }

    @Override
    public int getItemViewType(int i) {
        return this.ITEM_DATA;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        Categary user = this.categary.get(i);
        if (itemViewType == this.ITEM_DATA) {


            ItemHolder itemHolder = (ItemHolder) viewHolder;
            context = itemHolder.itemView.getContext();

            ((RequestBuilder) Glide.with(context).load(user.getCatimg()).centerCrop()).into(itemHolder.imgPhoto);

            itemHolder.tvName.setText(user.getCatname());

            itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onClick(user.getCatid());
                    Toast.makeText(activity, user.getCatid(), Toast.LENGTH_SHORT).show();
                    PowerPreference.getDefaultFile().putString("CatId",user.getCatid());
                    PowerPreference.getDefaultFile().putInt("Page",1);

                }
            });
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == this.ITEM_DATA) {
            return new ItemHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cat, viewGroup, false));
        }
        return null;
    }
}

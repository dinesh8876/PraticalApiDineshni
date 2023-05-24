package com.example.realtime.practicalapi.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
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
import com.example.realtime.practicalapi.OnVideoItemClick;
import com.example.realtime.practicalapi.R;
import com.example.realtime.practicalapi.model.Categary;
import com.example.realtime.practicalapi.model.video.VideoList;
import com.preference.PowerPreference;

import java.util.ArrayList;
import java.util.List;


public class CatVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_DATA = 0;
    private Activity activity;
    ArrayList<VideoList> categary;
    Context context;
    private OnVideoItemClick mCallback;



    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView effectThumb;
        public TextView effectName;

        public ItemHolder(CatVideoAdapter sP_Tool_UserAdapterUser, View view) {
            super(view);
            this.effectThumb = (ImageView) view.findViewById(R.id.vimg);
            this.effectName = (TextView) view.findViewById(R.id.vName);
        }
    }

    public CatVideoAdapter(Activity activity2,ArrayList<VideoList> categary,OnVideoItemClick listener) {
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
        VideoList user = this.categary.get(i);
        if (itemViewType == this.ITEM_DATA) {


            ItemHolder itemHolder = (ItemHolder) viewHolder;
            context = itemHolder.itemView.getContext();

            ((RequestBuilder) Glide.with(context).load(user.getEffectThumb()).centerCrop()).into(itemHolder.effectThumb);

            itemHolder.effectName.setText(user.getEffectName());

            itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onVideoClick(user.getEffectZip(), user.getEffectName());
                    Log.e("EffectZip", user.getEffectZip() );

                }
            });


        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == this.ITEM_DATA) {
            return new ItemHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_vide, viewGroup, false));
        }
        return null;
    }
}

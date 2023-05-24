package com.example.realtime.practicalapi.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.example.realtime.practicalapi.OnFolderItemClick;
import com.example.realtime.practicalapi.OnItemClick;
import com.example.realtime.practicalapi.OnVideoItemClick;
import com.example.realtime.practicalapi.R;
import com.example.realtime.practicalapi.model.ImageFolder;
import com.example.realtime.practicalapi.model.video.VideoList;

import java.util.ArrayList;


public class ImgFolderGetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_DATA = 0;
    private Activity activity;
    ArrayList<ImageFolder> categary;
    Context context;
    private OnFolderItemClick mCallback;



    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView folName;

        public ItemHolder(ImgFolderGetAdapter sP_Tool_UserAdapterUser, View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
            this.folName = (TextView) view.findViewById(R.id.textView);
        }
    }

    public ImgFolderGetAdapter(Activity activity2, ArrayList<ImageFolder> categary, OnFolderItemClick listener) {
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
        ImageFolder user = this.categary.get(i);
        if (itemViewType == this.ITEM_DATA) {


            ItemHolder itemHolder = (ItemHolder) viewHolder;
            context = itemHolder.itemView.getContext();

            ((RequestBuilder) Glide.with(context).load(user.getImage()).centerCrop()).into(itemHolder.imageView);
            Bitmap image = user.getImage();

            if (image != null){
                itemHolder.imageView.setImageBitmap(image);
            }
            else {
                // If no image is provided, display a folder icon.
                itemHolder.imageView.setImageResource(R.drawable.folder);
            }
            itemHolder.folName.setText(user.getPath());

            itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onClick(i);

                }
            });


        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == this.ITEM_DATA) {
            return new ItemHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_img_folder, viewGroup, false));
        }
        return null;
    }
}

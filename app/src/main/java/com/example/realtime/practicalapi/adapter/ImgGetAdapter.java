package com.example.realtime.practicalapi.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
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
import com.example.realtime.practicalapi.OnFolderItemClick;
import com.example.realtime.practicalapi.R;
import com.example.realtime.practicalapi.model.ImageFolder;
import com.example.realtime.practicalapi.model.ImageGet;

import java.util.ArrayList;


public class ImgGetAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int ITEM_DATA = 0;
    private Activity activity;
    ArrayList<ImageGet> categary;
    Context context;



    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView folName;

        public ItemHolder(ImgGetAdapter sP_Tool_UserAdapterUser, View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.imageView);
            this.folName = (TextView) view.findViewById(R.id.textView);
        }
    }

    public ImgGetAdapter(Activity activity2, ArrayList<ImageGet> categary) {
        this.activity = activity2;
        this.categary = categary;

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
        ImageGet user = this.categary.get(i);
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
            itemHolder.folName.setText(user.getName());

            itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(activity, user.getName(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == this.ITEM_DATA) {
            return new ItemHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_img, viewGroup, false));
        }
        return null;
    }
}

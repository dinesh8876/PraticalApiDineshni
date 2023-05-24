package com.example.realtime.practicalapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.realtime.practicalapi.adapter.ImgFolderGetAdapter;
import com.example.realtime.practicalapi.adapter.ImgGetAdapter;
import com.example.realtime.practicalapi.databinding.ActivityGallaryBinding;
import com.example.realtime.practicalapi.model.ImageFolder;
import com.example.realtime.practicalapi.model.ImageGet;
import com.example.realtime.practicalapi.utils.BitmapHelper;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class GallaryActivity extends AppCompatActivity implements OnFolderItemClick {
ActivityGallaryBinding binding;
ArrayList<ImageFolder> imgFolder = new ArrayList<>();
ArrayList<ImageGet> allImg= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGallaryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.rvfolder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

        imgFolder = createGridItems(Environment.getExternalStorageDirectory().toString()+ File.separator + Environment.DIRECTORY_DCIM);
        ImgFolderGetAdapter adapter = new ImgFolderGetAdapter(this, imgFolder,this::onClick);

        // Set the grid adapter
        binding.rvfolder.setAdapter(adapter);
        binding.rvFolderImage.setLayoutManager(new GridLayoutManager(this, 3));


    }
    private ArrayList<ImageFolder> createGridItems(String directoryPath) {
        ArrayList<ImageFolder> items = new ArrayList<ImageFolder>();

        // List all the items within the folder.
        File[] files = new File(directoryPath).listFiles(new ImageFileFilter());
        for (File file : files) {

            // Add the directories containing images or sub-directories
            if (file.isDirectory()
                    && file.listFiles(new ImageFileFilter()).length > 0) {

                items.add(new ImageFolder(file.getAbsolutePath(), true, null));
            }
            // Add the images
            else {
                Bitmap image = BitmapHelper.decodeBitmapFromFile(file.getAbsolutePath(),
                        50,
                        50);
                items.add(new ImageFolder(file.getAbsolutePath(), false, image));

            }
        }

        return items;
    }

    private ArrayList<ImageGet> AllImgGet(String directoryPath) {
        ArrayList<ImageGet> items = new ArrayList<ImageGet>();

        // List all the items within the folder.
        File[] files = new File(directoryPath).listFiles(new ImageFileFilter());
        for (File file : files) {

            // Add the directories containing images or sub-directories
            if (file.isDirectory()
                    && file.listFiles(new ImageFileFilter()).length > 0) {

                items.add(new ImageGet(file.getName(), true, null));
            }
            // Add the images
            else {
                Bitmap image = BitmapHelper.decodeBitmapFromFile(file.getAbsolutePath(),
                        50,
                        50);
                items.add(new ImageGet(file.getName(), false, image));

            }
        }

        return items;
    }
    /**
     * Checks the file to see if it has a compatible extension.
     */
    private boolean isImageFile(String filePath) {
        if (filePath.endsWith(".jpg") || filePath.endsWith(".png"))
        // Add other formats as desired
        {
            return true;
        }
        return false;
    }





    @Override
    public void onClick(int position) {
        allImg.clear();
            setFolderImageAdapter(imgFolder.get(position).getPath());


    }


    private class ImageFileFilter implements FileFilter {

        @Override
        public boolean accept(File file) {
            if (file.isDirectory()) {
                return true;
            }
            else if (isImageFile(file.getAbsolutePath())) {
                return true;
            }

            return false;
        }
    }



    private void setFolderImageAdapter(String path) {
        // Create a new grid adapter
        allImg = AllImgGet(path);
        if(allImg==null){

            return;
        }else{
            ImgGetAdapter adapter = new ImgGetAdapter(this, allImg);

            // Set the grid adapter
            binding.rvFolderImage.setAdapter(adapter);
        }


    }

}
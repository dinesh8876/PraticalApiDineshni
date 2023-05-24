package com.example.realtime.practicalapi.model;

import android.graphics.Bitmap;

public class ImageGet {


        private String name;
        private boolean isDirectory;
        private Bitmap image;


        public ImageGet(String name, boolean isDirectory, Bitmap image) {
            this.name = name;
            this.isDirectory = isDirectory;
            this.image = image;
        }


        public String getName() {
            return name;
        }


        public boolean isDirectory() {
            return isDirectory;
        }


        public Bitmap getImage() {
            return image;
        }

}

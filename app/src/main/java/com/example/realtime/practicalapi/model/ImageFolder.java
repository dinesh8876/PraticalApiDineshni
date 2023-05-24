package com.example.realtime.practicalapi.model;

import android.graphics.Bitmap;

public class ImageFolder {


        private String path;
        private boolean isDirectory;
        private Bitmap image;


        public ImageFolder(String path, boolean isDirectory, Bitmap image) {
            this.path = path;
            this.isDirectory = isDirectory;
            this.image = image;
        }


        public String getPath() {
            return path;
        }


        public boolean isDirectory() {
            return isDirectory;
        }


        public Bitmap getImage() {
            return image;
        }

}

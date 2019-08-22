package com.example.instagram.Utils;

import android.os.Environment;

public class FilePaths {

    //"Storage/emulated/0"
    public String ROOT_DIR = Environment.getExternalStorageDirectory().getPath();

    public String PICTURE = ROOT_DIR + "/Pictures";
    public String CAMERA = ROOT_DIR + "/DCIM/camera";

    public String FIREBASE_IMAGE_STORAGE =  "Photos/users/";
}

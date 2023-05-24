package com.example.realtime.practicalapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.realtime.practicalapi.adapter.CatVideoAdapter;
import com.example.realtime.practicalapi.adapter.CategaryAdapter;
import com.example.realtime.practicalapi.api.RetrofitAPI;
import com.example.realtime.practicalapi.api.RetrofitClient;
import com.example.realtime.practicalapi.databinding.ActivityMainBinding;
import com.example.realtime.practicalapi.model.Categary;
import com.example.realtime.practicalapi.model.MainList;
import com.example.realtime.practicalapi.model.video.MainVideoList;
import com.example.realtime.practicalapi.model.video.VideoList;
import com.preference.PowerPreference;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.EncryptionMethod;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClick , OnVideoItemClick{
    ActivityMainBinding binding;
    CategaryAdapter adapter;
    CatVideoAdapter adapter2;
    String CatId;
    int Page;
    private ProgressBar loadingPB;
    boolean isReached = false;
    int limit = 4, preLast;
    final Handler handler = new Handler();
    ArrayList<VideoList> videoList = new ArrayList<>();
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;

    private boolean isLoading;
    int vissibleThreshold = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                EXTERNAL_STORAGE_PERMISSION_CODE);
        binding.rvCategary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

        GridLayoutManager manager = new GridLayoutManager(this, 2);
        binding.rvVideo.setLayoutManager(manager);


        adapter2 = new CatVideoAdapter(MainActivity.this, videoList,MainActivity.this::onVideoClick);
        binding.rvVideo.setAdapter(adapter2);
        // on below line we are executing our method.
        RetrofitClient.getInstance().getApi().createPost().enqueue(new Callback<MainList>() {
            @Override
            public void onResponse(Call<MainList> call, Response<MainList> response) {
                // this method is called when we get response from our api.
                Log.e("Response","<<<<<<<<<<"+response);
                adapter = new CategaryAdapter(MainActivity.this, response.body().getData(), MainActivity.this::onClick);
                binding.rvCategary.setAdapter(adapter);
                CatId = response.body().getData().get(0).getCatid();
                GetVideo();
            }

            @Override
            public void onFailure(Call<MainList> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                Log.e("ErrorT", String.valueOf(t));
            }
        });




        binding.rvVideo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!isReached) {

                    int totalItemCOunt = manager.getItemCount();
                    int lastVissibleItemPOs = manager.findLastVisibleItemPosition();
                    if (!isLoading && totalItemCOunt <= lastVissibleItemPOs + vissibleThreshold) {
                        if (!isLoading) {
                            isLoading = true;
                            GetVideo();
                        }
                    }


                }
            }
        });

    }


    @Override
    public void onClick(String value) {
        CatId = value;
        Page = 1;
        videoList.clear();
        isReached=false;
        adapter2.notifyDataSetChanged();
        GetVideo();
    }

    public void onVideoClick(String value,String name) {
      String Zip = value;
        File sd = new File(Environment.getExternalStorageDirectory().toString()+ File.separator + Environment.DIRECTORY_DOWNLOADS+File.separator+getApplicationName(MainActivity.this));
        if(sd.exists()){

        }else {
            sd.mkdir();
        }
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    downloadZipFile(Zip,Environment.getExternalStorageDirectory().toString()+ File.separator + Environment.DIRECTORY_DOWNLOADS+File.separator+getApplicationName(MainActivity.this)+File.separator+name+".zip");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();


        Toast.makeText(this, Zip, Toast.LENGTH_SHORT).show();

    }

    public void GetVideo() {

        Log.e("TAG", "yess");
        Page++;

        RetrofitClient.getInstance().getApi().getVideo(Page, Integer.parseInt(CatId)).enqueue(new Callback<MainVideoList>() {
            @Override
            public void onResponse(Call<MainVideoList> call, Response<MainVideoList> response) {

                if (response.body() != null) {
                    if (Page >= response.body().getTotalPage()) {
                        isReached = true;
                    } else {
                        videoList.addAll(response.body().getRows());
                        adapter2.notifyDataSetChanged();
                        isLoading = false;
                    }
                }

            }

            @Override
            public void onFailure(Call<MainVideoList> call, Throwable t) {
                // setting text to our text view when
                // we get error response from API.
                Log.e("ErrorT", String.valueOf(t));
            }
        });
    }

    public void downloadZipFile(String urlStr, String destinationFilePath) {
        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(urlStr);

            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.d("downloadZipFile", "Server ResponseCode=" + connection.getResponseCode() + " ResponseMessage=" + connection.getResponseMessage());
            }

            // download the file
            input = connection.getInputStream();
            File f = new File(destinationFilePath);

                Log.d("downloadZipFile", "destinationFilePath=" + destinationFilePath);
                new File(destinationFilePath).createNewFile();
                output = new FileOutputStream(destinationFilePath);

                byte data[] = new byte[4096];
                int count;
                while ((count = input.read(data)) != -1) {
                    output.write(data, 0, count);
                }

            net.lingala.zip4j.ZipFile zipFile = new ZipFile(destinationFilePath, "pU#w]<GqE2Z$)m=H".toCharArray());
            try {
                zipFile.extractAll(Environment.getExternalStorageDirectory().toString()+ File.separator + Environment.DIRECTORY_DOWNLOADS+File.separator+getApplicationName(MainActivity.this));
            } catch (ZipException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("exption", "downloadZipFile: ",e );
            return;
        } finally {
            try {
                if (output != null) output.close();
                if (input != null) input.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("exption", "downloadZipFile: ",e );

            }

            if (connection != null) connection.disconnect();
        }

        File f = new File(destinationFilePath);

        Log.d("downloadZipFile", "f.getParentFile().getPath()=" + f.getParentFile().getPath());
        Log.d("downloadZipFile", "f.getName()=" + f.getName().replace(".zip", ""));
    }

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }




}
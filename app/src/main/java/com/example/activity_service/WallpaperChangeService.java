package com.example.activity_service;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class WallpaperChangeService extends Service implements Runnable {

    private int wallpaperId[] = {R.drawable.img1, R.drawable.img2};

    private int waktu;
    private int FLAG=0;
    private Thread t;
    private Bitmap gmbr1,gmbr2;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent,flags,startId);

        Bundle bundle=intent.getExtras();
        waktu = bundle.getInt("durasi");
        gmbr1= BitmapFactory.decodeResource(getResources(),wallpaperId[0]);
        gmbr2= BitmapFactory.decodeResource(getResources(),wallpaperId[1]);

        t = new Thread(WallpaperChangeService.this);
        t.start();
        return START_STICKY_COMPATIBILITY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        System.exit(0);
    }

    @Override
    public void run() {
        WallpaperManager myWallpaper;
        myWallpaper = WallpaperManager.getInstance(this);
        try {
            while (true){
                if (FLAG==0){
                    myWallpaper.setBitmap(gmbr1);
                    FLAG++;
                }else{
                    myWallpaper.setBitmap(gmbr2);
                    FLAG++;
                }
                Thread.sleep(100*waktu);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

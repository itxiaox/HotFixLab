package com.itxiaox.andfixlib;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import androidx.annotation.Nullable;

import java.io.File;

/**
 * 1.检查patch文件，2.下载patch文件，3，加载下载好的patch文件
 */
public class AndfixService extends Service {

    private static final int UPDATE_PATCH = 0X02;
    private static final int DOWNLOAD_PATCH = 0X01;
    private String mPatchFileDir;
    private String mPatchFile;
    private  Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case UPDATE_PATCH:
                      checkUpdatePatch();
                   break;
               case DOWNLOAD_PATCH:
                   downloadPatch();
                   break;
           }
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        handler.sendEmptyMessage(UPDATE_PATCH);

        return START_NOT_STICKY;//被系统回收后，不会自动重启
    }

    //完成文件目录的构造
    private void init() {
        mPatchFileDir = getExternalCacheDir().getAbsolutePath()+"/apatch/";
        File pathDir = new File(mPatchFileDir);
        try{
            if (pathDir==null || !pathDir.exists()){
                pathDir.mkdirs();
            }
        }catch(Exception e){
            e.printStackTrace();
            stopSelf();
        }

    }


    private  void checkUpdatePatch(){
        //询求补丁文件是否需要更新

    }

    private void downloadPatch() {

    }

}

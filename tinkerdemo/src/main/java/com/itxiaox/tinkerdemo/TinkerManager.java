package com.itxiaox.tinkerdemo;

import android.content.Context;
import android.util.Log;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.loader.app.ApplicationLike;

/**
 * 对Tinker所有的api做一层封装
 */
public class TinkerManager {
    private static boolean isInstance = false;

    private static final String TAG = "TinkerManager";
    private static ApplicationLike mAppLike;

    /**
     * 完成Tinker的初始化
     * @param applicationLike
     */
    public static void installTinker(ApplicationLike applicationLike){
        mAppLike = applicationLike;
        if (isInstance){
            return;
        }
        TinkerInstaller.install(mAppLike);//完成Tinker初始化
        isInstance = true;
    }


    /**
     * 加载补丁文件
     * @param path
     */
    public static void loadPatch(String path){
        if(Tinker.isTinkerInstalled()){
            TinkerInstaller.setLogIml(new TinkerLog.TinkerLogImp() {
                @Override
                public void v(String tag, String msg, Object... obj) {
                    Log.v(TAG, "v: "+msg);
                }

                @Override
                public void i(String tag, String msg, Object... obj) {
                    Log.i(TAG, "i: "+msg);
                }

                @Override
                public void w(String tag, String msg, Object... obj) {
                    Log.w(TAG, "w: "+msg);
                }

                @Override
                public void d(String tag, String msg, Object... obj) {
                    Log.d(TAG, "d: "+msg);
                }

                @Override
                public void e(String tag, String msg, Object... obj) {
                    Log.e(TAG, "e: "+msg);
                }

                @Override
                public void printErrStackTrace(String tag, Throwable tr, String format, Object... obj) {

                }
            });
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(),path);
        }
    }

    /**
     * 通过ApplicationLike获取Context
     * @return
     */
    private static Context getApplicationContext(){
        if (mAppLike!=null){
            return mAppLike.getApplication().getApplicationContext();
        }
        return null;
    }

}

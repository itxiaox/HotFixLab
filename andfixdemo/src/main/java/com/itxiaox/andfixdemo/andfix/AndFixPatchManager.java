package com.itxiaox.andfixdemo.andfix;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.IOException;

public class AndFixPatchManager {

    private static AndFixPatchManager instance;


    private static PatchManager patchManager;
    private AndFixPatchManager() {
    }


    /**
     * 初始AndFix初始化方法
     *  建议在Application 的onCreate中调用
     * @param context
     */
    public void initPatch(Context context) {
        patchManager = new PatchManager(context);
        patchManager.init(getVersionName(context));
        patchManager.loadPatch();
    }


    /**
     * 加载我们的patch文件
     * @param path
     */
    public void addPatch(String path){
        try {
             if (patchManager!=null){
                    patchManager.addPatch(path);
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AndFixPatchManager getInstance() {
        if (instance == null) {
            synchronized (AndFixPatchManager.class) {
                if (instance == null) {
                    instance = new AndFixPatchManager();
                }
            }
        }
        return instance;
    }

    private static String getVersionName(Context context) {
        String versionName = "1.0.0";
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}

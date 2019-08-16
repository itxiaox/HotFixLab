package com.itxiaox.andfixdemo;

import android.app.Application;

import com.itxiaox.andfixdemo.andfix.AndFixPatchManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //完成AndFix模块的初始化
        initAndFix();
    }

    private void initAndFix() {
        AndFixPatchManager.getInstance().initPatch(this);
    }
}

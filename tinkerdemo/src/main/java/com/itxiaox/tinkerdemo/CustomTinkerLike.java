package com.itxiaox.tinkerdemo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * 通过@DefaultLifeCycle 生成application类
 */
@DefaultLifeCycle(application = "com.itxiao.tinkerdemo.MyTinkerApplication",
        flags = ShareConstants.TINKER_ENABLE_ALL,
loadVerifyFlag =  false)
public class CustomTinkerLike extends ApplicationLike {

    public CustomTinkerLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        //使应用支持分包
        MultiDex.install(base);
        //tinker的初始化
        TinkerManager.installTinker(this);
    }
}

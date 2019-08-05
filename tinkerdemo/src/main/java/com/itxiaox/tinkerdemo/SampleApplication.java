package com.itxiaox.tinkerdemo;

import android.app.Application;
import android.util.Log;

import com.tencent.tinker.entry.ApplicationLike;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike;
import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback;

import java.util.HashMap;

public class SampleApplication extends Application {
    private static final String TAG = "SampleApplication";
    ApplicationLike applicationLike;
    @Override
    public void onCreate() {
        super.onCreate();
        //我们可以从这里获取Tinker加载过程的信息
        applicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike();

        // 初始化TinkerPatch SDK, 更多配置可参照API章节中的,初始化SDK
        TinkerPatch.init(applicationLike)
                .reflectPatchLibrary()
                .setPatchRollbackOnScreenOff(true)
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3);
        Log.d(TAG, "Current patch version is " + TinkerPatch.with().getPatchVersion());
        // 每隔3个小时(通过setFetchPatchIntervalByHours设置)去访问后台时候有更新,通过handler实现轮训的效果
        TinkerPatch.with().fetchPatchUpdateAndPollWithInterval();

        TinkerPatch.with().fetchDynamicConfig(new ConfigRequestCallback() {
            @Override
            public void onSuccess(HashMap<String, String> hashMap) {

            }

            @Override
            public void onFail(Exception e) {

            }
        },false);
    }
}

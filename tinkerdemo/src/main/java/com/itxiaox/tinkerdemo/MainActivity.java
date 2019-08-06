package com.itxiaox.tinkerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.tinker.lib.util.TinkerLog;
import com.tinkerpatch.sdk.TinkerPatch;
import com.tinkerpatch.sdk.server.callback.ConfigRequestCallback;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TinkerPatch.with().fetchPatchUpdate(true);
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, ""+ErrorUtils.getSum(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void fix2(View view) {

        TinkerPatch.with().fetchDynamicConfig(new ConfigRequestCallback() {
            @Override
            public void onSuccess(HashMap<String, String> hashMap) {
                TinkerLog.w(TAG, "request config success, config:" + hashMap);
            }

            @Override
            public void onFail(Exception e) {
                TinkerLog.w(TAG, "request config failed, exception:" + e);
            }
        },true);
    }

    public void clear(View view) {
        TinkerPatch.with().cleanAll();
    }

    /**
     * 二次启动
     */
    private void jumpToNext(Context getApplicationContext) {
//        TinkerLog.i(TAG,"跳转二次启动------");
        restartApplication();
//        TinkerLog.i(TAG,"关闭进程------");
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private void restartApplication() {
        final Intent intent = getPackageManager().
                getLaunchIntentForPackage(getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void goSecondActivity(View view) {
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}

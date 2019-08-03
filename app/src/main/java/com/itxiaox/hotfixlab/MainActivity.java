package com.itxiaox.hotfixlab;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if(checkSelfPermission(perms[0]) == PackageManager.PERMISSION_GRANTED){
                requestPermissions(perms,200);
            }
        }
    }

    public void jump(View view){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}

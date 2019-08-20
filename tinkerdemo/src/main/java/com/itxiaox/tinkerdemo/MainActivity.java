package com.itxiaox.tinkerdemo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private  final String FILE_END = ".apatch";
    private String patchPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patchPath = getExternalCacheDir().getAbsolutePath() +"/tapatch/";
        File file = new File(patchPath);
        if (file == null || !file.exists()){
            file.mkdirs();
        }
        Log.i(TAG, "onCreate: patchPath="+patchPath);

    }

    private String getPatchName(){
        return patchPath.concat("hotfix").concat(FILE_END);

    }

    public void loadPatch(View view) {
        TinkerManager.loadPatch(getPatchName());
    }
}

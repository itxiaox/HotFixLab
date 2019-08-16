package com.itxiaox.andfixdemo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.itxiaox.andfixdemo.andfix.AndFixPatchManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private  final String FILE_END = ".apatch";
    private String patchPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        patchPath = getExternalCacheDir().getAbsolutePath() +"/apatch/";
        File file = new File(patchPath);
        if (file == null || !file.exists()){
            file.mkdirs();
        }


    }

    public void createBug(View view) {
        Utils.printLog();
    }

    public void fixBug(View view) {
        AndFixPatchManager.getInstance().addPatch(getPatchName());
    }

    private String getPatchName(){
       return    patchPath.concat("andfix").concat(FILE_END);

    }
}

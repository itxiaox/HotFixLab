package com.itxiaox.hotfixlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.itxiaox.hotfixlab.error.ParamsSort;
import com.itxiaox.hotfixlib.Constants;
import com.itxiaox.hotfixlib.FileUtils;
import com.itxiaox.hotfixlib.FixDexUtils;

import java.io.File;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void show(View view){
        ParamsSort.match(this);
    }

    public void fix(View view){
        //将下载的修复包，复制到私有目录，然后再做解压工作

        //不模拟网络下载，直接将修复包放到SDCard
        File sourceFile = new File(Environment.getExternalStorageDirectory(), Constants.DEX_NAME);
        //目标路径 ，私有目录
        File targetFile = new File(getDir(Constants.DEX_DIR,
                Context.MODE_PRIVATE).getAbsolutePath() + File.separator +Constants.DEX_NAME);

        //如果存在之前修复过的dex
        if(targetFile.exists()){
            targetFile.delete();
            Toast.makeText(SecondActivity.this, "删除dex文件完成", Toast.LENGTH_SHORT).show();
        }
        try{
            FileUtils.copyFile(sourceFile,targetFile);
            Toast.makeText(SecondActivity.this, "复制dex文件完成", Toast.LENGTH_SHORT).show();
            //开始修复dex
            FixDexUtils.loadFixedDex(this);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

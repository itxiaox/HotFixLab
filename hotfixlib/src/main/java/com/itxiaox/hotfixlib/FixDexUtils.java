package com.itxiaox.hotfixlib;

import android.content.Context;

import java.io.File;
import java.util.HashSet;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class FixDexUtils {

    //class2.dex, 和 classes3.dex 同时需要修复

    private static HashSet<File> loadedDex = new HashSet<>();

    static {
        //修复之前清理集合
        loadedDex.clear();
    }
    public static void loadFixedDex(Context context) {

        File fileDir = context.getDir(Constants.DEX_DIR,Context.MODE_PRIVATE);
        //循环私有目录中的所有文件

        File[] listFile = fileDir.listFiles();

        for (File file : listFile) {
            if ( file.getName().endsWith(Constants.DEX_SUFFIX)
                    &&!"classes.dex".equals(file.getName()))//不为主包，修复包不能为主包
            {

                //找到了修复包dex文件，加入到集合
              loadedDex.add(file);
            }
        }

        //模拟系统类加载
        createDexClassLoader(context,fileDir);

    }
    //创建加载补丁的DexClassLoader类加载器
    private static void createDexClassLoader(Context context, File fileDir) {

        //创建解压目录
        String optimizedDir = fileDir.getAbsolutePath() + File.separator + "opt_dex";
        //创建目录
        File fopt = new File(optimizedDir);
        if (!fopt.exists()){
            //创建这个多级目录
            fopt.mkdirs();
        }

        for(File dex : loadedDex){
            //自有的类加载器
            DexClassLoader dexClassLoader = new DexClassLoader(dex.getAbsolutePath(),optimizedDir,
                    null,context.getClassLoader());
            //每循环一次，修复一次（插桩）
            hotfix(context,dexClassLoader );
        }


    }

    private static void hotfix(Context context, DexClassLoader classLoader) {

        //获取系统的PathClassLoader
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();

        try{
            //1. 获取自有的dexElements数组
            Object myElements  = ReflectUtils.getDexElements(ReflectUtils.getPathList(pathClassLoader));//先拿到PathList，再拿到elements数组

            //2. 获取系统的dexElements数据
            Object sysElements  = ReflectUtils.getDexElements(ReflectUtils.getPathList(classLoader));

            //3.合并并生成新的dexElments数组
            Object dexElements = ArrayUtils.combineArray(myElements,sysElements);

            //4.获取系统的pathList属性
            Object sysPathList = ReflectUtils.getPathList(pathClassLoader);

            //5.通过反射技术，将新的dexElements数组赋值给系统的pathList
            ReflectUtils.setField(sysPathList,sysPathList.getClass(),dexElements);
        }catch(Exception e){
            e.printStackTrace();
        }


    }
}

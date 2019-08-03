package com.itxiaox.hotfixlib;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {


    /**
     * 复制文件
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     * @throws IOException
     */
    public static void copyFile(File sourceFile,File targetFile) throws IOException {
      //新建文件输入流并对它进行缓冲
      FileInputStream fis = new FileInputStream(sourceFile);

      BufferedInputStream bis = new BufferedInputStream(fis);

      //新建文件输出流并对它进行缓冲

      FileOutputStream fos = new FileOutputStream(targetFile);

      BufferedOutputStream bos = new BufferedOutputStream(fos);
      //缓冲数组
      byte[] b = new byte[1024 * 5];
      int len;
      while ((len = bis.read(b))!=-1){
          bos.write(b,0,len);
      }
      //刷新此缓冲的输出流
      bos.flush();
      //关闭流
        bis.close();
        bos.close();
        fos.close();
        fis.close();
    }
}

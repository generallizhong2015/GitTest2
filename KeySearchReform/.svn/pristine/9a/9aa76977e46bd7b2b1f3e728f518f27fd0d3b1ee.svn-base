package com.gsoft.keyhandover.util;

import com.gsoft.keyhandover.BaseApplication;

import java.io.File;

public class FileUtil {

    /**
     * 初始化储存地址
     */
    public static String getCacheDir() {
        String sCacheDir = "";
        if (BaseApplication.instance().getApplicationContext().getExternalCacheDir() != null && isExistSDCard()) {
            sCacheDir = BaseApplication.instance().getApplicationContext().getExternalCacheDir().toString();
        } else {
            sCacheDir = BaseApplication.instance().getApplicationContext().getCacheDir().toString();
        }
        return sCacheDir;
    }

    public static boolean isExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public static boolean isFileExists(String filename) {
        try {
            File file = new File(getCacheDir() + "/" + filename);
            if (file.exists()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static boolean isFileExistsQuanMing(String filename) {
        try {
            File file = new File(filename);
            if (file.exists()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }


    public static boolean delete(File file) {
        if (file.isFile()) {
            return file.delete();
        }

        if (file.isDirectory()) {
            File[] childFiles = file.listFiles();
            if (childFiles == null || childFiles.length == 0) {
                return file.delete();
            }

            for (File childFile : childFiles) {
                delete(childFile);
            }
            return file.delete();
        }
        return false;
    }

    //删除文件
    public static void deleFile(File file) {
        file.delete();
    }
}

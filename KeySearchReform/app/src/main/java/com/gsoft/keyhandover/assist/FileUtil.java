package com.gsoft.keyhandover.assist;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.gsoft.keyhandover.util.L;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class FileUtil {

    static List<String> imgtypes = Arrays.asList("png", "jpg");
    static List<String> videoypes = Arrays.asList("mp4", "rmvb", "avi", "3gp");
    static List<String> wpsypes = Arrays.asList("doc", "ppt", "xls");

    //项目文件根目录
    private static String FILEROOT;
    //应用程序的图片的缓存
    public static final String FILEDIR = "/gzzj";

    public enum FileEnum {
        IMG,
        VIDEO,
        WPS
    }

    public static FileEnum getFileEnum(String s) {
        if (imgtypes.contains("s")) {
            return FileEnum.IMG;
        }
        if (videoypes.contains("s")) {
            return FileEnum.VIDEO;
        }
        if (wpsypes.contains(s)) {
            return FileEnum.WPS;
        }
        return FileEnum.IMG;
    }

    public static boolean createFolder(String filePath) {
        String folderName = getFolderName(filePath);
        if (folderName == null || folderName.length() == 0 || folderName.trim().length() == 0) {
            return false;
        }
        File folder = new File(folderName);
        if (folder.exists()) {
            return true;
        } else {
            return folder.mkdirs();
        }
    }

    public static String getFolderName(String filePath) {
        if (filePath == null || filePath.length() == 0 || filePath.trim().length() == 0) {
            return filePath;
        }
        int filePos = filePath.lastIndexOf(File.separator);
        return (filePos == -1) ? "" : filePath.substring(0, filePos);
    }


    public static void openImage(Context mContext, String imagePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(imagePath));
        intent.setDataAndType(uri, "image/*");
        mContext.startActivity(intent);
    }

    public static void openVideo(Context mContext, String videoPath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(videoPath));
        intent.setDataAndType(uri, "video/*");
        mContext.startActivity(intent);
    }

    public static void openURL(Context mContext, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }

    public static void downloadFile(Context context, String fileurl) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileurl));
        request.setDestinationInExternalPublicDir("/Download/", fileurl.substring(fileurl.lastIndexOf("/") + 1));
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }


    public static FileEnum getFileTypeFromUrl(String fileurl) {
        if (fileurl != null) {
            return getFileEnum(fileurl.substring(fileurl.lastIndexOf(".") + 1));
        }
        return FileEnum.IMG;
    }


    public static String getFileNameFromUrl(String fileurl) {
        if (fileurl != null) {
            return fileurl.substring(fileurl.lastIndexOf("/") + 1);
        }
        return fileurl;
    }


    /**
     * 获取sd卡的文件路径
     */
    public static String getSdPath(Context context) {
        if (checkSdCard()) {
            FILEROOT = Environment.getExternalStorageDirectory() + FILEDIR;
        } else {
            FILEROOT = (context.getFilesDir().getAbsolutePath() + FILEDIR);
        }
        return FILEROOT;
    }


    public static boolean checkSdCard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //sd卡可用
            return true;
        } else {
            //当前sd卡不可用
            return false;
        }
    }

    public static boolean isFileExists(Context context, String fileurl) {
        File file = new File(getSdPath(context) + "/" + fileurl);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public static void openAnyFile(Context mContext, String url) {
        try {
            String suffix = "";
            if (url != null) {
                suffix = url.substring(url.lastIndexOf(".") + 1);
            }
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.addCategory("android.intent.category.DEFAULT");
            Uri uri = Uri.fromFile(new File(url));
            intent.setDataAndType(uri, getTypeFile(suffix));
            mContext.startActivity(intent);
        } catch (Exception e) {
            return;
        }
    }

//    public static void openImage(Context mContext, String imagePath) {
//        Intent intent = new Intent("android.intent.action.VIEW");
//        intent.addCategory("android.intent.category.DEFAULT");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        Uri uri = Uri.fromFile(new File(imagePath));
//        intent.setDataAndType(uri, "image/*");
//        mContext.startActivity(intent);
//    }

    private static String getTypeFile(String suffix) {
        if (suffix.equals("3gp")) return "video/3gpp";
        else if (suffix.equals("apk")) return "application/vnd.android.package-archive";
        else if (suffix.equals("asf")) return "video/x-ms-asf";
        else if (suffix.equals("avi")) return "video/x-msvideo";
        else if (suffix.equals("bin")) return "application/octet-stream";
        else if (suffix.equals("bmp")) return "image/bmp";
        else if (suffix.equals("c")) return "text/plain";
        else if (suffix.equals("class")) return "application/octet-stream";
        else if (suffix.equals("conf")) return "text/plain";
        else if (suffix.equals("cpp")) return "text/plain";
        else if (suffix.equals("doc")) return "application/msword";
        else if (suffix.equals("docx"))
            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        else if (suffix.equals("xls")) return "application/vnd.ms-excel";
        else if (suffix.equals("xlsx"))
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        else if (suffix.equals("exe")) return "application/octet-stream";
        else if (suffix.equals("gif")) return "image/gif";
        else if (suffix.equals("gtar")) return "application/x-gtar";
        else if (suffix.equals("gz")) return "application/x-gzip";
        else if (suffix.equals("h")) return "text/plain";
        else if (suffix.equals("htm")) return "text/html";
        else if (suffix.equals("html")) return "text/html";
        else if (suffix.equals("jar")) return "application/java-archive";
        else if (suffix.equals("java")) return "text/plain";
        else if (suffix.equals("jpeg")) return "image/jpeg";
        else if (suffix.equals("jpg")) return "image/jpeg";
        else if (suffix.equals("js")) return "application/x-javascript";
        else if (suffix.equals("log")) return "text/plain";
        else if (suffix.equals("m3u")) return "audio/x-mpegurl";
        else if (suffix.equals("m4a")) return "audio/mp4a-latm";
        else if (suffix.equals("m4b")) return "audio/mp4a-latm";
        else if (suffix.equals("m4p")) return "audio/mp4a-latm";
        else if (suffix.equals("m4u")) return "video/vnd.mpegurl";
        else if (suffix.equals("m4v")) return "video/x-m4v";
        else if (suffix.equals("mov")) return "video/quicktime";
        else if (suffix.equals("mp2")) return "audio/x-mpeg";
        else if (suffix.equals("mp3")) return "audio/x-mpeg";
        else if (suffix.equals("mp4")) return "video/mp4";
        else if (suffix.equals("mpc")) return "application/vnd.mpohun.certificate";
        else if (suffix.equals("mpe")) return "video/mpeg";
        else if (suffix.equals("mpeg")) return "video/mpeg";
        else if (suffix.equals("mpg")) return "video/mpeg";
        else if (suffix.equals("mpg4")) return "video/mp4";
        else if (suffix.equals("mpga")) return "audio/mpeg";
        else if (suffix.equals("msg")) return "application/vnd.ms-outlook";
        else if (suffix.equals("ogg")) return "audio/ogg";
        else if (suffix.equals("pdf")) return "application/pdf";
        else if (suffix.equals("png")) return "image/png";
        else if (suffix.equals("pps")) return "application/vnd.ms-powerpoint";
        else if (suffix.equals("ppt")) return "application/vnd.ms-powerpoint";
        else if (suffix.equals("pptx"))
            return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
        else if (suffix.equals("prop")) return "text/plain";
        else if (suffix.equals("rc")) return "text/plain";
        else if (suffix.equals("rmvb")) return "audio/x-pn-realaudio";
        else if (suffix.equals("rtf")) return "application/rtf";
        else if (suffix.equals("sh")) return "text/plain";
        else if (suffix.equals("tar")) return "application/x-tar";
        else if (suffix.equals("tgz")) return "application/x-compressed";
        else if (suffix.equals("txt")) return "text/plain";
        else if (suffix.equals("wav")) return "audio/x-wav";
        else if (suffix.equals("wma")) return "audio/x-ms-wma";
        else if (suffix.equals("wmv")) return "audio/x-ms-wmv";
        else if (suffix.equals("wps")) return "application/vnd.ms-works";
        else if (suffix.equals("xml")) return "text/plain";
        else if (suffix.equals("z")) return "application/x-compress";
        else if (suffix.equals("zip")) return "application/x-zip-compressed";
        else return "*/*";
    }
}

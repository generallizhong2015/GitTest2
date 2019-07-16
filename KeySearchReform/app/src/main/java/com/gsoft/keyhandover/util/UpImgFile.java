package com.gsoft.keyhandover.util;


import android.content.Context;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


/**
 * Created by Administrator on 2018/4/13.
 */

public class UpImgFile {

    static String BOUNDARY = java.util.UUID.randomUUID().toString();
    static String PREFIX = "--", LINEND = "\r\n";
    static String MULTIPART_FROM_DATA = "multipart/form-data";
    static String CHARSET = "UTF-8";
    /* 上传文件至Server的方法 */
    /**
     * android上传文件到服务器
     *
     * @param file       需要上传的文件
     * @param RequestURL 请求的rul
     * @return 返回响应的内容
     */
    static final String _http = "http://" + HttpUtil.baseIp + HttpUtil.dk + "/KeyFileUpload.action";//这里是服务器的地址

    //    static final String _http = "http://192.168.10.13:8086/KeyFileUpload.action";//这里是服务器的地址
//    static final String _http = "http://10.194.102.253:8086/KeyFileUpload.action";//这里是服务器的地址
    public static int doPostPicture(Context context, String Strpath, Map<String, Object> paramMap, File pictureFile)
            throws Exception {
        Log.e("LZ--ip", _http);
        Log.e("LZ--上传参数", paramMap + "");
        URL url = new URL(_http);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false);
        conn.setReadTimeout(10 * 10000); // 缓存的最长时间
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);
        DataOutputStream os = new DataOutputStream(conn.getOutputStream());
        StringBuilder sb = new StringBuilder(); //用StringBuilder拼接报文，用于上传图片数据
        sb.append(PREFIX);
        sb.append(BOUNDARY);
        sb.append(LINEND);
        // Log.e("LZ图片路径", pictureFile.getName() + "");
        sb.append("Content-Disposition: form-data; name=\"picture\"; filename=\"" + pictureFile.getName() + "\"" + LINEND);
        sb.append("Content-Type: image/jpg; charset=" + CHARSET + LINEND);
        sb.append(LINEND);
        os.write(sb.toString().getBytes());
        // Log.e("Lz截取路径--2", pictureFile.getPath().substring(5));
        InputStream is = new FileInputStream(pictureFile.getPath().substring(5));

        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len); //写入图片数据
        }
        is.close();
        os.write(LINEND.getBytes());

        StringBuilder text = new StringBuilder();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) { //在for循环中拼接报文，上传文本数据
            text.append("--");
            text.append(BOUNDARY);
            text.append("\r\n");
            text.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n\r\n");
            text.append(entry.getValue());
            text.append("\r\n");
        }
        os.write(text.toString().getBytes("utf-8")); //写入文本数据
        // 请求结束标志
        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
        os.write(end_data);
        os.flush();
        os.close();
        // 得到响应码
        int res = conn.getResponseCode();
        conn.disconnect();
        return res;
    }


}



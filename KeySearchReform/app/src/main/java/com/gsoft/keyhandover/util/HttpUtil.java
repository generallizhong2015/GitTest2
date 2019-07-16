package com.gsoft.keyhandover.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

/**
 * Created by lz on 2017/10/6.
 */

public class HttpUtil {
    public static String baseIp = "192.168.1.2";
    public static String baseIp_ = "192.168.1.5";//测试IP
    //    public static String baseIp = "192.168.10.21";
//    public static String dk = ":8888";
    public static String dk = ":8086";//其他地址
    //改造地址
    public static String GZbaseIp_ = "10.194.95.42";
    //    public static String GZbaseIp_ = "192.168.10.21";
    public static String GZdk = ":80";

    public static <T> T doHttpPost(String address, String para, Class<T> classType) throws Exception {
        L.d("lz", address);
        String result = doHttpPost(address, para);
        L.d("lz", result);
        return JsonUtil.getObject(result, classType);
    }

    public static String doHttpPost(String address, String data) {
        HttpURLConnection urlConnection = null;
        try {
            // 根据地址创建URL对象
            URL url = new URL(address);
            // 根据URL对象打开链接
            urlConnection = (HttpURLConnection) url.openConnection();
            // 设置请求的方式
            urlConnection.setRequestMethod("POST");
            // 设置请求的超时时间
            urlConnection.setReadTimeout(5000);
            urlConnection.setConnectTimeout(5000);
            // 设置请求的头
            urlConnection.setRequestProperty("Connection", "keep-alive");
            // 设置请求的头
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置请求的头
            urlConnection.setRequestProperty("Content-Length", String.valueOf(data.getBytes().length));

            urlConnection.setDoOutput(true); // 发送POST请求必须设置允许输出
            urlConnection.setDoInput(true); // 发送POST请求必须设置允许输入
            //setDoInput的默认值就是true
            //获取输出流
            if (!StringUtils.isEmpty(data)) {
                OutputStream wr = urlConnection.getOutputStream();
                wr.write(data.getBytes());
                wr.flush();
            }
            L.i("lz", "ResponseCode：" + urlConnection.getResponseCode());
            // 获取响应的输入流对象
            InputStream is = urlConnection.getInputStream();
            // 创建字节输出流对象
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 定义读取的长度
            int len = 0;
            // 定义缓冲区
            byte buffer[] = new byte[1024];
            // 按照缓冲区的大小，循环读取
            while ((len = is.read(buffer)) != -1) {
                // 根据读取的长度写入到os对象中
                baos.write(buffer, 0, len);
            }
            // 返回字符串
            String result = new String(baos.toByteArray(), Charset.forName("UTF-8"));
            // 释放资源
            is.close();
            baos.close();
            return result;
        } catch (Exception e) {
            L.d("lz", "网络请求异常:" + e.toString());
        } finally {
            try {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            } catch (Exception er) {

            }
        }
        return null;
    }


    /**
     * @param urlPath  下载路径
     * @param filePath 文件保存路劲
     * @return 返回下载文件
     */
    public static void downloadFile(String urlPath, String filePath, String para, HttpCallback callback) {
        File file = null;
        L.i("lz", "urlPath:" + urlPath);
        try {
            URL url = new URL(urlPath);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.connect();

            //获取输出流
            if (!StringUtils.isEmpty(para)) {
                OutputStream wr = urlConnection.getOutputStream();
                wr.write(para.getBytes());
                wr.flush();
            }
            L.d("lz", "httpURLConnection.getResponseCode()" + httpURLConnection.getResponseCode());
            // 文件大小
            int fileLength = httpURLConnection.getContentLength();
            L.i("lz", "fileLength:" + fileLength);
            InputStream in = httpURLConnection.getInputStream();
            BufferedInputStream bin = new BufferedInputStream(in);
            file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            long len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
                callback.onPorgress((int) (len * 100 / fileLength));
            }
            bin.close();
            out.close();
            callback.onSuccess();
        } catch (Exception e) {
            L.e("lz", "downloadFileException：" + e.toString());
            callback.onFail();
        } finally {
            callback.onFinsh();
            return;
        }
    }

}

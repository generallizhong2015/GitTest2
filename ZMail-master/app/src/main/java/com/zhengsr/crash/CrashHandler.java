package com.zhengsr.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.android.zemaillib.ZMailManager;
import com.android.zemaillib.callback.IEmailSendListener;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by lz on 2019/1/15.
 * 全局异常日志记录
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private static CrashHandler instance = new CrashHandler();
    private Context mContext;

    // 用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<String, String>();

    // 用于格式化日期,作为日志文件名的一部分
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    private static final String TAG = "MainActivity";
    private static final String SEND_EMAIL = "591526999@qq.com";
    private static final String TO_EMAIL = "13881715535@139.com";
    private static final String PASSWORD = "pqyfchlmbsebdjf";//这是你申请的邮箱授权码（貌似只能qq邮箱与163才有，没有核实）
    private static boolean isUploadLog = false;

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler() {
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance() {
        return instance;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
//        autoClear(5);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            SystemClock.sleep(3000);
            // 退出程序
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息; 否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null)
            return false;

        try {
            // 使用Toast来显示异常信息
            new Thread() {

                @Override
                public void run() {
                    Looper.prepare();
//                    DialogUtil.showToast(mContext, "很抱歉,程序出现异常,即将重启...");
                    Looper.loop();
                }
            }.start();
            // 收集设备参数信息
            collectDeviceInfo(mContext);
            // 保存日志文件
            saveCrashInfoFile(ex);
            SystemClock.sleep(3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = null;

            pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName + "";
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
//            IsLog.Islog(e + "");
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     * @throws Exception
     */
    private String saveCrashInfoFile(Throwable ex) throws Exception {

        StringBuffer sb = new StringBuffer();
        try {
            SimpleDateFormat sDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            String date = sDateFormat.format(new Date());
            sb.append("\r\n" + date + "\n");
            for (Map.Entry<String, String> entry : infos.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                sb.append(key + "=" + value + "\n");
            }
            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            printWriter.flush();
            printWriter.close();
            String result = writer.toString();
            sb.append(result);
            Log.e("lz_ex", sb.append(result) + "");
            uploadLog(sb.toString() + "结束");
            Log.e("lz_sb", sb.toString());
            String fileName = writeFile(sb.toString());
            return fileName;
        } catch (Exception e) {
            Log.e("lz", "an error occured while writing file...", e);
            sb.append("an error occured while writing file...\r\n");
            writeFile(sb.toString());
        }
        return null;
    }

    /**
     * 文件命名 用时间：+ time
     */
    private String writeFile(String sb) throws Exception {
        String time = formatter.format(new Date());
        String fileName = "Ex_crash";
        if (FileUtil.hasSdcard()) {
            String path = getGlobalpath();
            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            FileOutputStream fos = new FileOutputStream(path + fileName, true);
            fos.write(sb.getBytes());
            fos.flush();
            fos.close();
        }
        return fileName;
    }

    //日志文件命名路径设置
    public static String getGlobalpath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "email_crash" + File.separator;
    }


    /**
     * 文件删除
     * <p>
     * day文件保存天数
     */
    public void autoClear(final int autoClearDay) {
        FileUtil.delete(getGlobalpath(), new FilenameFilter() {
            @Override
            public boolean accept(File file, String filename) {
                String s = FileUtil.getFileNameWithoutExtension(filename);
                int day = autoClearDay < 0 ? autoClearDay : -1 * autoClearDay;
                String date = "crash-" + DateUtil.getOtherDay(day);
                return date.compareTo(s) >= 0;
            }
        });
    }

    //向邮件中发送异常文本
    private void uploadLog(String msg_content) {
        if (isUploadLog) {
            Toast.makeText(mContext, "日志已上传成功", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer sbf = new StringBuffer("用户上传了客户端日志，请查收!\n系统参数如下:\n");
        sbf.append("手机厂商：").append(android.os.Build.MANUFACTURER).append("\n")
                .append("手机型号").append(android.os.Build.MODEL).append("\n")
                .append("手机当前系统语言：").append(Locale.getDefault().getLanguage()).append("\n")
                .append("Android系统版本号：").append(android.os.Build.ID);
        ZMailManager
                .fromAddr(SEND_EMAIL)
                .nickName("我是发送人")
                .password(PASSWORD)
                .host("smtp.qq.com")//是什么类型的邮箱就更改为什么类型，如为163邮箱 例：smtp.163.com
                .isSSLvertify(false)
                .port(25)
                .subject("[日志上报]")
                .content(sbf.toString() + ":" + msg_content)
//                .file()
//                .file(new String[]{})
                .toAddrs(new String[]{TO_EMAIL})
                .listener(new IEmailSendListener() {
                    @Override
                    public void sendStart() {
                        Toast.makeText(mContext, "正在发送......", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void sendFailed(String errorMsg) {

                        Toast.makeText(mContext, "日志发送失败：" + errorMsg, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void sendSuccess() {
                        isUploadLog = true;
                        Toast.makeText(mContext, "日志已上传成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .send();
    }
}

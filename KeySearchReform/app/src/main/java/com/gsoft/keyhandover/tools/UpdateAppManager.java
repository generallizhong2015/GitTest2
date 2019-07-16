package com.gsoft.keyhandover.tools;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;

import com.gsoft.keyhandover.BaseApplication;
import com.gsoft.keyhandover.util.FileUtil;
import com.gsoft.keyhandover.util.HttpCallback;
import com.gsoft.keyhandover.util.HttpUtil;
import com.gsoft.keyhandover.util.L;
import com.gsoft.keyhandover.util.SPUtils;
import com.gsoft.keyhandover.util.StringUtils;

import java.io.File;

/**
 * Created by Administrator on 2018/1/6/006.
 */

public class UpdateAppManager {

    private String versionCode = ""; // 当前版本号
    private String versionName = ""; // 当前版名
    private Context mContext;
    public String fileName;
    public CheckUpdateBean checkUpdateBean;
    PorgressDialog porgressDialog;
    Callback callback;

    public interface Callback {
        void callback();
    }


    public UpdateAppManager(Context mContext, CheckUpdateBean checkUpdateBean, Callback callback) {
        this.mContext = mContext;
        this.checkUpdateBean = checkUpdateBean;
        this.callback = callback;
        this.fileName = Config.APP_NAME + StringUtils.stringNumber(checkUpdateBean.getEditionName()) + ".apk";
        initVersionCode();
        if (Integer.parseInt(StringUtils.stringNumber(checkUpdateBean.getEditionNo())) > Integer.parseInt(versionCode)) {
            downlandApkFile();
        } else {
            callback.callback();
        }
    }

    /**
     * 获得版本号
     */
    private void initVersionCode() {
        try {
            versionCode = String.valueOf(BaseApplication.instance().getPackageManager().getPackageInfo(BaseApplication.instance().getPackageName(), 0).versionCode);
            versionName = String.valueOf(BaseApplication.instance().getPackageManager().getPackageInfo(BaseApplication.instance().getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 对比版本下载
     */
    private void downlandApkFile() {
        L.e("lz", "downlandApkFile");
        if (!FileUtil.isFileExists(fileName) || !(Boolean) SPUtils.get("is_downland_apk_finished", false)) {
            L.e("lz", "downlandApkFile不存在---------1");
            porgressDialog = new PorgressDialog(mContext);
            porgressDialog.show();
            porgressDialog.setProgress(0);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpUtil.downloadFile("http://" + HttpUtil.baseIp + ":8086" + checkUpdateBean.getFilePath(), FileUtil.getCacheDir() + "/" + fileName, null, new HttpCallback() {

                        @Override
                        public void onSuccess() {
                            SPUtils.put("is_downland_apk_finished", true);
                            File anFile = new File(FileUtil.getCacheDir() + "/" + fileName);
                            installApk(anFile);
                        }

                        @Override
                        public void onFail() {
                            SPUtils.put("is_downland_apk_finished", false);
                        }

                        @Override
                        public void onFinsh() {
                            porgressDialog.cancel();
                        }

                        @Override
                        public void onPorgress(int porgress) {
                            porgressDialog.setProgress(porgress);
                        }
                    });
                }
            }).start();


        } else {
            // 打开对话框
            L.e("lz", "downlandApkFile存在了---------2");
            File anFile = new File(FileUtil.getCacheDir() + "/" + fileName);
            installApk(anFile);

        }
    }


    /**
     * 安装
     */
    public void installApk(File file) {
        L.e("lz", "installApk");
        SPUtils.put("is_downland_apk_finished", false);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }

}

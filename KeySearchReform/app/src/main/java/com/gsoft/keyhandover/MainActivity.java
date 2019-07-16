package com.gsoft.keyhandover;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gsoft.keyhandover.buz.ApkToolBuz;
import com.gsoft.keyhandover.entity.IctRwmtUserData;
import com.gsoft.keyhandover.nodecontrolui.TaskListActivity;
import com.gsoft.keyhandover.tools.CheckUpdateBean;
import com.gsoft.keyhandover.tools.LoadingDialog;
import com.gsoft.keyhandover.tools.UpdateAppManager;
import com.gsoft.keyhandover.util.HttpUtil;
import com.gsoft.keyhandover.util.JsonUtil;
import com.gsoft.keyhandover.util.L;
import com.gsoft.keyhandover.util.StringUtils;

public class MainActivity extends Activity implements View.OnClickListener {

    String position;
    Context context;
    LoadingDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIntentData();
        context = this;
        initView();
    }

    @SuppressLint("WrongConstant")
    private void getIntentData() {
        Intent intent = getIntent();
        if (null != intent) {
            L.i("lz", "intent.getFlags():" + intent.getFlags());
            if (intent.getFlags() == BaseApplication.flags) {
                String data = intent.getStringExtra("data");
                L.e("lz", "data" + data);
                if (null != data) {
                    IctRwmtUserData ictRwmtUserData = JsonUtil.getObject(data, IctRwmtUserData.class);
                    BaseApplication.instance().user = ictRwmtUserData;
                    HttpUtil.baseIp = ictRwmtUserData.getUrlIP();
//                    HttpUtil.baseIp = "192.168.10.21";
                }
                position = intent.getStringExtra("position");
                L.e("lz", "position" + position);
                getAppVersion();
//                tioazhuan();
            }
        }
    }

    //apk、版本
    private CheckUpdateBean apkVersion;

    //获取版本信息
    public void getAppVersion() {
        loadingDialog = new LoadingDialog(this, "正在检查版本...", null);
        loadingDialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                //网络访问
                int code = -1;
                String message = "";
                ApkToolBuz.GetApkVersionResult result = null;
                try {
                    result = ApkToolBuz.GetApkVersion();
                    message = result.msg;
                    code = result.code;
                    apkVersion = result.data;
//                    apkVersion = JsonUtil.getObject("{\"version\":\"1.0\",\"versionCode\":\"100\",\"url\":\"http:\\/\\/xxx.xxx\"}", CheckUpdateBean.class);
                } catch (Exception e) {
                    message = "操作失败,请稍后重试！";
                } finally {
                    final String messageF = message;
                    final int codeF = code;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (codeF == 200)
                                dealVersionResult(codeF, messageF, apkVersion);
                        }
                    });
                }
            }
        }).start();
    }

    //更新管理
    private void dealVersionResult(int code, String message, CheckUpdateBean apkVersion) {

        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        if (code == 200) {
            new UpdateAppManager(context, apkVersion, new UpdateAppManager.Callback() {
                @Override
                public void callback() {
                    tiaozhuan();
                }
            });
        } else {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            tiaozhuan();
        }
    }

    //跳转
    int i = 0;

    private void tiaozhuan() {
        L.i("lz", "跳转啦!!!!!!!!-0--------------");

        if (i != 0) {
            return;
        }
        i++;
        if (!StringUtils.isEmpty(position)) {
            if (position.equals("1")) {
                startActivity(new Intent(this, KeyHandoverActivity.class));
                finish();
            } else if (position.equals("2")) {
                startActivity(new Intent(this, SearchActivity.class));
                finish();
            } else if (position.equals("3")) {
                BaseApplication.instance().isZJ = false;
                startActivity(new Intent(this, RemouldImplement_Activty.class));
                finish();
            } else if (position.equals("4")) {
                BaseApplication.instance().isZJ = true;
                startActivity(new Intent(this, RemouldQualityActivty.class));
                finish();
            } else if (position.equals("5")) {
                startActivity(new Intent(this, TaskListActivity.class));
                finish();
            }
        }
    }

    private void initView() {
        findViewById(R.id.nav_key).setOnClickListener(this);
        findViewById(R.id.nav_search).setOnClickListener(this);
        findViewById(R.id.nav_check).setOnClickListener(this);
        findViewById(R.id.nav_tool).setOnClickListener(this);

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_key:
                startActivity(new Intent(this, KeyHandoverActivity.class));
                break;
            case R.id.nav_search:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.nav_check:
                BaseApplication.instance().isZJ = true;
                startActivity(new Intent(this, RemouldQualityActivty.class));
                break;
            case R.id.nav_tool:
                BaseApplication.instance().isZJ = false;
                startActivity(new Intent(this, RemouldImplement_Activty.class));
                break;
        }
    }
}

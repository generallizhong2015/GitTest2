package com.zhengsr.crash;

import android.app.Application;

/**
 * Created by abc on 2019/7/2.
 */

public class CrashApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //如果启动异常日志文件记录，放开此行代码  路径：crash/时间.log
        CrashHandler.getInstance().init(this);
    }
}

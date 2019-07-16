package com.gsoft.keyhandover;

import android.app.Application;

import com.gsoft.keyhandover.entity.IctRwmtUserData;

/**
 * Created by Administrator on 2018/1/25.
 */

public class BaseApplication extends Application {
    final public static int flags = 93296;
    private static BaseApplication app;
    public IctRwmtUserData user;
    public boolean isZJ = true;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }


    public static BaseApplication instance() {
        if (app == null) {
            app = new BaseApplication();
        }
        return app;
    }

    public boolean isDQ() {//是否是地勤机械师或组长
        if (user.getRoleCode().equals("3") || user.getRoleCode().equals("6")) {
            return true;
        }
        return false;
    }
}

package com.android.zemaillib.bean;


import com.android.zemaillib.callback.IEmailSendListener;

/**
 * Created by abc
 * Created by abc on 2018/7/2
 */

public class ZEmailBean {
    public String fromAddr;
    public String nickName;
    public String password;
    public String subject;
    public String content;
    public String[] toAddrs;
    public String[] filePaths;
    public String url;
    public String host = null;
    public String port = null;
    public boolean isSSLverify = true;
    public IEmailSendListener listener;
}

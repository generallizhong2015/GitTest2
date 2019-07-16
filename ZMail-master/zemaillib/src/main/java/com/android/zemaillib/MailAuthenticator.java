package com.android.zemaillib;


import javax.mail.PasswordAuthentication;

/**
 * Created by abc on 2018/7/2.
 */

public class MailAuthenticator extends javax.mail.Authenticator {
    private String addr;
    private String password;

    public MailAuthenticator(String addr, String password) {
        this.addr = addr;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(addr,password);
    }
}

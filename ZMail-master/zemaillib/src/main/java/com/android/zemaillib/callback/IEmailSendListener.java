package com.android.zemaillib.callback;

/**
 * Created by abc on 2018/6/12.
 */

public interface IEmailSendListener {
    void sendStart();
    void sendFailed(String errorMsg);
    void sendSuccess();
}

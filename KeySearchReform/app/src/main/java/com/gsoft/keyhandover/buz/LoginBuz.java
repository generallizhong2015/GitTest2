package com.gsoft.keyhandover.buz;

import com.gsoft.keyhandover.entity.ServerResponse;
import com.gsoft.keyhandover.entity.UserEntity;
import com.gsoft.keyhandover.util.HttpUtil;
import com.gsoft.keyhandover.util.JsonUtil;

public class LoginBuz {

    public static class LoginRequest extends UserEntity {

        LoginRequest(UserEntity usr) {
            this.password = usr.password;
            this.account = usr.account;
            this.rfid = usr.rfid;
        }

    }

    public static class LoginResult extends ServerResponse {
        public UserEntity data;
    }

    public static LoginResult doLogin(UserEntity user) throws Exception {
        LoginRequest request = new LoginRequest(user);
        String para = JsonUtil.getString(request);
        String url = "user/login";
        LoginResult response = HttpUtil.doHttpPost(url, para, LoginResult.class);
        return response;

    }

}

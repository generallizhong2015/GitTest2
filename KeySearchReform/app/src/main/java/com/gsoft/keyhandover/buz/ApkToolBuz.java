package com.gsoft.keyhandover.buz;

import com.gsoft.keyhandover.entity.ServerResponse;
import com.gsoft.keyhandover.tools.CheckUpdateBean;
import com.gsoft.keyhandover.util.HttpUtil;
import com.gsoft.keyhandover.util.JsonUtil;
import com.gsoft.keyhandover.util.KeyValue;
import com.gsoft.keyhandover.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/12.
 */

public class ApkToolBuz {
    static List<KeyValue> bodyParams = new ArrayList<KeyValue>();


    public static class GetApkVersionRequest {

    }

    public static class GetApkVersionResult extends ServerResponse {
        public CheckUpdateBean data;
    }

    public static ApkToolBuz.GetApkVersionResult GetApkVersion() throws Exception {
        bodyParams.clear();
        bodyParams.add(new KeyValue("type", 2));

        String para = StringUtils.getPara(bodyParams);
        String url = "http://"+HttpUtil.baseIp + ":8086/GetAPKFile.action";

        ApkToolBuz.GetApkVersionResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, ApkToolBuz.GetApkVersionResult.class);
        } catch (Exception e) {

        }
        return response;
    }
}

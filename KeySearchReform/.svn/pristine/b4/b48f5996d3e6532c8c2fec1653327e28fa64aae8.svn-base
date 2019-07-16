package com.gsoft.keyhandover.buz;

import com.gsoft.keyhandover.entity.SearchInfoEntity;
import com.gsoft.keyhandover.entity.ServerResponse;
import com.gsoft.keyhandover.entity.TrackAsEntity1;
import com.gsoft.keyhandover.util.HttpUtil;
import com.gsoft.keyhandover.util.KeyValue;
import com.gsoft.keyhandover.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/19.
 */

public class SearchBuz {

    static List<KeyValue> bodyParams = new ArrayList<KeyValue>();


    public static TrackAsEntity1 getTrackAsByCard() {
        bodyParams.clear();

        //String url = "http://" + HttpUtil.baseIp + "/CheckListStaticWeb/Pages/TrainInTrack.ashx";
        String url = "http://" + HttpUtil.baseIp + "/CheckListStaticWeb/Pages/GetRepairTrackInfo.ashx";
        TrackAsEntity1 response = null;
        try {
            response = HttpUtil.doHttpPost(url, "", TrackAsEntity1.class);
        } catch (Exception e) {

        }
        return response;
    }


    public static class SearchInfoResult extends ServerResponse {
        public SearchInfoEntity data;
    }


    public static SearchInfoResult submit(String trackas) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("tp", trackas));

        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.baseIp + ":8086/getCardAndBeletNumber.action";
        SearchInfoResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, SearchInfoResult.class);
        } catch (Exception e) {

        }
        return response;
    }

}

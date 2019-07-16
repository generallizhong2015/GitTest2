package com.gsoft.keyhandover.buz;

import com.gsoft.keyhandover.entity.DailyPlanEntity;
import com.gsoft.keyhandover.entity.DayPlanPackEntity;
import com.gsoft.keyhandover.entity.FinishNameEntity;
import com.gsoft.keyhandover.entity.NodeEntity;
import com.gsoft.keyhandover.entity.NodeModleEntity;
import com.gsoft.keyhandover.entity.SearchInfoEntity;
import com.gsoft.keyhandover.entity.ServerResponse;
import com.gsoft.keyhandover.entity.TrianInfoStateEntity;
import com.gsoft.keyhandover.util.HttpUtil;
import com.gsoft.keyhandover.util.KeyValue;
import com.gsoft.keyhandover.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/2.
 */

public class NodeControlBuz {
    static List<KeyValue> bodyParams = new ArrayList<KeyValue>();


    public static class DayPlanIdResult extends ServerResponse {
        public String data;
    }

    public static NodeControlBuz.DayPlanIdResult getDayPlanId() {
        bodyParams.clear();
        String url = "http://" + HttpUtil.baseIp + ":8086/GetdDilyPlanId.action";
        DayPlanIdResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, "", DayPlanIdResult.class);
        } catch (Exception e) {

        }

        return response;
    }



    public static class DayPlanPackResult {
        public int Type;
        public String Desc;
        public List<DayPlanPackEntity> TaskInfoList = new ArrayList<>();
    }

    public static NodeControlBuz.DayPlanPackResult getDayPlanPack(String dayPlanId, String stuffId, String deptCode, String roleCode) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("queryDayPlan", "{\"DayPlanId\":\"" + dayPlanId + "\",\"StuffId\":\"" + stuffId + "\",\"DeptCode\":\"" + deptCode + "\",\"RoleCode\":\"" + roleCode + "\"}"));
//        bodyParams.add(new KeyValue("queryDayPlan", "{\"DayPlanId\":\"" + "2018-02-28-00" + "\",\"StuffId\":\"" + stuffId + "\",\"DeptCode\":\"" + deptCode + "\",\"RoleCode\":\"" + roleCode + "\"}"));
        bodyParams.add(new KeyValue("mainCyc", "0"));

        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.baseIp + "/CheckListStaticWeb/Pages/GetDayPlanData.ashx";
        DayPlanPackResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, DayPlanPackResult.class);
        } catch (Exception e) {

        }
        return response;
    }


    //固定职务获取作业包
    public static class DailyPlanResult {
        public int Type;
        public String Desc;
        public List<DailyPlanEntity> DailyPlanList = new ArrayList<>();
    }

    public static NodeControlBuz.DailyPlanResult DailyPlanPack(String dayPlanId) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("planId", dayPlanId));
        bodyParams.add(new KeyValue("mainCyc", "0"));

        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.baseIp + "/CheckListStaticWeb/Pages/GetPacketsByPlanId.ashx";
        DailyPlanResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, DailyPlanResult.class);
        } catch (Exception e) {

        }
        return response;
    }



    public static class TrianInfoResult {
        public int Type;
        public String Desc;
        public List<TrianInfoStateEntity> TrainInTrackList = new ArrayList<>();
    }

    public static NodeControlBuz.TrianInfoResult getTrianInfos() {
        bodyParams.clear();

        String url = "http://" + HttpUtil.baseIp + "/CheckListStaticWeb/Pages/TrainInTrack.ashx";
        TrianInfoResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, "", TrianInfoResult.class);
        } catch (Exception e) {

        }
        return response;
    }


    public static class NodesResult extends ServerResponse {
        public List<NodeModleEntity> data = new ArrayList<>();
    }

    public static NodeControlBuz.NodesResult getNodes(String tType, String mType) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("tType", tType));
        bodyParams.add(new KeyValue("mType", mType));
//        bodyParams.add(new KeyValue("tType", tType));
//        bodyParams.add(new KeyValue("mType", mType));

        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.baseIp + ":8086/getRoleWork.action";
        NodesResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, NodesResult.class);
        } catch (Exception e) {

        }
        return response;
    }


    public static class ProcessResult extends ServerResponse {
        public List<FinishNameEntity> data = new ArrayList<>();
    }

    public static NodeControlBuz.ProcessResult getProcess(String tType, String dayPlanNo) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("trainsetName", tType));
        bodyParams.add(new KeyValue("dayPlanNo", dayPlanNo));

        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.baseIp + ":8086/getFinishName.action";
        ProcessResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, ProcessResult.class);
        } catch (Exception e) {

        }
        return response;
    }


    public static ServerResponse submit(String flowId,
                                        String templateId,
                                        String nodeId,
                                        String condNodeId,
                                        String roleCode,
                                        String type,
                                        String flag,
                                        String trackplaCode,
                                        String trainsetId,
                                        String dayPlanNo,
                                        String dayOrNight,
                                        String taskType,
                                        String modifyFlag,
                                        String identifyCode,
                                        String trackCode,
                                        String nodeNum,
                                        String stuffCode,
                                        String deptCode,
                                        String traninNo,
                                        String largeMuiTarget,
                                        String statusFlag,
                                        String remark,
                                        String stuffName,
                                        String roleName,
                                        String deptName,
                                        String trackName,
                                        String conditionRcdId,
                                        String conditionRcdName,
                                        String branchCode,
                                        String branchName,
                                        String unitCode,
                                        String outTrainNo,
                                        String recordType,
                                        String beginFlag,
                                        String ipAddress,
                                        String hostName,
                                        String tType, String mType) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("flowId", flowId));
        bodyParams.add(new KeyValue("templateId", templateId));
        bodyParams.add(new KeyValue("nodeId", nodeId));
        bodyParams.add(new KeyValue("condNodeId", condNodeId));
        bodyParams.add(new KeyValue("roleCode", roleCode));
        bodyParams.add(new KeyValue("type", type));
        bodyParams.add(new KeyValue("flag", flag));
        bodyParams.add(new KeyValue("trackplaCode", trackplaCode));
        bodyParams.add(new KeyValue("trainsetId", trainsetId));
        bodyParams.add(new KeyValue("dayPlanNo", dayPlanNo));
        bodyParams.add(new KeyValue("dayOrNight", dayOrNight));
        bodyParams.add(new KeyValue("taskType", taskType));
        bodyParams.add(new KeyValue("modifyFlag", modifyFlag));
        bodyParams.add(new KeyValue("identifyCode", identifyCode));
        bodyParams.add(new KeyValue("trackCode", trackCode));
        bodyParams.add(new KeyValue("nodeNum", nodeNum));
        bodyParams.add(new KeyValue("stuffCode", stuffCode));
        bodyParams.add(new KeyValue("deptCode", deptCode));
        bodyParams.add(new KeyValue("traninNo", traninNo));
        bodyParams.add(new KeyValue("largeMuiTarget", largeMuiTarget));
        bodyParams.add(new KeyValue("statusFlag", statusFlag));
        bodyParams.add(new KeyValue("remark", remark));
        bodyParams.add(new KeyValue("stuffName", stuffName));
        bodyParams.add(new KeyValue("roleName", roleName));
        bodyParams.add(new KeyValue("deptName", deptName));
        bodyParams.add(new KeyValue("trackName", trackName));
        bodyParams.add(new KeyValue("conditionRcdId", conditionRcdId));
        bodyParams.add(new KeyValue("conditionRcdName", conditionRcdName));
        bodyParams.add(new KeyValue("branchCode", branchCode));
        bodyParams.add(new KeyValue("branchName", branchName));
        bodyParams.add(new KeyValue("unitCode", unitCode));
        bodyParams.add(new KeyValue("outTrainNo", outTrainNo));
        bodyParams.add(new KeyValue("recordType", recordType));
        bodyParams.add(new KeyValue("beginFlag", beginFlag));
        bodyParams.add(new KeyValue("ipAddress", ipAddress));
        bodyParams.add(new KeyValue("hostName", hostName));

        bodyParams.add(new KeyValue("tType", tType));
        bodyParams.add(new KeyValue("mType", mType));

        String para = StringUtils.getPara(bodyParams);
//        String url = "http://" + HttpUtil.baseIp + ":8086/recordedData.action";
        String url = "http://" + "192.168.1.5" + ":8086/recordedData.action";
        ServerResponse response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, ServerResponse.class);
        } catch (Exception e) {

        }
        return response;
    }

}

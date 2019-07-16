package com.gsoft.keyhandover.buz;

import android.util.Log;

import com.google.gson.Gson;
import com.gsoft.keyhandover.entity.AtitleEntity2;
import com.gsoft.keyhandover.entity.FileEntity;
import com.gsoft.keyhandover.entity.Loan_RetrunEntity;
import com.gsoft.keyhandover.entity.OutageEntity;
import com.gsoft.keyhandover.entity.PowerSupplyEntity;
import com.gsoft.keyhandover.entity.ProjectEntity;
import com.gsoft.keyhandover.entity.QtitleEntity2;
import com.gsoft.keyhandover.entity.SequenceOrState;
import com.gsoft.keyhandover.entity.ServerResponse;
import com.gsoft.keyhandover.entity.TrainEntity;
import com.gsoft.keyhandover.entity.TrainKeyResultEntity;
import com.gsoft.keyhandover.entity.TrainsKeyEntity;
import com.gsoft.keyhandover.util.HttpUtil;
import com.gsoft.keyhandover.util.KeyValue;
import com.gsoft.keyhandover.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class RemouldBuz {

    static List<KeyValue> bodyParams = new ArrayList<KeyValue>();


    /*获取车组号列表*/
    public static class TrainsResult extends ServerResponse {
        public List<TrainEntity> data;
    }

    public static TrainsResult getTracks(String dateS, String roleCode, String deptCode, String stuffName, String userType) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("date", dateS));
        bodyParams.add(new KeyValue("roleCode", roleCode));
        bodyParams.add(new KeyValue("deptCode", deptCode));
        bodyParams.add(new KeyValue("userName", stuffName));
        bodyParams.add(new KeyValue("userType", userType));

        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.GZbaseIp_ + HttpUtil.GZdk + "/api/v1/listTrain";
        TrainsResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, TrainsResult.class);
        } catch (Exception e) {

        }
        return response;
    }


    /*实施获取项点列表 */
    public static class AtitlesResult extends ServerResponse {
        public List<AtitleEntity2> data;
    }

    public static AtitlesResult getAtitles(String detailId, String roleCode, String deptCode, String stuffName, String userType) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("detailId", detailId));
        bodyParams.add(new KeyValue("roleCode", roleCode));
        bodyParams.add(new KeyValue("deptCode", deptCode));
        bodyParams.add(new KeyValue("userName", stuffName));
        bodyParams.add(new KeyValue("userType", userType));

        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.GZbaseIp_ + HttpUtil.GZdk + "/api/v1/listProNode";

        AtitlesResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, AtitlesResult.class);
        } catch (Exception e) {

        }
        return response;
    }

    /*质检获取项点列表 */
    public static class QtitlesResult extends ServerResponse {
        public List<QtitleEntity2> data;
    }

    public static QtitlesResult getQtitles(String detailId, String roleCode, String deptCode, String stuffName, String userType) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("detailId", detailId));
        bodyParams.add(new KeyValue("roleCode", roleCode));
        bodyParams.add(new KeyValue("deptCode", deptCode));
        bodyParams.add(new KeyValue("userName", stuffName));
        bodyParams.add(new KeyValue("userType", userType));

        String para = StringUtils.getPara(bodyParams);
        //String url = "http://" + HttpUtil.baseIp + HttpUtil.dk + "/api/v1/listProQcNode";
        String url = "http://" + HttpUtil.GZbaseIp_ + HttpUtil.GZdk + "/api/v1/listProNode";

        QtitlesResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, QtitlesResult.class);
        } catch (Exception e) {

        }
        return response;
    }


    /*获取文件列表*/
    public static class FilesResult extends ServerResponse {
        public List<FileEntity> data;
    }

    public static FilesResult getFiles(String id) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("operationId", id));

        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.GZbaseIp_ + HttpUtil.GZdk + "/api/v1/listProFile";

        FilesResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, FilesResult.class);
        } catch (Exception e) {

        }
        return response;
    }

    /*实施回填提交*/
    public static ServerResponse backFill(String detailNodeId, String remark, boolean qualified, String roleCode, String deptCode, String stuffName, String userType) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("detailNodeId", detailNodeId));
        bodyParams.add(new KeyValue("remark", remark));
        bodyParams.add(new KeyValue("qualified", qualified));
        bodyParams.add(new KeyValue("roleCode", roleCode));
        bodyParams.add(new KeyValue("deptCode", deptCode));
        bodyParams.add(new KeyValue("userName", stuffName));
        bodyParams.add(new KeyValue("userType", userType));
        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.GZbaseIp_ + HttpUtil.GZdk + "/api/v1/qpBackFill";

        ServerResponse response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, ServerResponse.class);
        } catch (Exception e) {

        }
        return response;
    }

    //实施全部回填
    public static ServerResponse AllbackFill(String detailId, String remark, String roleCode, String deptCode, String stuffName, String userType) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("detailId", detailId));
        bodyParams.add(new KeyValue("remark", remark));
        bodyParams.add(new KeyValue("roleCode", roleCode));
        bodyParams.add(new KeyValue("deptCode", deptCode));
        bodyParams.add(new KeyValue("userName", stuffName));
        bodyParams.add(new KeyValue("userType", userType));
        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.GZbaseIp_ + HttpUtil.GZdk + "/api/v1/qpOneStepBackFill";
        ServerResponse Sresponse = null;
        try {
            Sresponse = HttpUtil.doHttpPost(url, para, ServerResponse.class);
            Log.e("Lz---", Sresponse + "");
        } catch (Exception e) {

        }
        return Sresponse;
    }

    /*质检回填提交*/
    public static ServerResponse backFillQ(String nodeId, String remark, boolean qualified, String roleCode, String deptCode, String stuffName, String userType) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("detailNodeId", nodeId));
        bodyParams.add(new KeyValue("remark", remark));
        bodyParams.add(new KeyValue("qualified", qualified));
        bodyParams.add(new KeyValue("roleCode", roleCode));
        bodyParams.add(new KeyValue("deptCode", deptCode));
        bodyParams.add(new KeyValue("userName", stuffName));
        bodyParams.add(new KeyValue("userType", userType));

        String para = StringUtils.getPara(bodyParams);
        //String url = "http://" + HttpUtil.baseIp + HttpUtil.dk + "/api/v1/qcBackFill";
        String url = "http://" + HttpUtil.GZbaseIp_ + HttpUtil.GZdk + "/api/v1/qaBackFill";
        ServerResponse response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, ServerResponse.class);
        } catch (Exception e) {

        }
        return response;
    }

    //质检全部回填
    public static ServerResponse OneStepBackFill(String detailId, Boolean qualified, String remark, String roleCode, String deptCode, String stuffName, String userType) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("detailId", detailId));
        bodyParams.add(new KeyValue("qualified", qualified));
        bodyParams.add(new KeyValue("remark", remark));
        bodyParams.add(new KeyValue("roleCode", roleCode));
        bodyParams.add(new KeyValue("deptCode", deptCode));
        bodyParams.add(new KeyValue("userName", stuffName));
        bodyParams.add(new KeyValue("userType", userType));
        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.GZbaseIp_ + HttpUtil.GZdk + "/api/v1/qcOneStepBackFill";
        ServerResponse Sresponse = null;
        try {
            Sresponse = HttpUtil.doHttpPost(url, para, ServerResponse.class);
            Log.e("Lz---", Sresponse + "");
        } catch (Exception e) {

        }
        return Sresponse;
    }

    //获取项目名称
    public static class ProjectResult extends ServerResponse {
        public List<ProjectEntity> data;
    }

    public static ProjectResult getProject(String Trains, String date, String roleCode, String deptCode, String stuffName, String userType) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("trainCode", Trains));
        bodyParams.add(new KeyValue("date", date));
        bodyParams.add(new KeyValue("roleCode", roleCode));
        bodyParams.add(new KeyValue("deptCode", deptCode));
        bodyParams.add(new KeyValue("userName", stuffName));
        bodyParams.add(new KeyValue("userType", userType));
        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.GZbaseIp_ + HttpUtil.GZdk + "/api/v1/listPro";
        ProjectResult projectResult = null;
        try {
            projectResult = HttpUtil.doHttpPost(url, para, ProjectResult.class);
            // Log.e("lz",response+"");
        } catch (Exception e) {

        }
        return projectResult;
    }

    //查询辆序及车组号
    public static class SequenceOrStateResult extends ServerResponse {
        public List<SequenceOrState> data;
    }

    public static SequenceOrStateResult getSequenceOrState(String operationId, String trainCode, String roleCode, String deptCode, String stuffName, String userType) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("operationId", operationId));
        bodyParams.add(new KeyValue("trainCode", trainCode));//车组号
        bodyParams.add(new KeyValue("roleCode", roleCode));
        bodyParams.add(new KeyValue("deptCode", deptCode));
        bodyParams.add(new KeyValue("userName", stuffName));
        bodyParams.add(new KeyValue("userType", userType));
        String para = StringUtils.getPara(bodyParams);
//        String url = "http://" + HttpUtil.baseIp + HttpUtil.dk + "/api/v1/listProContent";
        String url = "http://" + HttpUtil.GZbaseIp_ + HttpUtil.GZdk + "/api/v1/listProContent";
        SequenceOrStateResult sequenceOrStateResult = null;
        try {
            sequenceOrStateResult = HttpUtil.doHttpPost(url, para, SequenceOrStateResult.class);
        } catch (Exception e) {

        }
        return sequenceOrStateResult;
    }

    /**
     * 端供电历史记录查询
     */
    public static class PowerSupplyResult extends ServerResponse {
        public List<PowerSupplyEntity> data;
    }

    public static PowerSupplyResult PowerSupply_GD(String trackPosition, String fTime, String lTime) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("trackPosition", trackPosition));
        bodyParams.add(new KeyValue("startTime", fTime));
        bodyParams.add(new KeyValue("endTime", lTime));

        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.baseIp + HttpUtil.dk + "/getPowerSupplyInfo.action";
        PowerSupplyResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, PowerSupplyResult.class);
        } catch (Exception e) {
        }
        return response;
    }

    /**
     * 断电历史记录查询
     */
    public static class OutageResult extends ServerResponse {
        public List<OutageEntity> data;
    }

    public static OutageResult Outage_DD(String trackPosition, String fTime, String lTime) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("trackPosition", trackPosition));
        bodyParams.add(new KeyValue("startTime", fTime));
        bodyParams.add(new KeyValue("endTime", lTime));

        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.baseIp + HttpUtil.dk + "/getPowerStopInfo.action";
        OutageResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, OutageResult.class);
        } catch (Exception e) {
        }
        return response;
    }

    /*获取车型列表*/
    public static class TrainsTypeResult extends ServerResponse {
        public List<TrainsKeyEntity> data;
    }

    public static TrainsTypeResult getTraintype() {
        bodyParams.clear();
        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.baseIp + HttpUtil.dk + "/getTrainType.action";
        TrainsTypeResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, TrainsTypeResult.class);
        } catch (Exception e) {

        }
        return response;
    }

    /*获取车组号列表*/
    public static class TrainsNumResult extends ServerResponse {
        public List<TrainsKeyEntity> data;
    }

    public static TrainsNumResult getTrainNum(String TrainType) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("type", TrainType));
        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.baseIp + HttpUtil.dk + "/getTrainNumber.action";
        Log.e("LZ改造IP", HttpUtil.baseIp + HttpUtil.dk);
        TrainsNumResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, TrainsNumResult.class);
        } catch (Exception e) {

        }
        return response;
    }

    /*获取钥匙名称列表*/
    public static class TrainsKeyNameResult extends ServerResponse {
        public List<TrainsKeyEntity> data;
    }

    public static TrainsKeyNameResult getTrainKeyName(String TrainType, String TrainNum) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("type", TrainType));
        bodyParams.add(new KeyValue("number", TrainNum));
        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.baseIp + HttpUtil.dk + "/getKeyName.action";
        TrainsKeyNameResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, TrainsKeyNameResult.class);
        } catch (Exception e) {

        }
        return response;
    }

    /**
     * 钥匙查询结果
     */

    public static class TrainsKeyResult extends ServerResponse {
        public List<TrainKeyResultEntity> data;
    }

    public static TrainsKeyResult getTrainKey(String TrainType, String TrainNum, String Trainkeyname) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("type", TrainType));
        bodyParams.add(new KeyValue("number", TrainNum));
        bodyParams.add(new KeyValue("keyName", Trainkeyname));
        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.baseIp + HttpUtil.dk + "/getKeyState.action";

        TrainsKeyResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, TrainsKeyResult.class);
        } catch (Exception e) {

        }
        return response;
    }


    /**
     * 借用归还记录
     */
    public static class D_A_D_A_Loan_retrun_Result extends ServerResponse {
      public  List<List<Loan_RetrunEntity>> data;
    }

    public static D_A_D_A_Loan_retrun_Result D_A_D_A_Loan_retrun(String trackPosition, String fTime, String lTime) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("tp", trackPosition));
        bodyParams.add(new KeyValue("startTime", fTime));
        bodyParams.add(new KeyValue("endTime", lTime));
        Gson g = new Gson();
//        Log.e("LZ---参数", g.toJson(bodyParams));
        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.baseIp + HttpUtil.dk + "/CardUserInfo.action";

        D_A_D_A_Loan_retrun_Result response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, D_A_D_A_Loan_retrun_Result.class);
        } catch (Exception e) {
        }
        return response;
    }
/**
 * CESHI
 */
    /**
     *
     */
    public static class _PowerSupplyResult extends ServerResponse {
      public  List<List<Loan_RetrunEntity>> data;
    }

    public static _PowerSupplyResult _PowerSupply_GD(String trackPosition, String fTime, String lTime) {
        bodyParams.clear();
        bodyParams.add(new KeyValue("tp", trackPosition));
        bodyParams.add(new KeyValue("startTime", fTime));
        bodyParams.add(new KeyValue("endTime", lTime));

        String para = StringUtils.getPara(bodyParams);
        String url = "http://" + HttpUtil.baseIp + HttpUtil.dk + "/CardUserInfo.action";
        _PowerSupplyResult response = null;
        try {
            response = HttpUtil.doHttpPost(url, para, _PowerSupplyResult.class);
        } catch (Exception e) {
        }
        Log.e("LZ----111",response.data.size()+"");
        return response;
    }
}

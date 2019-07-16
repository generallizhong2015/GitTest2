package com.gsoft.keyhandover.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/23.
 */

public class DailyPlanEntity {

    /**
     * TrainsetID : CRH1A-1031
     * TrainsetNo : 1031
     * TrainsetType : null
     * PlanPublishPorson : null
     * TrainsetRunKms : null
     * TrainsetBatch : CRH1A_1
     * PutInTrainNo :
     * PutInDate :
     * PutInTrack :
     * WorkPacketList : [{"PacketCode":"ls20180322211042","PacketName":"02车3轴制动盘裂纹近限，需更换02车3轴轮对","DeptCode":"0062700791","DeptName":"二级修6班","MaintCycCode":"-1","PacketType":"3"},{"PacketCode":"ls20180322211100","PacketName":"03车3位空簧漏风，需落架检查处置","DeptCode":"0062700791","DeptName":"二级修6班","MaintCycCode":"-1","PacketType":"3"}]
     */

    private String TrainsetID;
    private String TrainsetNo;
    private Object TrainsetType;
    private Object PlanPublishPorson;
    private Object TrainsetRunKms;
    private String TrainsetBatch;
    private String PutInTrainNo;
    private String PutInDate;
    private String PutInTrack;
    private List<WorkPacketListBean> WorkPacketList;

    public String getTrainsetID() {
        return TrainsetID;
    }

    public void setTrainsetID(String TrainsetID) {
        this.TrainsetID = TrainsetID;
    }

    public String getTrainsetNo() {
        return TrainsetNo;
    }

    public void setTrainsetNo(String TrainsetNo) {
        this.TrainsetNo = TrainsetNo;
    }

    public Object getTrainsetType() {
        return TrainsetType;
    }

    public void setTrainsetType(Object TrainsetType) {
        this.TrainsetType = TrainsetType;
    }

    public Object getPlanPublishPorson() {
        return PlanPublishPorson;
    }

    public void setPlanPublishPorson(Object PlanPublishPorson) {
        this.PlanPublishPorson = PlanPublishPorson;
    }

    public Object getTrainsetRunKms() {
        return TrainsetRunKms;
    }

    public void setTrainsetRunKms(Object TrainsetRunKms) {
        this.TrainsetRunKms = TrainsetRunKms;
    }

    public String getTrainsetBatch() {
        return TrainsetBatch;
    }

    public void setTrainsetBatch(String TrainsetBatch) {
        this.TrainsetBatch = TrainsetBatch;
    }

    public String getPutInTrainNo() {
        return PutInTrainNo;
    }

    public void setPutInTrainNo(String PutInTrainNo) {
        this.PutInTrainNo = PutInTrainNo;
    }

    public String getPutInDate() {
        return PutInDate;
    }

    public void setPutInDate(String PutInDate) {
        this.PutInDate = PutInDate;
    }

    public String getPutInTrack() {
        return PutInTrack;
    }

    public void setPutInTrack(String PutInTrack) {
        this.PutInTrack = PutInTrack;
    }

    public List<WorkPacketListBean> getWorkPacketList() {
        return WorkPacketList;
    }

    public void setWorkPacketList(List<WorkPacketListBean> WorkPacketList) {
        this.WorkPacketList = WorkPacketList;
    }

    public static class WorkPacketListBean {
        /**
         * PacketCode : ls20180322211042
         * PacketName : 02车3轴制动盘裂纹近限，需更换02车3轴轮对
         * DeptCode : 0062700791
         * DeptName : 二级修6班
         * MaintCycCode : -1
         * PacketType : 3
         */

        private String PacketCode;
        private String PacketName;
        private String DeptCode;
        private String DeptName;
        private String MaintCycCode;
        private String PacketType;

        public String getPacketCode() {
            return PacketCode;
        }

        public void setPacketCode(String PacketCode) {
            this.PacketCode = PacketCode;
        }

        public String getPacketName() {
            return PacketName;
        }

        public void setPacketName(String PacketName) {
            this.PacketName = PacketName;
        }

        public String getDeptCode() {
            return DeptCode;
        }

        public void setDeptCode(String DeptCode) {
            this.DeptCode = DeptCode;
        }

        public String getDeptName() {
            return DeptName;
        }

        public void setDeptName(String DeptName) {
            this.DeptName = DeptName;
        }

        public String getMaintCycCode() {
            return MaintCycCode;
        }

        public void setMaintCycCode(String MaintCycCode) {
            this.MaintCycCode = MaintCycCode;
        }

        public String getPacketType() {
            return PacketType;
        }

        public void setPacketType(String PacketType) {
            this.PacketType = PacketType;
        }
    }
}

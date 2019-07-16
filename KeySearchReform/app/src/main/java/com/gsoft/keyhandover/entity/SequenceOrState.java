package com.gsoft.keyhandover.entity;

/**
 * Created by Administrator on 2018/3/1.
 */

public class SequenceOrState {

    /**
     * trainOrder : 06
     * nodeTotal : 2  回填总数
     * trainName : CRH380A-2779
     * detailId : 2492DF32F1554414AEB2C489AD3258FB
     * qaCompleteCount : 2  已回填数
     * trainCode : 2779
     * status : 1
     */

    private String trainOrder;
    private int nodeTotal;
    private String trainName;
    private String detailId;
    private int qaCompleteCount;
    private String trainCode;
    private int status;

    public String getTrainOrder() {
        return trainOrder;
    }

    public void setTrainOrder(String trainOrder) {
        this.trainOrder = trainOrder;
    }

    public int getNodeTotal() {
        return nodeTotal;
    }

    public void setNodeTotal(int nodeTotal) {
        this.nodeTotal = nodeTotal;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public int getQaCompleteCount() {
        return qaCompleteCount;
    }

    public void setQaCompleteCount(int qaCompleteCount) {
        this.qaCompleteCount = qaCompleteCount;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public void setTrainCode(String trainCode) {
        this.trainCode = trainCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

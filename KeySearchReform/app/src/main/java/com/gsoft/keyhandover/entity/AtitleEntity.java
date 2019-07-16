package com.gsoft.keyhandover.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/2/2.
 */

public class AtitleEntity implements Parcelable {


    /**
     * status : 0
     * backFill : 0
     * nodeId : C3B00A02526142639DBBAEC18E2D8584
     * nodeName : 打开冰箱
     * riskTip : 无
     * nodeOrder : 0
     * performCondition : 冰箱
     */

    private int status;
    private int backFill;
    private String nodeId;
    private String nodeName;
    private String riskTip;
    private int nodeOrder;
    private String performCondition;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBackFill() {
        return backFill;
    }

    public void setBackFill(int backFill) {
        this.backFill = backFill;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getRiskTip() {
        return riskTip;
    }

    public void setRiskTip(String riskTip) {
        this.riskTip = riskTip;
    }

    public int getNodeOrder() {
        return nodeOrder;
    }

    public void setNodeOrder(int nodeOrder) {
        this.nodeOrder = nodeOrder;
    }

    public String getPerformCondition() {
        return performCondition;
    }

    public void setPerformCondition(String performCondition) {
        this.performCondition = performCondition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.status);
        dest.writeInt(this.backFill);
        dest.writeString(this.nodeId);
        dest.writeString(this.nodeName);
        dest.writeString(this.riskTip);
        dest.writeInt(this.nodeOrder);
        dest.writeString(this.performCondition);
    }

    public AtitleEntity() {
    }

    protected AtitleEntity(Parcel in) {
        this.status = in.readInt();
        this.backFill = in.readInt();
        this.nodeId = in.readString();
        this.nodeName = in.readString();
        this.riskTip = in.readString();
        this.nodeOrder = in.readInt();
        this.performCondition = in.readString();
    }

    public static final Parcelable.Creator<AtitleEntity> CREATOR = new Parcelable.Creator<AtitleEntity>() {
        @Override
        public AtitleEntity createFromParcel(Parcel source) {
            return new AtitleEntity(source);
        }

        @Override
        public AtitleEntity[] newArray(int size) {
            return new AtitleEntity[size];
        }
    };
}

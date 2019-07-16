package com.gsoft.keyhandover.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/1.
 */

public class AtitleEntity2 implements Parcelable {

    /**
     * detailId : 940E4934CA4C4362B52B68FD06D564DC
     * detailNodeId : null
     * qaRemark : null
     * qaCheck : 验收1
     * qpRemark : null
     * nodeName : 导入程序文件
     * riskTip : 提示1
     * order : 0
     * performCondition : 条件1
     * status : 0
     * qaControl : 1
     */

    private String detailId;
    private Object detailNodeId;
    private Object qaRemark;
    private String qaCheck;
    private Object qpRemark;
    private String nodeName;
    private String riskTip;
    private int order;
    private String performCondition;
    private int status;
    private int qaControl;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public Object getDetailNodeId() {
        return detailNodeId;
    }

    public void setDetailNodeId(Object detailNodeId) {
        this.detailNodeId = detailNodeId;
    }

    public Object getQaRemark() {
        return qaRemark;
    }

    public void setQaRemark(Object qaRemark) {
        this.qaRemark = qaRemark;
    }

    public String getQaCheck() {
        return qaCheck;
    }

    public void setQaCheck(String qaCheck) {
        this.qaCheck = qaCheck;
    }

    public Object getQpRemark() {
        return qpRemark;
    }

    public void setQpRemark(Object qpRemark) {
        this.qpRemark = qpRemark;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getPerformCondition() {
        return performCondition;
    }

    public void setPerformCondition(String performCondition) {
        this.performCondition = performCondition;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQaControl() {
        return qaControl;
    }

    public void setQaControl(int qaControl) {
        this.qaControl = qaControl;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.detailId);
        dest.writeString((String) this.detailNodeId);
        dest.writeString((String) this.qaRemark);
        dest.writeString(this.qaCheck);
        dest.writeString((String) this.qpRemark);
        dest.writeString(this.nodeName);
        dest.writeString(this.riskTip);
        dest.writeInt(this.order);
        dest.writeString(this.performCondition);
        dest.writeInt(this.status);
        dest.writeInt(this.qaControl);
    }

    public AtitleEntity2() {
    }

    protected AtitleEntity2(Parcel in) {

        this.detailId = in.readString();
        this.detailNodeId = in.readString();
        this.qaRemark = in.readString();
        this.qaCheck = in.readString();
        this.qpRemark = in.readString();
        this.nodeName = in.readString();
        this.riskTip = in.readString();
        this.order = in.readInt();
        this.performCondition = in.readString();
        this.status = in.readInt();
        this.qaControl = in.readInt();
    }

    public static final Parcelable.Creator<AtitleEntity2> CREATOR = new Parcelable.Creator<AtitleEntity2>() {
        @Override
        public AtitleEntity2 createFromParcel(Parcel source) {
            return new AtitleEntity2(source);
        }

        @Override
        public AtitleEntity2[] newArray(int size) {
            return new AtitleEntity2[size];
        }
    };
}

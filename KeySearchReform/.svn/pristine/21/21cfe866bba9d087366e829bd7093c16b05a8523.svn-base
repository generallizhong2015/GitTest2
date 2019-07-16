package com.gsoft.keyhandover.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/1.
 */

public class DayPlanPackEntity implements Parcelable {


    /**
     * TrainsetID : CRH2A-4041
     * TrainsetNo : 4041
     * PutInTrainNo :
     * PutInDate :
     * PutInTrack :
     * PacketInfoList : [{"PacketCode":"###########CRH2A_3#1","PacketName":"一级修","DeptCode":null,"DeptName":null,"MaintCycCode":"1","PacketType":"1"},{"PacketCode":"03###00627#######779","PacketName":"夏季滤网（5-9月，8天包）","DeptCode":null,"DeptName":null,"MaintCycCode":"2","PacketType":"1"},{"PacketCode":"03###00627#######781","PacketName":"I2","DeptCode":null,"DeptName":null,"MaintCycCode":"2","PacketType":"1"},{"PacketCode":"03###00627#######785","PacketName":"M2-2","DeptCode":null,"DeptName":null,"MaintCycCode":"2","PacketType":"1"},{"PacketCode":"03###00627#######788","PacketName":"春、秋季整修包","DeptCode":null,"DeptName":null,"MaintCycCode":"2","PacketType":"1"},{"PacketCode":"xt000000000000000004","PacketName":"人工清洗","DeptCode":null,"DeptName":null,"MaintCycCode":"-1","PacketType":"6"},{"PacketCode":"xt000000000000000006","PacketName":"ATP","DeptCode":null,"DeptName":null,"MaintCycCode":"-1","PacketType":"6"},{"PacketCode":"xt000000000000000008","PacketName":"CIR","DeptCode":null,"DeptName":null,"MaintCycCode":"-1","PacketType":"6"},{"PacketCode":"xt000000000000000010","PacketName":"绝缘子清洁","DeptCode":null,"DeptName":null,"MaintCycCode":"-1","PacketType":"6"}]
     */

    private String TrainsetID;
    private String TrainsetNo;
    private String PutInTrainNo;
    private String PutInDate;
    private String PutInTrack;
    private List<PacketInfoListBean> PacketInfoList;

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

    public List<PacketInfoListBean> getPacketInfoList() {
        return PacketInfoList;
    }

    public void setPacketInfoList(List<PacketInfoListBean> PacketInfoList) {
        this.PacketInfoList = PacketInfoList;
    }

    public static class PacketInfoListBean implements Parcelable {

        /**
         * PacketCode : ###########CRH2A_3#1
         * PacketName : 一级修
         * DeptCode : null
         * DeptName : null
         * MaintCycCode : 1
         * PacketType : 1
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.PacketCode);
            dest.writeString(this.PacketName);
            dest.writeString(this.DeptCode);
            dest.writeString(this.DeptName);
            dest.writeString(this.MaintCycCode);
            dest.writeString(this.PacketType);
        }

        public PacketInfoListBean() {
        }

        protected PacketInfoListBean(Parcel in) {
            this.PacketCode = in.readString();
            this.PacketName = in.readString();
            this.DeptCode = in.readString();
            this.DeptName = in.readString();
            this.MaintCycCode = in.readString();
            this.PacketType = in.readString();
        }

        public static final Creator<PacketInfoListBean> CREATOR = new Creator<PacketInfoListBean>() {
            @Override
            public PacketInfoListBean createFromParcel(Parcel source) {
                return new PacketInfoListBean(source);
            }

            @Override
            public PacketInfoListBean[] newArray(int size) {
                return new PacketInfoListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.TrainsetID);
        dest.writeString(this.TrainsetNo);
        dest.writeString(this.PutInTrainNo);
        dest.writeString(this.PutInDate);
        dest.writeString(this.PutInTrack);
        dest.writeList(this.PacketInfoList);
    }

    public DayPlanPackEntity() {
    }

    protected DayPlanPackEntity(Parcel in) {
        this.TrainsetID = in.readString();
        this.TrainsetNo = in.readString();
        this.PutInTrainNo = in.readString();
        this.PutInDate = in.readString();
        this.PutInTrack = in.readString();
        this.PacketInfoList = new ArrayList<PacketInfoListBean>();
        in.readList(this.PacketInfoList, PacketInfoListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<DayPlanPackEntity> CREATOR = new Parcelable.Creator<DayPlanPackEntity>() {
        @Override
        public DayPlanPackEntity createFromParcel(Parcel source) {
            return new DayPlanPackEntity(source);
        }

        @Override
        public DayPlanPackEntity[] newArray(int size) {
            return new DayPlanPackEntity[size];
        }
    };
}

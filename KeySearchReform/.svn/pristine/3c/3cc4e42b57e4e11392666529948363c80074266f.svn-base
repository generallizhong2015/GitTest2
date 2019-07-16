package com.gsoft.keyhandover.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2018/3/1.
 */

public class TrianInfoStateEntity implements Parcelable {


    /**
     * TrackCode : 0000000003
     * TrackName : DJ1
     * TrackPla : 2
     * TrainsetName : CRH2A-4022
     * Marshal : 8
     * IsJoin : false
     * JoinTrainsetName : null
     * FirCarDir : 01
     * TrainIn :
     * TimeTrainIn : 2016-08-17 20:55:42
     * TrainOut :
     * TimeTrainOut : 0001-01-01 00:00:00
     */
    private String TrackCode;
    private String TrackName;
    private String TrackPla;
    private String TrainsetName;
    private int Marshal;
    private boolean IsJoin;
    private String JoinTrainsetName;
    private String FirCarDir;
    private String TrainIn;
    private String TimeTrainIn;
    private String TrainOut;
    private String TimeTrainOut;

    public String getTrackCode() {
        return TrackCode;
    }

    public void setTrackCode(String TrackCode) {
        this.TrackCode = TrackCode;
    }

    public String getTrackName() {
        return TrackName;
    }

    public void setTrackName(String TrackName) {
        this.TrackName = TrackName;
    }

    public String getTrackPla() {
        return TrackPla;
    }

    public void setTrackPla(String TrackPla) {
        this.TrackPla = TrackPla;
    }

    public String getTrainsetName() {
        return TrainsetName;
    }

    public void setTrainsetName(String TrainsetName) {
        this.TrainsetName = TrainsetName;
    }

    public int getMarshal() {
        return Marshal;
    }

    public void setMarshal(int Marshal) {
        this.Marshal = Marshal;
    }

    public boolean isIsJoin() {
        return IsJoin;
    }

    public void setIsJoin(boolean IsJoin) {
        this.IsJoin = IsJoin;
    }

    public String getJoinTrainsetName() {
        return JoinTrainsetName;
    }

    public void setJoinTrainsetName(String JoinTrainsetName) {
        this.JoinTrainsetName = JoinTrainsetName;
    }

    public String getFirCarDir() {
        return FirCarDir;
    }

    public void setFirCarDir(String FirCarDir) {
        this.FirCarDir = FirCarDir;
    }

    public String getTrainIn() {
        return TrainIn;
    }

    public void setTrainIn(String TrainIn) {
        this.TrainIn = TrainIn;
    }

    public String getTimeTrainIn() {
        return TimeTrainIn;
    }

    public void setTimeTrainIn(String TimeTrainIn) {
        this.TimeTrainIn = TimeTrainIn;
    }

    public String getTrainOut() {
        return TrainOut;
    }

    public void setTrainOut(String TrainOut) {
        this.TrainOut = TrainOut;
    }

    public String getTimeTrainOut() {
        return TimeTrainOut;
    }

    public void setTimeTrainOut(String TimeTrainOut) {
        this.TimeTrainOut = TimeTrainOut;
    }

    @Override
    public String toString() {
        return "getHttpJobBasicInformatiztion{" +
                "TrackCode='" + TrackCode + '\'' +
                ", TrackName='" + TrackName + '\'' +
                ", TrackPla='" + TrackPla + '\'' +
                ", TrainsetName='" + TrainsetName + '\'' +
                ", Marshal=" + Marshal +
                ", IsJoin=" + IsJoin +
                ", JoinTrainsetName=" + JoinTrainsetName +
                ", FirCarDir='" + FirCarDir + '\'' +
                ", TrainIn='" + TrainIn + '\'' +
                ", TimeTrainIn='" + TimeTrainIn + '\'' +
                ", TrainOut='" + TrainOut + '\'' +
                ", TimeTrainOut='" + TimeTrainOut + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.TrackCode);
        dest.writeString(this.TrackName);
        dest.writeString(this.TrackPla);
        dest.writeString(this.TrainsetName);
        dest.writeInt(this.Marshal);
        dest.writeByte(this.IsJoin ? (byte) 1 : (byte) 0);
        dest.writeString(this.JoinTrainsetName);
        dest.writeString(this.FirCarDir);
        dest.writeString(this.TrainIn);
        dest.writeString(this.TimeTrainIn);
        dest.writeString(this.TrainOut);
        dest.writeString(this.TimeTrainOut);
    }

    public TrianInfoStateEntity() {
    }

    protected TrianInfoStateEntity(Parcel in) {
        this.TrackCode = in.readString();
        this.TrackName = in.readString();
        this.TrackPla = in.readString();
        this.TrainsetName = in.readString();
        this.Marshal = in.readInt();
        this.IsJoin = in.readByte() != 0;
        this.JoinTrainsetName = in.readString();
        this.FirCarDir = in.readString();
        this.TrainIn = in.readString();
        this.TimeTrainIn = in.readString();
        this.TrainOut = in.readString();
        this.TimeTrainOut = in.readString();
    }

    public static final Parcelable.Creator<TrianInfoStateEntity> CREATOR = new Parcelable.Creator<TrianInfoStateEntity>() {
        @Override
        public TrianInfoStateEntity createFromParcel(Parcel source) {
            return new TrianInfoStateEntity(source);
        }

        @Override
        public TrianInfoStateEntity[] newArray(int size) {
            return new TrianInfoStateEntity[size];
        }
    };
}

package com.gsoft.keyhandover.entity;

/**
 * Created by Administrator on 2018/3/23.
 */

public class TrainsKeyEntity {

    /**
     * trainType : CRH380A
     */

    private String trainType;
    /**
     * trainNumber : CRH2A-2225
     */

    private String trainNumber;
    /**
     * keyName : CRH380A-2660备用
     */

    private String keyName;


    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}

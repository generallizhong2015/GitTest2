package com.gsoft.keyhandover.entity;

/**
 * Created by Administrator on 2018/11/8.
 */

public class Loan_RetrunEntity {


    /**
     * getName : 张三
     * gTime : 1516863087000
     * returnName : 张三
     * rTime : 1516863110000
     * card : 1
     * belt : 1
     */

    private String getName;
    private long gTime;
    private String returnName;
    private long rTime;
    private String card;
    private String belt;

    public String getGetName() {
        return getName;
    }

    public void setGetName(String getName) {
        this.getName = getName;
    }

    public long getGTime() {
        return gTime;
    }

    public void setGTime(long gTime) {
        this.gTime = gTime;
    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public long getRTime() {
        return rTime;
    }

    public void setRTime(long rTime) {
        this.rTime = rTime;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getBelt() {
        return belt;
    }

    public void setBelt(String belt) {
        this.belt = belt;
    }
}

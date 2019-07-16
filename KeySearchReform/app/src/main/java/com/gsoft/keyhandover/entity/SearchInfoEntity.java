package com.gsoft.keyhandover.entity;

/**
 * Created by Administrator on 2018/1/19.
 */

public class SearchInfoEntity {


    /**
     * cardInNumber : 0
     * cardOutNumber : 0
     * beltInNumber : 0
     * beltOutNumber : 0
     */

    private int cardInNumber;
    private int cardOutNumber;
    private int beltInNumber;
    private int beltOutNumber;

    public int getCardInNumber() {
        return cardInNumber;
    }

    public void setCardInNumber(int cardInNumber) {
        this.cardInNumber = cardInNumber;
    }

    public int getCardOutNumber() {
        return cardOutNumber;
    }

    public void setCardOutNumber(int cardOutNumber) {
        this.cardOutNumber = cardOutNumber;
    }

    public int getBeltInNumber() {
        return beltInNumber;
    }

    public void setBeltInNumber(int beltInNumber) {
        this.beltInNumber = beltInNumber;
    }

    public int getBeltOutNumber() {
        return beltOutNumber;
    }

    public void setBeltOutNumber(int beltOutNumber) {
        this.beltOutNumber = beltOutNumber;
    }
}

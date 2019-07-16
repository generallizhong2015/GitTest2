package com.gsoft.keyhandover.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 */

public class TrackAsEntity1 {


    /**
     * TrackInfoList : [{"TrackCode":"0000000003","TrackName":"DJ1"},{"TrackCode":"0000000004","TrackName":"DJ2"},{"TrackCode":"0000000005","TrackName":"DJ3"},{"TrackCode":"0000000006","TrackName":"DJ4"},{"TrackCode":"0000000062","TrackName":"DJ5"},{"TrackCode":"0000000063","TrackName":"DJ6"},{"TrackCode":"0000000064","TrackName":"DJ7"},{"TrackCode":"0000000065","TrackName":"DJ8"}]
     * Desc :
     * Type : 1
     */

    private String Desc;
    private int Type;
    private List<TrackInfoListBean> TrackInfoList;

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public List<TrackInfoListBean> getTrackInfoList() {
        return TrackInfoList;
    }

    public void setTrackInfoList(List<TrackInfoListBean> TrackInfoList) {
        this.TrackInfoList = TrackInfoList;
    }

    public static class TrackInfoListBean {
        /**
         * TrackCode : 0000000003
         * TrackName : DJ1
         */

        private String TrackCode;
        private String TrackName;

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
    }
}

package com.gsoft.keyhandover.entity;

import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */

public class StaffEntity {

    /**
     * StaffEntity : {"StaffInfo":{"StuffCode":"00627001473","StuffName":"宋震"},"UserInfo":{"UserID":"00627004727","UserName":"songzhen","PassWord":"1"},"Depot":{"DeptID":"00627","DeptName":"成都动车段"},"RepairUnit":{"DeptID":"021","DeptName":"成都东运用所"},"MDeptInfo":{"DeptID":"0062700771","DeptName":"机械拆装组"},"JDeptInfo":{"DeptID":null,"DeptName":null},"RoleList":[{"RoleCode":"124","RoleName":"检修车间检修员"}]}
     * Desc :
     * Type : 1
     */

    private StaffEntityBean StaffEntity;
    private String Desc;
    private int Type;

    public StaffEntityBean getStaffEntity() {
        return StaffEntity;
    }

    public void setStaffEntity(StaffEntityBean StaffEntity) {
        this.StaffEntity = StaffEntity;
    }

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

    public static class StaffEntityBean {
        /**
         * StaffInfo : {"StuffCode":"00627001473","StuffName":"宋震"}
         * UserInfo : {"UserID":"00627004727","UserName":"songzhen","PassWord":"1"}
         * Depot : {"DeptID":"00627","DeptName":"成都动车段"}
         * RepairUnit : {"DeptID":"021","DeptName":"成都东运用所"}
         * MDeptInfo : {"DeptID":"0062700771","DeptName":"机械拆装组"}
         * JDeptInfo : {"DeptID":null,"DeptName":null}
         * RoleList : [{"RoleCode":"124","RoleName":"检修车间检修员"}]
         */

        private StaffInfoBean StaffInfo;
        private UserInfoBean UserInfo;
        private DepotBean Depot;
        private RepairUnitBean RepairUnit;
        private MDeptInfoBean MDeptInfo;
        private JDeptInfoBean JDeptInfo;
        private List<RoleListBean> RoleList;

        public StaffInfoBean getStaffInfo() {
            return StaffInfo;
        }

        public void setStaffInfo(StaffInfoBean StaffInfo) {
            this.StaffInfo = StaffInfo;
        }

        public UserInfoBean getUserInfo() {
            return UserInfo;
        }

        public void setUserInfo(UserInfoBean UserInfo) {
            this.UserInfo = UserInfo;
        }

        public DepotBean getDepot() {
            return Depot;
        }

        public void setDepot(DepotBean Depot) {
            this.Depot = Depot;
        }

        public RepairUnitBean getRepairUnit() {
            return RepairUnit;
        }

        public void setRepairUnit(RepairUnitBean RepairUnit) {
            this.RepairUnit = RepairUnit;
        }

        public MDeptInfoBean getMDeptInfo() {
            return MDeptInfo;
        }

        public void setMDeptInfo(MDeptInfoBean MDeptInfo) {
            this.MDeptInfo = MDeptInfo;
        }

        public JDeptInfoBean getJDeptInfo() {
            return JDeptInfo;
        }

        public void setJDeptInfo(JDeptInfoBean JDeptInfo) {
            this.JDeptInfo = JDeptInfo;
        }

        public List<RoleListBean> getRoleList() {
            return RoleList;
        }

        public void setRoleList(List<RoleListBean> RoleList) {
            this.RoleList = RoleList;
        }

        public static class StaffInfoBean {
            /**
             * StuffCode : 00627001473
             * StuffName : 宋震
             */

            private String StuffCode;
            private String StuffName;

            public String getStuffCode() {
                return StuffCode;
            }

            public void setStuffCode(String StuffCode) {
                this.StuffCode = StuffCode;
            }

            public String getStuffName() {
                return StuffName;
            }

            public void setStuffName(String StuffName) {
                this.StuffName = StuffName;
            }
        }

        public static class UserInfoBean {
            /**
             * UserID : 00627004727
             * UserName : songzhen
             * PassWord : 1
             */

            private String UserID;
            private String UserName;
            private String PassWord;

            public String getUserID() {
                return UserID;
            }

            public void setUserID(String UserID) {
                this.UserID = UserID;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }

            public String getPassWord() {
                return PassWord;
            }

            public void setPassWord(String PassWord) {
                this.PassWord = PassWord;
            }
        }

        public static class DepotBean {
            /**
             * DeptID : 00627
             * DeptName : 成都动车段
             */

            private String DeptID;
            private String DeptName;

            public String getDeptID() {
                return DeptID;
            }

            public void setDeptID(String DeptID) {
                this.DeptID = DeptID;
            }

            public String getDeptName() {
                return DeptName;
            }

            public void setDeptName(String DeptName) {
                this.DeptName = DeptName;
            }
        }

        public static class RepairUnitBean {
            /**
             * DeptID : 021
             * DeptName : 成都东运用所
             */

            private String DeptID;
            private String DeptName;

            public String getDeptID() {
                return DeptID;
            }

            public void setDeptID(String DeptID) {
                this.DeptID = DeptID;
            }

            public String getDeptName() {
                return DeptName;
            }

            public void setDeptName(String DeptName) {
                this.DeptName = DeptName;
            }
        }

        public static class MDeptInfoBean {
            /**
             * DeptID : 0062700771
             * DeptName : 机械拆装组
             */

            private String DeptID;
            private String DeptName;

            public String getDeptID() {
                return DeptID;
            }

            public void setDeptID(String DeptID) {
                this.DeptID = DeptID;
            }

            public String getDeptName() {
                return DeptName;
            }

            public void setDeptName(String DeptName) {
                this.DeptName = DeptName;
            }
        }

        public static class JDeptInfoBean {
            /**
             * DeptID : null
             * DeptName : null
             */

            private Object DeptID;
            private Object DeptName;

            public Object getDeptID() {
                return DeptID;
            }

            public void setDeptID(Object DeptID) {
                this.DeptID = DeptID;
            }

            public Object getDeptName() {
                return DeptName;
            }

            public void setDeptName(Object DeptName) {
                this.DeptName = DeptName;
            }
        }

        public static class RoleListBean {
            /**
             * RoleCode : 124
             * RoleName : 检修车间检修员
             */

            private String RoleCode;
            private String RoleName;

            public String getRoleCode() {
                return RoleCode;
            }

            public void setRoleCode(String RoleCode) {
                this.RoleCode = RoleCode;
            }

            public String getRoleName() {
                return RoleName;
            }

            public void setRoleName(String RoleName) {
                this.RoleName = RoleName;
            }
        }
    }
}

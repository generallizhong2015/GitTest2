package com.gsoft.keyhandover.entity;


public class UserEntity {
    public String user_name;//姓名
    public String password;//密码
    public String account;//登陆账号
    public String rfid;//工牌id
    public String phone;//电话号码
    public String gender;//性别
    public String position;//职位
    public String org_name;//店铺名称
    public int id;
    public UserEntity(){

    }
    public UserEntity(String account,String passWord){
        this.account=account;
        this.password=passWord;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", account='" + account + '\'' +
                ", rfid='" + rfid + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", position='" + position + '\'' +
                ", org_name='" + org_name + '\'' +
                '}';
    }

}

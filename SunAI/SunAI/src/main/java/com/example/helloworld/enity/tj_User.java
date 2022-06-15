package com.example.helloworld.enity;


import lombok.Data;

@Data
public class tj_User {
    String user_id;
    String User_name="";
    String introduction="";
    int point=0;
    String user_number="";
    String open_id="";
    String avatar ="";
    String institude ="";

    public String getInstitude() {
        return institude;
    }

    public void setInstitude(String institude) {
        this.institude = institude;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public int getPoint() {
        return point;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        User_name = user_name;
    }
}

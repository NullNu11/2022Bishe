package com.example.helloworld.enity;

import lombok.Data;

import java.util.Date;

@Data
public class tj_user_gift {
    int gift_id;
    String user_id;
    Date acquire_time;

    public String getUser_id() {
        return user_id;
    }

    public int getGift_id() {
        return gift_id;
    }

    public Date getAcquire_time() {
        return acquire_time;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setGift_id(int gift_id) {
        this.gift_id = gift_id;
    }

    public void setAcquire_time(Date acquire_time) {
        this.acquire_time = acquire_time;
    }
}

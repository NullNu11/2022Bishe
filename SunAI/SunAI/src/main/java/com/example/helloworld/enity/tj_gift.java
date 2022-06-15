package com.example.helloworld.enity;

import lombok.Data;

import java.util.Date;

@Data
public class tj_gift {

    int gift_id;
    String gift_name = "";
    String image_url = "";
    int point = 0;
    int stock = 0;
    Date last_time=null;
    String gift_introduction;
    Date gettimes;

    public Date getGettimes() {
        return gettimes;
    }

    public void setGettimes(Date gettimes) {
        this.gettimes = gettimes;
    }

    public int getPoint() {
        return point;
    }

    public Date getLast_time() { return last_time; }

    public int getGift_id() {
        return gift_id;
    }

    public int getStock() {
        return stock;
    }

    public String getGift_name() {
        return gift_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setGift_id(int gift_id) {
        this.gift_id = gift_id;
    }

    public void setGift_name(String gift_name) {
        this.gift_name = gift_name;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setLast_time(Date last_time) {
        this.last_time = last_time;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

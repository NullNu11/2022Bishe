package com.example.helloworld.enity;


import lombok.Data;

@Data

public class Sends {

    int id;

    String kinds;
    int num;

    public int getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public String getKind() {
        return kinds;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKind(String kind) {
        this.kinds = kind;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

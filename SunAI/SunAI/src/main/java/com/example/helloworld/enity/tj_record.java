package com.example.helloworld.enity;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class tj_record {
    /*
     create table tj_record(
record_id int,
user_id varchar(20) not null,
update_time Timestamp,
record_data varchar (200),
primary key(record_id),
foreign key (user_id) references tj_user);*/

    int record_id=0;
    String user_id="";
    String record_data="";
    Date update_time=null;
    String weather="";
    float x_mood,y_mood;


    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setX_mood(float x_mood) {
        this.x_mood = x_mood;
    }

    public void setY_mood(float y_mood) {
        this.y_mood = y_mood;
    }

    public String getWeather() {
        return weather;
    }

    public float getX_mood() {
        return x_mood;
    }

    public float getY_mood() {
        return y_mood;
    }

    public String getUser_id() {
        return user_id;
    }

    public int getRecord_id() {
        return record_id;
    }

    public String getRecord_data() {
        return record_data;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setRecord_id(int record_id) {
        this.record_id = record_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setRecord_data(String record_data) {
        this.record_data = record_data;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}

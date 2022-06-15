package com.example.helloworld.enity;

import lombok.Data;

import java.util.Date;

@Data
public class tj_comment {
    int comment_id;
    String user_id;
    String comment;
    Date time;
    int music_id;

    public String getUser_id() {
        return user_id;
    }

    public Date getTime() {
        return time;
    }

    public int getComment_id() {
        return comment_id;
    }

    public String getComment() {
        return comment;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

package com.example.helloworld.enity;

import lombok.Data;

@Data
public class tj_Question {

    int question_id = 0;
    String question_data = "";
    String question_type = "";
    String answer_6points = "";
    String answer_5points = "";
    String answer_4points = "";
    String answer_3points = "";
    String answer_2points = "";
    String answer_1points = "";
    String answer_0points = "";

    public int getQuestion_id() {
        return question_id;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public String getQuestion_data() {
        return question_data;
    }

    public String getAnswer_0points() {
        return answer_0points;
    }

    public String getAnswer_1points() {
        return answer_1points;
    }

    public String getAnswer_2points() {
        return answer_2points;
    }

    public String getAnswer_3points() {
        return answer_3points;
    }

    public String getAnswer_4points() {
        return answer_4points;
    }

    public String getAnswer_5points() {
        return answer_5points;
    }

    public String getAnswer_6points() {
        return answer_6points;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public void setQuestion_data(String question_data) {
        this.question_data = question_data;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public void setAnswer_0points(String answer_0points) {
        this.answer_0points = answer_0points;
    }

    public void setAnswer_1points(String answer_1points) {
        this.answer_1points = answer_1points;
    }

    public void setAnswer_2points(String answer_2points) {
        this.answer_2points = answer_2points;
    }

    public void setAnswer_3points(String answer_3points) {
        this.answer_3points = answer_3points;
    }

    public void setAnswer_4points(String answer_4points) {
        this.answer_4points = answer_4points;
    }

    public void setAnswer_5points(String answer_5points) {
        this.answer_5points = answer_5points;
    }

    public void setAnswer_6points(String answer_6points) {
        this.answer_6points = answer_6points;
    }
}

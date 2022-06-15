package com.example.helloworld.enity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.jdbc.core.SqlReturnType;

import java.util.Date;

@Data
public class question_answer {

    int answer_id;
    String user_id = "";
    int question_id;
    String questionnaire_id;
    Date update_time;
    String answer_data = "";
    int answer_point;

    public int getAnswer_point() {
        return answer_point;
    }

    public void setAnswer_point(int answer_point) {
        this.answer_point = answer_point;
    }

    public String getQuestionnaire_id() {
        return questionnaire_id;
    }

    public void setQuestionnaire_id(String questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public String getAnswer_data() {
        return answer_data;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public void setAnswer_data(String answer_data) {
        this.answer_data = answer_data;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }
}

package com.example.helloworld.enity;

import lombok.Data;

@Data
public class tj_questionnaire {
    int questionnaire_id;
    String questionnaire_name;
    String questionnaire_time;
    int questionnaire_available;

    public void setQuestionnaire_available(int questionnaire_available) {
        this.questionnaire_available = questionnaire_available;
    }

    public void setQuestionnaire_id(int questionnaire_id) {
        this.questionnaire_id = questionnaire_id;
    }

    public void setQuestionnaire_name(String questionnaire_name) {
        this.questionnaire_name = questionnaire_name;
    }

    public void setQuestionnaire_time(String questionnaire_time) {
        this.questionnaire_time = questionnaire_time;
    }

    public int getQuestionnaire_available() {
        return questionnaire_available;
    }

    public int getQuestionnaire_id() {
        return questionnaire_id;
    }

    public String getQuestionnaire_name() {
        return questionnaire_name;
    }

    public String getQuestionnaire_time() {
        return questionnaire_time;
    }
}

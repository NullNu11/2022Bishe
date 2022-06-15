package com.example.helloworld.service;

import com.example.helloworld.enity.tj_Question;
import com.example.helloworld.enity.question_answer;
import com.example.helloworld.enity.tj_questionnaire;

import java.util.Date;

public interface questionService {
    Boolean addquestion(tj_Question tjQuestion);
    Boolean delete_question(String type);
    tj_Question[] search_question(String type);

    Boolean delete_answer(String user_id,String datetime);
    question_answer[] search_answer(String id);
    Boolean add_answer(question_answer answer);

    tj_questionnaire[] search_questionnaire();
    int find_today(String user_id, String datetime);
}

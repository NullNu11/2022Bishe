package com.example.helloworld.mapper;

import com.example.helloworld.enity.tj_Question;
import com.example.helloworld.enity.question_answer;
import com.example.helloworld.enity.tj_questionnaire;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface questionMapper {
    Boolean add_question(String data, String type, String point6, String point5, String point4, String point3, String point2, String point1, String point0);
    //#{question_id}, #{data},#{type},#{6point},#{5point}，#{4point}，#{3point}，#{2point}，#{1point}，#{0point}
    Boolean delete_question(String type);
    tj_Question[] search_question(String type);

    Boolean delete_answer(String user_id,String datetime);
    question_answer[] search_answer(String id);
    Boolean add_answer(question_answer answer);

    tj_questionnaire[] search_questionnaire();
    int find_today(String user_id, String datetime);
}

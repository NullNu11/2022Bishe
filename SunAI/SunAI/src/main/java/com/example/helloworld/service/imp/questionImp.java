package com.example.helloworld.service.imp;

import com.example.helloworld.enity.tj_Question;
import com.example.helloworld.enity.question_answer;
import com.example.helloworld.enity.tj_questionnaire;
import com.example.helloworld.mapper.questionMapper;
import com.example.helloworld.service.questionService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;

@Service
public class questionImp implements questionService {
    @Override
    public Boolean addquestion(tj_Question tjQu) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean falg = null;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            questionMapper mapper = sqlSession.getMapper(questionMapper.class);
            falg = mapper.add_question(tjQu.getQuestion_data(),tjQu.getQuestion_type(),tjQu.getAnswer_6points(),tjQu.getAnswer_5points(),tjQu.getAnswer_4points()
            ,tjQu.getAnswer_3points(),tjQu.getAnswer_2points(),tjQu.getAnswer_1points(),tjQu.getAnswer_0points());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return falg;
    }

    @Override
    public Boolean delete_question(String type) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean falg = null;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            questionMapper mapper = sqlSession.getMapper(questionMapper.class);
            falg = mapper.delete_question(type);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return falg;
    }

    @Override
    public tj_Question[] search_question(String type) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        tj_Question[] falg = null;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            questionMapper mapper = sqlSession.getMapper(questionMapper.class);
            falg = mapper.search_question(type);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return falg;
    }

    @Override
    public Boolean delete_answer(String user_id, String datetime) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean falg = null;
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            questionMapper mapper = sqlSession.getMapper(questionMapper.class);
            falg = mapper.delete_answer(user_id,datetime);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return falg;
    }

    @Override
    public question_answer[] search_answer(String id) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        question_answer[] falg = null;
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            questionMapper mapper = sqlSession.getMapper(questionMapper.class);
            falg = mapper.search_answer(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return falg;
    }

    @Override
    public Boolean add_answer(question_answer answer) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean falg = null;
        try {
            Date date = new Date(System.currentTimeMillis());
            //答案上传时间
            answer.setUpdate_time(date);
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            questionMapper mapper = sqlSession.getMapper(questionMapper.class);
            falg = mapper.add_answer(answer);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return falg;
    }

    @Override
    public tj_questionnaire[] search_questionnaire() {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        tj_questionnaire[] falg = null;
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            questionMapper mapper = sqlSession.getMapper(questionMapper.class);
            falg = mapper.search_questionnaire();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return falg;
    }

    @Override
    public int find_today(String user_id, String datetime) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        int falg=0;
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            questionMapper mapper = sqlSession.getMapper(questionMapper.class);
            falg = mapper.find_today(user_id,datetime);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return falg;
    }
}

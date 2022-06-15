package com.example.helloworld.service.imp;


import com.example.helloworld.enity.tj_User;
import com.example.helloworld.mapper.userMapper;
import com.example.helloworld.service.loginService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class loginImp implements loginService {
    @Override
    public Boolean register(tj_User user) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean s = null;
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            userMapper mapper = sqlSession.getMapper(userMapper.class);
            s = mapper.register(user);
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
        return s;
    }

    @Override
    public tj_User login(String id) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        tj_User tj_user = new tj_User();
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            userMapper mapper = sqlSession.getMapper(userMapper.class);
            tj_user = mapper.login_User(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tj_user;
    }

    @Override
    public Boolean change_name(String name, String id) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean tj_user = null;
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            userMapper mapper = sqlSession.getMapper(userMapper.class);
            tj_user = mapper.change_name(name, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tj_user;
    }

    @Override
    public Boolean delete_user(String id) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean tj_user = null;
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            userMapper mapper = sqlSession.getMapper(userMapper.class);
            tj_user = mapper.delete_user(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tj_user;
    }

    @Override
    public tj_User[] topthree(int a) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        tj_User[] tj_user = null;
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            userMapper mapper = sqlSession.getMapper(userMapper.class);
            tj_user = mapper.topthree(a);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tj_user;
    }

    @Override
    public int findall() {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        int tj_user = 0;
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            userMapper mapper = sqlSession.getMapper(userMapper.class);
            tj_user = mapper.findall();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tj_user;
    }

    @Override
    public tj_User[] find_all_user() {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        tj_User[] tj_user = null;
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            userMapper mapper = sqlSession.getMapper(userMapper.class);
            tj_user = mapper.find_all_users();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tj_user;
    }

    @Override
    public Boolean change_introd(String id, String intro) {

        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean flag = null;
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            userMapper mapper = sqlSession.getMapper(userMapper.class);
            flag = mapper.change_introd(id,intro);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

}

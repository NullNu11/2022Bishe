package com.example.helloworld.service.imp;

import com.example.helloworld.mapper.pointMapper;
import com.example.helloworld.service.pointService;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.InputStream;
@Service
public class pointImp implements pointService {
    @Override
    public Boolean updatepoint(String id, int point) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean tj_user = null;
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            pointMapper mapper = sqlSession.getMapper(pointMapper.class);
            tj_user = mapper.update_Point(id,point);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tj_user;
    }
}

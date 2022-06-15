package com.example.helloworld.service.imp;

import com.example.helloworld.enity.tj_gift;
import com.example.helloworld.enity.tj_user_gift;
import com.example.helloworld.mapper.giftMapper;
import com.example.helloworld.service.giftService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;

@Service
public class giftImp implements giftService {
    private static final Logger log = LoggerFactory.getLogger(recordImp.class);
    @Override
    public Boolean add_gift(tj_gift gift) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean flag = null;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            giftMapper mapper = sqlSession.getMapper(giftMapper.class);
//          Date date=new Date(System.currentTimeMillis());
//          gift.setLast_time(date);
            flag=mapper.add_gift(gift);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return flag;
    }

    @Override
    public Boolean delete_gift(String id) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean flag = null;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            giftMapper mapper = sqlSession.getMapper(giftMapper.class);
//          Date date=new Date(System.currentTimeMillis());
//          gift.setLast_time(date);
            flag=mapper.delete_gift(id);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return flag;
    }

    @Override
    public Boolean update_gift(tj_gift gift) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean flag = null;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            giftMapper mapper = sqlSession.getMapper(giftMapper.class);
            flag=mapper.update_gift(gift);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return flag;
    }

    @Override
    public tj_gift[] search_gift() {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        tj_gift[] flag = null;
        try {
            Date today = new Date(System.currentTimeMillis());
            //String today=date.toString();
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            giftMapper mapper = sqlSession.getMapper(giftMapper.class);
//          Date date=new Date(System.currentTimeMillis());
//          gift.setLast_time(date);
            flag=mapper.search_gift(today);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return flag;
    }

    @Override
    public Boolean stock_sub(int gift_id) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean flag = null;
        try {
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            giftMapper mapper = sqlSession.getMapper(giftMapper.class);
            flag=mapper.stock_sub(gift_id);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return flag;
    }

    @Override
    public Boolean add_user_gift(tj_user_gift user_gift) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean flag = null;
        try {
            Date date = new Date(System.currentTimeMillis());
            user_gift.setAcquire_time(date);
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            giftMapper mapper = sqlSession.getMapper(giftMapper.class);
            flag=mapper.add_user_gift(user_gift);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return flag;
    }

    @Override
    public Boolean delete_user_gift(tj_user_gift user_gift) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean flag = null;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            giftMapper mapper = sqlSession.getMapper(giftMapper.class);
            flag=mapper.delete_user_gift(user_gift);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return flag;
    }

    @Override
    public tj_user_gift[] search_user_gift_byUser(String user_id) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        tj_user_gift[] flag = null;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            giftMapper mapper = sqlSession.getMapper(giftMapper.class);
            flag=mapper.search_user_gift_byUser(user_id);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return flag;
    }

    @Override
    public tj_user_gift[] search_user_gift_byGift(String gift_id) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        tj_user_gift[] flag = null;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            giftMapper mapper = sqlSession.getMapper(giftMapper.class);
            flag=mapper.search_user_gift_byGift(gift_id);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return flag;
    }
    @Override
    public tj_gift find_one(int id){
        String resources = "mybatis-config.xml";
        InputStream in = null;
        tj_gift flag = null;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            giftMapper mapper = sqlSession.getMapper(giftMapper.class);
            flag=mapper.find_one(id);
        }
        catch (Exception e) {
            //e.printStackTrace();
            log.error("",e);
        }
        return flag;
    }
}

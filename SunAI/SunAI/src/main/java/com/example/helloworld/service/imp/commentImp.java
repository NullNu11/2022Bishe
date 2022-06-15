package com.example.helloworld.service.imp;

import com.example.helloworld.enity.tj_comment;
import com.example.helloworld.mapper.commentMapper;
import com.example.helloworld.mapper.giftMapper;
import com.example.helloworld.service.commentService;
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
public class commentImp implements commentService {
    private static final Logger log = LoggerFactory.getLogger(commentImp.class);
    @Override
    public Boolean add_comnent(tj_comment comment) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean flag = null;
        try {
            Date date = new Date(System.currentTimeMillis());
            comment.setTime(date);
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            commentMapper mapper = sqlSession.getMapper(commentMapper.class);
            flag=mapper.add_comment(comment);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return flag;
    }
}

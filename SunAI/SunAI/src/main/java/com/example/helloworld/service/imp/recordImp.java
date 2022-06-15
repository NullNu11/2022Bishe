package com.example.helloworld.service.imp;

import com.example.helloworld.enity.tj_record;
import com.example.helloworld.mapper.recordMapper;
import com.example.helloworld.service.recordService;
import com.example.helloworld.tools.return_cord_data;
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
public class recordImp implements recordService {
    private static final Logger log = LoggerFactory.getLogger(recordImp.class);

    @Override
    public Boolean add_record(tj_record record) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean flag = null;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            recordMapper mapper = sqlSession.getMapper(recordMapper.class);
            flag=mapper.addrecord(record);
        }
        catch (Exception e) {
             e.printStackTrace();
             log.error("",e);
             //调试阶段imp的日志打出来
            //运行阶段貌似可以直接删掉
            //main记录了日志    同一个error好像不需要记录两次
            //这里的error是 sql异常   main里是null pointer异常
        }
        return flag;
    }

    @Override
    public int search_today(String id, String today) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Integer flag = 0;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            recordMapper mapper = sqlSession.getMapper(recordMapper.class);
            flag=mapper.search_today(id,today);
            if(flag==null)
            {
                flag=0;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
            //调试阶段imp的日志打出来
            //运行阶段貌似可以直接删掉
            //main记录了日志    同一个error好像不需要记录两次
            //这里的error是 sql异常   main里是null pointer异常
        }
        return flag;
    }

    @Override
    public Boolean updata_record(String data, Date update_time, int record_id) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean flag=null;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            recordMapper mapper = sqlSession.getMapper(recordMapper.class);
            flag=mapper.update_record(data,update_time,record_id);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
            //调试阶段imp的日志打出来
            //运行阶段貌似可以直接删掉
            //main记录了日志    同一个error好像不需要记录两次
            //这里的error是 sql异常   main里是null pointer异常
        }
        return flag;
    }

    @Override
    public Boolean delete_record(String id, String delete_date) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        Boolean flag = null;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            recordMapper mapper = sqlSession.getMapper(recordMapper.class);
            flag=mapper.delete_record(id,delete_date);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return flag;
    }

    @Override
    public return_cord_data search_record(String id) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        tj_record flag[] = null;
        return_cord_data returnData = new return_cord_data();
        try {
            int count=0;
            int con=0;
            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            recordMapper mapper = sqlSession.getMapper(recordMapper.class);
            flag=mapper.search_record(id);
            returnData.record=flag;
            //86400000
            //System.out.println(flag[0].getUpdate_time().getTime()-flag[1].getUpdate_time().getTime());
            for(int i=0;i<(flag.length-1)&&i<30;i++)
            {
                count++;
                //今天00-00-00     明天23-23-59
                if(flag[i].getUpdate_time().getTime()-flag[i+1].getUpdate_time().getTime()<(0)&&flag[i].getUpdate_time().getTime()-flag[i+1].getUpdate_time().getTime()>(-86400000*2+1))
                {
                        con++;
                }
            }
            returnData.count=count+1;
            returnData.maxContinue =con+1;
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return returnData;
    }
    @Override
    public int findall(){
        String resources = "mybatis-config.xml";
        InputStream in = null;
        int  flag = 0;
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            recordMapper mapper = sqlSession.getMapper(recordMapper.class);
            flag=mapper.findall();
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return flag;
    }

    @Override
    public String find_max(String user_id) {
        String resources = "mybatis-config.xml";
        InputStream in = null;
        String  flag = "";
        try {

            in = Resources.getResourceAsStream(resources);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
            SqlSession sqlSession = sessionFactory.openSession(true);
            recordMapper mapper = sqlSession.getMapper(recordMapper.class);
            flag=mapper.find_max(user_id);
        }
        catch (Exception e) {
            e.printStackTrace();
            log.error("",e);
        }
        return flag;

    }

}

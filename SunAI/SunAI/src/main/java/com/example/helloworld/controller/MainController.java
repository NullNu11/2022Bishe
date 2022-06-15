package com.example.helloworld.controller;

import com.example.helloworld.enity.Sends;
import com.example.helloworld.enity.tj_User;
import com.example.helloworld.mapper.SendMapper;

import java.net.URL;


import org.springframework.core.io.ClassPathResource;
import com.example.helloworld.tools.result;
import com.example.helloworld.service.loginService;
import com.example.helloworld.service.pointService;
import com.example.helloworld.service.questionService;
import com.example.helloworld.service.recordService;
import oracle.jdbc.driver.OracleDriver;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    loginService login;
    @Autowired
    pointService pointS;
    @Autowired
    questionService questionS;
    @Autowired
    recordService recordS;


    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    //JDBC
    //测试连接查询成功
    //@GetMapping("/test")
    String database() {

        String st = null;
        OracleDriver oracleDriver = new OracleDriver();
        try {

            //jdbc:oracle:thin:@localhost:1521:orcl
            DriverManager.deregisterDriver(oracleDriver);
            Connection connect = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "system", "Oracle501215605");
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from tj_user");
            //if (resultSet.next()) {
            //    st = resultSet.getString(1);
            //}
            st = "";
            while (resultSet.next()) {
                st = st + resultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return st;
    }

    //    @PostMapping("/post")
//    public  Sends testpost(int id){
//
//        Sends sends;//=sendService.findOneSend(id);
//        sends=sendMapper.selectById(id);
//        return sends;
//    }
    //@GetMapping("/mybatis")
    public String text() throws IOException {
        String resources = "mybatis-config.xml";
        InputStream in = Resources.getResourceAsStream(resources);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = sessionFactory.openSession(true);
        SendMapper mapper = sqlSession.getMapper(SendMapper.class);
        List<Sends> send = mapper.selectSend();
        return send.toString();
    }

    //注册api
    @PostMapping("/register")
    public Map register(@RequestBody tj_User user) {

        try {
            if (user.getUser_id() == null || user.getUser_name() == null
                    || user.getUser_id() == "" || user.getUser_name() == "") {
                return result.fail("用户名或ID不能为空");
            } else {
                //先找库里是不是存在
                //在选择插入还是直接登录
                tj_User login_flag = this.login.login(user.getOpen_id());
                //没找到    记录为空
                if (login_flag == null) {
                    Boolean flag = null;
                    user.setAvatar("https://sunai.oss-cn-beijing.aliyuncs.com/2021/06/11/%5D%7D4M6ISNW1IUYY%406A4%24G7NC.png");
                    user.setPoint(0);
                    user.setIntroduction("今天一定会比昨天更开心");
                    flag = this.login.register(user);
                    if (flag) {
                        return result.succ(" 注册成功", user);
                    } else {
                        return result.succ(" 注册失败");
                    }
                } else {
                    return result.fail("该用户已注册，可直接登录");
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("注册失败", e);
            //System.out.println(e.getMessage()+"000000000000000");
            return result.succ("注册失败");
        }
    }

    //使用对象  ok？   以便后面加参数
    @PostMapping("/login")
    public Map login(@RequestBody tj_User user) {
        try {
            if (user.getOpen_id() != null && user.getOpen_id() != "") {
                tj_User flag = login.login(user.getOpen_id());
                if (flag != null) {
                    return result.succ("登录成功", flag);
                } else {
                    return result.fail("登录失败");
                }
            } else {
                return result.fail("ID不能为空，请重新输入");
            }

        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("登录失败", e);
            return result.fail("登录失败");
        }
    }

    @PostMapping("/change_name")
    public Map change_name(@RequestBody tj_User user) {
        try {
            if (user.getUser_id() == null || user.getUser_name() == null
                    || user.getUser_id() == "" || user.getUser_name() == "") {
                return result.fail("用户名或ID不能为空");
            } else {
                Boolean a = login.change_name(user.getUser_name(), user.getUser_id());
                if (a) {
                    return result.fail("用户名修改成功");
                } else {
                    return result.fail("用户名修改失败");
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("用户名修改失败", e);
            return result.succ("用户名修改失败");
        }
    }

    @PostMapping("/delete_user")
    public Map delete_user(@RequestParam String id) {
        try {
            Boolean a = login.delete_user(id);
            if (a) {
                return result.succ("用户删除成功");
            } else {
                return result.fail("用户删除失败");
            }
        } catch (Exception e) {
            LOGGER.error("用户删除失败", e);
            return result.fail("用户删除失败");
        }
    }

    @PostMapping("/update_point")
    public Map update(@RequestParam String id, @RequestParam int point) {
        try {
            Boolean a = pointS.updatepoint(id, point);
            if (a) {
                return result.succ("积分修改成功");
            } else
                return result.fail("积分修改失败");
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("积分修改失败", e);
            return result.fail("积分修改失败");

        }
    }

    @PostMapping("/update_intro")
    public Map updateIntro(@RequestParam String id, @RequestParam String intro) {
        try {
            Boolean a = login.change_introd(id, intro);
            if (a) {
                return result.succ("签名修改成功");
            } else
                return result.fail("签名修改失败");
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("签名修改失败", e);
            return result.fail("签名修改失败");

        }
    }

    @PostMapping("/top")
    public Map topthree(@RequestParam int count) {
        try {
            tj_User[] a = login.topthree(count);

            return result.succ("查找前三成功", a);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查找前三失败", e);
            return result.succ("查找前三失败");

        }
    }

    @GetMapping("/find_all_user")
    public Map findall() {
        try {
            int a = login.findall();
            return result.succ("查找用户数量", a);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查找用户数量", e);
            return result.succ("查找用户数量");

        }
    }


    @GetMapping("/find_user_detail")
    public Map find_user_detail() {
        try {
            tj_User[] a = login.find_all_user();
            return result.succ("查看所有用户成功", a);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.error("查看所有用户失败", e);
            return result.succ("查看所有用户失败");

        }
    }

    @GetMapping("/upload")
    public String upload() {

        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String file1 = MainController.class.getResource("").getPath();
        URL url = null;
        try {
            url = new ClassPathResource("static").getURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = "E:/Src";
        File dest = new File(filePath + fileName);
        System.out.println(fileName + "00000000" + url);
        try {
            file.transferTo(Paths.get(String.valueOf(url)));
            LOGGER.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败！";
    }

    @GetMapping("/test")
    public String test() {

        return "发布 成功";
    }

}

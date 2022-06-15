package com.example.helloworld.mapper;

import com.example.helloworld.enity.tj_User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//注册 登录
public interface userMapper {

    public Boolean register(tj_User tj_user);

    public tj_User login_User(String id);

    public Boolean change_name(String name, String id);

    Boolean delete_user(String id);

    Boolean update_point_add(String id,int point);

    tj_User[] topthree(int a);

    int findall();

    tj_User[] find_all_users();

    Boolean change_introd(String id,String intro);

}

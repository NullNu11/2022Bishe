package com.example.helloworld.service;

import com.example.helloworld.enity.tj_User;

public interface loginService {
    Boolean register(tj_User user);

    tj_User login(String id);

    Boolean change_name(String name, String id);

    Boolean delete_user(String id);

    tj_User[] topthree(int count);

    int findall();

    tj_User[] find_all_user();

    Boolean change_introd(String id, String intro);
}

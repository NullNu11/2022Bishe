package com.example.helloworld;

import com.example.helloworld.tools.WebConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;

@SpringBootTest
class HelloworldApplicationTests {

    @Test
    void contextLoads() {
        String token = new WebConfig().getToken();
        System.out.println(token);
    }

}

package com.example.helloworld.controller;

import com.example.helloworld.enity.tj_comment;
import com.example.helloworld.enity.tj_gift;
import com.example.helloworld.service.commentService;
import com.example.helloworld.tools.result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class commentController {
    private static final Logger log = LoggerFactory.getLogger(commentController.class);
    @Autowired
    commentService comment;
    @PostMapping("/add_comment")
    public Map<String, Object> add_gift(@RequestBody tj_comment c){
        Boolean flag = null;
        try {
            flag = comment.add_comnent(c);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        if (flag) {
            return result.succ("评论添加成功");

        } else {
            return result.fail("评论添加失败");
        }
    }
}

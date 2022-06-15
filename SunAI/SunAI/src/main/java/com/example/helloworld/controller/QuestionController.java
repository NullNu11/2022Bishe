package com.example.helloworld.controller;

import com.example.helloworld.enity.question_answer;
import com.example.helloworld.enity.tj_Question;
import com.example.helloworld.enity.tj_gift;
import com.example.helloworld.enity.tj_questionnaire;
import com.example.helloworld.service.pointService;
import com.example.helloworld.service.questionService;
import com.example.helloworld.tools.result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class QuestionController {

    @Autowired
    questionService questionS;
    @Autowired
    pointService pointS;
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @PostMapping("/add_question")
    public Map add_question(@RequestBody tj_Question[] tjQuestions) {
        int times = 0, succ_times = 0;
        try {
            //null好像没啥必要但是写的好的  成员变量有初始值
            if (tjQuestions[0].getQuestion_data() == null || tjQuestions[0].getQuestion_type() == null
                    || tjQuestions[0].getAnswer_6points() == null || tjQuestions[0].getQuestion_data().equals("")
                    || tjQuestions[0].getQuestion_type().equals("") || tjQuestions[0].getAnswer_6points().equals("")) {
                return result.fail("问题，类型和第一个选项不能为空");

            } else {

                for (tj_Question s : tjQuestions) {
                    Boolean a = questionS.addquestion(s);
                    //System.out.println(s.getQuestion_id());
                    if (a) {
                        //questionS.addquestion(s);
                        succ_times++;
                    }
                    times++;
                }
            }
        } catch (Exception e) {
            log.error("发布问卷失败", e);
            return result.fail("插入失败");
        }
        if (times != succ_times || times == 0) {
            return result.fail("插入失败");
        } else {
            return result.succ("插入成功");
        }
    }

    @GetMapping("/delete_question")
    public Map delete_question(@RequestParam String type) {
        Boolean flag = null;
        try {

            flag = questionS.delete_question(type);
        } catch (Exception e) {
            log.error("问卷删除失败", e);
        }
        if (flag) {
            return result.succ("问卷删除成功");
        } else {
            return result.fail("问卷删除失败");
        }
    }

    @GetMapping("/search_question")
    public Map search_question(@RequestParam String type) {
        tj_Question[] flag = null;
        try {

            flag = questionS.search_question(type);
        } catch (Exception e) {
            log.error("问卷查找失败", e);
        }
        if (flag != null) {
            return result.succ("问卷查找成功", flag);
        } else {
            return result.fail("问卷查找失败");
        }
    }

    @GetMapping("/search_questionnaire")
    public Map search_questionnaire() {
        tj_questionnaire[] flag = null;
        try {

            flag = questionS.search_questionnaire();
        } catch (Exception e) {
            log.error("问卷分类查找失败", e);
        }
        if (flag != null) {
            return result.succ("问卷分类查找成功", flag);
        } else {
            return result.fail("问卷分类查找失败");
        }
    }

    @GetMapping("/search_answer")
    public Map search_answer(@RequestParam String id) {
        question_answer[] flag = null;
        try {
            flag = questionS.search_answer(id);
        } catch (Exception e) {
            log.error("问卷答案查找失败", e);
        }
        if (flag != null) {
            return result.succ("问卷答案查找成功", flag);
        } else {
            return result.fail("问卷答案查找失败");
        }
    }

    @GetMapping("/delete_answer")
    public Map delete_answer(@RequestParam String id, @RequestParam String date) {
        Boolean flag = null;
        try {

            flag = questionS.delete_answer(id, date);
        } catch (Exception e) {
            log.error("问卷答案删除失败", e);
        }
        if (flag != null) {
            return result.succ("问卷答案删除成功", flag);
        } else {
            return result.fail("问卷答案删除失败");
        }
    }

    @GetMapping("/find_today")
    public Map find_today(@RequestParam String id) {
        int flag = 0;
        try {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String today = simpleDateFormat.format(date);
            flag = questionS.find_today(id, today);
            System.out.println(flag);
        } catch (Exception e) {
            log.error("查找失败", e);
        }
        {
            return result.succ("今天有问卷", flag);
        }
    }

    @PostMapping("/add_answer")
    public Map add_answer(@RequestBody question_answer[] answer, @RequestParam String id, @RequestParam String type) {
        Boolean flag = null;
        try {
            for (question_answer answers : answer) {
                answers.setUser_id(id);
                answers.setQuestionnaire_id(type);
                flag = questionS.add_answer(answers);
            }

        } catch (Exception e) {
            log.error("问卷答案上传失败", e);
        }
        if (flag != null) {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String today = simpleDateFormat.format(date);
            int count = questionS.find_today(id, today);
            //System.out.println(count);
            if(count > answer.length){
                //今天打过了不需要再加分
                //pointS.updatepoint(id, 10);
                return result.succ("问卷答案上传成功，今天分数已达上限", flag);
            }
            else {
                pointS.updatepoint(id, 10);
                return result.succ("+10分，问卷答案上传成功", flag);
            }
        } else {
            return result.fail("问卷答案上传失败");
        }
    }
}

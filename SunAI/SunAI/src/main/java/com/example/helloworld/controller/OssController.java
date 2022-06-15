package com.example.helloworld.controller;


import com.example.helloworld.enity.tj_music;
import com.example.helloworld.service.OssService;
import com.example.helloworld.service.recordService;
import com.example.helloworld.tools.result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    OssService ossService;

    @Autowired
    recordService recordS;

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    //上传头像的方法
    @PostMapping
    public Map<String, Object> uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return result.succ(url);
    }

    @PostMapping("/music")
    public Map<String, Object> search_music(@RequestParam String id) {

        String type="";
        String flag = "";
        try {

            flag = recordS.find_max(id);
        } catch (Exception e) {
            log.error("最近打卡信息查询失败", e);
        }
        if (flag != "") {
            //return result.succ("最近打卡信息查询成功",flag);
            int max=5,min=1;
            int ran2 = (int) (Math.random()*(max-min)+min);

            switch (ran2) {
                //火
                case 1:
                   type="徵调音乐";
                    break;
                    //木
                case 2:
                    type="角调音乐";
                    break;
                    //金
                case 3:
                    type="商调音乐";
                    break;
                    //水
                case 4:
                    type="羽调音乐";
                    break;
                    //土
                case 5:
                    type="宫调音乐";
                    break;

            }
            tj_music[] tj_music = ossService.search_music(type);
            return result.succ("查找成功", tj_music);
        } else {
            return result.fail("最近打卡信息查询失败");
        }
    }
}


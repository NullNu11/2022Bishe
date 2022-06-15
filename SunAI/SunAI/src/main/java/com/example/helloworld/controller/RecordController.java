package com.example.helloworld.controller;

import com.example.helloworld.enity.tj_record;
import com.example.helloworld.service.pointService;
import com.example.helloworld.tools.result;
import com.example.helloworld.service.imp.recordImp;
import com.example.helloworld.service.recordService;
import com.example.helloworld.tools.return_cord_data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
public class RecordController {

    @Autowired
    recordService recordS;
    @Autowired
    pointService pointS;
    private static final Logger log = LoggerFactory.getLogger(recordImp.class);

    //    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    //注解式序列化时间有用
    //一天只允许上传一条打卡信息    检查存在   update
    @PostMapping("/add_record")
    public Map add_record(@RequestBody tj_record record) {
        try {
            if (record.getUser_id() == null  ||
                    record.getUser_id() == "") {
                return result.fail("用户ID和打卡内容不能为空");
            } else {
                //查询今天有没有打卡数据
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String today = simpleDateFormat.format(date);
                int tamp = recordS.search_today(record.getUser_id(), today);
                System.out.println("tamp = " + tamp);
                System.out.println("record.getUser_id() = " + record.getUser_id());
                System.out.println("today = " + today);
                record.setUpdate_time(date);//记录对象  添加时间  当下时间
                if(tamp>0){
                    //今天有数据   update
                    Boolean updata_record_flag= recordS.updata_record(record.getRecord_data(),date,tamp);
                    if(updata_record_flag)
                    {
                        return result.succ("打卡信息更新成功");
                    }else {
                        return result.fail("打卡信息更新失败");
                    }
                }
                else {
                    //今天没数据   insert
                    Boolean flag = recordS.add_record(record);
                    if (flag) {
                        pointS.updatepoint(record.getUser_id(), 2);
                        return result.succ("打卡信息上传成功");
                    } else {
                        return result.fail("打卡信息上传失败");
                    }
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            log.error("打卡信息上传失败", e);
            return result.fail("打卡信息上传失败");
        }
    }


    @GetMapping("/delete_record")
    public Map delete(String id, String data)
    {
        Boolean flag = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = data;
            Date date = simpleDateFormat.parse(dateStr);
            flag = recordS.delete_record(id,dateStr);
            System.out.println("data = " + data);
        } catch (Exception e) {
            log.error("记录删除失败", e);
        }
        if (flag) {
            return result.succ("记录删除成功");
        } else {
            return result.fail("记录删除失败");
        }
    }

    @GetMapping("/search_record")
    public Map serach(@RequestParam String id) {
        return_cord_data flag = null;
        try {

            flag = recordS.search_record(id);
        } catch (Exception e) {
            log.error("记录查询失败", e);
        }
        if (flag!=null) {
            return result.succ("记录查询成功",flag);
        } else {
            return result.fail("记录查询失败");
        }
    }

    @GetMapping("/find_all_record")
    public Map findall() {
        int flag = 0;
        try {

            flag = recordS.findall();
        } catch (Exception e) {
            log.error("打卡数量查询失败", e);
        }
        if (flag!=0) {
            return result.succ("打卡数量查询成功",flag);
        } else {
            return result.fail("打卡数量查询失败");
        }
    }

}

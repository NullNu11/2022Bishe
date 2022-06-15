package com.example.helloworld.controller;

import com.example.helloworld.enity.tj_gift;
import com.example.helloworld.enity.tj_user_gift;
import com.example.helloworld.tools.result;
import com.example.helloworld.service.giftService;
import com.example.helloworld.service.imp.recordImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GiftController {


    @Autowired
    giftService giftS;

    private static final Logger log = LoggerFactory.getLogger(recordImp.class);

    @PostMapping("/add_gift")
    public Map<String, Object> add_gift(@RequestBody tj_gift gift) {
        Boolean flag = null;
        try {
            flag = giftS.add_gift(gift);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        if (flag) {
            return result.succ("礼品添加成功");

        } else {
            return result.fail("礼品添加失败");
        }
    }

    @PostMapping("/update_gift")
    public Map update(@RequestBody tj_gift gift) {
        Boolean flag = null;
        try {

            flag = giftS.update_gift(gift);
        } catch (Exception e) {
            log.error("礼品更新失败", e);
        }
        if (flag) {
            return result.succ("礼品更新成功");
        } else {
            return result.fail("礼品更新失败");
        }
    }

    @GetMapping("/delete_gift")
    public Map delete(String id) {
        Boolean flag = null;
        try {

            flag = giftS.delete_gift(id);
        } catch (Exception e) {
            log.error("礼品删除失败", e);
        }
        if (flag) {
            return result.succ("礼品删除成功");
        } else {
            return result.fail("礼品删除失败");
        }
    }

    @GetMapping("/search_gift")
    public Map serach() {
        tj_gift flag[] = null;
        try {
            flag = giftS.search_gift();
        } catch (Exception e) {
            log.error("礼品查询失败", e);
        }
        if (flag != null) {
            return result.succ("礼品查询成功", flag);
        } else {
            return result.fail("礼品查询失败");
        }
    }

    //    Boolean add_user_gift(tj_user_gift user_gift);
//    Boolean delete_user_gift(tj_user_gift user_gift);
//    tj_user_gift[] search_user_gift_byUser(String user_id);
//    tj_user_gift[] search_user_gift_byGift(String gift_id);
    @PostMapping("/add_user_gift")
    public Map addUserGift(@RequestBody tj_user_gift user_gift) {
        Boolean flag = null;
        try {
            flag = giftS.add_user_gift(user_gift);
            giftS.stock_sub(user_gift.getGift_id());
        } catch (Exception e) {
            log.error("礼品用户关系添加失败", e);
        }
        if (flag) {
            return result.succ("礼品用户关系添加成功");
        } else {
            return result.fail("礼品用户关系添加失败");
        }
    }

    @PostMapping("/delete_user_gift")
    public Map delete_user_gift(@RequestBody tj_user_gift user_gift) {
        Boolean flag = null;
        try {

            flag = giftS.delete_user_gift(user_gift);
        } catch (Exception e) {
            log.error("礼品用户关系删除失败", e);
        }
        if (flag) {
            return result.succ("礼品用户关系删除成功");
        } else {
            return result.fail("礼品用户关系删除失败");
        }
    }


    @GetMapping("/search_user_gift_byUser")
    public Map search_user_gift_byUser(@RequestParam String user_id) {
        tj_user_gift[] flag = null;
        tj_gift[] tj_gifts = null;
        try {
            flag = giftS.search_user_gift_byUser(user_id);
            tj_gifts = new tj_gift[flag.length];
            int i=0;
            for (tj_user_gift f:flag) {

                tj_gifts[i]=giftS.find_one(f.getGift_id());
                tj_gifts[i].setGettimes((f.getAcquire_time()));
                i++;
            }
        } catch (Exception e) {
            log.error("通过用户查找礼品失败", e);
        }
        if (tj_gifts!=null) {
            return result.succ("通过用户查找礼品成功",tj_gifts);
        } else {
            return result.fail("通过用户查找礼品失败");
        }
    }

    @GetMapping("/search_user_gift_byGift")
    public Map search_user_gift_byGift(@RequestParam String gift_id) {
        tj_user_gift[] flag = null;
        try {

            flag = giftS.search_user_gift_byGift(gift_id);
        } catch (Exception e) {
            log.error("通过礼品查找失败", e);
        }
        if (flag!=null) {
            return result.succ("通过礼品查找成功",flag);
        } else {
            return result.fail("通过礼品查找失败");
        }
    }

    @GetMapping("/find_one_gift")
    public Map find_one(@RequestParam int id) {
        tj_gift flag = null;
        try {

            flag = giftS.find_one(id);
        } catch (Exception e) {
            log.error("礼品查找失败", e);
        }
        if (flag!=null) {
            return result.succ("礼品查找成功",flag);
        } else {
            return result.fail("礼品查找失败");
        }
    }

}

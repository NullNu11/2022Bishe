package com.example.helloworld.service;

import com.example.helloworld.enity.tj_gift;
import com.example.helloworld.enity.tj_record;
import com.example.helloworld.enity.tj_user_gift;

public interface giftService {
    Boolean add_gift(tj_gift gift);
    Boolean delete_gift(String id);
    Boolean update_gift(tj_gift gift);
    tj_gift[] search_gift();

    Boolean stock_sub(int gift_id);
    tj_gift find_one(int id);

    Boolean add_user_gift(tj_user_gift user_gift);
    Boolean delete_user_gift(tj_user_gift user_gift);
    tj_user_gift[] search_user_gift_byUser(String user_id);
    tj_user_gift[] search_user_gift_byGift(String gift_id);
}

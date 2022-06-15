package com.example.helloworld.mapper;

import com.example.helloworld.enity.tj_gift;
import com.example.helloworld.enity.tj_user_gift;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
@Mapper
public interface giftMapper {
    Boolean add_gift(tj_gift gift);
    Boolean delete_gift(String id);
    Boolean update_gift(tj_gift gift);
    tj_gift[] search_gift(Date today);
    Boolean stock_sub(int gift_id);
    tj_gift find_one(int id);
    /*
    <insert id="add_user_gift">
        insert into tj_user_gift
        values (user_id = #{user_id}, gift_id = #{gift_id}, acquire_time = #{today})
    </insert>
    <delete id="delete_user_gift">
        delete
        from tj_user_gift
        where user_id = #{user_id}
          and gift_id = #{gift_id}
    </delete>
    <select id="search_user_gift_byUser" resultType="tj_user_gift">
         select * from tj_user_gift where user_id=#{user_id}
    </select>

    <select id="search_user_gift_byGift" resultType="tj_user_gift">
        select * from tj_user_gift where gift_id=#{gift_id}
    </select>
    */
    Boolean add_user_gift(tj_user_gift user_gift);
    Boolean delete_user_gift(tj_user_gift user_gift);
    tj_user_gift[] search_user_gift_byUser(String user_id);
    tj_user_gift[] search_user_gift_byGift(String gift_id);
}

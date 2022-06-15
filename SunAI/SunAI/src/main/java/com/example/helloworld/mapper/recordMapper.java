package com.example.helloworld.mapper;

import com.example.helloworld.enity.tj_record;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
@Mapper
public interface recordMapper {
    Boolean addrecord(tj_record record);
    Integer search_today(String user_id, String today);
    Boolean update_record(String data, Date update_time, int record_id);
    Boolean delete_record(String id,String delete_date);
    tj_record[] search_record(String id);
    int findall();
    String find_max(String user_id);
}

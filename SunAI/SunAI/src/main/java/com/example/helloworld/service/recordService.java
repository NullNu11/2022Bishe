package com.example.helloworld.service;

import com.example.helloworld.enity.tj_record;

import com.example.helloworld.tools.return_cord_data;

import java.util.Date;

public interface recordService {
    Boolean add_record(tj_record record);
    int search_today(String id, String today);
    Boolean updata_record(String data,Date update_time,int record_id);

    Boolean delete_record(String id,String delete_date);
    return_cord_data search_record(String id);
    int findall();
    String find_max(String user_id);
}

package com.example.helloworld.mapper;

import com.example.helloworld.enity.tj_music;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface musicMapper {
    tj_music[] search_music(String type);
}

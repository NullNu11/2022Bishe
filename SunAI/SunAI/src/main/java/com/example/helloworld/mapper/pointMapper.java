package com.example.helloworld.mapper;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface pointMapper {
    public Boolean update_Point(String id, int newPoint);
}

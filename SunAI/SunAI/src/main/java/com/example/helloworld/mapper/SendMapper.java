package com.example.helloworld.mapper;


import com.example.helloworld.enity.Sends;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;
@Mapper
public interface SendMapper  {
   public List<Sends>selectSend();
   public Boolean insertUser(String user_id,String user_name,String introduction,int point);
}

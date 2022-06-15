package com.example.helloworld.service;

import com.example.helloworld.enity.tj_music;
import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String uploadFileAvatar(MultipartFile file);

    tj_music[] search_music(String type);

}

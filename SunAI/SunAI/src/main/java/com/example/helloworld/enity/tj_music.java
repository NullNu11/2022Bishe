package com.example.helloworld.enity;

public class tj_music {

    int music_id;
    String music_url;
    String image_url;
    String type;
    String name;
    String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getMusic_id() {
        return music_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getMusic_url() {
        return music_url;
    }

    public String getType() {
        return type;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setMusic_id(int music_id) {
        this.music_id = music_id;
    }

    public void setMusic_url(String music_url) {
        this.music_url = music_url;
    }

    public void setType(String type) {
        this.type = type;
    }

}

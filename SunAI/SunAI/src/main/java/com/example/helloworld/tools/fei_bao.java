package com.example.helloworld.tools;

public class fei_bao {

    public static void main(String[] args) {
        tj_User feifei = new tj_User();
        feifei.user_id="520";

        System.out.println(feifei.user_id);
    }
}


class tj_User {
    String user_id;
    String User_name = "";
    String introduction = "";
    int point = 0;
    String user_number = "";
    String open_id = "";
    String avatar = "";
    String institude = "";
}
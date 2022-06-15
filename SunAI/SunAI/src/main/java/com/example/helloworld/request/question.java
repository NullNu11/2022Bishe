package com.example.helloworld.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class question {

    @GetMapping("/a")
    String index()
    {
        return "redirect:/main";
    }
}

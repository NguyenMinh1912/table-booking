package com.tableorder.tableorder.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String homePage(){
        System.out.printf("Hello");
        return "index.html";
    }


}

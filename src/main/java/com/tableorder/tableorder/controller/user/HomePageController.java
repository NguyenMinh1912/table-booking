package com.tableorder.tableorder.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String redirectHome(Principal principal){
        return "redirect:homePage";
    }

    @GetMapping("/homePage")
    public String homePage(Principal principal){
        return "index.html";
    }

}

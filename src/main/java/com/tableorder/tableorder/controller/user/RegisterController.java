package com.tableorder.tableorder.controller.user;

import com.tableorder.tableorder.model.UserModel;
import com.tableorder.tableorder.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {
    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("register")
    public String registerGuestUserForm(Model model){
        model.addAttribute("user", new UserModel());
        return "register";
    }

    @PostMapping("/register")
    public String registerGuestUser(@ModelAttribute UserModel form, Model model, HttpServletRequest request) {
        model.addAttribute("user", form);
        authService.registerGuestUser(form, request);
        return "redirect:/";
    }


}

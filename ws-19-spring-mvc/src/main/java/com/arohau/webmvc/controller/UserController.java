package com.arohau.webmvc.controller;

import com.arohau.webmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @ModelAttribute("user")
    public User userModel(){
        return new User();
    }

    @GetMapping("/user")
    public String user(@Validated User user, Model model) {
        System.out.println("User Page Requested");
        model.addAttribute("userName", user.getName());
        return "user";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        System.out.println("HomeController GET /registration");
        System.out.println(model);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@Validated @ModelAttribute("user") User user,
                               BindingResult bindingResult, Model model) {
        System.out.println("call register");
        System.out.println(user);
        System.out.println(bindingResult);
        System.out.println(model);
        System.out.println("saving user");
        return "home";
    }

}

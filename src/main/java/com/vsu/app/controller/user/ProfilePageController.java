package com.vsu.app.controller.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/user/profile-page")
public class ProfilePageController {

    @GetMapping
    public String show(){
        return "user/profile-page";
    }
}

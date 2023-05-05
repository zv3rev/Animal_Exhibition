package com.vsu.app.controller;

import com.vsu.app.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = {"","/", "/welcome"})
@AllArgsConstructor
public class WelcomeController {
    ProfileService profileService;

    @GetMapping
    public String show(HttpSession session, Model model){
        if(session.getAttribute("loggedUsername")!=null){
            model.addAttribute("isSignedIn", true);
            model.addAttribute("profile", profileService.get((String) session.getAttribute("loggedUsername")));
        }
        return "welcome";
    }
}

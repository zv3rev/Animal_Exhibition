package com.vsu.app.controller;

import com.vsu.app.dto.ProfileDto;
import com.vsu.app.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@RequestMapping("/sign-in")
public class SignInController {

    private ProfileService profileService;

    @GetMapping
    public String show() {
        return "sign-in";
    }

    @PostMapping
    public String authorizeUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, Model model, HttpSession session) {
        ProfileDto profileDto = profileService.validate(username,password);
        if (profileDto != null){
            session.setAttribute("loggedUsername", profileDto.getUsername());
            session.setAttribute("loggedId", profileDto.getId());
            session.setAttribute("loggedRole", profileDto.getRole().toString());
            return "redirect:/welcome";
        }
        model.addAttribute("isSignInFailed", true);
        return "redirect:/sign-in";
    }
}

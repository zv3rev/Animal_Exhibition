package com.vsu.app.controller;

import com.vsu.app.dto.ProfileDto;
import com.vsu.app.request.CreateProfileRequest;
import com.vsu.app.service.ProfileService;
import com.vsu.app.utilities.ProfileMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/sign-up")
public class SignUpController {
    private ProfileService profileService;
    private ProfileMappingUtils profileMappingUtils;

    @GetMapping
    @ModelAttribute
    public String show(Model model){
        model.addAttribute("profile", new CreateProfileRequest());
        return "sign-up";
    }

    @PostMapping
    public String register(@ModelAttribute("profile") @Valid CreateProfileRequest profileRequest, HttpSession session){
        ProfileDto profileDto = profileMappingUtils.mapToProfileDto(profileRequest);
        if(profileService.add(profileMappingUtils.mapToProfile(profileDto))){
            profileDto = profileService.get(profileDto.getUsername());
            session.setAttribute("loggedUsername", profileDto.getUsername());
            session.setAttribute("loggedId", profileDto.getId());
            session.setAttribute("loggedRole", profileDto.getRole().toString());
            return "redirect:/welcome";
        }

        //TODO: добавить сообщение о неуспешной регистрации
        return "sign-up";
    }
}

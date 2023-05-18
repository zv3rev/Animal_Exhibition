package com.vsu.app.controller;

import com.vsu.app.request.CreateProfileRequest;
import com.vsu.app.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/sign-up")
public class SignUpController {
    private ProfileService profileService;

    @PostMapping
    public boolean signUpProfile(@RequestBody @Valid CreateProfileRequest profileRequest){
        return profileService.add(profileRequest);
    }
}

package com.vsu.app.controller;

import com.vsu.app.dto.ProfileDto;
import com.vsu.app.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class ProfileController {
    private ProfileService profileService;

    @GetMapping("/{username}")
    public ProfileDto getByUsername(@PathVariable String username){
        return profileService.getByUsername(username);
    }
}

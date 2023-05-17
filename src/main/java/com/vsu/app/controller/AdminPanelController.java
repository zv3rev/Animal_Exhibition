package com.vsu.app.controller;

import com.vsu.app.dto.ProfileDto;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.request.EditProfileRequest;
import com.vsu.app.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@AllArgsConstructor
@RestController
@RequestMapping("/admins/{adminId}")
public class AdminPanelController {
    private ProfileService profileService;

    @GetMapping("/users")
    public Collection<ProfileDto> getAllUsers(@PathVariable("adminId") Long adminId) throws UnauthorizedAccessException {
        return profileService.getAll(adminId);
    }

    @DeleteMapping("/users/{userId}")
    public boolean deleteUser(@PathVariable("adminId") Long adminId, @PathVariable("userId") Long userId) throws UnauthorizedAccessException {
        return profileService.delete(userId, adminId);
    }

    @PutMapping("/users/{userId}")
    public ProfileDto editUser(@PathVariable("adminId") Long adminId, @PathVariable("userId") Long userId, @RequestBody @Valid EditProfileRequest editProfileRequest) throws UnauthorizedAccessException {
        return  profileService.edit(userId, adminId, editProfileRequest);
    }
}

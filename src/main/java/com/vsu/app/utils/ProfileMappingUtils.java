package com.vsu.app.utils;

import com.vsu.app.dto.ProfileDto;
import com.vsu.app.entity.Profile;
import com.vsu.app.request.CreateProfileRequest;
import org.springframework.stereotype.Component;

@Component
public class ProfileMappingUtils {
    public ProfileDto mapToProfileDto(Profile profile){
        return new ProfileDto(
                profile.getId(),
                profile.getUsername(),
                profile.getFio(),
                profile.getEmail(),
                profile.getPhone(),
                profile.getRole()
        );
    }

    public Profile mapToProfile(CreateProfileRequest request){
        return Profile.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .fio(request.getFio())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
    }
}

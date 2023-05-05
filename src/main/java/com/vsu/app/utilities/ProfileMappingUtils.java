package com.vsu.app.utilities;

import com.vsu.app.dto.ProfileDto;
import com.vsu.app.entity.Profile;
import com.vsu.app.entity.Role;
import com.vsu.app.request.CreateProfileRequest;
import org.springframework.stereotype.Component;

@Component
public class ProfileMappingUtils {
    public ProfileDto mapToProfileDto(Profile profile){
        return new ProfileDto(
                profile.getId(),
                profile.getUsername(),
                profile.getPassword(),
                profile.getFio(),
                profile.getEmail(),
                profile.getPhone(),
                profile.getRole()
        );
    }

    public Profile mapToProfile(ProfileDto profileDto){
        return Profile.builder()
                .id(profileDto.getId())
                .username(profileDto.getUsername())
                .password(profileDto.getPassword())
                .fio(profileDto.getFio())
                .email(profileDto.getEmail())
                .phone(profileDto.getPhone())
                .role(profileDto.getRole())
                .build();
    }

    public ProfileDto mapToProfileDto(CreateProfileRequest profileRequest) {
        return new ProfileDto(
                0L,
                profileRequest.getUsername(),
                profileRequest.getPassword(),
                profileRequest.getFio(),
                profileRequest.getEmail(),
                profileRequest.getPhone(),
                Role.EXHIBITOR
        );
    }
}

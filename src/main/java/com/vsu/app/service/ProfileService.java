package com.vsu.app.service;

import com.vsu.app.dto.ProfileDto;
import com.vsu.app.entity.Profile;
import com.vsu.app.repository.ProfileRepository;
import com.vsu.app.utilities.ProfileMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProfileService {
    private ProfileRepository profileRepository;
    private ProfileMappingUtils profileMappingUtils;

    public boolean add(Profile profile){
        profile.setPassword(String.valueOf(profile.getPassword().hashCode()));
        return profileRepository.create(profile);
    }

    public ProfileDto validate(String username, String password){
        Profile profile = profileRepository.getByUsername(username);
        if (profile.getPassword().equals(String.valueOf(password.hashCode()))){
            return profileMappingUtils.mapToProfileDto(profile);
        }
        return null;
    }

    public ProfileDto get(String username){
        return profileMappingUtils.mapToProfileDto(profileRepository.getByUsername(username));
    }
}

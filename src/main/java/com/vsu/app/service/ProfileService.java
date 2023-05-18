package com.vsu.app.service;

import com.vsu.app.dto.ProfileDto;
import com.vsu.app.entity.Profile;
import com.vsu.app.entity.Role;
import com.vsu.app.exception.UnauthorizedAccessException;
import com.vsu.app.repository.ProfileRepository;
import com.vsu.app.request.CreateProfileRequest;
import com.vsu.app.request.EditProfileRequest;
import com.vsu.app.utilities.ProfileMappingUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ProfileService {
    private ProfileRepository profileRepository;
    private ProfileMappingUtils profileMappingUtils;

    public boolean add(CreateProfileRequest createProfileRequest){
        Profile profile = profileMappingUtils.mapToProfile(createProfileRequest);
        profile.setPassword(String.valueOf(profile.getPassword().hashCode()));
        profile.setRole(Role.EXHIBITOR);

        return profileRepository.create(profile);
    }

    public ProfileDto getByUsername(String username){
        return profileMappingUtils.mapToProfileDto(profileRepository.getByUsername(username));
    }

    public List<ProfileDto> getAll(Long admin_id) throws UnauthorizedAccessException {
        if (!checkAdminRole(admin_id)){
            throw new UnauthorizedAccessException("Only administrator can get list of all users");
        }

        return profileRepository.getAllUsers().stream()
                .map((profile) -> profileMappingUtils.mapToProfileDto(profile))
                .collect(Collectors.toList());
    }

    public boolean delete(Long id, Long admin_id) throws UnauthorizedAccessException {
        if(!checkAdminRole(admin_id)){
            throw new UnauthorizedAccessException("Only administrator can delete users");
        }

        return profileRepository.delete(id);
    }

    public ProfileDto edit(Long id, Long admin_id, EditProfileRequest editProfileRequest) throws UnauthorizedAccessException {
        if(!checkAdminRole(admin_id)){
            throw new UnauthorizedAccessException("Only administrator can delete users");
        }

        Profile profileToEdit = profileRepository.getById(id);

        profileToEdit.setPassword(String.valueOf(editProfileRequest.getPassword().hashCode()));
        profileToEdit.setFio(editProfileRequest.getFio());
        profileToEdit.setEmail(editProfileRequest.getEmail());
        profileToEdit.setPhone(editProfileRequest.getPhone());
        profileToEdit.setRole(editProfileRequest.getRole());

        if(profileRepository.update(profileToEdit)){
            return profileMappingUtils.mapToProfileDto(profileToEdit);
        }
        else{
            return profileMappingUtils.mapToProfileDto(profileRepository.getById(id));
        }
    }

    //TODO: оставить так или сделать JWT?
    public boolean checkAdminRole(Long admin_id){
        Profile admin = profileRepository.getById(admin_id);
        return admin != null && admin.getRole() == Role.ADMINISTRATOR;
    }


}

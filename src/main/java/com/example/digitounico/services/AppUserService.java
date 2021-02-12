package com.example.digitounico.services;

import com.example.digitounico.entities.AppUser;
import com.example.digitounico.entities.dto.UserRequest;
import com.example.digitounico.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public void create(UserRequest user) {
        appUserRepository.create(user.toAppUser());
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public AppUser findByUid(UUID uuid) {
        return appUserRepository.findByUid(uuid);
    }

    public void updateOrCreate(UUID uid, UserRequest user) {
        var fetchedUser = appUserRepository.findByUid(uid);

        if (nonNull(fetchedUser)) {
            fetchedUser.setName(user.getName());
            fetchedUser.setEmail(user.getEmail());
            appUserRepository.update(fetchedUser);
        } else {
            appUserRepository.create(user.toAppUser(uid));
        }
    }

    public void delete(UUID uuid) {
        appUserRepository.delete(uuid);
    }
}

package com.example.digitounico.services;

import com.example.digitounico.entities.AppUser;
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

    public void create(AppUser user) {
        appUserRepository.create(user);
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public AppUser findByUid(UUID uuid) {
        return appUserRepository.findByUid(uuid);
    }

    public void updateOrCreate(AppUser user) {
        var fetchedUser = appUserRepository.findByUid(user.getUid());

        if (nonNull(fetchedUser)) {
            appUserRepository.update(user);
        } else {
            create(user);
        }
    }

    public void delete(UUID uuid) {
        appUserRepository.delete(uuid);
    }
}

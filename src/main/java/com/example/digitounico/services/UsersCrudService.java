package com.example.digitounico.services;

import com.example.digitounico.entities.AppUser;
import com.example.digitounico.entities.dto.UserRequest;
import com.example.digitounico.exceptions.ApplicationException;
import com.example.digitounico.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.digitounico.exceptions.ApplicationExceptionType.EMAIL_ALREADY_USED;
import static com.example.digitounico.exceptions.ApplicationExceptionType.ENTITY_NOT_FOUND;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class UsersCrudService {

    private final AppUserRepository appUserRepository;

    public void create(AppUser appUser) {
        var fetchedUser = appUserRepository.findByEmail(appUser.getEmail());

        if (nonNull(fetchedUser))
            throw new ApplicationException(EMAIL_ALREADY_USED);

        appUserRepository.create(appUser);
    }

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public AppUser findByUid(UUID uuid) {
        var fetchedUser = appUserRepository.findByUid(uuid);

        if (isNull(fetchedUser))
            throw new ApplicationException(ENTITY_NOT_FOUND);

        return fetchedUser;
    }

    public void updateOrCreate(AppUser user) {
        var fetchedUser = appUserRepository.findByUid(user.getUid());

        if (nonNull(fetchedUser)) {
            fetchedUser.setName(user.getName());
            fetchedUser.setEmail(user.getEmail());
            appUserRepository.update(fetchedUser);
        } else {
            create(fetchedUser);
        }
    }

    public void delete(UUID uuid) {
        appUserRepository.delete(uuid);
    }
}

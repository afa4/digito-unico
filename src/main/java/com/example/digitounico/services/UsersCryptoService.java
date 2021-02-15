package com.example.digitounico.services;

import com.example.digitounico.entities.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersCryptoService {

    private final UsersCrudService usersCrudService;

    public AppUser encrypt(UUID userUid) {
        var user = usersCrudService.findByUid(userUid);

        return null;
    }

    public AppUser decrypt(UUID userUid) {
        var user = usersCrudService.findByUid(userUid);
        return null;
    }
}

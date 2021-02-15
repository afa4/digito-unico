package com.example.digitounico.services;

import com.example.digitounico.entities.AppUser;
import com.example.digitounico.utils.CryptoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersCryptoService {

    private final UsersCrudService usersCrudService;

    public AppUser encrypt(UUID userUid, String base64EncodedPublicKey) {
        var user = usersCrudService.findByUid(userUid);

        user.setName(CryptoUtil.encrypt(user.getName(), base64EncodedPublicKey));
        user.setEmail(CryptoUtil.encrypt(user.getEmail(), base64EncodedPublicKey));

        usersCrudService.update(user);

        return user;
    }

    public AppUser decrypt(UUID userUid,String base64EncodedPrivateKey) {
        var user = usersCrudService.findByUid(userUid);

        user.setName(CryptoUtil.decrypt(user.getName(), base64EncodedPrivateKey));
        user.setEmail(CryptoUtil.decrypt(user.getEmail(), base64EncodedPrivateKey));

        usersCrudService.update(user);

        return user;
    }
}

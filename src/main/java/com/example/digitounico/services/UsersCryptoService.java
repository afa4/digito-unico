package com.example.digitounico.services;

import com.example.digitounico.entities.AppUser;
import com.example.digitounico.exceptions.ApplicationException;
import com.example.digitounico.utils.CryptoUtil;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.digitounico.exceptions.ApplicationExceptionType.USER_DATA_ALREADY_ENCRYPTED;

@Service
@RequiredArgsConstructor
public class UsersCryptoService {

    private final UsersCrudService usersCrudService;

    public AppUser encrypt(UUID userUid, String base64EncodedPublicKey) {

        var user = usersCrudService.findByUid(userUid);

        if (Base64.isBase64(user.getName()))
            throw new ApplicationException(USER_DATA_ALREADY_ENCRYPTED);

        user.setName(CryptoUtil.encrypt(user.getName(), base64EncodedPublicKey));
        user.setEmail(CryptoUtil.encrypt(user.getEmail(), base64EncodedPublicKey));

        return usersCrudService.update(user);
    }

    public AppUser decrypt(UUID userUid, String base64EncodedPrivateKey) {
        var user = usersCrudService.findByUid(userUid);

        user.setName(CryptoUtil.decrypt(user.getName(), base64EncodedPrivateKey));
        user.setEmail(CryptoUtil.decrypt(user.getEmail(), base64EncodedPrivateKey));

        return usersCrudService.update(user);
    }
}

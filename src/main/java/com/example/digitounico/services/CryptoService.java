package com.example.digitounico.services;

import com.example.digitounico.exceptions.ApplicationException;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import static com.example.digitounico.exceptions.ApplicationExceptionType.INVALID_RSA_PUBLIC_KEY;

@Service
public class CryptoService {
    public String encrypt(String value, String rsaPublicKey) {
        PublicKey publicKey = parse(rsaPublicKey);
        return "";
    }

    private PublicKey parse(String rsaPublicKey) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(rsaPublicKey.getBytes());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ApplicationException(INVALID_RSA_PUBLIC_KEY);
        }
    }

    public String decrypt(String value, String rsaPublicKey) {
        return "";
    }
}

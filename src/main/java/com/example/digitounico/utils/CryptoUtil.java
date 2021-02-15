package com.example.digitounico.utils;

import com.example.digitounico.exceptions.ApplicationException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static com.example.digitounico.exceptions.ApplicationExceptionType.*;

public class CryptoUtil {

    public static String encrypt(String data, String rsaPublicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            cipher.init(Cipher.ENCRYPT_MODE, parsePublicKey(rsaPublicKey));

            var base64EncodedResult = Base64.getEncoder().encode(cipher.doFinal(data.getBytes()));
            return new String(base64EncodedResult);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException e) {
            throw new ApplicationException(INTERNAL_ERROR);
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            throw new ApplicationException(INVALID_RSA_PUBLIC_KEY);
        }
    }

    public static String decrypt(String data, String rsaPrivateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            cipher.init(Cipher.DECRYPT_MODE, parsePrivateKey(rsaPrivateKey));

            var base64EncodedData = Base64.getDecoder().decode(data.getBytes());
            return new String(cipher.doFinal(base64EncodedData));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException e) {
            throw new ApplicationException(INTERNAL_ERROR);
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            throw new ApplicationException(INVALID_RSA_PRIVATE_KEY);
        }
    }

    private static PublicKey parsePublicKey(String rsaPublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var decodedKey = Base64.getDecoder().decode(rsaPublicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private static PrivateKey parsePrivateKey(String rsaPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var decodedKey = Base64.getDecoder().decode(rsaPrivateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }
}

package com.example.digitounico.utils;

import com.example.digitounico.exceptions.ApplicationException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import static com.example.digitounico.exceptions.ApplicationExceptionType.INTERNAL_ERROR;
import static com.example.digitounico.exceptions.ApplicationExceptionType.INVALID_RSA_PUBLIC_KEY;

public class CryptoUtil {

    public static String encrypt(String data, String rsaPublicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/None/OAEPWithSHA1AndMGF1Padding");

            PublicKey publicKey = parsePublicKey(rsaPublicKey);

            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            return new String(cipher.doFinal(data.getBytes()));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException e) {
            throw new ApplicationException(INTERNAL_ERROR);
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            throw new ApplicationException(INVALID_RSA_PUBLIC_KEY);
        }
    }

    public static String decrypt(String data, String rsaPrivateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/None/OAEPWithSHA1AndMGF1Padding");

            PrivateKey privateKey = parsePrivateKey(rsaPrivateKey);

            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            return new String(cipher.doFinal(data.getBytes()));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException e) {
            throw new ApplicationException(INTERNAL_ERROR);
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            throw new ApplicationException(INVALID_RSA_PUBLIC_KEY);
        }
    }

    private static PublicKey parsePublicKey(String rsaPublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(rsaPublicKey.getBytes());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    private static PrivateKey parsePrivateKey(String rsaPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(rsaPrivateKey.getBytes());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }
}

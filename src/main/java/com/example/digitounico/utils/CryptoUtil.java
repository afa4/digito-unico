package com.example.digitounico.utils;

import com.example.digitounico.exceptions.ApplicationException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static com.example.digitounico.exceptions.ApplicationExceptionType.*;

public class CryptoUtil {

    private static final int BIT_LENGTH = 2048;

    public static String encrypt(String data, String rsaPublicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            RSAPublicKey publicKey = parsePublicKey(rsaPublicKey);

            if (publicKey.getModulus().bitLength() != BIT_LENGTH)
                throw new ApplicationException(INVALID_RSA_KEY);

            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            var base64EncodedResult = Base64.getEncoder().encode(cipher.doFinal(data.getBytes()));
            return new String(base64EncodedResult);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | BadPaddingException e) {
            throw new ApplicationException(INTERNAL_ERROR);
        } catch (IllegalBlockSizeException e) {
            throw new ApplicationException(TOO_LONG_DATA_TO_BE_ENCRYPTED);
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            throw new ApplicationException(INVALID_RSA_KEY);
        }
    }

    public static String decrypt(String data, String rsaPrivateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            cipher.init(Cipher.DECRYPT_MODE, parsePrivateKey(rsaPrivateKey));

            var base64EncodedData = Base64.getDecoder().decode(data.getBytes());
            return new String(cipher.doFinal(base64EncodedData));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
            throw new ApplicationException(INTERNAL_ERROR);
        } catch (BadPaddingException e) {
            throw new ApplicationException(DECRYPTION_ERROR);
        } catch (IllegalBlockSizeException e) {
            throw new ApplicationException(TOO_LONG_DATA_TO_BE_DECRYPTED);
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            throw new ApplicationException(INVALID_RSA_KEY);
        }
    }

    private static RSAPublicKey parsePublicKey(String rsaPublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var decodedKey = Base64.getDecoder().decode(rsaPublicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    private static PrivateKey parsePrivateKey(String rsaPrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var decodedKey = Base64.getDecoder().decode(rsaPrivateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }
}

package com.example.digitounico.utils;

import com.example.digitounico.exceptions.ApplicationException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CryptoUtilTest {

    /**
     * RSA Key pair generated on https://www.devglan.com/online-tools/rsa-encryption-decryption
     */
    private final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz9RiN/vUvoSgxIX01MMUNNyyW+BxQ2CS0vVYKsf5LR+Eg5W6rCH7IsWhcHbi4tTguA/zVWE6KjjqoP6En47mBjqVeFkmJbeLdjF0dYY2vueJBZ/9Ch8U9vDzLCSFBcWhrlns206shSFB3OzeMozKcL9iCYZSOlypAfGrwSO9J2QxCAxD75RLkJ5RNvWk5uC4QPVrwy/myyTM4lzCY0Yfeav/iR7WNKTuzKWNATJquZVcROQO0qj+JUyhwbgPISy8CTJXTqDd1LGj4VK4yxbowWSk8kLp3uEpdfvMx8tc905fJwGsuEenOpWsjVFimpsCEzJFQ7Ee6DlprkKensnTCwIDAQAB";
    private final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDP1GI3+9S+hKDEhfTUwxQ03LJb4HFDYJLS9Vgqx/ktH4SDlbqsIfsixaFwduLi1OC4D/NVYToqOOqg/oSfjuYGOpV4WSYlt4t2MXR1hja+54kFn/0KHxT28PMsJIUFxaGuWezbTqyFIUHc7N4yjMpwv2IJhlI6XKkB8avBI70nZDEIDEPvlEuQnlE29aTm4LhA9WvDL+bLJMziXMJjRh95q/+JHtY0pO7MpY0BMmq5lVxE5A7SqP4lTKHBuA8hLLwJMldOoN3UsaPhUrjLFujBZKTyQune4Sl1+8zHy1z3Tl8nAay4R6c6layNUWKamwITMkVDsR7oOWmuQp6eydMLAgMBAAECggEAX0Zh8IBGO0DZ4r32S0NTFBjIu03dHqI7LPLEhHhARutX+Gq/tQGu+3XWWJRn1sz4Z+kICHAWFh0ZksybR/pegSSCOTXFQy0thfWOCgKJPmMucaGygt1U4w/6qpXjVY4QXLlk1/TtKerhIsKD6nCYra9O5kbpk0+/OuDreBNGN7PmNPkk+OFiw9SY00JS67Vl8mS/nBRPrqVIvKJAazbnCgLJ4j7YXJ4Usfx4mESKhDGoezms7WggfNpfF/QMihRgYwwjIMKpoo/EI9s9TKq28rWx/XDIX+LYquT12vt4wjRnMfypADZCo5tIkC9zB7EDMJQyVh6jSxZrDh63rHIXYQKBgQD+czU8g2Y+WmfDIBE7ox+uIlGYbvVTA2owwNNTdqQ1Pe78qS9jErRckr/cnGrgdeYZqFyVAqgYoVulymVLnyrm0exUSa8lu5hjKUchx++Muy0AfHu64fp7aHWZWHl4oQ2UYsVRHpBgThDk1zQSb2Xcmut5NYLH5vX3ghUp551m5wKBgQDRGHmxNWNK9d6DHaXiPkOQi9HsVtHgEU6Ati7Ur0nKUtTNY6CPBG/TxJKJqy+bXjMw6icWOpK5GLDW//zelgGbe8+FAWsahTL2dFxs/QTEpeRwY1l0K9/kgNB2To3y8cP3iB0uIDcQtylvcKcM9rgJM4BoPVgUdaS8VVXLmeGCPQKBgQCDwAeas7bnJkx/3kTOCmLG+YIiWaGxQB1l7RH/L3lLECyEqt2cgPzeyiWKB3vXiXSBaIm3pk5/ol0f9TKJ+3ptVdj2s8h60y3OensYZLwGtIxSqiIu2/+kkqwBoKwU599uK85beGXtV76rnzqZKREnLxgqBKkY9N/7LmHi9sirlQKBgC5iKYyf9TjJteSMDSJVAZPtEFJHKCN2c12mDFlt0QaqvT4anaFcMJpffso/ST5D9UMiji0mLSoWShRHkQanbA/btTsiB2pIpfd59I7Qyd/9lOFBiLR2jyIXWScFYgPEkd41OZPj3ZM9rllkOgpsKIMSA6gcA4/2UObeXzvdFONZAoGAZLVkG/q9N65uHqHg+g52mr1Ip49LBI/66Kpmjf0Iunx96KIK6h0/akDtWbnzTHTgCkXU4WFk2kPNT0LlcjC58KshKBKDWFyZREoPJQMi0yZTWs6LMQ17+fWnOf8mKzp2evlCEhIsiRm7bg1ZI23P6SikHFQHiCbaZ4BGVV5PZCg=";

    @Test
    public void shouldEncryptDataBas64Encoded_whenReceivesARSAPublicKey() {
        var data = "Single Digit Application!";

        var result = CryptoUtil.encrypt(data, PUBLIC_KEY);

        assertTrue(Base64.isBase64(result));
    }

    @Test
    public void shouldDecryptEncryptedData() {
        var encrypted = CryptoUtil.encrypt("Single Digit Application!", PUBLIC_KEY);

        var result = CryptoUtil.decrypt(encrypted, PRIVATE_KEY);

        assertEquals("Single Digit Application!", result);
    }

    @Test
    public void shouldThrowApplicationException_whenTriesToEncryptWithANot2048BitLengthKey() {
        var publicKeyWith1024BitLength =
                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC6ABmFLk5XFe97lxXlD8JEtI0cR1nGQP6TaRj40V56+5iB4gye/tZWh8LmG4L2/+6skDWOH6C3Wc8FFCRcx0D43PBMQ9C1UdIYtwSoWLMXsxeI4oBEllu7iua6hMzy94JNBtWB0PvI4aFjK+cG6riFL35WlS0ilq0rv/U81qn/CQIDAQAB";

        assertThrows(ApplicationException.class, () ->
                CryptoUtil.encrypt("Single Digit Application!", publicKeyWith1024BitLength));
    }
}

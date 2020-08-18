package com.f1soft.pki.demo.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author Manjit Shakya <mnzitshakya@gmail.com>
 */
@Slf4j
public class SecretKeyUtil {

    /**
     * Method to generate {@link SecretKey}
     *
     * <p>
     * secretKey be at least 16 bytes
     *
     * Key length must be multiple of 16
     *
     * Key must be 16,24,32 bytes
     *
     * 16 byte = 128 bit
     * 24 byte = 192 bit
     * 32 byte = 256 bit
     *
     * </p>16
     *
     * @return SecretKey
     * @throws Exception
     */
    public static SecretKey getSecretKey(byte[] secretKey) throws Exception {
        if (secretKey.length % 16 == 0 && secretKey.length <= 32) {

            log.debug("Secret Key In String: {}", new String(secretKey, "UTF-8"));

            return new SecretKeySpec(secretKey, "AES");
        } else {
            throw new RuntimeException("AES Keys must 16, 24, 32 in length");
        }
    }

    /**
     * Converts {@link SecretKey} to {@link String} Base64 Encoded Format
     *
     * @param secretKey
     * @return
     * @throws Exception
     */
    public static String secretKeyToBase64EncodedString(SecretKey secretKey) throws Exception {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * Converts Base64 Encoded {@link String} to {@link SecretKey}
     *
     * @param base64EncodedString
     * @return
     * @throws Exception
     */
    public static SecretKey base64EncodedStringToSecretKey(String base64EncodedString) throws Exception {
        return getSecretKey(Base64.getDecoder().decode(base64EncodedString));
    }

    /**
     * Converting {@link String} to {@link SecretKey}
     *
     * @param secretKeyInString
     * @return
     * @throws Exception
     */
    public static SecretKey stringToSecretKey(String secretKeyInString) throws Exception {
        return getSecretKey(secretKeyInString.getBytes("UTF-8"));
    }

}

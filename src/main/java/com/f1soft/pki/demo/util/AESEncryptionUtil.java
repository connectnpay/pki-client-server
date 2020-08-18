package com.f1soft.pki.demo.util;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Slf4j
public class AESEncryptionUtil {

    /**
     * method to encrypt using AES
     *
     * @param strToEncrypt - plaintext
     * @param secretKey
     * @return String - encrypted plaintext
     */
    public static String encrypt(String strToEncrypt, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }

    /**
     * method to decrypt using AES
     *
     * @param strToDecrypt - encoded text
     * @param secretKey
     * @return String - plaintext
     */
    public static String decrypt(String strToDecrypt, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }

}

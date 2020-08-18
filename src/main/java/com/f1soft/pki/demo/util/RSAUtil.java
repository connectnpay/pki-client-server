package com.f1soft.pki.demo.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Slf4j
public class RSAUtil {
    /**
     * Signs data with privateKey
     *
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String generateSignature(byte[] data, String privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(getPrivateKey(privateKey));
        signature.update(data);

        byte[] signatureBytes = signature.sign();

        return Base64.getEncoder().encodeToString(signatureBytes);
    }

    /**
     * Verifies using publicKey
     *
     * @param data
     * @param publicKey
     * @param signature
     * @return
     * @throws Exception
     */
    public static boolean verifySignature(String data, String publicKey, byte[] signature) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(getPublicKey(publicKey));
        publicSignature.update(data.getBytes());

        return publicSignature.verify(signature);
    }

    /**
     * Encrypts {@link String} data using {@link String} publicKey
     *
     * @param data
     * @param publicKey
     * @return java.lang.String
     * @throws Exception
     */
    public static String encrypt(String data, String publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));

        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
    }

    /**
     * Decrypts {@link String} data using {@link String} privateKey
     *
     * @param data
     * @param privateKey
     * @return java.lang.String
     * @throws Exception
     */
    public static String decrypt(String data, String privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));

        return new String(cipher.doFinal(Base64.getDecoder().decode(data)), "UTF-8");
    }

    /**
     * Base64 Encoded private key to {@link PrivateKey}
     *
     * @param base64PrivateKey
     * @return java.security.PrivateKey
     */
    public static PrivateKey getPrivateKey(String base64PrivateKey) {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (Exception e) {
            log.error("Exception : ", e);
            return null;
        }
    }

    /**
     * Base64 Encoded public key to {@link PublicKey}
     *
     * @param base64PublicKey
     * @return java.security.PublicKey
     */
    public static PublicKey getPublicKey(String base64PublicKey) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (Exception e) {
            log.error("Exception : ", e);
            return null;
        }
    }
}

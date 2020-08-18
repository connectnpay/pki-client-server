package com.f1soft.pki.demo.security;

import com.f1soft.pki.demo.dto.RequestWrapper;
import com.f1soft.pki.demo.util.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;

/**
 * @author Manjit Shakya <mnzitshakya@gmail.com>
 */
@Slf4j
public class PKISecurityTool extends Base64Util {

    public static final String CHARSET = "UTF-8";

    /**
     * @param receiverEncryptionPublicKey
     * @param senderSignaturePrivateKey
     * @param data
     * @return
     */
    public static RequestWrapper encryptSigner(String receiverEncryptionPublicKey, String senderSignaturePrivateKey, String data) throws Exception {

        // Random String generator

        String randomString = RandomGeneratorUtil.getAlphaNumericString(32);

        // Generating Secret Key Object from Random String for AES Encryption

        SecretKey secretKey = SecretKeyUtil.getSecretKey(randomString.getBytes(CHARSET));

        // Encrypting data (Base64Encoded encrypted data)

        String aesEncryptedData = AESEncryptionUtil.encrypt(data, secretKey);

        // Encrypting secretKeyInString (Base64Encoded encrypted secret key)

        String encryptedSecretKeyInString = RSAUtil.encrypt(randomString, receiverEncryptionPublicKey);

        // Generating signature (Base64Encoded signature)

        String encodedSignature = RSAUtil.generateSignature(aesEncryptedData.getBytes(CHARSET), senderSignaturePrivateKey);

        // Generating final requestWrapper

        RequestWrapper requestWrapper = RequestWrapper.builder()
                .data(aesEncryptedData)
                .secretKey(encryptedSecretKeyInString)
                .signature(encodedSignature)
                .build();

        return requestWrapper;
    }

    public static String decryptVerifier(String receiverEncryptionPrivateKey, String senderSignaturePublicKey, RequestWrapper requestWrapper) throws Exception {

        // Base64 Decoding signature

        byte[] signatureInByte = Base64Util.decode(requestWrapper.getSignature());

        // Verifying signature

        RSAUtil.verifySignature(requestWrapper.getData(), senderSignaturePublicKey, signatureInByte);

        log.debug("Signature verified");

        // Decrypting secretKey

        String secretKeyInString = RSAUtil.decrypt(requestWrapper.getSecretKey(), receiverEncryptionPrivateKey);

        log.debug("Key decrypted");

        // String secret key to SecretKey

        SecretKey secretKey = SecretKeyUtil.stringToSecretKey(secretKeyInString);

        // Decrypting data

        return AESEncryptionUtil.decrypt(requestWrapper.getData(), secretKey);

    }

}

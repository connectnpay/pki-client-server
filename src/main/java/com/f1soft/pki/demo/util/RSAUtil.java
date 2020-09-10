package com.f1soft.pki.demo.util;

import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;

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
     * Signs data with privateKey
     *
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String generateSignature2(byte[] data, String privateKey) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(data);
        byte[] outputDigest = messageDigest.digest();

        AlgorithmIdentifier sha256Aid = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE);
        DigestInfo di = new DigestInfo(sha256Aid, outputDigest);
        //sign SHA256 with RSA
        Signature rsaSignature = Signature.getInstance("NONEwithRSA");
        rsaSignature.initSign(getPrivateKey(privateKey));
        byte[] encodedDigestInfo = di.toASN1Primitive().getEncoded();
        rsaSignature.update(encodedDigestInfo);
        byte[] signed = rsaSignature.sign();

        System.out.println("hash: " + Base64.getEncoder().encodeToString(outputDigest));

        String signature = Base64.getEncoder().encodeToString(signed);
        System.out.println("signature: " + signature);

        return signature;
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
        boolean verified = publicSignature.verify(signature);
        if (!verified) {
            throw new RuntimeException("Invalid Key.");
        }
        return verified;
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
    public static boolean verifySignature2(String data, String publicKey, byte[] signature) throws Exception {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(data.getBytes());
        byte[] outputDigest = messageDigest.digest();

        AlgorithmIdentifier sha256Aid = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE);
        DigestInfo digestInfo = new DigestInfo(sha256Aid, outputDigest);

        //sign SHA256 with RSA
        Signature rsaSignature = Signature.getInstance("NONEwithRSA");
        byte[] encodedDigestInfo = digestInfo.toASN1Primitive().getEncoded();

        rsaSignature.initVerify(getPublicKey(publicKey));
        rsaSignature.update(encodedDigestInfo);

        System.out.println("hash: " + Base64Util.encode(outputDigest));

        boolean verified = rsaSignature.verify(signature);
        if (!verified) {
            throw new RuntimeException("Invalid Key.");
        }
        return verified;
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

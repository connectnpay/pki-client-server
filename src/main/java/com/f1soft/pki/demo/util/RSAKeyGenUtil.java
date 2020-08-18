package com.f1soft.pki.demo.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */


public class RSAKeyGenUtil {

    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 1; i++) {
            generateKeyPair();
        }
    }

    public static void generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        String serverSignaturePrivateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        String serverSignaturePublicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());

        System.out.println("private final static String serverSignaturePrivateKey = " + "\"" + serverSignaturePrivateKey + "\";");
        System.out.println("private final static String serverSignaturePublicKey = " + "\"" + serverSignaturePublicKey + "\";");

        keyPair = keyPairGenerator.generateKeyPair();
        String serverEncryptionPrivateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        ;
        String serverEncryptionPublicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());

        System.out.println("private final static String serverEncryptionPrivateKey = " + "\"" + serverEncryptionPrivateKey + "\";");
        System.out.println("private final static String serverEncryptionPublicKey = " + "\"" + serverEncryptionPublicKey + "\";");

        keyPair = keyPairGenerator.generateKeyPair();
        String clientSignaturePrivateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        ;
        String clientSignaturePublicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());

        System.out.println("private final static String clientSignaturePrivateKey = " + "\"" + clientSignaturePrivateKey + "\";");
        System.out.println("private final static String clientSignaturePublicKey = " + "\"" + clientSignaturePublicKey + "\";");

        keyPair = keyPairGenerator.generateKeyPair();
        String clientEncryptionPrivateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        ;
        String clientEncryptionPublicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());

        System.out.println("private final static String clientEncryptionPrivateKey = " + "\"" + clientEncryptionPrivateKey + "\";");
        System.out.println("private final static String clientEncryptionPublicKey = " + "\"" + clientEncryptionPublicKey + "\";");

//        System.out.println("INSERT INTO CLIENT_KEY_PAIR (CLIENT_SIGNATURE_PUBLIC_KEY,"
//                + "CLIENT_ENCRYPTION_PUBLIC_KEY,"
//                + "SERVER_SIGNATURE_PRIVATE_KEY,"
//                + "SERVER_SIGNATURE_PUBLIC_KEY,"
//                + "SERVER_ENCRYPTION_PRIVATE_KEY,"
//                + "SERVER_ENCRYPTION_PUBLIC_KEY,"
//                + "CLIENT_ID) VALUES "
//                + "('" + clientSignaturePublicKey + "',"
//                + "'" + clientEncryptionPublicKey + "',"
//                + "'" + serverSignaturePrivateKey + "',"
//                + "'" + serverSignaturePublicKey + "',"
//                + "'" + serverEncryptionPrivateKey + "',"
//                + "'" + serverEncryptionPublicKey + "'"
//                + ",'');"
//        );
    }
}

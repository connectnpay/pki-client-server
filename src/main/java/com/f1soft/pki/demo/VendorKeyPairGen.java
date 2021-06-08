package com.f1soft.pki.demo;

import com.f1soft.pki.demo.util.PEMUtil;
import java.io.IOException;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
@Slf4j
public class VendorKeyPairGen {

    private static String senderSignaturePrivateKey;
    private static String senderSignaturePublicKey;
    private static String senderEncryptionPrivateKey;
    private static String senderEncryptionPublicKey;
    private static String receiverSignaturePrivateKey;
    private static String receiverSignaturePublicKey;
    private static String receiverEncryptionPrivateKey;
    private static String receiverEncryptionPublicKey;

    static {
        //Read from PEM
        try {
            senderSignaturePrivateKey = Base64.getEncoder().encodeToString(PEMUtil.readPrivateKeyFromFile("D:\\keys\\for_sbi\\sbi\\signature\\private_key.pem", "RSA").getEncoded());
            senderSignaturePublicKey = Base64.getEncoder().encodeToString(PEMUtil.readPublicKeyFromFile("D:\\keys\\for_sbi\\sbi\\signature\\public_key.pem", "RSA").getEncoded());
            senderEncryptionPrivateKey = Base64.getEncoder().encodeToString(PEMUtil.readPrivateKeyFromFile("D:\\keys\\for_sbi\\sbi\\encryption\\private_key.pem", "RSA").getEncoded());
            senderEncryptionPublicKey = Base64.getEncoder().encodeToString(PEMUtil.readPublicKeyFromFile("D:\\keys\\for_sbi\\sbi\\encryption\\public_key.pem", "RSA").getEncoded());
            receiverSignaturePrivateKey = Base64.getEncoder().encodeToString(PEMUtil.readPrivateKeyFromFile("D:\\keys\\for_sbi\\cnp\\signature\\private_key.pem", "RSA").getEncoded());
            receiverSignaturePublicKey = Base64.getEncoder().encodeToString(PEMUtil.readPublicKeyFromFile("D:\\keys\\for_sbi\\cnp\\signature\\public_key.pem", "RSA").getEncoded());
            receiverEncryptionPrivateKey = Base64.getEncoder().encodeToString(PEMUtil.readPrivateKeyFromFile("D:\\keys\\for_sbi\\cnp\\encryption\\private_key.pem", "RSA").getEncoded());
            receiverEncryptionPublicKey = Base64.getEncoder().encodeToString(PEMUtil.readPublicKeyFromFile("D:\\keys\\for_sbi\\cnp\\encryption\\public_key.pem", "RSA").getEncoded());

        } catch (IOException e) {
            log.debug("Exception : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("INSERT INTO CLIENT_KEY_PAIR (CLIENT_SIGNATURE_PUBLIC_KEY,"
                + "CLIENT_ENCRYPTION_PUBLIC_KEY,"
                + "SERVER_SIGNATURE_PRIVATE_KEY,"
                + "SERVER_SIGNATURE_PUBLIC_KEY,"
                + "SERVER_ENCRYPTION_PRIVATE_KEY,"
                + "SERVER_ENCRYPTION_PUBLIC_KEY,"
                + "CLIENT_ID) VALUES "
                + "('" + senderSignaturePublicKey + "',"
                + "'" + senderEncryptionPublicKey + "',"
                + "'" + receiverSignaturePrivateKey + "',"
                + "'" + receiverSignaturePublicKey + "',"
                + "'" + receiverEncryptionPrivateKey + "',"
                + "'" + receiverEncryptionPublicKey + "'"
                + ",'');"
        );
    }
}

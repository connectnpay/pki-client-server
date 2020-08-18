package com.f1soft.pki.demo;

import com.f1soft.pki.demo.dto.RequestWrapper;
import com.f1soft.pki.demo.security.PKISecurityTool;
import com.f1soft.pki.demo.util.JacksonUtil;
import com.f1soft.pki.demo.util.PEMUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.util.Base64;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Slf4j
public class TestMain {
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
            senderSignaturePrivateKey = Base64.getEncoder().encodeToString(PEMUtil.readPrivateKeyFromFile("D:\\KeyPair\\server\\signature\\private_key_pkcs8.pem", "RSA").getEncoded());
            senderSignaturePublicKey = Base64.getEncoder().encodeToString(PEMUtil.readPublicKeyFromFile("D:\\KeyPair\\server\\signature\\public_key.pem", "RSA").getEncoded());
            senderEncryptionPrivateKey = Base64.getEncoder().encodeToString(PEMUtil.readPrivateKeyFromFile("D:\\KeyPair\\server\\encryption\\private_key_pkcs8.pem", "RSA").getEncoded());
            senderEncryptionPublicKey = Base64.getEncoder().encodeToString(PEMUtil.readPublicKeyFromFile("D:\\KeyPair\\server\\encryption\\public_key.pem", "RSA").getEncoded());
            receiverSignaturePrivateKey = Base64.getEncoder().encodeToString(PEMUtil.readPrivateKeyFromFile("D:\\KeyPair\\client\\signature\\private_key_pkcs8.pem", "RSA").getEncoded());
            receiverSignaturePublicKey = Base64.getEncoder().encodeToString(PEMUtil.readPublicKeyFromFile("D:\\KeyPair\\client\\signature\\public_key.pem", "RSA").getEncoded());
            receiverEncryptionPrivateKey = Base64.getEncoder().encodeToString(PEMUtil.readPrivateKeyFromFile("D:\\KeyPair\\client\\encryption\\private_key_pkcs8.pem", "RSA").getEncoded());
            receiverEncryptionPublicKey = Base64.getEncoder().encodeToString(PEMUtil.readPublicKeyFromFile("D:\\KeyPair\\client\\encryption\\public_key.pem", "RSA").getEncoded());

        } catch (IOException e) {
            log.debug("Exception : " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();

        String data = "{\"signature\":\"EfaIrzQLVKLNktOzTqLPYpKFBEg6lMBzlPhOIrOgxAhwwz13WTu+cCGR7P+wkFcXuV6hLBLSF+75eKDs\\/Uq8idiTqjRT9r6S0lm9lZNzzYRiunDMisPSZEhhiBnf7DdvY4l8uGd3VeIz0NXX1BATutb5GzfvyS2olDJasLfJ\\/Tb4h5BrPt50gtQwhELAc7eWxpgPWpx6NiwSX5DMNTOHaJQnDlEv7K\\/ssizKZQg5fAkhrk+A0hN7sFoPd7NmlCBjxlC8aPgPxfBa1K0bvURDt1T1NdpW98ox7L3l7zzgDBUgCzK4zolcRTtgCknH0dPt8o\\/uKBu0IPODR9FFu\\/AKJQ==\",\"secretKey\":\"e2BHePoURvoxS5NyQp2gj+7VrP1cKAPCYtkoVFw\\/sAlBmsSXfaNcuPZRxALqReHiuS3aWlvSb69VOO9jPtI3vMtJYbY6Ly7nhW1yJZziKZ3JmTxqhWb0HoyT7cGBz\\/Xg77MH8aZAqhquPy0zJPnNdKeybIppZpspWCdq6E8JFAwyHsVMuQg3fFVJuY\\/LyaGptyEwKa4yQJJP0iu2sL\\/+QaOt4N4MXPawGzIHR7nHsMrpnLiBFDkwAVbUQzZ3oTOC1DGi8A0lMxNVYHTwZ5LR8eELb5KnavtkR2z7G36cBwvzUdVQI4l2kgcWItP63WW624VcXA0n8wNsBtXWoIGKjQ==\",\"data\":\"C9DdNzW\\/f\\/hK307cCqlVJ4kcZd5qw8LzLRqUjbtqLKsGXmYMgBNuzSBvIEh1yNBMTngLAHxQl8ZgOKHF+qFM+bbl7aB2NvqptSXFu9uj194=\",\"clientKey\":\"f1soft\"}";

        RequestWrapper requestWrapper = JacksonUtil.get(data, RequestWrapper.class);


        log.debug("Request Wrapper : {}", data);
        String outputData = PKISecurityTool.decryptVerifier(receiverEncryptionPrivateKey, senderSignaturePublicKey, requestWrapper);

        log.debug("Output Data : {}", outputData);
    }
}

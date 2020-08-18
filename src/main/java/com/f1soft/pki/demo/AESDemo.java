package com.f1soft.pki.demo;

import com.f1soft.pki.demo.util.AESEncryptionUtil;
import com.f1soft.pki.demo.util.RandomGeneratorUtil;
import com.f1soft.pki.demo.util.SecretKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

import javax.crypto.SecretKey;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Slf4j
public class AESDemo {
    public static void main(String[] args) throws Exception{
        BasicConfigurator.configure();
        String data = "Le aba bhayo hai";
        SecretKey secretKey = SecretKeyUtil.stringToSecretKey(RandomGeneratorUtil.getAlphaNumericString(32));
        String encryptedData = AESEncryptionUtil.encrypt(data, secretKey);

        log.debug(encryptedData);

        String output = AESEncryptionUtil.decrypt(encryptedData, secretKey);

        log.debug(output);

    }
}

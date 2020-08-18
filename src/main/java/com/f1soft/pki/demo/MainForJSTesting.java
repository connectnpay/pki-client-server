package com.f1soft.pki.demo;

import com.f1soft.pki.demo.dto.RequestWrapper;
import com.f1soft.pki.demo.security.PKISecurityTool;
import com.f1soft.pki.demo.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.BasicConfigurator;

/**
 * @author Manjit Shakya
 * @email manjit.shakya@f1soft.com
 */
@Slf4j
public class MainForJSTesting {
    private final static String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCP4pGYmFM7sW3S0el1O7/0mBggdvzYdcr79kBW/Qxude4xM3/zH2BsMGmt8p5+k87ynGT96W8R3vcWIL0VgoMcKXgEZiDee/Avand2x9u3IeH4yXqzOvs1uiPugTa0SS5tOWReKubMaIssRTF5mzzON1j8aUWKYOEPQdnCJ0NV+HbCuWRJbZbQ0etCWZdmlpqTdb7PqZM9b67M2RpyxUJzc6VmJdPRo7WKuAv+CwyzATtfTBaW4ARTe8yUq1GqrSRjDVq8eYqa/etk/XwTE6kO57nXFlARbd9J3SP5J7LBWLVW5Bdz4qlHe8418fKib4xnS7lxbRb5xQhhZ2z2Ob4TAgMBAAECggEAZQQhSeuvi9oei4M6D0daleNuFOTU0Tepwcl6FFhmMOr0xnAspUjjDjHTD/+lDKLDCQuJz8XiZ76M5Gkptv9BAzWOADCfZPPIhdGOe8MG24SbPGpfjZOcKHU9osXu3RX/1UkU0RP3o4zGINeXS5QUVYcAH33dB7DKcUa/FhMwsBrJ3i0aIwqwPwkXJDsvlkoMybNmQ86eqgc6hEq5UIDNo8hnYCCUXCIDnCbWxBuuetEGwfyZNniiZhfop3YV7hPijXeDB8ZnIXI/KjteyYGgCyIdynFwhJv2NmoTid+lAezCusud62i8r8Js0caG3bvZqF6oB8SkpnIazDQZttVOIQKBgQDEewAp4lrT9gtvrLFbqvx/3BxU+GQko0Cf5wZpLbGuWbuE4oOiIAZkUTvc5kwxcPSKgEopImJmgS9YmhlRrbMHiTikCckA9llV0M0xLMAp7gFWy7uUQBbrciFTjOc03lNZCnMIJYcjOyVFMOSdn8qeYXXtvkJJ0FGISIaoJyKtgwKBgQC7eM7uXaHhIRWT9rkQqmrJ5Bk79FInblXebBm7/ujejC3lnKeVMzCW2DQe9p2b5jWQyihcnZSyOziAmeegQUqbhZ8CHWnmKnU1v9cFGIsjUfYfOoSp0A6q4DSyBxJVm03BEv4ZsCrAA/xrV0GvRwJiW1yIvHMXNhDcyMUMyhDYMQKBgF+trhS3X5ecV1FYfT2wdCknXLycANg+j3fOnraVQ9Mtaz14uLU4dyRe0pHtq+jfopXXh0WkstpLymyRDgdSWL4wul5XTYSkcjhx55osup8wgYJsz7mPxBl/iG8RTH3YTiosBnxPGhGz+aBKAOopKBgcSbsuTUV4pluBReGYvTBVAoGAUDuT1FAeR8txALJG4esNaPpGEnKt9lIyTc7V9K9T2msD+ZDh5+jQkr5VECtbqK0Nn7nlLD4EtYLC28cCaoOG+qhlNPKBsA/bufUwgO3QHn2laBgTTtnMbTUnWEnOfvTIgikutq++nA8YqJffdLRfQNj48Uw1tWleLh9+tcqFCwECgYBhh57Q0SFKZqdYyIHuy4Pv7W2vkk0SFChq+sxZkOsAhvZWc10TxFHxoQNo+TlFC3YFwiMOBeB1oI7NZXI8zmT6ZPRQPrlq7SOJke/Uab4NE66vChyonVX1CBMru/da1zJqwPJg0SWVQjauLn3ae01ACbKmNYwW+CC7YKrf7q5Vfw==";
    private final static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj+KRmJhTO7Ft0tHpdTu/9JgYIHb82HXK+/ZAVv0MbnXuMTN/8x9gbDBprfKefpPO8pxk/elvEd73FiC9FYKDHCl4BGYg3nvwL2p3dsfbtyHh+Ml6szr7Nboj7oE2tEkubTlkXirmzGiLLEUxeZs8zjdY/GlFimDhD0HZwidDVfh2wrlkSW2W0NHrQlmXZpaak3W+z6mTPW+uzNkacsVCc3OlZiXT0aO1irgL/gsMswE7X0wWluAEU3vMlKtRqq0kYw1avHmKmv3rZP18ExOpDue51xZQEW3fSd0j+SeywVi1VuQXc+KpR3vONfHyom+MZ0u5cW0W+cUIYWds9jm+EwIDAQAB";

    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
//        testRSAEncDec();
//        testSignVerify();
//        testAESEncDec();
//        callEncryptSigner();
        callDecryptVerifier();
    }

    public static void testSignVerify() throws Exception {
        String signature = RSAUtil.generateSignature("Manjit".getBytes(), privateKey);
        boolean verified = RSAUtil.verifySignature("Manjit", publicKey, Base64Util.decode("LfhwAWe2vSi4ucKaKDPPAhyjz/TLBqz2oQAvr7OcqUiFDWqeach1hRf/NZaxR81gxE7TiovG28rIuqIK+p5SX3IyqaV2bWFiz7Bg4GcsQbjtUAu5Dz8pGshxVmlbd4TbBgjQWJpTRZ/GvKDkKXUIi4Ii5tQ8tRukM0RjBngvpZUhCQcWHH3uLULnh8VALs0IvK7FewaAse0Lms6gCWYcifVoFciGUJEXAdXq0W9Da53QHRe9csBi/CBmSruk75U5bK8V+VgimNvKmu7BBBUocroV7xWz/Enoczk8Ys0SpwQhvNB6/hw5xsJ16QzrevOauD//AIaqDMhcGX5JrhwG6A=="));

        System.out.println(signature);
        System.out.println(verified);
    }

    public static void testRSAEncDec() throws Exception {
        String rsaEncrypted = RSAUtil.encrypt("Manjit", publicKey);
        String plaintext = RSAUtil.decrypt("VmjqwRIy87ijWVqWGxHG8UUVTDHt2J6PD35vFudYhmcflD2af42Yma4b6nI99HTOBoIMggDvqCf0qIw3EtGb7SMDh6AhWYvYH9r/FOvKABkHk6EEZXlY91yLAXRu8VYlSvBO1EaBtaYuZcrVubXFKhc4tu+E65C1qHbaXHI0P7TlNVBlcDVtfrGCpEvKL7VXtocpshBESXEKQk6S6nQ1amwA4FL0p2if5ocQr71kl8WuWwz/y4nx3DuDq2zl7buBEFhzzSU70ZItyHGPeBg2UBQmSW9VQsJaBdgmztXf/kRrJPgnnggk9S2zkisVqeyyuSCUAR+f2livnhFP0EK/Ug==", privateKey);

        System.out.println(rsaEncrypted);
        System.out.println(plaintext);
    }

    public static void testAESEncDec() throws Exception {
        String secretKey = RandomGeneratorUtil.getAlphaNumericString(32);

        String aesEncrypted = AESEncryptionUtil.encrypt("Manjit", SecretKeyUtil.stringToSecretKey(secretKey));
        String plaintext = AESEncryptionUtil.decrypt("shWTE6kD9BYpPDOzsiWe1w==", SecretKeyUtil.stringToSecretKey("X0LVZaTbaJQRrr9wbpAdUZrSPnnNeXl7"));

        System.out.println(secretKey);
        System.out.println(aesEncrypted);
        System.out.println(plaintext);
    }

    public static void callEncryptSigner() throws Exception {
        RequestWrapper requestWrapper = PKISecurityTool.encryptSigner(publicKey, privateKey, "Manjit");

        System.out.println(JacksonUtil.getString(requestWrapper));
    }

    public static void callDecryptVerifier() throws Exception {
        String data = "{\"data\":\"ShPVmAeuTeahhyK5fPNp9g==\",\"secretKey\":\"ikjEYBlI8rGFjl644XexCe2ZxzOeB6vV/nD0cDhMmuAtmuxSRBA8wQ3Qsm3/YE0AP4m8T8Lcx48sa1Mv2vQ0Qc8nRQYYBDSx+IjRUVG6q0C63nJ+AJ8lEijbym9VgMLWaSC350t/NRaiAsQcqPGO9Rkb1VO7ygNdoF11z+6P4DkmgAQ6rc7RxnJdMUeWga1CXt3mDKdmBW4ntxDiL40ziu++kM84P5E7LFrURaIsn43S8GMlAfZBz3WRNPN5znOnhqkZSMtL7bSF/VsXBroqXoC4VpBgNVWMwF4imTZLfk7EqMckBr9Qym9zQjSLxYr23oxOZnLpDN36zVObtsWHjQ==\",\"signature\":\"HkUuiqoPhADDpUMPn8TM+8uoJO7UXhcsFW4BgYpWP0svQP+8puqaYVmrjK8sSXMA5kcs+lqKHDUGySoW+JZOTV3cqE58wmQ0olfuWKzVqLvX0bD5rWDFHUMO1p3NYttQS/bkm5+Tr3dlxZFpyM75+O81upc5fjFikJ1uBFEyuEksK8DaOVPPOqOEAED/LR40DPqN++cptg8fvnUXuGh7ScYHDwY7f4v/Zd0FeMOFTqvaGoFhcaor+ctFHI6Ox86obIHJitEKp8/kWW8xSzrd5s/2dGtZdvLtmUIXhyqqe7dw2Lt8jWS59NORAfv0BLRLYjziuYRkq4Oczjot7fHGRQ==\"}";

        RequestWrapper requestWrapper = JacksonUtil.get(data, RequestWrapper.class);

        String plainText = PKISecurityTool.decryptVerifier(privateKey, publicKey, requestWrapper);

        System.out.println(plainText);
    }

}

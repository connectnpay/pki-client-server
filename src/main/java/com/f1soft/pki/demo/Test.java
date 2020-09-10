package com.f1soft.pki.demo;

import com.f1soft.pki.demo.util.RSAUtil;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;

/**
 *
 * @author Manjit Shakya <manjit.shakya@f1soft.com>
 */
public class Test {

    private final static String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCP4pGYmFM7sW3S0el1O7/0mBggdvzYdcr79kBW/Qxude4xM3/zH2BsMGmt8p5+k87ynGT96W8R3vcWIL0VgoMcKXgEZiDee/Avand2x9u3IeH4yXqzOvs1uiPugTa0SS5tOWReKubMaIssRTF5mzzON1j8aUWKYOEPQdnCJ0NV+HbCuWRJbZbQ0etCWZdmlpqTdb7PqZM9b67M2RpyxUJzc6VmJdPRo7WKuAv+CwyzATtfTBaW4ARTe8yUq1GqrSRjDVq8eYqa/etk/XwTE6kO57nXFlARbd9J3SP5J7LBWLVW5Bdz4qlHe8418fKib4xnS7lxbRb5xQhhZ2z2Ob4TAgMBAAECggEAZQQhSeuvi9oei4M6D0daleNuFOTU0Tepwcl6FFhmMOr0xnAspUjjDjHTD/+lDKLDCQuJz8XiZ76M5Gkptv9BAzWOADCfZPPIhdGOe8MG24SbPGpfjZOcKHU9osXu3RX/1UkU0RP3o4zGINeXS5QUVYcAH33dB7DKcUa/FhMwsBrJ3i0aIwqwPwkXJDsvlkoMybNmQ86eqgc6hEq5UIDNo8hnYCCUXCIDnCbWxBuuetEGwfyZNniiZhfop3YV7hPijXeDB8ZnIXI/KjteyYGgCyIdynFwhJv2NmoTid+lAezCusud62i8r8Js0caG3bvZqF6oB8SkpnIazDQZttVOIQKBgQDEewAp4lrT9gtvrLFbqvx/3BxU+GQko0Cf5wZpLbGuWbuE4oOiIAZkUTvc5kwxcPSKgEopImJmgS9YmhlRrbMHiTikCckA9llV0M0xLMAp7gFWy7uUQBbrciFTjOc03lNZCnMIJYcjOyVFMOSdn8qeYXXtvkJJ0FGISIaoJyKtgwKBgQC7eM7uXaHhIRWT9rkQqmrJ5Bk79FInblXebBm7/ujejC3lnKeVMzCW2DQe9p2b5jWQyihcnZSyOziAmeegQUqbhZ8CHWnmKnU1v9cFGIsjUfYfOoSp0A6q4DSyBxJVm03BEv4ZsCrAA/xrV0GvRwJiW1yIvHMXNhDcyMUMyhDYMQKBgF+trhS3X5ecV1FYfT2wdCknXLycANg+j3fOnraVQ9Mtaz14uLU4dyRe0pHtq+jfopXXh0WkstpLymyRDgdSWL4wul5XTYSkcjhx55osup8wgYJsz7mPxBl/iG8RTH3YTiosBnxPGhGz+aBKAOopKBgcSbsuTUV4pluBReGYvTBVAoGAUDuT1FAeR8txALJG4esNaPpGEnKt9lIyTc7V9K9T2msD+ZDh5+jQkr5VECtbqK0Nn7nlLD4EtYLC28cCaoOG+qhlNPKBsA/bufUwgO3QHn2laBgTTtnMbTUnWEnOfvTIgikutq++nA8YqJffdLRfQNj48Uw1tWleLh9+tcqFCwECgYBhh57Q0SFKZqdYyIHuy4Pv7W2vkk0SFChq+sxZkOsAhvZWc10TxFHxoQNo+TlFC3YFwiMOBeB1oI7NZXI8zmT6ZPRQPrlq7SOJke/Uab4NE66vChyonVX1CBMru/da1zJqwPJg0SWVQjauLn3ae01ACbKmNYwW+CC7YKrf7q5Vfw==";
    private final static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj+KRmJhTO7Ft0tHpdTu/9JgYIHb82HXK+/ZAVv0MbnXuMTN/8x9gbDBprfKefpPO8pxk/elvEd73FiC9FYKDHCl4BGYg3nvwL2p3dsfbtyHh+Ml6szr7Nboj7oE2tEkubTlkXirmzGiLLEUxeZs8zjdY/GlFimDhD0HZwidDVfh2wrlkSW2W0NHrQlmXZpaak3W+z6mTPW+uzNkacsVCc3OlZiXT0aO1irgL/gsMswE7X0wWluAEU3vMlKtRqq0kYw1avHmKmv3rZP18ExOpDue51xZQEW3fSd0j+SeywVi1VuQXc+KpR3vONfHyom+MZ0u5cW0W+cUIYWds9jm+EwIDAQAB";

    public static void main(String[] args) throws GeneralSecurityException, IOException {

//        testSignature(RSAUtil.getPrivateKey(privateKey));
        testSignature2(RSAUtil.getPrivateKey(privateKey));

    }

    public static void testSignature(PrivateKey privateKey) throws GeneralSecurityException, IOException {
        System.out.println("\nGreenhandOriginal:");

        String s = "1234";
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(s.getBytes());
        byte[] outputDigest = messageDigest.digest();
        //sign SHA256 with RSA
        Signature rsaSignature = Signature.getInstance("NONEwithRSA");
        rsaSignature.initSign(privateKey);
        rsaSignature.update(outputDigest);
        byte[] signed = rsaSignature.sign();
        System.out.println("SHA-256 + NONEwithRSA signature: " + bytesToHex(signed));

        //compute SHA256withRSA as a single step
        Signature rsaSha256Signature = Signature.getInstance("SHA256withRSA");
        rsaSha256Signature.initSign(privateKey);
        rsaSha256Signature.update(s.getBytes());
        byte[] signed2 = rsaSha256Signature.sign();
        System.out.println("SHA256withRSA signature:         " + bytesToHex(signed2));
    }

    public static void testSignature2(PrivateKey privateKey) throws GeneralSecurityException, IOException {
        System.out.println("\nGreenhandUpdated:");

        String s = "1234";
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(s.getBytes());
        byte[] outputDigest = messageDigest.digest();

        AlgorithmIdentifier sha256Aid = new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256, DERNull.INSTANCE);
        DigestInfo di = new DigestInfo(sha256Aid, outputDigest);
        //sign SHA256 with RSA
        Signature rsaSignature = Signature.getInstance("NONEwithRSA");
        rsaSignature.initSign(privateKey);
        byte[] encodedDigestInfo = di.toASN1Primitive().getEncoded();
        rsaSignature.update(encodedDigestInfo);
        byte[] signed = rsaSignature.sign();
        System.out.println("method 1: " + bytesToHex(signed));
        System.out.println("    hash: " + bytesToHex(outputDigest));
        System.out.println("    algo: " + sha256Aid.getAlgorithm());
        System.out.println("    info: " + bytesToHex(encodedDigestInfo));

        //compute SHA256withRSA as a single step
        Signature rsaSha256Signature = Signature.getInstance("SHA256withRSA");
        rsaSha256Signature.initSign(privateKey);
        rsaSha256Signature.update(s.getBytes());
        byte[] signed2 = rsaSha256Signature.sign();
        System.out.println("method 2: " + bytesToHex(signed2));
    }

    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}

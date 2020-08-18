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
 * @author Manjit Shakya <mnzitshakya@gmail.com>
 */
@Slf4j
public class Main {

    private static String senderSignaturePrivateKey;
    private static String senderSignaturePublicKey;
    private static String senderEncryptionPrivateKey;
    private static String senderEncryptionPublicKey;
    private static String receiverSignaturePrivateKey;
    private static String receiverSignaturePublicKey;
    private static String receiverEncryptionPrivateKey;
    private static String receiverEncryptionPublicKey;


    static {
//
//        // Directly by value
//        senderSignaturePrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCKixfBEBAKrfrRJhEnHEfPmLQ/0BExp+4b/Kd+Xs/mie/Xg7lJqPOx1GNQQXaXnDOE9pcWBP7dmeKfJEibRfoAxAHLAtKXHxDklUPJI+o/P2zat8+ZYJHeX4yHVrRya/UbAm7pooacahSkUrzSUih9NX8DAF/O/wpeMU00FsWko5sqfhr40cQAvbr5MU/Vbz0e6oPPtRzj9e+A7fV95uaIdVEavLFaUZlQ7/8Y3R5q5w1gZA++HQJquTBsr/wd37aMbkErFR2JRE52v98dmsYf8GwzLxpX/sygbtZ40v3vIBqm0Dq6tBIlEKuAekjEGpK/3VoB9W97KkVI+BVCGQtFAgMBAAECggEAFHpgxXmxVVLICxgcWvKULrw8kF4Z3IQc7ugWfdhX1JS47dsbjSa69qMVFuzGMby15MZqKKgOYAT5+VnsnX7naCuQtEgzhoDiB1eE0HtNiZG/bkjTF7GtffgTloGbZHpOnXJroH8UdJQ9fth37oKUV4WLw47PmpIaHzgcWEGxbxdECNwQSWRWuuLzYTpMglqO79tDXkwjdzT9oPHOVVXY6fPi3Id0azxVaxyS640uDOb7Ev9/QEr6yRqkRlokFgA5IHRo9z+ZZMXfdaG+U8XdHgoipMoFikiINHr74Y6nDdtF052W+q/krxF5nvaBofvh3RlAIgwihQYbCpBA6gGEIQKBgQDCqPIV8v3gjkHdV7V8Tnq8vkRYZTF6HqeE70KNavkXSeZue/E8Jh6DCelwwJShujoYiavFBhakbbhEU8dPCPVvzzFncZAkHP9eyuzxvoaW0BxMnenZvbQ6GhZ2iZt1j0hU7G84z6dWM/KKS8yokGGo4sTpx+5kwU6iUxe/WkZ7iQKBgQC2M0RfJ1+oloM0mQ/aEFkg4pzVuXBS5hBeoCqlYZkNEBP6NXKcbhTj0PL/xFdZg2507orHFzWd6dUmGugHv+QJQG81pbyqct540Jrt3CCOPaPZa8Mkh2uerlMUau9RSWXGIJ1Z91d8n5Xuqlrkm+k59wR60OtpF96KtYSzL8W23QKBgAZ7JYRBkN5XHi5Ga052NnPuScw/okQUnTM53BdlRmHWHgvzqHZ+NmnafKE6aDhyQNjsTIHE0CAtXwM3Xt1syO8uENEG4Ouy43W+f+EUnNqMl0SdxVlwjX2ZvMG5lyl02c2qCZH0p/egxyR/nZLHK9k5o6C8/VzxarLQ3WuLj+kxAoGAdPknDRv/KwIujCc0aSCTyCwU2pXqSh6QJeAYYQLuutc2rC2CPnHiEXzFdXU9Sye5U3MOP9Fjx9Um1gbyLn+AzHvEqiGUpa2HrPLodIsdj5R9TAoGR751VnO+vN4GdR51K6UmwGqujUD7hjVyk1CRhHhVNPBKIreDVXRCFLQ38DUCgYEAuGGlAXWdPuDCHorNHQNVc/79pzX1lflC6Fk4yF26Rm49AnJb7S1hXBgzeR4585DaRlRsOlLd9X+uRlys2Ws5U4G5Be84lfZbw/gyEdU1Pjy1+yl0cSO2Hu3RPm/h1nBJGNvNFVAnyw4chs6My0WWU6yvKexMTFkspSi2SRFs+4I=";
//        senderSignaturePublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiosXwRAQCq360SYRJxxHz5i0P9ARMafuG/ynfl7P5onv14O5SajzsdRjUEF2l5wzhPaXFgT+3ZninyRIm0X6AMQBywLSlx8Q5JVDySPqPz9s2rfPmWCR3l+Mh1a0cmv1GwJu6aKGnGoUpFK80lIofTV/AwBfzv8KXjFNNBbFpKObKn4a+NHEAL26+TFP1W89HuqDz7Uc4/XvgO31febmiHVRGryxWlGZUO//GN0eaucNYGQPvh0CarkwbK/8Hd+2jG5BKxUdiUROdr/fHZrGH/BsMy8aV/7MoG7WeNL97yAaptA6urQSJRCrgHpIxBqSv91aAfVveypFSPgVQhkLRQIDAQAB";
//        senderEncryptionPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCh0ghkSd2wjGD6j2SS8VYke8g0m5zbLRHPL7vEdVwN6vZygQaXStSggpD20IW8G+4UaRls9KebYXEJoqvRcp9n7VqRM+ujcukIEYuHXZModPHAK4V3Z7VXtYlTWLTqiEINjrxpP+SISwMjnBVrZoPWb4OqORmfsr3mY3WefoN9sbLBGmSI/CCh705ezHpkf7G7KnxMK9gT6/K2Y2lQp5CKP2tJ0xnHbdBDQPY23/ddGZOMV6xuJQST3Psrx5pu6FBT/ar3QlauL4Y/fLZBV6aSyXMf17IEGRA+iy0gQZa7sFIgWyl9qVho2wRMsKDZWu13VyoKlyz0Ag1yX3nqfPy1AgMBAAECggEACUEdwb6HsyUUFsBEnDK5HnUFyhxsKAy3zAWK031yukkJtxqx6c0Zw+QzrinWvB9MylYIYjbLrxMvh8GJ2fyYKTVlzJAPldVohlGajS9IzzOA7+CNTXrc4Bs9s0qLbi5EuMnE9K/2veSsVUkD4JSITacq/H0vffaW7aoae+n4UyPTLzEm1AmtM8bzQPArciTTH1RmQyg2PfgeumuLZhobwvCCQh2zPSJFH4jCYp9a+H04XXdX2ZKrsJxNHUO8I0hOWUrNJswwEp1hQqgfBj8uiWFayu4LYrAr/iwpRHvS6hzNg5tEO7pFnVb7LTZANts/P0tNog+IS8sFlQfYcAiPMQKBgQDRTs8svKwRzUFGGBsukZXG8LynLHIHM0iovIrf2dQMObFJxcKCDSkl+TPcCHnQPgvLS3JFUEcXCjRRRb0ghdt9UXLPQ85oGtT/U5z2V0YsEZWB3gPMj4Gp73EiL40JbjySDIjbg3BG/IwK3rnRcZWHRTnpE0TsKZGTRxGx/OnGawKBgQDF603P5WntrDvlUAVrW8ZQJFk7m95UsKoSVyEWVExsM02SLF3RunA5mj8+A6i1M84Qvpf+aevGhHfp/x5TLmpsuCZsHwHOaxeZOV85mvWOrxwpPwnVjx+Mq/vBIP1EfuxDMQB9CeXreRvZLWNzh8YTPSRLQUhBqA+qhi+gl/3RXwKBgByc2Gd2N1OLqpOpcfXQwxfaCwATbFcdpJ4kgBuPzJQLQvghx7A9mlVtlrBKnFRiVvxoiOt+yOkWZQaDMRKEGfgsrTQ2Qb4rhNiOlpN6ZMZbdQO21PAe4gvYBrA0reQPJMqWWhMgCbY6ie5meiJuk5FVXlBFaRObryHiAkJBn7eXAoGBAKW1UE3rH5iOJwvkxtTPXygEdu2woCK6U3rNlndSlzXUcnMbUGXjukiQO7kZaSxltndQjIPHfU+V46x4HJS6fQKP4COQhnukMHrqjS727ffL50EldX9nuJuX904I5+RsfDX5lhYarnVLziQ0m1rEZ/P9siCMBQprwsocVU4ZU7rzAoGAHReTyJLjRIbyjmOnfubCM23p1V/am20VM/TL8moUTvVroTyg9C0Krsv4iyo2VvjXBJNYYKqTB4P92ZQ1VBMs6fomdgm70tCcgEBwaz5fpn997CJLLENOFdD/A/vhu+CqscYOOED+l5cv5bhJLMCTDp3tqAeJ1aQ9zjY406pldLU=";
//        senderEncryptionPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAodIIZEndsIxg+o9kkvFWJHvINJuc2y0Rzy+7xHVcDer2coEGl0rUoIKQ9tCFvBvuFGkZbPSnm2FxCaKr0XKfZ+1akTPro3LpCBGLh12TKHTxwCuFd2e1V7WJU1i06ohCDY68aT/kiEsDI5wVa2aD1m+DqjkZn7K95mN1nn6DfbGywRpkiPwgoe9OXsx6ZH+xuyp8TCvYE+vytmNpUKeQij9rSdMZx23QQ0D2Nt/3XRmTjFesbiUEk9z7K8eabuhQU/2q90JWri+GP3y2QVemkslzH9eyBBkQPostIEGWu7BSIFspfalYaNsETLCg2Vrtd1cqCpcs9AINcl956nz8tQIDAQAB";
//        receiverSignaturePrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCrfh7spOVSLJ7KiSpKE7qF7eLmPu90W1QHKpAxJARsHPN5lEZVJG4dqOS6iBZZM9fEN1IgqxqCBm2aVhJnfsH/2PjdRDu/XWfZGCLgQighqyNC+drNpSmLGx3FqUzcUsrl1zwFQFRz+y7qAeCAAmXrR5ug2F5jRDnkcIkoNbIKDsrECkuZ8yyBgdGgucwHV1vVJmzh0iknzME0cRU2mcW4yH1uNKXU6Vu7e0WIu3udpq9txDtfviPbw5vuy7AkvSrbMMMtWDJD9cu6Mm04zNqnqEAh2KZlJn+5eKFml3IStXbPgvZ8ijxDcRymRqNwkezATyVUhlHLqAyeRuUlO0IzAgMBAAECggEAEZefjoEf8T0YkQhfKTg64zBTHXGJWPNhfFa2cgQS3BBgPbGjQWq6TRqdVDFPsZjq4nsWVrmWv43vQmzf+HP9bMSSSwiL5Iatd4zNTxoaSdHnjSBafMwbpeSDOlqywstPeyKISrWdtEQpPOIyaI9WaztuUKLBlAdUtQWCXHidqgBNSJJXypYuKOFu45Sl+Dte/eljRkm00tg3+5QAwD7UCH+x+5t+XocjBdMOfR51rSEHRiHdBOLWPrMjJCmN1vIoU2xX4fK6Y9zViIcpor+Re7wlWW3NHt/e2159Uotbfe6YBobTLC/pDg07hUNwQoGN6tFsJJ891Ex6kD9UZeRuWQKBgQDmPvKl5vn2doM/wROq+Sr0CZMLW3BfmshLWe2bBjTCZcgW+cUjusfud71XTJ6eDpqXatatfMWhDCIyciwFXOw2HPsss2Z9kvwYwKeGvuB5q237yW8IyiUcikcwd3dt9HNY0/Bv2C6B4zzyp4eM+7pgJDPUvKe++exIg7jhJqDLLwKBgQC+rMi8Pn5EISvkRj/UfA3lcVDZ5xA4L2tkdaUWfaIeMewCkGeqPFf40qWCsfeEvOeZeKkfM+R6Ehm+zwlfSlk6SjKRZp2AgGrGwvzwOklu6Qzf34InukSbEvbGOmE+I28DUsb4nBeYys/YG6Y9pBqqQjDoGtgLgbgnYlYXmKyoPQKBgEZS+agEeVp27jWd0lUJT75D4l64qgT5cK3bJMCRz7GEhu3FkhoMpZ5HglRUuanLChqvbiyFDJPY8eT4jEuDCz6n8WvAy9AVAdzJkyGL0WI4IxqhdsF3hIhID+BUzOyPml+KNEjk/ad8cZQn6TX9ePp+dDpI+O9SxIGNgf163+fPAoGBALs+pmnl3M0+3gFv0yLxKBxXaRRzSoRCXO3scdBiU5/fWmsyL/sHA0UAZzSvW+hoMe+OhFZ1+Tq/J7UvT37aXkFVt879vFICydiihw3Jz28xQUlgDkrBKro2p0wG7JO+7DeRRH/DmZCcKfuJO3lw/BaSpcBpDBxQqO1ULkNB1NvpAoGAWvc1gqon8pGb6hLuxFOM/i0X/OT7AlXbRbEU3U7CEK5AZyEnviQCjcmTSUN7Ftxwb/boNuoCDaRRn+c26jHu88/GLQ+kpU84RAKv0ikR6Qi7oD02fLKK3Yo++DWOqu2IyDk+VWwmyf67qnuqKPqqJSkYAwXTBDZE/O3SP3SLBpE=";
//        receiverSignaturePublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAq34e7KTlUiyeyokqShO6he3i5j7vdFtUByqQMSQEbBzzeZRGVSRuHajkuogWWTPXxDdSIKsaggZtmlYSZ37B/9j43UQ7v11n2Rgi4EIoIasjQvnazaUpixsdxalM3FLK5dc8BUBUc/su6gHggAJl60eboNheY0Q55HCJKDWyCg7KxApLmfMsgYHRoLnMB1db1SZs4dIpJ8zBNHEVNpnFuMh9bjSl1Olbu3tFiLt7naavbcQ7X74j28Ob7suwJL0q2zDDLVgyQ/XLujJtOMzap6hAIdimZSZ/uXihZpdyErV2z4L2fIo8Q3EcpkajcJHswE8lVIZRy6gMnkblJTtCMwIDAQAB";
//        receiverEncryptionPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCPUPGwSphNFvorUUKCp/gyOmkDCUPJrX6oghq2LPckuakMZeEN2job3IuBEMFRUIIVVOuo7TuGjs6EmyfLhIDZYj/ljl85uNF9ox8mAUMpRSE7mrYWlpltp5PT+tM+UGNhZdUlJEt+CvAPUdD+HDLXxyf5ZONinB7D9/7ABxiaZgnfb3+Vc5itR9ufTl7jfugxuXQ3+XuQBTwHg6fJGTOlZ66c0JpDIL/BTdICsCpWvvTCp7WppmA4wA0M2WbLFiVt4K9kXYFf78NOT0aGXhs0Z0YkyMdXFnH5LtRt44hOodKuEw7/h7zAMvoI6g5NgFmC8+czGuJ8Il+AI4i1k3gXAgMBAAECggEAdRiy0NKyC9KpRrlXOX/aV9fHs1Zfj6n04eOjxZOJvvT/PYUEVBgpAFi7lxHEkhbQonNHNTfYOgH2lPxckHnL+VHAWWRxQ61RZXDhY2EGqfK0y4mGG4CjDagtLbcz4Cc4e/0DIRy9ndd7+1hjBSyvEgTEEY4wB4L91CqNeAKURJm/PAVZVgl5NKJqBiS1l+w438i/8xrzBab70z+wKOcTZPMZtda0naYJd17JGUuLzcYXluGGvOJd/PEX42ezFzz6D9Vu1K2kjzQ6e4dFIc/f5BWMmuBnfaK5cFiqcK1Pu5jJCgMmVTO4YLbyGRqpu8EbWN7ntHDOa9qwVULtP7ZyWQKBgQDI7mRePkPpnwzjh3gDvG8e4jmQBBN8CrQcHlETH6cpr3IeY4Au+syPW0spwLPMklnjhn8fl2zLJodY+iMf1YZJdlLJiR7l2gIz/pd6xU8EC+jxENbXIVKBXuIpmbWgvTF5PcjUzsGBZyDI6dEkIz42exVLadGR9cXDroxgmn29VQKBgQC2mDP3bgjrZ1jewvMr5oQujLrMKRlWytDno6XtHnv3oYILn2NUdjx4gYv+kOU3ibmImTjRqtHKbcFUzYLgPP4EoQc1bGwZ1XfKON8YmeAGQhGkKlkbc9zfIhgaWGAtkJ+nHmmKSrvtJAkb8mXFum4pxZdrQk+ir3FrNksAB5l/uwKBgQCBQ+zKPKCQQcSW9GYfcTDD26JQpm6qlHA+zro15V0zki2Go1ip4KuNZpw6ZkDF0TmHCNXryw0wvL7CXVoPdt0Hkdm80jxQfyp/rMBjOWCWJ9taiwzMJiHgHNy/CqYwnMahS0BSuBkBWQlceCAWcZ3qitCuTYuHrWfndfnVhE50aQKBgQCcYqtPZZyaEX+Z+ppOpTXcsIdkSGb9YOOfIRgBiDRgmKFK1w45zrJn+3wtYWgQMxMGnUR+vrDDkVEeEo3fBB4DVO9ZoCzNy3i3aBIbfd7TPU7irap3yroHGapPFhX5sH4soe1+lhdyOYTbvLXRraSgfiz7nmWaTmzziCKgOeIL9QKBgDOClw/GdZVfN2/XUHdn9G2gkk87eUHNlxWq2t6gkjY2nl0pfEugSb6q/hhA8w1E72QksHkvXZljpDty59D3qG65MRSzy8UdRLkjSxjOQpbOKj7/KyBhWonUwGNPtNV0A84j0XHezPDjt84th0HgMTq0dpbnTalCPNFCgby6pGp7";
//        receiverEncryptionPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj1DxsEqYTRb6K1FCgqf4MjppAwlDya1+qIIatiz3JLmpDGXhDdo6G9yLgRDBUVCCFVTrqO07ho7OhJsny4SA2WI/5Y5fObjRfaMfJgFDKUUhO5q2FpaZbaeT0/rTPlBjYWXVJSRLfgrwD1HQ/hwy18cn+WTjYpwew/f+wAcYmmYJ329/lXOYrUfbn05e437oMbl0N/l7kAU8B4OnyRkzpWeunNCaQyC/wU3SArAqVr70wqe1qaZgOMANDNlmyxYlbeCvZF2BX+/DTk9Ghl4bNGdGJMjHVxZx+S7UbeOITqHSrhMO/4e8wDL6COoOTYBZgvPnMxrifCJfgCOItZN4FwIDAQAB";

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

        String inputData = "friends\": [\n" +
                "      {\n" +
                "        \"id\": 0,\n" +
                "        \"name\": \"Espinoza Hodge\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Karin Vinson\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Julianne Callahan\"\n" +
                "      }\n" +
                "    ],\n";


        log.debug("---------------------------Client Sending Request---------------------------------");

        log.debug("Input Data : {}", inputData);

        RequestWrapper requestWrapper = PKISecurityTool.encryptSigner(receiverEncryptionPublicKey, senderSignaturePrivateKey, inputData);

        log.debug(JacksonUtil.getString(requestWrapper));

        String outputData = PKISecurityTool.decryptVerifier(receiverEncryptionPrivateKey, senderSignaturePublicKey, requestWrapper);

        log.debug("Output Data : {}", outputData);

        log.debug("Output data is equal to Input Data : {}", outputData.equals(inputData));

        log.debug("---------------------------Server Processing Request---------------------------------");


        log.debug("=====================================================================================");


        log.debug("---------------------------Server Sending Response-----------------------------------");

        log.debug("Input Data : {}", inputData);

        requestWrapper = PKISecurityTool.encryptSigner(senderEncryptionPublicKey, receiverSignaturePrivateKey, inputData);

        log.debug(JacksonUtil.getString(requestWrapper));

        outputData = PKISecurityTool.decryptVerifier(senderEncryptionPrivateKey, receiverSignaturePublicKey, requestWrapper);

        log.debug("Output Data : {}", outputData);

        log.debug("Output data is equal to Input Data : {}", outputData.equals(inputData));

        log.debug("---------------------------Client Processing Response---------------------------------");
    }
}

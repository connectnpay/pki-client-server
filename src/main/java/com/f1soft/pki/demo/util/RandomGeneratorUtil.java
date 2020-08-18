package com.f1soft.pki.demo.util;

import java.util.Random;

/**
 * @author santosh
 */
public class RandomGeneratorUtil {

    final static int[] sizeTable = {0, 9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE};
    private static final String ALPHA = "abcdefghijklmnopqrst";
    private static final String NUM = "0123456789";
    private static Random random = new Random();

    public static int generate(int digit) {
        int highest = sizeTable[digit] + 1;
        int lowest = sizeTable[digit - 1] + 1;

        int generated = random.nextInt(highest);

        if (generated < lowest) {
            generated = generate(digit);
        }

        return generated;
    }

    public static String generateRandomNumber(int alphaLength, int numericLength) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < alphaLength; i++) {
            int ndx = (int) (Math.random() * ALPHA.length());
            sb.append(ALPHA.charAt(ndx));
        }
        for (int i = 0; i < numericLength; i++) {
            int ndx = (int) (Math.random() * NUM.length());
            sb.append(NUM.charAt(ndx));
        }
        return sb.toString();
    }

    /**
     * Generates random text of given size
     *
     * @param n - length for generating random text
     * @return String - random generated text of size n
     */
    public static String getAlphaNumericString(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length() * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }
}

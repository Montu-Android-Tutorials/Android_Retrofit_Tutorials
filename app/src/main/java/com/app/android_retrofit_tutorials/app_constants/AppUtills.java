package com.app.android_retrofit_tutorials.app_constants;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class AppUtills {


    /*
     * returns random integer between minimum and maximum range
     */
    public static int getRandomInteger(int maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }


    public static String encrypt(String message, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}

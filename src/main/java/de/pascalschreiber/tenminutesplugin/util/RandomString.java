package de.pascalschreiber.tenminutesplugin.util;

import java.util.Random;

public class RandomString {

    public static String get(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890ß";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }
}

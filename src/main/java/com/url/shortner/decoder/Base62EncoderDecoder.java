package com.url.shortner.decoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for converting base_10 value to base_62
 */
public class Base62EncoderDecoder {

    private static final String VALID_CHARACTER_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE_62 = VALID_CHARACTER_STRING.length();
    private static final Map<Integer, Character> BASE_62_ENCODING_MAP = new HashMap<>();
    private static final List<Character> CHARACTERS = new ArrayList<>();

    public Base62EncoderDecoder() {
        for (int i = 0; i < BASE_62; i++) {
            final Character character = VALID_CHARACTER_STRING.charAt(i);
            BASE_62_ENCODING_MAP.put(i, character);
            CHARACTERS.add(character);
        }
    }

    /**
     * Method take argument long and return string , encoded to base_62
     *
     * @param number value in base 10 {@link Long}
     * @return value in base 62 {@link String}
     */
    public String baseToBase62(long number) {
        final StringBuilder shortURlSuffix = new StringBuilder();
        while (number > 0) {
            final int reminder = (int) (number % BASE_62);
            shortURlSuffix.append(BASE_62_ENCODING_MAP.get(reminder));
            number = number / BASE_62;
        }
        return shortURlSuffix.reverse().toString();
    }


    /**
     * Method takes argument in base_62 and return in base_10
     *
     * @param subUrl encoded string value {@link String}
     * @return decoded base_10 value {@link Long}
     */
    public Long base62ToBase10(final String subUrl) {
        long urlId = 0L;
        int range = subUrl.length();
        for (final char c : subUrl.toCharArray()) {
            final int index = CHARACTERS.indexOf(c);
            urlId += (index * Math.pow(BASE_62, --range));
        }
        return urlId;
    }
}

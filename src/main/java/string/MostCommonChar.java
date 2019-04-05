package string;

import java.util.HashMap;
import java.util.Map;

public final class MostCommonChar {

    public static void main(String[] args) {

        MostCommonChar mcc = new MostCommonChar();

        System.out.println(mcc.findMostCommonChar("abcde"));
        System.out.println(mcc.findMostCommonChar("aabbccddee"));
        System.out.println(mcc.findMostCommonChar("aabbccddeeb"));
    }

    public char findMostCommonChar(String str) {

        char[] chars = str.toCharArray();

        int max = Integer.MIN_VALUE;
        char mostCommon = ' ';
        Map<Character, Integer> charsCount = new HashMap<>();

        for (char c : chars) {
            int count = charsCount.getOrDefault(c, 0);
            charsCount.put(c, count + 1);
            if (count + 1 > max) {
                max = count + 1;
                mostCommon = c;
            }
        }

        return mostCommon;

    }


}

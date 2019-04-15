package dp;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatedSubSequence {

    private String s;

    private int n;

    public LongestRepeatedSubSequence(String s) {
        this.s = s;
        this.n = s.length();
    }

    public int longestRepeated() {
        String r = removeDuplicates();

        int m = r.length();

        int[][] table = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (r.charAt(i - 1) == s.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Integer.max(table[i][j - 1], table[i - 1][j]);
                }
            }
        }

        return table[m][n];
    }

    public int longest() {
        int[][] table = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j && s.charAt(i - 1) == s.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Integer.max(table[i][j - 1], table[i - 1][j]);
                }
            }
        }

        return table[n][n];
    }

    private String removeDuplicates() {

        Map<Character, Integer> count = new HashMap<>();

        for (char c: s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        for (Map.Entry<Character, Integer> e: count.entrySet()) {
            e.setValue(e.getValue() / 2);
        }

        char[] chars = new char[n];
        int j = 0;
        for (char c: s.toCharArray()) {
            if (count.get(c) > 0) {
                chars[j++] = c;
            }

            count.put(c, count.get(c) - 1);
        }

        return new String(chars, 0, j);
    }

    public static void main(String[] args) {
        LongestRepeatedSubSequence lrs = new LongestRepeatedSubSequence("ATACTCGGA");

        System.out.println(lrs.longestRepeated());
        System.out.println(lrs.longest());
    }
}

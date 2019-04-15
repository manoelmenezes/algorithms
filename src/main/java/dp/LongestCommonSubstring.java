package dp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LongestCommonSubstring {

    private String str1;

    private String str2;

    private int m;

    private int n;

    public LongestCommonSubstring(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
        this.m = str1.length();
        this.n = str2.length();
    }

    public List<String> longestSubstring() {
        int[][] table = new int[m + 1][n + 1];

        int[] maxLength = {0};
        List<Integer> endIndex = new ArrayList<>();

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {

                    table[i][j] = table[i - 1][j - 1] + 1;

                    if (table[i][j] > maxLength[0]) {
                        endIndex.clear();
                        endIndex.add(i);
                        maxLength[0] = table[i][j];
                    } else if (table[i][j] == maxLength[0]) {
                        endIndex.add(i);
                    }
                }
            }
        }

        return endIndex.stream()
                .map(i -> str1.substring(i - maxLength[0], i))
                .collect(Collectors.toList());

    }

    public static void main(String[] args) {
        LongestCommonSubstring lcs = new LongestCommonSubstring("ABCXYZ", "AABCSXYZ");

        System.out.println(lcs.longestSubstring());
    }


}
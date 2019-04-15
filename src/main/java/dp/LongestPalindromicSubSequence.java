package dp;

public class LongestPalindromicSubSequence {

    private String s;

    private int n;

    public LongestPalindromicSubSequence(String s) {
        this.s = s;
        this.n = s.length();
    }

    public int longestPalindromicSubSequence() {
        String r = reverse();

        int[][] table = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == r.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Integer.max(table[i][j - 1], table[i - 1][j]);
                }
            }
        }

        return table[n][n];
    }

    public int longestPalindromicSubSequenceRecursive() {
        return doLongestRecursive(0, n - 1);
    }

    private int doLongestRecursive(int i, int j) {
        if (i > j) {
            return 0;
        }

        if (i == j) {
            return 1;
        }

        if (s.charAt(i) == s.charAt(j)) {
            return 2 + doLongestRecursive(i + 1, j - 1);
        }

        return Integer.max(doLongestRecursive(i, j - 1), doLongestRecursive(i + 1, j));
    }

    private String reverse() {
        char[] chars = s.toCharArray();

        for (int i = 0; i < n / 2; i++) {
            char c = chars[i];
            chars[i] = chars[n - i - 1];
            chars[n - i - 1] = c;
        }

        return new String(chars);
    }

    public static void main(String[] args) {
        LongestPalindromicSubSequence lps = new LongestPalindromicSubSequence("ABBDCACB");

        int longest1 = lps.longestPalindromicSubSequence();
        int longest2 = lps.longestPalindromicSubSequenceRecursive();

        System.out.println("longest1: " + longest1);
        System.out.println("longest2: " + longest2);
    }
}

package dp;

public class LongestCommonSubSequence {

    private String str1;

    private String str2;

    public LongestCommonSubSequence(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
    }

    public int lcs() {
        int m = str1.length();
        int n = str2.length();

        // space optimization when only the size of LCS is required.
        int[] table = new int[n + 1];
        int prev;

        for (int i = 1; i <= m; i++) {

            prev = table[0];
            for (int j = 1; j <= n; j++) {
                // stores the value of previous table[i - 1][j]
                int backup = table[j];
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    table[j] = prev + 1;
                } else {
                    // table[j] contains the value of previous table[i - 1][j]
                    // table[j - 1] contains the value of previous table[i][j - 1]
                    table[j] = Integer.max(table[j - 1], table[j]);
                }
                // since the value of j was already computed so backup contains the value of
                // table[i - 1][j - 1] to be stored in prev for use in next iteration.
                prev = backup;
            }

        }

        return table[n];
    }

    public static void main(String[] args) {
//        LongestCommonSubSequence lcs = new LongestCommonSubSequence("XMJYAUZ", "MZJAWXU");
        LongestCommonSubSequence lcs = new LongestCommonSubSequence("AA", "AA");
        System.out.println(lcs.lcs());
    }
}

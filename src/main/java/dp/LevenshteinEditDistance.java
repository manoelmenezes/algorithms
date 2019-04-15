package dp;

public class LevenshteinEditDistance {

    private String X;

    private String Y;

    private int m;

    private int n;

    public LevenshteinEditDistance(String X, String Y) {
        this.X = X;
        this.Y = Y;
        this.m = X.length();
        this.n = Y.length();
    }

    public int editDistance() {
        int[][] table = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            table[i][0] = i;
        }

        for (int j = 1; j <= n; j++) {
            table[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1];
                } else {
                    table[i][j] = Integer.min(table[i - 1][j - 1],
                            Integer.min(table[i - 1][j], table[i][j - 1])) + 1;
                }
            }
        }

        return table[m][n];
    }

    public static void main(String[] args) {
        LevenshteinEditDistance ed = new LevenshteinEditDistance("kitten", "sitting");

        System.out.println(ed.editDistance());
    }
}

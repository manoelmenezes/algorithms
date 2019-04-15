package dp;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindAllLCS {

    private String str1;

    private String str2;

    private int m;

    private int n;

    private int[][] table;

    public FindAllLCS(String str1, String str2) {
        this.str1 = str1;
        this.str2 = str2;
        this.m = str1.length();
        this.n = str2.length();
        this.table = new int[m + 1][n + 1];
    }

    public int lcsLength() {

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    table[i][j] = table[i - 1][j - 1] + 1;
                } else {
                    table[i][j] = Integer.max(table[i][j - 1], table[i - 1][j]);
                }
            }
        }

        return table[m][n];

    }

    public Set<String> getLcs() {
        return doGetLcs(m, n);
    }

    private Set<String> doGetLcs(int i, int j) {

        if (i <= 0 || j <= 0) {
            return Sets.newHashSet("");
        }

        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {

            Set<String> tmp = doGetLcs(i - 1, j - 1);

            return tmp.stream()
                    .map(s -> s + str1.charAt(i - 1))
                    .collect(Collectors.toSet());

        } else if (table[i][j - 1] == table[i - 1][j]) {

            Set<String> tmp1 = doGetLcs(i, j - 1);

            Set<String> tmp2 = doGetLcs(i - 1, j);

            tmp1.addAll(tmp2);

            return tmp1;

        } else if (table[i][j - 1] > table[i - 1][j]) {

            return doGetLcs(i, j - 1);

        } else {

            return doGetLcs(i - 1, j);
        }
    }

    public static void main(String[] args) {
        FindAllLCS findAllLCS = new FindAllLCS("XMJYAUZ", "MZJAWXU");

        System.out.println(findAllLCS.lcsLength());

        System.out.println(findAllLCS.getLcs());

        findAllLCS = new FindAllLCS("ABC", "BDCABA");

        System.out.println(findAllLCS.lcsLength());

        System.out.println(findAllLCS.getLcs());

        findAllLCS = new FindAllLCS("AB", "CD");

        System.out.println(findAllLCS.lcsLength());

        System.out.println(findAllLCS.getLcs());

    }

}

package matrix;

public class MaximizeExpressionValue {

    private int[] array;
    private int n;

    public MaximizeExpressionValue(int[] array) {
        this.array = array;
        this.n = array.length;
    }

    public int maximize() {
        int[][] min = new int[n][n];

        for (int i = 0; i < n - 1; i++) {
            min[i][i + 1] = i;
        }

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 2; j < n; j++) {
                min[i][j] = array[j - 1] < array[min[i][j - 1]] ? j - 1 : min[i][j - 1];
            }
        }

        int maxV2 = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;

        for (int r = 2; r < n - 1; r++) {

            int v2 = array[r - 1] - array[min[0][r - 1]];

            if (v2 > maxV2) {
                maxV2 = v2;
            }

            int maxV1 = Integer.MIN_VALUE;

            for (int i = 1; i + r < n; i++) {
                int v1 = array[r + i] - array[min[r][r + i]];

                if (v1 > maxV1) {
                    maxV1 = v1;
                }
            }

            if (maxV1 + maxV2 > max) {
                max = maxV1 + maxV2;
            }
        }

        return max;

    }

}

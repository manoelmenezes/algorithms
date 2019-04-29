package bit;

/**
 * You have a 3x3 grid. In each cell you can have two colors, white or black. At the beginning, the matrix has some cells painted white and others black.
 * If you change a color cell, that is, grid [i] [j] the cells grid [i-1] [j], grid [i + 1] [j], grid [i] [j-1] and grid [ i] [j + 1] also change (if these positions do not leave the 3x3 grid), that is, if they were white, they change to black.
 * Return, the minimum number of cells you have to flip for the 3x3 grid to be totally white. If you cannot do this, return -1!
 * Sample input/output - ibb.co/3Y0WVNR
 *
 * Solution: generate all combinations of flips. The matrix has 9 positions, in each position
 * we have two options flip or not
 * 2 * 2 ... * 2 = 2^9 = 512
 *
 *
 */
public class ChangeColor {

    public static int getMinFlips(String grid, String[] flips, int i)  {
        if (grid.equals("111111111")) return 0;
        if (i == 9) return -1;

        int ret;
        String r = Integer.toBinaryString(Integer.valueOf(grid, 2) ^ Integer.valueOf(flips[i], 2));
        ret = getMinFlips(r, flips, i + 1);
        if (ret > -1) {
            return ret + 1;
        }
        return getMinFlips(grid, flips, i + 1);
    }

    public static int getMinFlips(String grid) {
        String[] flips = {
                "110100000",
                "111010000",
                "011001000",
                "100110100",
                "010111010",
                "001011001",
                "000100110",
                "000010111",
                "000001011"
        };
        return getMinFlips(grid, flips, 0);
    }

    public static void main(String[] args) {
        // WBW
        // BBB
        // WBW

        // BWB
        // WWW
        // BWB

        // 101000101 ^
        // 110100000

        // 011100101 ^
        // 111010000

        // 100110101 ^
        // 011001000

        // 111111101 ^
        // 100110100

        // 011001001 ^
        // 010111010

        // 001110011 ^
        // 001011001

        // 000101010 ^
        // 000100110

        // 000001100 ^
        // 000010111

        // 000011011 ^
        // 000001011

        // WBW
        // BBW
        // WWB

        // WWB
        // WBB
        // BBB

        // BBW
        // BWW
        // WWW

        System.out.println(getMinFlips("101000101"));
        System.out.println(getMinFlips("101001110"));
        System.out.println(getMinFlips("110100000"));
        System.out.println(getMinFlips("001011111"));
    }

}

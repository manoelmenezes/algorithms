package bit;

public class PositionOfRightMostSetBit {

    public static int position(int i) {

        int tmp = (i - 1);

        int c = 1;

        while ((tmp & 1) == 1) {
            tmp = tmp >> 1;
            c++;
        }

        return c;

    }
}

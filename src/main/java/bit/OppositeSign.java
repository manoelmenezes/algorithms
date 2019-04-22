package bit;

public class OppositeSign {

    public static boolean hasOppositeSign(int i, int j) {
        return (i >> 31) != (j >> 31);
    }
}

package bit;

public class ToggleBit {

    public static int toggle(int i, int bit) {
        return i ^ (1 << (bit - 1));
    }
}

package bit;

public class TurnOnBit {

    public static int turnOn(int i, int bit) {
        return i | (1 << (bit - 1));
    }
}

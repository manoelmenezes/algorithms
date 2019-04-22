package bit;

public class TurnOffBit {

    public static int turnOff(int i, int bit) {
        int j = (Integer.MAX_VALUE << bit) | (Integer.MAX_VALUE >> (32 - bit));
        // i & ~(1 << (bit - 1))
        return i & j;
    }
}

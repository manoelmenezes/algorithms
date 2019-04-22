package bit;

public class Swap {

    // x ^ x = 0
    // x + y = z
    // x^x + x^y = z^x

    public static void swap(int[] array, int i, int j) {
         array[i] = array[i] ^ array[j];
         array[j] = array[i] ^ array[j];
         array[i] = array[i] ^ array[j];
    }
}

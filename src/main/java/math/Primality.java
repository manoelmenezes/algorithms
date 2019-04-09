package math;

public class Primality {

    public static boolean test(int n) {
        for (int j = 2; j <= Math.sqrt(n); j++) {
            if (n % j == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        for (int i = 2; i <= 100; i++) {

            if (test(i)) {
                System.out.println(i);
            }

        }

    }

}

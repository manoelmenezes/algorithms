package bit;

public class FindMinMaxWithoutBranching {

    public static int min(int x, int y) {
        return y + ((x - y) & ((x - y) >> 31));
    }

    public static int max(int x, int y) {
        return y + ((x - y) & ((y - x) >> 31));
    }

    public static void main(String[] args) {
        System.out.println(min(1, 2));
        System.out.println(max(1, 2));
    }

}

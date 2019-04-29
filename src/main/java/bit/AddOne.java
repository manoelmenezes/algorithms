package bit;

public class AddOne {

    /*
    Complement: invert and add one, if c is complement of x
    c = addOne(invert(x))
     */
    public static int addOne(int i) {
        return -~i;
    }

    public static int complement2(int i) {
        return addOne(~i);
    }

    public static void main(String[] args) {
        System.out.println(addOne(1));
        System.out.println(addOne(2));
        System.out.println(addOne(3));
        System.out.println(complement2(1));
        System.out.println(complement2(2));
        System.out.println(Integer.toBinaryString(~1));
        System.out.println(~1 + 1);
        System.out.println(complement2(-2));
    }
}

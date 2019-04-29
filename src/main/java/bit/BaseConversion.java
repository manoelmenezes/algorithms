package bit;

public class BaseConversion {

    public static int convertFrom2To10(String binary) {
        if (binary.length() > 32) {
            throw new IllegalArgumentException("Length of binary string must be at most 32 bits");
        }

        int factor = 1;

        int signal = binary.length() == 32 && binary.charAt(0) == '1' ? -1 : 1;

        if (signal == -1) {
            binary = complement2(binary);
        }

        int result = 0;

        int limit = binary.length() == 32 ? 1 : 0;
        for (int i = binary.length() - 1; i >= limit; i--) {

            if (binary.charAt(i) == '1') {
                result += factor;
            }

            factor *= 2;
        }

        return signal * result;

    }

    public static String complement2(String binary) {
        StringBuilder sb = new StringBuilder();
        for (char c : binary.toCharArray()) {
            sb.append(c == '1' ? '0' : '1');
        }

        int size = sb.length();
        for (int i = 0; i < 32 - size; i++) {
            sb.insert(0, '1');
        }

        char carry = '1';
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (carry == '1' && sb.charAt(i) == '1') {
                sb.setCharAt(i, '0');
            }

            if (carry == '1' && sb.charAt(i) == '0') {
                sb.setCharAt(i, '1');
                carry = '0';
            }
        }

        if (carry == '1') {
            sb.insert(0, '1');
        }

        return sb.toString();
    }

    /*1010
        10 / 2 = 5
        0

        5 / 2 = 2
        1

        2 / 2 = 1
        0

        1 / 2 = 0
        1
     */
    public static String convertFrom10To2(int number) {
        if (number == 0) {
            return "0";
        }

        int signal = number >= 0 ? 1 : -1;

        number = number >= 0 ? number : -number;

        StringBuilder sb = new StringBuilder();

        while (number > 0) {
            sb.insert(0,  number % 2);

            number = number / 2;
        }

        return signal == -1 ? complement2(sb.toString()) : sb.toString();

    }

    public static void main(String[] args) {
        System.out.println(convertFrom2To10("0"));
        System.out.println(convertFrom2To10("1"));
        System.out.println(convertFrom2To10("10"));
        System.out.println(convertFrom2To10("11"));
        System.out.println(convertFrom2To10("100"));
        System.out.println(convertFrom2To10("101"));
        System.out.println(convertFrom2To10("110"));
        System.out.println(convertFrom2To10("111"));
        System.out.println(convertFrom2To10("1000"));
        System.out.println(convertFrom2To10("11111111111111111111111111111111"));

        System.out.println(complement2("1"));

        System.out.println(convertFrom10To2(0));
        System.out.println(convertFrom10To2(1));
        System.out.println(convertFrom10To2(2));
        System.out.println(convertFrom10To2(3));
        System.out.println(convertFrom10To2(4));
        System.out.println(convertFrom10To2(5));
        System.out.println(convertFrom10To2(6));
        System.out.println(convertFrom10To2(7));
        System.out.println(convertFrom10To2(8));
        System.out.println(convertFrom10To2(9));
        System.out.println(convertFrom10To2(10));
        System.out.println(convertFrom10To2(-1));
    }

}

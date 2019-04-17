package string;

public class NumberToExcelColumnNameConverter {

    public String convert(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int x = (n - 1) % 26;

            sb.append((char) (x + 'A'));

            n = (n - 1) / 26;

        }

        return sb.reverse().toString();

    }

    public static void main(String[] args) {
        NumberToExcelColumnNameConverter converter = new NumberToExcelColumnNameConverter();

        System.out.println("527  -  TG: " + converter.convert(527).equals("TG"));
        System.out.println("562  –  UP: " + converter.convert(562).equals("UP"));
        System.out.println("1    –   A: " + converter.convert(1).equals("A"));
        System.out.println("26   –   Z: " + converter.convert(26).equals("Z"));
        System.out.println("27   –  AA: " + converter.convert(27).equals("AA"));
        System.out.println("1014 – ALZ: " + converter.convert(1014).equals("ALZ"));
        System.out.println("1015 – AMA: " + converter.convert(1015).equals("AMA"));
    }

}

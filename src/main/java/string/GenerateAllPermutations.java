package string;

import java.util.ArrayList;
import java.util.List;

public class GenerateAllPermutations {

    public List<String> permutationsIterative(String s) {
        List<List<String>> table = new ArrayList<>();
        table.add(List.of(s.charAt(0) + ""));

        for (int i = 1; i < s.length(); i++) {

            char c = s.charAt(i);

            List<String> perms = table.get(i - 1);

            List<String> tmp = new ArrayList<>();

            for (String p: perms) {
                tmp.add(c + p);
                tmp.add(p + c);

                for (int j = 1; j < p.length(); j++) {
                    tmp.add(p.substring(0, j) + c + p.substring(j));
                }
            }

            table.add(tmp);
        }

        return table.get(table.size() - 1);
    }

    public List<String> permutationsRecursive(String s) {
        if (s.length() == 1) {
            return List.of("" + s.charAt(0));
        }

        List<String> perms = permutationsRecursive(s.substring(1));

        List<String> result =  new ArrayList<>();

        char c = s.charAt(0);

        for (String p: perms) {
            result.add(c + p);
            result.add(p + c);

            for (int i = 1; i < p.length(); i++) {
                result.add(p.substring(0, i) + c + p.substring(i));
            }

        }

        return result;
    }

    public static void main(String[] args) {
        GenerateAllPermutations generator = new GenerateAllPermutations();

        System.out.println(generator.permutationsRecursive("a"));
        System.out.println(generator.permutationsRecursive("ab"));
        System.out.println(generator.permutationsRecursive("abc"));
        System.out.println(generator.permutationsRecursive("abcd"));

        System.out.println(generator.permutationsIterative("a"));
        System.out.println(generator.permutationsIterative("ab"));
        System.out.println(generator.permutationsIterative("abc"));
        System.out.println(generator.permutationsIterative("abcd"));

    }
}

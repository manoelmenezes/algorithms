package set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SequenceGenerator {

    public static boolean isPossible(List<List<Integer>> seqs) {

        Map<Integer, Integer> count = new HashMap<>();
        Map<Integer, Set<Integer>> seqM = new HashMap<>();

        for (List<Integer> l: seqs) {
            for (int i = 1; i < l.size(); i++) {
                Integer n = l.get(i);
                Integer m = l.get(i - 1);

                Integer c = count.getOrDefault(n, 0);
                count.put(n, c + 1);

                Set<Integer> tmp = seqM.getOrDefault(m, new HashSet<>());
                tmp.add(n);
                seqM.put(m, tmp);
            }
        }

        do {
            Set<Integer> k1 = new HashSet<>(count.keySet());
            Set<Integer> k2 = new HashSet<>(seqM.keySet());

            k2.removeAll(k1);

            if (k2.isEmpty()) {
                return false;
            }

            for (Integer i: k2) {

                for (Integer j: seqM.get(i)) {

                    count.put(j, count.get(j) - 1);
                    if (count.get(j) == 0) {
                        count.remove(j);
                    }
                }

                if (seqM.get(i).size() == 1) {
                    seqM.remove(i);
                } else {
                    List<Integer> tmp = new ArrayList<>(seqM.get(i));
                    tmp.remove(0);
                    seqM.put(i, new HashSet<>(tmp));
                }
            }


        } while (!count.isEmpty() && !seqM.isEmpty());


        return true;
    }

}

package dp;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LongestIncreasingSubSequence {

    private int[] array;

    private int n;

    public LongestIncreasingSubSequence(int[] array) {
        this.array = array;
        this.n = array.length;
    }

    /**
     * For each i find the previous LIIS where array[j] < array[i] and LIIS[j] is
     * the maximum among the previous.
     *
     * @return
     */
    public List<Integer> longestIncreasingSubSequence() {
        List<List<Integer>> table = new ArrayList<>(n);

        table.add(Lists.newArrayList(array[0]));

        for (int i = 1; i < n; i++) {
            table.add(Lists.newArrayList());
        }

        List<Integer> max = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (table.get(j).size() > table.get(i).size() && array[j] <= array[i]) {
                    table.set(i, new ArrayList<>(table.get(j)));
                }
            }
            table.get(i).add(array[i]);

            if (table.get(i).size() > max.size()) {
                max = table.get(i);
            }
        }

        return max;
    }

    public List<Integer> liss() {

        List<IndexAndSmallBefore> indexesAndSmallBefore = getIndexesAndSmallBefore();

        List<IndexAndSmallBefore> result = new LinkedList<>();
        result.add(indexesAndSmallBefore.get(indexesAndSmallBefore.size() - 1));

        for (int i = n - 2; i >= 0; i--) {
            IndexAndSmallBefore iab = indexesAndSmallBefore.get(i);
            if (array[iab.getIndex()] <= array[result.get(0).getIndex()] && iab.getIndex() < result.get(0).getIndex()) {
                result.add(0, iab);
            }
        }

        return result.stream()
                .map(iab -> array[iab.getIndex()])
                .collect(Collectors.toList());

    }

    public List<IndexAndSmallBefore> getIndexesAndSmallBefore() {
        List<IndexAndSmallBefore> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            IndexAndSmallBefore indexAndSmallBefore = new IndexAndSmallBefore(i, 0, array[i]);

            for (int j = 0; j < i; j++) {
                if (array[j] <= array[i]) {
                    indexAndSmallBefore.increment();
                }
            }

            result.add(indexAndSmallBefore);
        }

        result.sort(Comparator.comparing(IndexAndSmallBefore::getCountSmallBefore));

        return result;
    }

    public List<Integer> getLiss() {
        List<IndexAndSmallBefore> indexesAndSmallBefore = IntStream.range(0, n)
                .mapToObj(i -> new IndexAndSmallBefore(i, 0, array[i]))
                .collect(Collectors.toList());

        indexesAndSmallBefore.sort(Comparator.comparing(IndexAndSmallBefore::getValue));

        PriorityQueue<Integer> indexes = new PriorityQueue<>();
        indexes.add(indexesAndSmallBefore.get(0).getIndex());

        for (int i = 1; i < n; i++) {
            IndexAndSmallBefore indexAndSmallBefore = indexesAndSmallBefore.get(i);

            List<Integer> tmp = new ArrayList<>();
            while (!indexes.isEmpty() && indexes.peek() < indexAndSmallBefore.getIndex()) {
                tmp.add(indexes.poll());
                indexAndSmallBefore.increment();
            }

            indexes.addAll(tmp);
            indexes.add(indexAndSmallBefore.getIndex());

        }

        indexesAndSmallBefore.sort(Comparator.comparing(IndexAndSmallBefore::getCountSmallBefore));

        List<IndexAndSmallBefore> result = new LinkedList<>();
        result.add(indexesAndSmallBefore.get(indexesAndSmallBefore.size() - 1));

        for (int i = n - 2; i >= 0; i--) {
            IndexAndSmallBefore indexAndSmallBefore = indexesAndSmallBefore.get(i);

            if (array[indexAndSmallBefore.getIndex()] <= array[result.get(0).getIndex()]
                    && indexAndSmallBefore.getIndex() < result.get(0).getIndex()) {
                result.add(0, indexAndSmallBefore);
            }
        }

        return result.stream()
                .map(isb -> array[isb.getIndex()])
                .collect(Collectors.toList());

    }

    private static final class IndexAndSmallBefore {
        private int index;
        private int countSmallBefore;
        private int value;

        public IndexAndSmallBefore(int index, int countSmallBefore, int value) {
            this.index = index;
            this.countSmallBefore = countSmallBefore;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public int getCountSmallBefore() {
            return countSmallBefore;
        }

        public int getIndex() {
            return index;
        }

        public void increment() {
            countSmallBefore++;
        }
    }

    /**
     *
     */
    public static void main(String[] args) {
        int[] array = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };

        LongestIncreasingSubSequence liss = new LongestIncreasingSubSequence(array);

        System.out.println(liss.liss());

        System.out.println(liss.longestIncreasingSubSequence());

        System.out.println(liss.getLiss());


    }

}

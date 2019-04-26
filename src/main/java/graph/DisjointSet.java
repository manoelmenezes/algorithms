package graph;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet {

    private Map<Integer, Integer> parent = new HashMap<>();

    private Map<Integer, Integer> rank = new HashMap<>();

    public void makeSet(int[] universe) {
        for (int i: universe) {
            parent.put(i, i);
            rank.put(i, 0);
        }
    }

    public int find(int k) {
        if (parent.get(k) != k) {
            parent.put(k, find(parent.get(k)));
        }

        return parent.get(k);
    }

    public void union(int a, int b) {
        int x = find(a);
        int y = find(b);

        if (x == y) {
            return;
        }

        int xRank = rank.get(x);
        int yRank = rank.get(y);

        if (xRank < yRank) {
            parent.put(x, y);
        } else if (yRank < xRank) {
            parent.put(y, x);
        } else {
            parent.put(x, y);
            rank.put(y, yRank + 1);
        }

    }

    public static void printSets(int[] universe, DisjointSet ds) {
        for (int i: universe) {
            System.out.print(ds.find(i) + " ");
        }

        System.out.println();
    }

    public static void main(String[] args)
    {
        // universe of items
        int[] universe = { 1, 2, 3, 4, 5 };

        // initialize DisjointSet class
        DisjointSet ds = new DisjointSet();

        // create singleton set for each element of universe
        ds.makeSet(universe);
        printSets(universe, ds);

        ds.union(4, 3); // 4 and 3 are in same set
        printSets(universe, ds);

        ds.union(2, 1); // 1 and 2 are in same set
        printSets(universe, ds);

        ds.union(1, 3); // 1, 2, 3, 4 are in same set
        printSets(universe, ds);
    }

}
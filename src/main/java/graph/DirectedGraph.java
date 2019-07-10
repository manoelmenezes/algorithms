package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DirectedGraph {

    public DirectedGraph(ArrayList<Edge> edges, Set<Integer> nodes) {
        this(edges, nodes.size());

        for (Integer n: nodes) {
            adjMap.putIfAbsent(n, new ArrayList<>());
        }
    }

    public static final class Edge {
        private int src;
        private int dest;

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }

        public int getSrc() {
            return src;
        }

        public int getDest() {
            return dest;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Edge)) {
                return false;
            }

            Edge e = (Edge) o;

            return src == e.src && dest == e.dest;

        }

        @Override
        public int hashCode() {
            int result = 17;

            result = 31 * result + src;
            result = 31 * result + dest;

            return result;
        }
    }

    private List<List<Integer>> adj;
    private Map<Integer, List<Integer>> adjMap;

    private int N;

    public DirectedGraph(List<Edge> edges, int N) {
        this.N = N;

        this.adj = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        this.adjMap = new HashMap<>();

        for (Edge e: edges) {
            int src = e.getSrc();
            int dst = e.getDest();

            List<Integer> tmp = adjMap.getOrDefault(src, new ArrayList<>());
            tmp.add(dst);
            adjMap.put(src, tmp);

            adj.get(e.getSrc()).add(e.getDest());
        }

    }

    public Map<Integer, List<Integer>> getAdjMap() {
        return adjMap;
    }

    public List<Integer> getAdj(int src) {
        return adj.get(src);
    }

    public int getN() {
        return N;
    }
}

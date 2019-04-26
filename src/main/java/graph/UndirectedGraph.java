package graph;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph {

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
    }

    private List<List<Integer>> adj;

    public UndirectedGraph(List<Edge> edges, int n) {
        adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (Edge e: edges) {
            adj.get(e.getSrc()).add(e.getDest());
            adj.get(e.getDest()).add(e.getSrc());
        }
    }

    public List<Integer> getAdj(int src) {
        return adj.get(src);
    }
}

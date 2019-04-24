package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FloydWarshallShortestPath {

    public static final class FloydWarshallOutput {
        private int[][] dist;
        private int[][] path;

        public FloydWarshallOutput(int[][] dist, int[][] path) {
            this.dist = dist;
            this.path = path;
        }

        public int[][] getDist() {
            return dist;
        }

        public int[][] getPath() {
            return path;
        }

        public void print() {
            System.out.println(">>> Cost matrix <<<");

            for (int i = 0; i < dist.length; i++) {
                System.out.println(Arrays.toString(dist[i]));
            }

            System.out.println();
            System.out.println(">>> Paths <<<");

            for (int i = 0; i < path.length; i++) {
                for (int j = 0; j < path[i].length; j++) {
                    System.out.println(String.format("Path from %d to %d: %s", i, j, getPath(i, j, path[i]).toString()));
                }

            }
        }

        private List<Integer> getPath(int from, int to, int[] path) {
            List<Integer> result = new LinkedList<>();

            while (to != -1) {
                result.add(0, to);
                to = path[to];
            }

            return result;
        }
    }

    public FloydWarshallOutput shortestPath(WeightDirectedGraph graph) {

        int N = graph.getN();

        int[][] dist = new int[N][N];

        int[][] path = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    dist[i][j] = Integer.MAX_VALUE;
                }
                path[i][j] = -1;
            }
        }

        for (WeightDirectedGraph.Edge e: graph.getEdges()) {
            dist[e.getSrc()][e.getDest()] = e.getWeight();
            path[e.getSrc()][e.getDest()] = e.getSrc();
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE
                            && dist[k][j] != Integer.MAX_VALUE
                            && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        path[i][j] = path[k][j];
                    }
                }

                if (dist[i][i] < 0) {
                    throw new IllegalArgumentException("Graph has negative cycle");
                }
            }
        }

        return new FloydWarshallOutput(dist, path);

    }

    public static void main(String[] args) {
        int N = 4;

        List<WeightDirectedGraph.Edge> edges = List.of(
                new WeightDirectedGraph.Edge(0, 2, -2),
                new WeightDirectedGraph.Edge(1, 0, 4),
                new WeightDirectedGraph.Edge(1, 2, 3),
                new WeightDirectedGraph.Edge(2, 3, 2),
                new WeightDirectedGraph.Edge(3, 1, -1)
        );

        WeightDirectedGraph graph = new WeightDirectedGraph(edges, N);

        FloydWarshallOutput output = new FloydWarshallShortestPath().shortestPath(graph);

        output.print();
    }


}

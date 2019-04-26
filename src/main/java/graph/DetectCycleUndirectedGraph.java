package graph;

import java.util.*;

public class DetectCycleUndirectedGraph {

    public boolean containsCycle(UndirectedGraph graph) {
        boolean containsCycle = false;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, 0);

        while (!queue.isEmpty() && !containsCycle) {
            int u = queue.poll();

            for (int v: graph.getAdj(u)) {
                Integer p = parent.get(v);
                if (p == null) {
                    parent.put(v, u);
                    queue.add(v);
                } else if (parent.get(u) != v) {
                    containsCycle = true;
                }
            }
        }

        return containsCycle;
    }

    public static void main(String[] args) {
        // vector of graph edges as per above diagram
        List<UndirectedGraph.Edge> edges = Arrays.asList(
                new UndirectedGraph.Edge(0, 1), new UndirectedGraph.Edge(0, 2),
                new UndirectedGraph.Edge(0, 3), new UndirectedGraph.Edge(1, 4),
                new UndirectedGraph.Edge(1, 5), new UndirectedGraph.Edge(4, 8),
                new UndirectedGraph.Edge(4, 9), new UndirectedGraph.Edge(3, 6),
                new UndirectedGraph.Edge(3, 7), new UndirectedGraph.Edge(6, 11),
                new UndirectedGraph.Edge(6, 10)
                //, new UndirectedGraph.Edge(5, 9)
                // edge (5->9) introduces a cycle in the graph
        );

        // Set number of vertices in the graph
        final int N = 13;

        // create a graph from edges
        UndirectedGraph graph = new UndirectedGraph(edges, N);

        DetectCycleUndirectedGraph detectCycleUndirectedGraph = new DetectCycleUndirectedGraph();
        boolean containsCycle = detectCycleUndirectedGraph.containsCycle(graph);

        if (containsCycle) {
            System.out.println("Contains cycle");
        } else {
            System.out.println("No cycle");
        }
    }

}

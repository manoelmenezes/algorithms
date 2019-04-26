package graph;

import java.util.*;

public class CheckUndirectedGraphIsTree {

    /**
     * {@link UndirectedGraph} is tree when it is connected and
     * contains no cycle.
     *
     * @param graph {@link UndirectedGraph}
     * @return true if tree, false otherwise.
     */
    public boolean isTree(UndirectedGraph graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);

        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, -1);

        boolean containsCycle = false;

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

        return !containsCycle && parent.size() == graph.getN();
    }

    public static void main(String[] args) {
        // initialize edges as per above diagram
        List<UndirectedGraph.Edge> edges = List.of(
                new UndirectedGraph.Edge(0, 1),
                new UndirectedGraph.Edge(1, 2),
                new UndirectedGraph.Edge(2, 3),
                new UndirectedGraph.Edge(3, 4),
                new UndirectedGraph.Edge(4, 5)
//                ,
//                new UndirectedGraph.Edge(5, 0)
                // edge (5->0) introduces a cycle in the graph
        );

        // Number of nodes in the graph
        int N = 6;

        // create a graph from edges
        UndirectedGraph graph = new UndirectedGraph(edges, N);

        CheckUndirectedGraphIsTree checkUndirectedGraphIsTree = new CheckUndirectedGraphIsTree();
        boolean isTree = checkUndirectedGraphIsTree.isTree(graph);

        if (isTree) {
            System.out.println("It is tree");
        } else {
            System.out.println("It is not tree");
        }

    }

}

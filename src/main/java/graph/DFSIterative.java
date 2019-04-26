package graph;

import java.util.*;

public class DFSIterative {

    public List<Integer> dfs(UndirectedGraph graph, int source) {
        List<Integer> result = new ArrayList<>();

        Stack<Integer> stack = new Stack<>();
        stack.push(source);

        Set<Integer> visited = new HashSet<>();

        while (!stack.isEmpty()) {
            int u = stack.pop();

            if (visited.contains(u)) {
                continue;
            }

            result.add(u);
            visited.add(u);

            for (int i = graph.getAdj(u).size() - 1; i >= 0; i--) {
                int v = graph.getAdj(u).get(i);
                if (!visited.contains(v)) {
                    stack.push(v);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        List<UndirectedGraph.Edge> edges = List.of(new UndirectedGraph.Edge(0, 1),
                new UndirectedGraph.Edge(0, 6), new UndirectedGraph.Edge(0, 7),
                new UndirectedGraph.Edge(1, 2), new UndirectedGraph.Edge(1, 5),
                new UndirectedGraph.Edge(2, 3), new UndirectedGraph.Edge(2, 4),
                new UndirectedGraph.Edge(7, 8), new UndirectedGraph.Edge(7, 11),
                new UndirectedGraph.Edge(8, 9), new UndirectedGraph.Edge(8, 10));

        UndirectedGraph graph = new UndirectedGraph(edges, 12);

        List<Integer> result = new DFSIterative().dfs(graph, 0);

        System.out.println(result);
    }
}

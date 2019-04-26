package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFSRecursive {

    public List<Integer> dfs(UndirectedGraph graph, int source) {
        Set<Integer> visited = new HashSet<>();

        List<Integer> result  = new ArrayList<>();

        doDfs(graph, source, visited, result);

        return result;
    }

    private void doDfs(UndirectedGraph graph, int source, Set<Integer> visited, List<Integer> result) {
        visited.add(source);
        result.add(source);

        for (int v: graph.getAdj(source)) {
            if (!visited.contains(v))  {
                doDfs(graph, v, visited, result);
            }
        }
    }

    public static void main(String[] args) {
        List<UndirectedGraph.Edge> edges = List.of(new UndirectedGraph.Edge(0, 1),
                new UndirectedGraph.Edge(0, 6), new UndirectedGraph.Edge(0, 7),
                new UndirectedGraph.Edge(1, 2), new UndirectedGraph.Edge(1, 5),
                new UndirectedGraph.Edge(2, 3), new UndirectedGraph.Edge(2, 4),
                new UndirectedGraph.Edge(7, 8), new UndirectedGraph.Edge(7, 11),
                new UndirectedGraph.Edge(8, 9), new UndirectedGraph.Edge(8, 10));

        UndirectedGraph graph = new UndirectedGraph(edges, 12);

        List<Integer> result = new DFSRecursive().dfs(graph, 0);

        System.out.println(result);
    }


}

package graph;

import java.util.*;

public class BFSRecursive {

    public List<Integer> bfs(UndirectedGraph graph, int source) {
        List<Integer> result = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        Set<Integer> visited = new HashSet<>();
        visited.add(source);

        doBfs(graph, queue, visited, result);

        return result;
    }

    private void doBfs(UndirectedGraph graph, Queue<Integer> queue, Set<Integer> visited, List<Integer> result) {
        if (queue.isEmpty()) {
            return;
        }

        Integer u = queue.poll();
        result.add(u);

        for (Integer v: graph.getAdj(u)) {
            if (!visited.contains(v)) {
                visited.add(v);
                queue.add(v);
            }
        }

        doBfs(graph, queue, visited, result);
    }

    public static void main(String[] args) {
        List<UndirectedGraph.Edge> edges = List.of(new UndirectedGraph.Edge(0, 1),
                new UndirectedGraph.Edge(0, 2), new UndirectedGraph.Edge(1, 2));

        UndirectedGraph graph = new UndirectedGraph(edges, 3);

        BFSRecursive bfs = new BFSRecursive();
        List<Integer> result = bfs.bfs(graph, 0);

        System.out.println(result);
    }
}

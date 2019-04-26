package graph;

import java.util.*;

public class BFSIterative {

    public List<Integer> bfs(UndirectedGraph graph, int source) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);

        Set<Integer> visited = new HashSet<>();
        visited.add(source);

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            Integer u = queue.poll();

            result.add(u);

            for (int v: graph.getAdj(u)) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }

        return result;
    }
}

package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class EulerPaths {

    public List<Integer> paths(UndirectedGraph graph) {

        Set<Integer> evenDegree = new HashSet<>();
        Set<Integer> oddDegree = new HashSet<>();

        for (int i = 0; i < graph.getN(); i++) {
            if (graph.getAdj(i).size() % 2 == 0) {
                evenDegree.add(i);
            } else {
                oddDegree.add(i);
            }
        }


        if (oddDegree.size() > 2) {
            return new ArrayList<>();
        }

        Integer startVertex = oddDegree.stream().findFirst().orElse(0);

        Stack<Integer> stack = new Stack<>();
        List<Integer> eulerPath = new ArrayList<>();

        while (!stack.isEmpty() || !graph.getAdj(startVertex).isEmpty()) {
            if (graph.getAdj(startVertex).isEmpty()) {
                eulerPath.add(startVertex);
                startVertex = stack.pop();
            } else {
                List<Integer> adj = graph.getAdj(startVertex);
                Integer v = adj.get(0);

                adj.remove(0);
                graph.getAdj(v).remove(startVertex);

                stack.push(startVertex);
                startVertex = v;
            }
        }

        eulerPath.add(startVertex);

        return eulerPath;
    }

    public static void main(String[] args) {
        List<UndirectedGraph.Edge> edges = List.of(
                new UndirectedGraph.Edge(0, 4),
                new UndirectedGraph.Edge(0, 1),
                new UndirectedGraph.Edge(1, 2),
                new UndirectedGraph.Edge(1, 3),
                new UndirectedGraph.Edge(2, 3)
        );

        int n = 5;

        UndirectedGraph graph = new UndirectedGraph(edges, n);

        System.out.println(new EulerPaths().paths(graph));
    }

}

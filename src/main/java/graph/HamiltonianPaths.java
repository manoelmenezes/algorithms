package graph;

import java.util.*;

public class HamiltonianPaths {

    public List<List<Integer>> getHamiltonianPaths(UndirectedGraph graph) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int v = 0; v < graph.getN(); v++) {
            if (!visited.contains(v)) {
                doGetHamiltonianPaths(graph, v, visited, path, result);
            }
        }

        return result;
    }

    public static void printAllHamiltonianPaths(UndirectedGraph g, int v,
                                                boolean[] visited, List<Integer> path, int N)
    {
        // if all the vertices are visited, then
        // hamiltonian path exists
        if (path.size() == N)
        {
            // print hamiltonian path
            for (int i : path)
                System.out.print(i + " ");
            System.out.println();

            return;
        }

        // Check if every edge starting from vertex v leads
        // to a solution or not
        for (int w : g.getAdj(v))
        {
            // process only unvisited vertices as Hamiltonian
            // path visits each vertex exactly once
            if (!visited[w])
            {
                visited[w] = true;
                path.add(w);

                // check if adding vertex w to the path leads
                // to solution or not
                printAllHamiltonianPaths(g, w, visited, path, N);

                // Backtrack
                visited[w] = false;
                path.remove(path.size()-1);
            }
        }
    }


    private void doGetHamiltonianPaths(UndirectedGraph graph,
                                       int source,
                                       Set<Integer> visited,
                                       List<Integer> path,
                                       List<List<Integer>> result) {

        visited.add(source);
        path.add(source);

        if (path.size() == graph.getN()) {
            result.add(new ArrayList<>(path));
        } else {
            for (int u: graph.getAdj(source)) {

                if (!visited.contains(u)) {
                    doGetHamiltonianPaths(graph, u, visited, path, result);
                }
            }
        }

        visited.remove(source);
        path.remove(path.size() - 1);

    }

    public static void main(String[] args) {
        // vector of graph edges as per above diagram
        List<UndirectedGraph.Edge> edges = Arrays.asList(
                new UndirectedGraph.Edge(0, 1), new UndirectedGraph.Edge(0, 2), new UndirectedGraph.Edge(0, 3),
                new UndirectedGraph.Edge(1, 2), new UndirectedGraph.Edge(1, 3), new UndirectedGraph.Edge(2, 3)
        );

        // Set number of vertices in the graph
        final int N = 4;

        // create a graph from edges
        UndirectedGraph graph = new UndirectedGraph(edges, N);

        HamiltonianPaths hamiltonianPaths = new HamiltonianPaths();
        System.out.println(hamiltonianPaths.getHamiltonianPaths(graph));

        // starting node
        int start = 0;

        // add starting node to the path
        List<Integer> path = new ArrayList<>();
        path.add(start);

        // mark start node as visited
        boolean[] visited = new boolean[N];
        visited[start] = true;

        printAllHamiltonianPaths(graph, start, visited, path, N);
    }

}

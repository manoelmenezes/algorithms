package graph;

import java.util.*;

public class ShortestPathFinder {

    private Graph graph;

    public ShortestPathFinder(Graph graph) {
        this.graph = graph;
    }

    public ShortestPathOutput shortestPath(Vertex source, Vertex destination) {

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(source);

        Map<Vertex, Integer> distances = new HashMap<>();
        distances.put(source, 0);

        Map<Vertex, Vertex> parents = new HashMap<>();

        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            Integer distance = distances.get(vertex);

            List<Vertex> adj = graph.getAdj(vertex);

            for (Vertex v: adj) {
                Integer d = distances.get(v);

                if (d == null) {
                    distances.put(v, distance + 1);
                    queue.add(v);
                    parents.put(v, vertex);

                    if (v.equals(destination)) {
                        return new ShortestPathOutput(distance + 1, buildPath(source, destination, parents));
                    }
                }
            }

        }

        throw new RuntimeException("Destination not found");

    }

    private List<Vertex> buildPath(Vertex source, Vertex destination, Map<Vertex, Vertex> parents) {

        Vertex parent = parents.get(destination);
        List<Vertex> result = new LinkedList<>();
        result.add(destination);

        while (parent != null) {
            result.add(0, parent);
            parent = parents.get(parent);
        }

        return result;
    }

    public static class ShortestPathOutput {
        private int distance;
        private List<Vertex> path;

        public ShortestPathOutput(int distance, List<Vertex> path) {
            this.distance = distance;
            this.path = path;
        }

        public int getDistance() {
            return distance;
        }

        public List<Vertex> getPath() {
            return path;
        }
    }
}

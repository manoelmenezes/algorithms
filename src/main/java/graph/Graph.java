package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {

    private int n;

    private Map<Vertex, List<Vertex>> adj;

    public Graph(int n) {
        this.n = n;
        this.adj = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        adj.putIfAbsent(vertex, new LinkedList<>());
    }

    public void add(Vertex u, Vertex v) {
        List<Vertex> uAdj = adj.getOrDefault(u, new LinkedList<>());
        uAdj.add(v);
        adj.put(u, uAdj);
    }

    public List<Vertex> getAdj(Vertex vertex) {
        return adj.getOrDefault(vertex, new LinkedList<>());
    }

}

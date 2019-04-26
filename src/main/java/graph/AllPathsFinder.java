package graph;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFinder {

    private Graph graph;

    public AllPathsFinder(Graph graph) {
        this.graph = graph;
    }

    public List<List<Vertex>> find(Vertex from, Vertex to) {
        List<List<Vertex>> paths = new ArrayList<>();
        List<Vertex> currentPath = new ArrayList<>();
        currentPath.add(from);

        dfs(from, to, paths, currentPath);

        return paths;
    }

    private void dfs(Vertex from, Vertex to, List<List<Vertex>> paths, List<Vertex> currentPath) {

        for (Vertex v: graph.getAdj(from)) {
            currentPath.add(v);

            if (v.equals(to)) {
                List<Vertex> path = new ArrayList<>(currentPath);
                paths.add(path);
                currentPath.remove(currentPath.size() - 1);
            } else {
                dfs(v, to, paths, currentPath);
            }
        }
        currentPath.remove(currentPath.size() - 1);

    }
}

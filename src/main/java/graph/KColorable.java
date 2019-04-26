package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KColorable {

    enum Color {
        BLUE,
        GREEN,
        RED,
        YELLOW
    }

    public List<List<Color>> kColorable(UndirectedGraph graph) {
        List<List<Color>> result = new ArrayList<>();
        Color[] colors = new Color[graph.getN()];
        doKColorable(graph, 0, colors, result);

        return result;
    }

    private void doKColorable(UndirectedGraph graph, int source, Color[] colors, List<List<Color>> result) {

        if (source == graph.getN()) {
            result.add(List.of(colors));
            return;
        }

        for (Color c: Color.values()) {
            if (canColor(graph, source, c, colors)) {
                colors[source] = c;

                doKColorable(graph, source + 1, colors, result);

                colors[source] = null;
            }
        }

    }

    private boolean canColor(UndirectedGraph graph, int source, Color color, Color[] colors) {
        for (int v: graph.getAdj(source)) {
            if (colors[v] == color) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // vector of graph edges as per above diagram
        List<UndirectedGraph.Edge> edges = Arrays.asList(
                new UndirectedGraph.Edge(0, 1), new UndirectedGraph.Edge(0, 4),
                new UndirectedGraph.Edge(0, 5), new UndirectedGraph.Edge(4, 5),
                new UndirectedGraph.Edge(1, 4), new UndirectedGraph.Edge(1, 3),
                new UndirectedGraph.Edge(2, 3), new UndirectedGraph.Edge(2, 4)
        );

        // Set number of vertices in the graph
        final int N = 6;

        // create a graph from edges
        UndirectedGraph graph = new UndirectedGraph(edges, N);

        System.out.println(new KColorable().kColorable(graph).size());
    }
}

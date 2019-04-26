package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrivalDepartureTimeDfs {

    public static final class ArrivalDepartureTime {
        private int vertex;
        private int arrival;
        private int departure;

        public ArrivalDepartureTime(int vertex, int arrival) {
            this.vertex = vertex;
            this.arrival = arrival;
        }

        public int getVertex() {
            return vertex;
        }

        public int getArrival() {
            return arrival;
        }

        public int getDeparture() {
            return departure;
        }

        public void setDeparture(int departure) {
            this.departure = departure;
        }

        @Override
        public String toString() {
            return String.format("AD(%d, %d, %d)", vertex, arrival, departure);
        }
    }

    public List<ArrivalDepartureTime> dfs(DirectedGraph graph, int source) {
        List<ArrivalDepartureTime> result = new ArrayList<>();

        Set<Integer> visited = new HashSet<>();

        int[] time = {0};

        doDfs(graph, source, visited, time, result);

        for (int vertex = 0; vertex < graph.getN(); vertex++) {
            if (!visited.contains(vertex)) {
                doDfs(graph, vertex, visited, time, result);
            }
        }

        return result;
    }

    private void doDfs(DirectedGraph graph,
                       int source,
                       Set<Integer> visited,
                       int[] time,
                       List<ArrivalDepartureTime> result) {

        visited.add(source);

        ArrivalDepartureTime arrivalDepartureTime = new ArrivalDepartureTime(source, time[0]);
        result.add(arrivalDepartureTime);

        time[0]++;

        for (int v: graph.getAdj(source)) {
            if (!visited.contains(v)) {
                doDfs(graph, v, visited, time, result);
            }
        }

        arrivalDepartureTime.setDeparture(time[0]);

        time[0]++;

    }

    public static void main(String[] args) {
        List<DirectedGraph.Edge> edges = List.of(
                new DirectedGraph.Edge(0, 1), new DirectedGraph.Edge(0, 2),
                new DirectedGraph.Edge(2, 3), new DirectedGraph.Edge(2, 4),
                new DirectedGraph.Edge(3, 1), new DirectedGraph.Edge(3, 5),
                new DirectedGraph.Edge(4, 5), new DirectedGraph.Edge(6, 7));

        DirectedGraph graph = new DirectedGraph(edges, 8);

        ArrivalDepartureTimeDfs dfs = new ArrivalDepartureTimeDfs();
        List<ArrivalDepartureTime> result = dfs.dfs(graph, 0);

        System.out.println(result);
    }

}

package design.fourwayintersection;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

enum Color {
    GREEN,
    YELLOW,
    RED
}

class TrafficLight {

    String id;
    int greenTime;
    int yellowTime;
    int redTime;
    Color color;

    public TrafficLight(String id, int greenTime, int yellowTime, int redTime) {
        this.id = id;
        this.greenTime = greenTime;
        this.yellowTime = yellowTime;
        this.redTime = redTime;
        this.color = Color.RED;
    }

    public void green() {
        System.out.println(id + " is now GREEN");
        this.color = Color.GREEN;
    }

    public void yellow() {
        System.out.println(id + " is now YELLOW");
        this.color = Color.YELLOW;
    }

    public void red() {
        System.out.println(id + " is now RED");
        this.color = Color.RED;
    }

    public boolean isRed() {
        return color == Color.RED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrafficLight that = (TrafficLight) o;
        return greenTime == that.greenTime &&
                yellowTime == that.yellowTime &&
                redTime == that.redTime &&
                Objects.equals(id, that.id) &&
                color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, greenTime, yellowTime, redTime, color);
    }

    @Override
    public String toString() {
        return "TrafficLight{" +
                "id='" + id + '\'' +
                ", greenTime=" + greenTime +
                ", yellowTime=" + yellowTime +
                ", redTime=" + redTime +
                ", color=" + color +
                '}';
    }
}

class TrafficLightSet {
    private Set<TrafficLight> set;

    private int greenTime;
    private int yellowTime;
    private int redTime;

    public TrafficLightSet(Set<TrafficLight> set) {
        this.set = new HashSet<>(set);

        TrafficLight tl = this.set.stream().findFirst().get();

        boolean differentTimes = this.set.stream()
                .filter(t -> t.greenTime != tl.greenTime || t.yellowTime != tl.yellowTime || t.redTime != tl.redTime)
                .collect(Collectors.toList()).size() != 0;

        if (differentTimes) {
            throw new IllegalArgumentException("different times not allowed in traffic light set");
        }

        boolean allRed = this.set.stream().filter(t -> t.isRed()).collect(Collectors.toList()).size() == this.set.size();

        if (!allRed) {
            throw new IllegalArgumentException("All traffic lights in set must start red");
        }

        this.greenTime = tl.greenTime;
        this.yellowTime = tl.yellowTime;
        this.redTime = tl.redTime;
    }

    public void start() throws InterruptedException {
        set.stream().forEach(tl -> tl.green());
        Thread.currentThread().sleep(greenTime);

        set.stream().forEach(tl -> tl.yellow());
        Thread.currentThread().sleep(yellowTime);

        set.stream().forEach(tl -> tl.red());
        Thread.currentThread().sleep(redTime);
    }
}

class Controller {

    Queue<TrafficLightSet> trafficLightSets;

    Controller(Queue<TrafficLightSet> trafficLightSets) {
        this.trafficLightSets = trafficLightSets;
    }

    public void control() throws InterruptedException {

        while (true) {

            TrafficLightSet tfs = trafficLightSets.poll();
            tfs.start();
            trafficLightSets.add(tfs);
        }
    }
}

public class TrafficLightMain {

    public static void main(String[] args) throws InterruptedException {

        int greenTime = 5000;
        int yellowTime = 1000;
        int redTime = 3000;

        TrafficLightSet tfs1 = new TrafficLightSet(Set.of(
                new TrafficLight("TF1", greenTime, yellowTime, redTime),
                new TrafficLight("TF2", greenTime, yellowTime, redTime)));

        TrafficLightSet tfs2 = new TrafficLightSet(Set.of(
                new TrafficLight("TF3", greenTime, yellowTime, redTime),
                new TrafficLight("TF4", greenTime, yellowTime, redTime)));

        TrafficLightSet tfs3 = new TrafficLightSet(Set.of(
                new TrafficLight("TF5", greenTime, yellowTime, redTime),
                new TrafficLight("TF6", greenTime, yellowTime, redTime)));


        Queue<TrafficLightSet> tfss = new LinkedList<>();
        tfss.add(tfs1);
        tfss.add(tfs2);
        tfss.add(tfs3);

        Controller controller = new Controller(tfss);

        controller.control();
    }

}


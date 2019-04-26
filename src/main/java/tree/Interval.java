package tree;

public class Interval {

    private int start;
    private int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean intersect(Interval interval) {
        Interval startFirst = start < interval.start ? this : interval;
        Interval startSnd = startFirst == this ? interval : this;

        return startFirst.end > startSnd.start;
    }
}

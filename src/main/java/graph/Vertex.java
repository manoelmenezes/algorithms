package graph;

public class Vertex {

    private int i;
    private int j;

    public Vertex(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vertex)) {
            return false;
        }

        Vertex vertex = (Vertex) o;

        return i == vertex.i && j == vertex.j;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + i;
        result = 31 * result + j;

        return result;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", i, j);
    }
}

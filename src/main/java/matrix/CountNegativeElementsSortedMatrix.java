package matrix;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class CountNegativeElementsSortedMatrix {

    private int[][] matrix;

    private int m;

    private int n;

    public CountNegativeElementsSortedMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
    }

    public int countStartingFromTopLeft() {
        int count = 0;

        Cell cell = new Cell(0, 0);
        Queue<Cell> cells = new LinkedList<>();
        cells.add(cell);

        Set<Cell> visited = new HashSet<>();
        visited.add(cell);

        while (!cells.isEmpty()) {
            Cell c = cells.poll();

            if (matrix[c.getI()][c.getJ()] >= 0) {
                continue;
            }

            count++;

            Cell right = c.getJ() < n - 1 ? new Cell(c.getI(), c.getJ() + 1) : null;
            if (right != null && !visited.contains(right)) {
                visited.add(right);
                cells.add(right);
            }

            Cell down = c.getI() < m - 1 ? new Cell(c.getI() + 1, c.getJ()) : null;
            if (down != null && !visited.contains(down)) {
                visited.add(down);
                cells.add(down);
            }
        }

        return count;
    }

    public int countStartingFromTopRight() {
        int row = 0;
        int column = n - 1;
        int count = 0;

        while (row < m && column >= 0)

        if (matrix[row][column] < 0) {
            count += column + 1;
            row++;
        } else {
            column--;
        }

        return count;

    }
}

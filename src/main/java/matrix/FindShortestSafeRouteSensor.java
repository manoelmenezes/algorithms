package matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class FindShortestSafeRouteSensor {

    private static int[] ROWS = {1, 0, -1, 0};

    private static int[] COLS = {0, 1, 0, -1};

    private static int[] ROWS_ZERO = {1, 0, -1, 0, 1, 1, -1, -1};

    private static int[] COLS_ZERO = {0, 1, 0, -1, 1, -1, 1, -1};

    private int[][] matrix;

    private int m;

    private int n;

    public FindShortestSafeRouteSensor(int[][] matrix) {
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
    }

    public List<Cell> find() {
        Queue<Cell> queue = new LinkedList<>();

        Map<Cell, Cell> parent = new HashMap<>();

        for (int r = 0; r < m; r++) {
            Cell cell = new Cell(r, 0);
            if (isValid(cell)) {
                queue.add(cell);
                parent.put(cell, null);
            }
        }

        while (!queue.isEmpty()) {
            Cell cell = queue.poll();

            if (cell.getJ() == n - 1) {
                return buildPath(cell, parent);
            }

            for (int i = 0; i < ROWS.length; i++) {
                Cell c = new Cell(cell.getI() + ROWS[i], cell.getJ() + COLS[i]);
                if (isValid(c) && !parent.containsKey(c)) {
                    queue.add(c);
                    parent.put(c, cell);
                }
            }
        }

        return new ArrayList<>();
    }

    private List<Cell> buildPath(Cell cell, Map<Cell, Cell> parent) {
        List<Cell> path = new LinkedList<>();
        path.add(cell);

        Cell current = cell;
        Cell p = null;
        while ((p = parent.get(current)) != null) {
            path.add(0, p);
            current = p;
        }

        return path;
    }

    private boolean isValid(Cell c) {


        return (c.getI() >= 0 && c.getI() < m)
                && (c.getJ() >= 0 && c.getJ() < n)
                && matrix[c.getI()][c.getJ()] == 1
                && noZeroAround(c);
    }

    private boolean noZeroAround(Cell c) {

        for (int i = 0; i < ROWS_ZERO.length; i++) {
            int row = c.getI() + ROWS_ZERO[i];
            int col = c.getJ() + COLS_ZERO[i];

            if ((row >= 0 && row < m) && (col >= 0 && col < n) && matrix[row][col] == 0) {
                return false;
            }
        }

        return true;

    }

}

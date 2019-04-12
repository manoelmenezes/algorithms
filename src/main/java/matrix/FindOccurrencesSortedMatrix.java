package matrix;

import java.util.ArrayList;
import java.util.List;

public class FindOccurrencesSortedMatrix {

    private int[][] matrix;
    private int m;
    private int n;

    public FindOccurrencesSortedMatrix(int[][] matrix) {
        this.matrix = matrix;
        this.m = matrix.length;
        this.n = matrix[0].length;
    }

    public List<Cell> find(int element) {
        List<Cell> occurrences = new ArrayList<>();

        int row = 0;
        int column = n - 1;

        while (row < m && column >= 0) {
            if (matrix[row][column] == element) {
                occurrences.add(new Cell(row, column));
                row++;
                column--;
            } else if (matrix[row][column] > element) {
                column--;
            } else {
                row++;
            }
        }

        return occurrences;
    }

}

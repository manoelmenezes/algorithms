package string;

// Given a 2D grid of characters and a word, find all occurrences of given word in grid. A word can be matched in all 8 directions at any point.

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

class Cell {

    int row;
    int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cell)) {
            return false;
        }

        Cell c = (Cell) o;

        return c.row == row && c.column == column;
    }

    @Override
    public int hashCode() {
        int r = 17;

        r = 31 * r + row;
        r = 31 * r + column;

        return r;
    }

    @Override
    public String toString() {
        return "(" + row + "," + column + ")";
    }

}

class Occurrence {

    List<Cell> cells;

    public Occurrence(List<Cell> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return cells.toString();
    }
}

public class FindWord {

    static List<Occurrence> findOccurrences(char[][] grid, String word) {

        List<Occurrence> occurrences = new ArrayList<>();

        int rows = grid.length;
        int columns = grid[0].length;

        char firstChar = word.charAt(0);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (grid[i][j] == firstChar) {
                    Cell cell = new Cell(i, j);

                    Set<Cell> visited = new HashSet<>();
                    visited.add(cell);

                    List<Cell> cells = new ArrayList<>();
                    cells.add(cell);

                    doFind(grid, cell, word, 1, cells, visited, occurrences);
                }
            }
        }

        return occurrences;
    }

    private static void doFind(char[][] grid,
                               Cell cell,
                               String word,
                               int position,
                               List<Cell> cells,
                               Set<Cell> visited,
                               List<Occurrence> occurrences) {

        if (position == word.length()) {
            occurrences.add(new Occurrence(new ArrayList<>(cells)));

            return;
        }

        char c = word.charAt(position);

        for (Cell n: neighbours(cell, grid.length, grid[0].length)) {

            // This condition does not allow cycle.
            // To allow cycle, replace by:
            // if ((n.row != cell.row || n.column != cell.column) && c == grid[n.row][n.column]) {
            if (!visited.contains(n) && c == grid[n.row][n.column]) {
                visited.add(n);
                cells.add(n);

                doFind(grid, n, word, position + 1, cells, visited, occurrences);

                visited.remove(n);
                cells.remove(cells.size() - 1);
            }
        }
    }

    private static List<Cell> neighbours(Cell cell, int rows, int columns) {
        List<Cell> result = new ArrayList<>();

        int r = cell.row;
        int c = cell.column;

        if (r - 1 >= 0) {
            result.add(new Cell(r - 1, c));
        }

        if (c - 1 >= 0) {
            result.add(new Cell(r, c - 1));
        }

        if (r - 1 >= 0 && c - 1 >= 0) {
            result.add(new Cell(r - 1, c - 1));
        }

        if (r + 1 < rows) {
            result.add(new Cell(r + 1, c));
        }

        if (c + 1 < columns) {
            result.add(new Cell(r, c + 1));
        }

        if (r + 1 < rows && c + 1 < columns) {
            result.add(new Cell(r + 1, c + 1));
        }

        if (r + 1 < rows && c - 1 >= 0) {
            result.add(new Cell(r + 1, c - 1));
        }

        if (r - 1 >= 0 && c + 1 < columns) {
            result.add(new Cell(r - 1, c + 1));
        }

        return result;
    }

    public static void main(String[] args) {
        char[][] grid1 = {
                {'B', 'N', 'E', 'Y', 'S'},
                {'H', 'E', 'D', 'E', 'S'},
                {'S', 'G', 'N', 'D', 'E'}
        };

        System.out.println(findOccurrences(grid1, "DES"));

        char[][] grid2 = {
                {'B', 'N', 'E', 'Y', 'S'},
                {'H', 'E', 'D', 'E', 'S'},
                {'S', 'G', 'N', 'D', 'E'}};


        System.out.println(findOccurrences(grid2, "BNEGSHBN"));

    }

}


























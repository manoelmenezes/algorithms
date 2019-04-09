package matrix;

import com.google.common.collect.Lists;

import java.util.List;

enum State {

    INCREASE_COLUMN("INCREASE_ROW"),
    INCREASE_ROW("DECREASE_COLUMN"),
    DECREASE_COLUMN("DECREASE_ROW"),
    DECREASE_ROW("INCREASE_COLUMN");

    private String nextState;

    State(String nextState) {
        this.nextState = nextState;
    }

    public State getNextState() {
        return State.valueOf(nextState);
    }
}

public class MatrixSpiralOrder {

    public static List<Integer> getSpiralOrder(int[][] matrix) {

        int rows = matrix.length;
        int columns = matrix[0].length;
        int totalItems = rows * columns;

        int columnRight = matrix[0].length - 1;
        int columnLeft = 0;

        int rowBottom = matrix.length - 1;
        int rowTop = 0;

        int count = 1;

        State currentState = State.INCREASE_COLUMN;

        List<Integer> result = Lists.newArrayList();

        while (count <= totalItems) {

            switch (currentState) {
                case INCREASE_COLUMN:

                    for (int c = columnLeft; c <= columnRight; c++ ) {
                        result.add(matrix[rowTop][c]);
                    }
                    rowTop++;

                    break;
                case INCREASE_ROW:

                    for (int r = rowTop; r <= rowBottom; r++) {
                        result.add(matrix[r][columnRight]);
                    }
                    columnRight--;

                    break;
                case DECREASE_COLUMN:

                    for (int c = columnRight; c >= columnLeft; c--) {
                        result.add(matrix[rowBottom][c]);
                    }
                    rowBottom--;
                    break;
                case DECREASE_ROW:
                    for (int r = rowBottom; r >= rowTop; r--) {
                        result.add(matrix[r][columnLeft]);
                    }
                    columnLeft++;

                    break;
            }

            currentState = currentState.getNextState();
            count++;
        }

        return result;

    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9}
        };

        List<Integer> spiralOrder = getSpiralOrder(matrix);

        System.out.println(spiralOrder);
    }

}

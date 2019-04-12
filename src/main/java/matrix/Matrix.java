package matrix;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Getter
@Value
public final class Matrix {

    private final int m;

    private final int n;

    @NonNull
    private final int[][] matrix;

    public Matrix(@NonNull final int[][] matrix) {
        Preconditions.checkArgument(matrix.length > 0, "Matrix cannot be empty");
        Preconditions.checkArgument(
                IntStream.range(0, matrix.length)
                        .filter(i -> matrix[i] != null)
                        .count() == matrix.length,
                "Matrix cannot have null rows"
        );
        Preconditions.checkArgument(
                IntStream.range(0, matrix.length)
                        .filter(i -> matrix[i].length > 0)
                    .count() == matrix.length,
                "Matrix cannot have empty rows");
        Preconditions.checkArgument(
               IntStream.range(0, matrix.length)
                       .filter(i -> matrix[i].length == matrix[0].length)
                       .count() == matrix.length,
               "All rows need to have the same number of elements"
        );

        this.m = matrix.length;
        this.n = matrix[0].length;

        this.matrix = new int[m][];

        IntStream.range(0, m)
                .forEach(i ->
                    this.matrix[i] = Arrays.copyOf(matrix[i], n));
    }

    public boolean isSquare() {
        return m == n;
    }

    public boolean isDiagonal() {
        Stream<Boolean> allZerosExceptDiagonal = IntStream.range(0, m)
                .mapToObj(i -> IntStream.range(0, n)
                        .filter(j -> i != j)
                        .mapToObj(j -> Boolean.valueOf(matrix[i][j] == 0)))
                .flatMap(isZero -> isZero);


        return isSquare() && allZerosExceptDiagonal.allMatch(isZero -> isZero);
    }

    public boolean isIdentity() {
        Stream<Boolean> allOnesInDiagonal = IntStream.range(0, m)
                .mapToObj(i -> Boolean.valueOf(matrix[i][i] == 1));

        return isDiagonal() && allOnesInDiagonal.allMatch(isOne -> isOne);
    }

    public boolean isZeroMatrix() {
        Stream<Boolean> allZeros = IntStream.range(0, m)
                .mapToObj(i -> IntStream.range(0, n)
                        .mapToObj(j -> Boolean.valueOf(matrix[i][j] == 0)))
                .flatMap(isZero -> isZero);

        return allZeros.allMatch(isZero -> isZero);
    }

    public Matrix transpose() {
        int[][] input = new int[n][m];

        IntStream.range(0, n)
                .forEach(i -> IntStream.range(0, m)
                .forEach(j -> input[i][j] = matrix[j][i]));

        return new Matrix(input);
    }

    public boolean isTridiagonal() {
        Stream<Boolean> allZerosExceptDiagonalAndBellowAndAbove = IntStream.range(0, n)
                .mapToObj(i -> IntStream.range(0, m)
                        .filter(j -> i != j && i != j + 1 && i + 1 != j)
                        .mapToObj(j -> Boolean.valueOf(matrix[i][j] == 0)))
                .flatMap(isZero -> isZero);

        return isSquare() && allZerosExceptDiagonalAndBellowAndAbove.allMatch(isZero -> isZero);

    }

    public boolean isUnitUpperTriangular() {
        Stream<Boolean> isUnitUpperTriangular = IntStream.range(0, m)
                .mapToObj(i -> IntStream.range(0, n)
                        .filter(j -> i >= j)
                        .mapToObj(j -> i == j ? Boolean.valueOf(matrix[i][j] == 1) : Boolean.valueOf(matrix[i][j] == 0)
                        ))
                .flatMap(isZero -> isZero);

        return isSquare() && isUnitUpperTriangular.allMatch(is -> is);
    }

    public boolean isUpperTriangular() {
        Stream<Boolean> allZerosBellowDiagonal = IntStream.range(0, m)
                .mapToObj(i -> IntStream.range(0, n)
                    .filter(j -> i > j)
                    .mapToObj(j -> Boolean.valueOf(matrix[i][j] == 0)))
                .flatMap(isZero -> isZero);

        return isSquare() && allZerosBellowDiagonal.allMatch(isZero -> isZero);
    }

    public boolean isLowerTriangular() {
        Stream<Boolean> allZerosAboveDiagonal = IntStream.range(0, m)
                .mapToObj(i -> IntStream.range(0, n)
                        .filter(j -> i < j)
                        .mapToObj(j -> Boolean.valueOf(matrix[i][j] == 0)))
                .flatMap(isZero -> isZero);

        return isSquare() && allZerosAboveDiagonal.allMatch(isZero -> isZero);
    }

    public boolean isUnitLowerTriangular() {
        Stream<Boolean> isUnitLowerTriangular = IntStream.range(0, m)
                .mapToObj(i -> IntStream.range(0, n)
                        .filter(j -> i <= j)
                        .mapToObj(j -> i == j ? Boolean.valueOf(matrix[i][j] == 1) : Boolean.valueOf(matrix[i][j] == 0)
                        ))
                .flatMap(isZero -> isZero);

        return isSquare() && isUnitLowerTriangular.allMatch(is -> is);
    }

    public boolean isPermutation() {
        IntStream rowsWithExactlyOne1 = IntStream.range(0, m)
                .filter(row -> rowHasExactlyOne1(row));

        IntStream columnsWithExactlyOne1 = IntStream.range(0, n)
                .filter(column -> columnHasExactlyOne1(column));

        return isSquare() && rowsWithExactlyOne1.count() == m && columnsWithExactlyOne1.count() == n;
    }

    private boolean columnHasExactlyOne1(final int column) {
        IntStream rowsWithZero = IntStream.range(0, m)
                .filter(row -> matrix[row][column] == 0);

        IntStream rowsWithOne = IntStream.range(0, m)
                .filter(row -> matrix[row][column] == 1);

        return rowsWithZero.count() == m - 1 && rowsWithOne.count() == 1;
    }

    private boolean rowHasExactlyOne1(final int row) {
        IntStream columnsWithZero = IntStream.range(0, n)
                .filter(column -> matrix[row][column] == 0);

        IntStream columnsWithOne = IntStream.range(0, n)
               .filter(column -> matrix[row][column] == 1);

        return columnsWithZero.count() == n - 1 && columnsWithOne.count() == 1;
    }

    public boolean isSymmetric() {
        return equals(matrix, transpose().matrix);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Matrix)) {
            return false;
        }
        Matrix otherMatrix = (Matrix) o;

        return m == otherMatrix.m && n == otherMatrix.n && equals(matrix, otherMatrix.matrix);
    }

    @Override
    public int hashCode() {
        int[] result = {17};

result[0] = 31 * result[0] + Integer.hashCode(m);
        result[0] = 31 * result[0] + Integer.hashCode(n);

        Arrays.stream(matrix)
            .forEach(a -> result[0] = 31 * result[0] + Arrays.hashCode(a));

        return result[0];
    }

    public Matrix add(@NonNull Matrix B) {
        Preconditions.checkArgument(m == B.m && n == B.n, String.format("Dimensions incompatible (%d, %d) + (%d, %d)", m, n, B.m, B.n) );

        int[][] result = new int[m][n];

        IntStream.range(0, m)
                .forEach(i -> IntStream.range(0, n)
                    .forEach(j -> result[i][j] = matrix[i][j] + B.matrix[i][j]));

        return new Matrix(result);
    }

    public Matrix subtract(@NonNull Matrix B) {
        return add(B.negative());
    }

    public Matrix multiply(@NonNull Matrix B) {
        Preconditions.checkArgument(n == B.m, String.format("Dimensions are incompatible. (%d, %d) x (%d, %d)", m, n, B.m, B.n));

        int[][] result = new int[m][B.n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < B.n; j++) {
                result[i][j] = 0;

                for (int k = 0; k < n; k++) {
                    result[i][j] += matrix[i][k] * B.matrix[k][j];
                }
            }
        }

        return new Matrix(result);
    }

    public boolean isRowVector() {
        return m == 1;
    }

    public boolean isColumnVector() {
        return n == 1;
    }

    public boolean isNumber() {
        return m == 1 && n == 1;
    }

    public Matrix vectorInnerProduct(Matrix y) {
        Preconditions.checkState(isColumnVector(), String.format("Current matrix is not a column vector. Dimensions (%d, %d)", m, n ));
        Preconditions.checkArgument(y.isColumnVector(), String.format("Parameter is not a column vector. Dimensions (%d, %d)", y.m, y.n));

        return transpose().multiply(y);
    }

    public Matrix vectorOuterProduct(Matrix y) {
        Preconditions.checkState(isColumnVector(), String.format("Current matrix is not a column vector. Dimensions (%d, %d)", m, n));
        Preconditions.checkArgument(y.isColumnVector(), String.format("Parameter is not a column vector. Dimensions (%d, %d)", y.m, y.n) );

        return multiply(y.transpose());
    }

    public Matrix multiply(int scalar) {
        int[][] result = new int[m][n];

        IntStream.range(0, m)
                .forEach(i -> IntStream.range(0, n)
                    .forEach(j -> result[i][j] = matrix[i][j] * scalar ));

        return new Matrix(result);
    }

    public Matrix negative() {
        return multiply(-1);
    }

    public boolean isUnitVector(final int i) {
        Preconditions.checkState(isColumnVector() || isRowVector(), String.format("Matrix is not a vector. Dimensions (%d, %d)", m, n));
        Preconditions.checkArgument((isColumnVector() && i >= 0 && i < m) || (isRowVector() && i >= 0 && i < n), String.format("Parameter must be in the range [0,%d)", isColumnVector() ? m : n));

        long zeros;
        if (isColumnVector()) {
            zeros = IntStream.range(0, m)
                    .filter(j -> matrix[j][0] == 0)
                    .count();
        } else {
            zeros = IntStream.range(0, n)
                    .filter(j -> matrix[0][j] == 0)
                    .count();
        }

        return (isColumnVector() && matrix[i][0] == 1 && zeros == m - 1)
                || (isRowVector() && matrix[0][i] == 1 && zeros == n - 1);
    }

    public double euclideanNorm() {
        Preconditions.checkState(isColumnVector(), String.format("Current matrix is not a column vector. Dimensions (%d, %d)", m, n));

        Matrix result = vectorInnerProduct(this);

        assert result.isNumber();

        return Math.sqrt(result.getMatrix()[0][0]);
    }

    public static boolean equals(int[][] A, int[][] B) {
        return IntStream.range(0, A.length)
                .mapToObj(i -> IntStream.range(0, A[0].length)
                        .mapToObj(j -> Boolean.valueOf(A[i][j] == B[i][j])))
                .flatMap(equals -> equals)
                .allMatch(equals -> equals);
    }
}

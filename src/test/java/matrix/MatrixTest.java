package matrix;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MatrixTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyMatrix() {
        // set up
        int[][] matrix = {};

        // SUT
        new Matrix(matrix);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithMatrixWithNullRows() {
        // set up
        int[][] matrix = {null};

        // SUT
        new Matrix(matrix);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithMatrixWithEmptyRows() {
        // set up
        int[][] matrix = {{}};

        // SUT
        new Matrix(matrix);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithMatrixWithRowsWithDifferentNumberOfElements() {
        // set up
        int[][] matrix = {
                {1},
                {1, 2}
        };

        // SUT
        new Matrix(matrix);
    }

    @Test
    public void testConstructorWithSuccess() {
        // set up
        int[][] input = {
                {1, 2, 3},
                {4, 5, 6}
        };

        // SUT
        Matrix matrix = new Matrix(input);

        // assert
        assertEquals(2, matrix.getM());
        assertEquals(3, matrix.getN());
        assertTrue(IntStream.range(0, input.length)
                .mapToObj(i -> Boolean.valueOf(Arrays.equals(input[i], matrix.getMatrix()[i])))
                .allMatch(isTrue -> isTrue));
    }

    @Test
    public void testIsSquareWithSquareMatrix() {
        // set up
        int[][] input = {
                {1, 2},
                {3, 4}
        };

        // SUT
        Matrix matrix = new Matrix(input);

        // assert
        assertTrue(matrix.isSquare());

    }

    @Test
    public void testIsSquareWithoutSquareMatrix() {
        // set up
        int[][] input = {
                {1, 2, 3},
                {4, 5, 6}
        };

        // SUT
        Matrix matrix = new Matrix(input);

        // assert
        assertFalse(matrix.isSquare());
    }

    @Test
    public void testIsDiagonalWithDiagonalMatrix() {
        // set up
        int[][] input1 = {
                {2, 0},
                {0, 3}
        };

        int[][] input2 = {
                {1}
        };

        int[][] input3 = {
                {1, 0, 0},
                {0, 2, 0},
                {0, 0, 3}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);
        Matrix matrix3 = new Matrix(input3);

        // assert
        assertTrue(matrix1.isDiagonal());
        assertTrue(matrix2.isDiagonal());
        assertTrue(matrix3.isDiagonal());
    }

    @Test
    public void testIsDiagonalWithNotDiagonalMatrix() {
        // set up
        int[][] input1 = {
                {1, 1},
                {0, 1}
        };

        int[][] input2 = {
                {1, 0, 1},
                {0, 1, 0},
                {0, 0, 1}
        };

        int[][] input3 = {
                {1, 2, 3},
                {4, 5, 6}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);
        Matrix matrix3 = new Matrix(input3);

        // assert
        assertFalse(matrix1.isDiagonal());
        assertFalse(matrix2.isDiagonal());
        assertFalse(matrix3.isDiagonal());
    }

    @Test
    public void testIsIdentityWithIdentityMatrix() {
        // set up
        int[][] input1 = {
                {1}
        };

        int[][] input2 = {
                {1, 0},
                {0, 1}
        };

        int[][] input3 = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);
        Matrix matrix3 = new Matrix(input3);

        // assert
        assertTrue(matrix1.isIdentity());
        assertTrue(matrix2.isIdentity());
        assertTrue(matrix3.isIdentity());
    }

    @Test
    public void testIsIdentityWithNoIdentityMatrix() {
        // set up
        int[][] input1 = {
                {2, 0},
                {0, 1}
        };

        int[][] input2 = {
                {2, 1},
                {0, 1}
        };

        int[][] input3 = {
                {1, 0, 0},
                {0, 1, 0}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);
        Matrix matrix3 = new Matrix(input3);

        // assert
        assertFalse(matrix1.isIdentity());
        assertFalse(matrix2.isIdentity());
        assertFalse(matrix3.isIdentity());

    }

    @Test
    public void testIsZeroMatrixWithNoZeroMatrix() {
        // set up
        int[][] input1 = {
                {1}
        };

        int[][] input2 = {
                {0, 0},
                {0, 1}
        };

        int[][] input3 = {
                {0, 0, 0},
                {0, 0, 1},
                {0, 0, 0}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);
        Matrix matrix3 = new Matrix(input3);

        // assert
        assertFalse(matrix1.isZeroMatrix());
        assertFalse(matrix2.isZeroMatrix());
        assertFalse(matrix3.isZeroMatrix());
    }

    @Test
    public void testTranspose() {
        // set up
        int[][] input1 = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] transpose1 = {
                {1, 4},
                {2, 5},
                {3, 6}
        };

        int[][] input2 = {
                {1}
        };

        int[][] transpose2 = {
                {1}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix actualTranspose1 = matrix1.transpose();

        Matrix matrix2 = new Matrix(input2);
        Matrix actualTranspose2 = matrix2.transpose();

        // assert
        assertTrue(equals(transpose1, actualTranspose1.getMatrix()));
        assertTrue(equals(transpose2, actualTranspose2.getMatrix()));
    }

    @Test
    public void testIsTridiagonal() {
        // set up
        int[][] input1 = {
                {1, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 1, 1},
                {0, 0, 1, 1}
        };

        int[][] input2 = {
                {1, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);

        // assert
        assertTrue(matrix1.isTridiagonal());
        assertFalse(matrix2.isTridiagonal());
    }

    @Test
    public void testIsUpperTriangularMatrix() {
        // set up
        int[][] input1 = {
                {1, 1, 1, 1},
                {0, 1, 1, 1},
                {0, 0, 1, 1},
                {0, 0, 0, 1}
        };

        int[][] input2 = {
                {1, 1, 1, 1},
                {0, 1, 1, 1},
                {0, 0, 1, 1},
                {1, 0, 0, 1}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);

        // assert
        assertTrue(matrix1.isUpperTriangular());
        assertFalse(matrix2.isUpperTriangular());
    }

    @Test
    public void testIsLowerTriangularMatrix() {
        // set up
        int[][] input1 = {
                {1, 0, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 1, 0},
                {1, 1, 1, 1}
        };

        int[][] input2 = {
                {1, 0, 0, 1},
                {1, 1, 0, 0},
                {1, 1, 1, 0},
                {1, 1, 1, 1}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);

        // assert
        assertTrue(matrix1.isLowerTriangular());
        assertFalse(matrix2.isLowerTriangular());
    }

    @Test
    public void testIsUnitUpperTriangular() {
        // set up
        int[][] input1 = {
                {1, 2, 3, 4},
                {0, 1, 2, 3},
                {0, 0, 1, 2},
                {0, 0, 0, 1}
        };

        int[][] input2 = {
                {1, 2, 3, 4},
                {0, 1, 2, 3},
                {0, 0, 1, 2},
                {0, 0, 0, 2}
        };

        int[][] input3 = {
                {1, 2, 3, 4},
                {0, 1, 2, 3},
                {0, 0, 1, 2},
                {0, 0, 1, 1}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);
        Matrix matrix3 = new Matrix(input3);

        // assert
        assertTrue(matrix1.isUnitUpperTriangular());
        assertFalse(matrix2.isUnitUpperTriangular());
        assertFalse(matrix3.isUnitUpperTriangular());
    }

    @Test
    public void testIsUnitLowerTriangular() {
        // set up
        int[][] input1 = {
                {1, 0, 0, 0},
                {2, 1, 0, 0},
                {3, 5, 1, 0},
                {4, 6, 7, 1}
        };

        int[][] input2 = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {2, 2, 1, 0},
                {3, 1, 1, 2}
        };

        int[][] input3 = {
                {1, 0, 0, 1},
                {3, 1, 0, 0},
                {4, 2, 1, 0},
                {4, 4, 1, 1}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);
        Matrix matrix3 = new Matrix(input3);

        // assert
        assertTrue(matrix1.isUnitLowerTriangular());
        assertFalse(matrix2.isUnitLowerTriangular());
        assertFalse(matrix3.isUnitLowerTriangular());
    }

    @Test
    public void testIsPermutation() {
        // set up
        int[][] input1 = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };

        int[][] input2 = {
                {2, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };

        int[][] input3 = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 1}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);
        Matrix matrix3 = new Matrix(input3);

        // assert
        assertTrue(matrix1.isPermutation());
        assertFalse(matrix2.isPermutation());
        assertFalse(matrix3.isPermutation());

    }

    @Test
    public void testIsSymmetric() {
        // set up
        int[][] input1 = {
                {1, 2, 3},
                {2, 6, 4},
                {3, 4, 5}
        };

        int[][] input2 = {
                {1, 2, 4},
                {2, 6, 4},
                {3, 4, 5}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);

        // assert
        assertTrue(matrix1.isSymmetric());
        assertFalse(matrix2.isSymmetric());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddWithIncompatibleDimensions() {
        // set up
        int[][] input1 = {
                {1, 2},
                {3, 4}
        };

        int[][] input2 = {
                {1, 2, 3},
                {1, 2, 3}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);

        matrix1.add(matrix2);
    }

    @Test
    public void testAdd() {
        // set up
        int[][] input1 = {
                {1, 2},
                {3, 4}
        };

        int[][] input2 = {
                {1, 2},
                {3, 4}
        };

        int[][] expected = {
                {2, 4},
                {6, 8}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);

        Matrix sum = matrix1.add(matrix2);

        assertTrue(equals(expected, sum.getMatrix()));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractWithIncompatibleDimensions() {
        // set up
        int[][] input1 = {
                {1, 2},
                {3, 4}
        };

        int[][] input2 = {
                {1, 2, 3},
                {1, 2, 3}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);

        matrix1.subtract(matrix2);
    }

    @Test
    public void testSubtract() {
        // set up
        int[][] input1 = {
                {1, 2},
                {3, 4}
        };

        int[][] input2 = {
                {1, 2},
                {3, 4}
        };

        int[][] expected = {
                {0, 0},
                {0, 0}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);

        Matrix diff = matrix1.subtract(matrix2);

        assertTrue(equals(expected, diff.getMatrix()));

    }

    @Test
    public void testScalarMultiplication() {
        // set up
        int[][] input1 = {
                {1, 2},
                {3, 4}
        };

        int[][] expected = {
                {2, 4},
                {6, 8}
        };

        // SUT
        Matrix matrix = new Matrix(input1);

        Matrix result = matrix.multiply(2);

        assertTrue(equals(expected, result.getMatrix()));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyWithIncompatibleDimensions() {
        // set up
        int[][] input1 = {
                {1, 2},
                {3, 4}
        };

        int[][] input2 = {
                {1, 2, 3},
                {1, 2, 3},
                {1, 2, 3}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);

        matrix1.multiply(matrix2);
    }

    @Test
    public void testMultiply() {
        // set up
        int[][] input1 = {
                {1, 2},
                {3, 4}
        };

        int[][] input2 = {
                {1, 2, 3},
                {1, 2, 3}
        };

        int[][] expected = {
                {3, 6, 9},
                {7, 14, 21}
        };

        // SUT
        Matrix matrix1 = new Matrix(input1);
        Matrix matrix2 = new Matrix(input2);

        Matrix result = matrix1.multiply(matrix2);

        assertTrue(equals(expected, result.getMatrix()));
    }

    @Test
    public void testMultiplicationIdentityProperty() {
        // set up
        int[][] identityInput = {
                {1, 0},
                {0, 1}
        };

        int[][] input = {
                {2, 3},
                {4, 5}
        };

        // SUT
        Matrix I = new Matrix(identityInput);
        Matrix A = new Matrix(input);

        Matrix result1 = A.multiply(I);
        Matrix result2 = I.multiply(A);

        assertTrue(equals(input, result1.getMatrix()));
        assertTrue(equals(input, result2.getMatrix()));
    }

    @Test
    public void testMultiplicationAssociativeProperty() {
        // set up
        int[][] input1 = {
                {1, 2},
                {3, 4}
        };

        int[][] input2 = {
                {5, 6},
                {7, 8}
        };

        int[][] input3 = {
                {9, 10},
                {11, 12}
        };

        // SUT
        Matrix A = new Matrix(input1);
        Matrix B = new Matrix(input2);
        Matrix C = new Matrix(input3);

        Matrix result1 = A.multiply(B.multiply(C));
        Matrix result2 = A.multiply(B).multiply(C);

        assertTrue(equals(result1.getMatrix(), result2.getMatrix()));
    }

    @Test
    public void testMultiplicationDistributeProperty() {
        // set up
        int[][] input1 = {
                {1, 2},
                {3, 4}
        };

        int[][] input2 = {
                {5, 6},
                {7, 8}
        };

        int[][] input3 = {
                {9, 10},
                {11, 12}
        };

        // SUT
        Matrix A = new Matrix(input1);
        Matrix B = new Matrix(input2);
        Matrix C = new Matrix(input3);

        Matrix result1 = A.multiply(B.add(C));
        Matrix result2 = A.multiply(B).add(A.multiply(C));

        Matrix result3 = A.add(B).multiply(C);
        Matrix result4 = A.multiply(C).add(B.multiply(C));

        assertTrue(equals(result1.getMatrix(), result2.getMatrix()));
        assertTrue(equals(result3.getMatrix(), result4.getMatrix()));

    }

    @Test
    public void testMultiplicationHasNotCommutativeProperty() {
        // set up
        int[][] input1 = {
                {0, 1},
                {0, 0}
        };

        int[][] input2 = {
                {0, 0},
                {1, 0}
        };

        // SUT
        Matrix A = new Matrix(input1);
        Matrix B = new Matrix(input2);

        Matrix result1 = A.multiply(B);
        Matrix result2 = B.multiply(A);

        assertFalse(equals(result1.getMatrix(), result2.getMatrix()));
    }

    @Test(expected = IllegalStateException.class)
    public void testVectorOuterProductWhenCurrentMatrixIsNotColumnVector() {
        // set up
        int[][] input1 = {
                {1, 2, 3}
        };

        int[][] input2 = {
                {1},
                {2},
                {3}
        };

        // SUT
        Matrix x = new Matrix(input1);
        Matrix y = new Matrix(input2);

        x.vectorOuterProduct(y);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testVectorOuterProductWhenParameterIsNotColumnVector() {
        // set up
        int[][] input1 = {
                {1},
                {2},
                {3}
        };

        int[][] input2 = {
                {1, 2, 3}
        };

        // SUT
        Matrix x = new Matrix(input1);
        Matrix y = new Matrix(input2);

        x.vectorOuterProduct(y);
    }

    @Test
    public void testVectorOuterProduct() {
        // set up
        int[][] input1 = {
                {1},
                {2},
                {3}
        };

        int[][] input2 = {
                {1},
                {2},
                {3}
        };

        Matrix expected = new Matrix(new int[][] {
                {1, 2, 3},
                {2, 4, 6},
                {3, 6, 9}
        });

        // SUT
        Matrix x = new Matrix(input1);
        Matrix y = new Matrix(input2);

        Matrix outerProduct = x.vectorOuterProduct(y);

        // assert
        assertEquals(expected, outerProduct);
    }

    @Test(expected = IllegalStateException.class)
    public void testVectorInnerProductWhenCurrentMatrixIsNotColumnVector() {
        // set up
        int[][] input1 = {
                {1, 2, 3}
        };

        int[][] input2 = {
                {4},
                {5},
                {6}
        };

        // SUT
        Matrix x = new Matrix(input1);
        Matrix y = new Matrix(input2);

        x.vectorInnerProduct(y);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVectorInnerProductWhenParameterIsNotColumnVector() {
        // set up
        int[][] input1 = {
                {1},
                {2},
                {3}
        };

        int[][] input2 = {
                {4, 5, 6}
        };

        // SUT
        Matrix x = new Matrix(input1);
        Matrix y = new Matrix(input2);

        x.vectorInnerProduct(y);
    }

    @Test
    public void testVectorInnerProduct() {
        // set up
        int[][] input1 = {
                {1},
                {2},
                {3}
        };

        int[][] input2 = {
                {4},
                {5},
                {6}
        };

        int expected = input1[0][0] * input2[0][0] + input1[1][0] * input2[1][0] + input1[2][0] * input2[2][0];

        // SUT
        Matrix x = new Matrix(input1);
        Matrix y = new Matrix(input2);

        Matrix innerProduct = x.vectorInnerProduct(y);

        // assert
        assertEquals(1, innerProduct.getN());
        assertEquals(1, innerProduct.getN());
        assertEquals(expected, innerProduct.getMatrix()[0][0]);
        assertTrue(innerProduct.isNumber());
    }

    @Test(expected = IllegalStateException.class)
    public void testIsUnitVectorWhenItIsNotVector() {
        // set up
        int[][] input = {
                {1, 2},
                {3, 4}
        };

        // SUT
        Matrix notAVector = new Matrix(input);
        notAVector.isUnitVector(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsUnitVectorWhenIsVectorButParameterIsNegative() {
        // set up
        int[][] input = {
                {1, 2, 3}
        };

        // SUT
        Matrix vector = new Matrix(input);

        vector.isUnitVector(-1);
    }

    @Test
    public void testIsUnitVectorWhenIsVector() {
        // set up
        int[][] input1 = {
                {1, 2, 3}
        };

        int[][] input2 = {
                {1},
                {2},
                {3}
        };

        int[][] input3 = {
            {1, 0, 0}
        };

        int[][] input4 = {
                {1},
                {0},
                {0}
        };

        // SUT
        Matrix vector1 = new Matrix(input1);
        Matrix vector2 = new Matrix(input2);
        Matrix vector3 = new Matrix(input3);
        Matrix vector4 = new Matrix(input4);

        assertFalse(vector1.isUnitVector(0));
        assertFalse(vector1.isUnitVector(1));
        assertFalse(vector1.isUnitVector(2));

        assertFalse(vector2.isUnitVector(0));
        assertFalse(vector2.isUnitVector(1));
        assertFalse(vector2.isUnitVector(2));

        assertTrue(vector3.isUnitVector(0));
        assertFalse(vector3.isUnitVector(1));
        assertFalse(vector3.isUnitVector(2));

        assertTrue(vector4.isUnitVector(0));
        assertFalse(vector4.isUnitVector(1));
        assertFalse(vector4.isUnitVector(2));
    }

    @Test(expected = IllegalStateException.class)
    public void testEuclideanNormWhenNotColumnVector() {
        // set up
        int[][] input = {
                {1, 2, 3}
        };

        // SUT
        Matrix matrix = new Matrix(input);
        matrix.euclideanNorm();
    }

    @Test
    public void testEuclideanNormWhenColumnVector() {
        // set up
        int[][] input = {
                {1},
                {1},
                {1},
                {1}
        };

        // SUT
        Matrix columnVector = new Matrix(input);

        // assert
        assertEquals(Math.sqrt(1 * 1 + 1 * 1 + 1 * 1 + 1 * 1), columnVector.euclideanNorm(), 0.0d);
    }

    private static boolean equals(int[][] A, int[][] B) {
        return IntStream.range(0, A.length)
                .mapToObj(i -> IntStream.range(0, A[0].length)
                                .mapToObj(j -> Boolean.valueOf(A[i][j] == B[i][j])))
                .flatMap(equals -> equals)
                .allMatch(equals -> equals);
    }

}

/**
 * Trevor Herrera
 * Java 2 (R01)
 * 02/22/2026
 * Assignment: Sparse Matrix
 * Purpose: Create a sparse matrix from a primitive matrix, create a primitive matrix from a sparse matrix, and get the
 *          value that occurs the most in a matrix.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SparseMatrix {

    private final int rows;
    private final int cols;
    private final int defaultValue;
    private final ArrayList<SparseEntry> nonDefaultValues;

    /**
     * Constructs a SparseMatrix object with an integer matrix as parameters.
     *
     * @param matrix The standard matrix to be converted to a SparseMatrix object.
     */
    public SparseMatrix(int[][] matrix) {

        this.rows = matrix.length;
        this.cols = matrix[0].length;

        // Determine the most common value to use as the default
        int defaultValue = getMajorityValue(matrix);
        this.defaultValue = defaultValue;

        // Store only the values that differ from the default
        ArrayList<SparseEntry> entries = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != defaultValue) {
                    entries.add(new SparseEntry(matrix[i][j], i, j));
                }
            }
        }
        this.nonDefaultValues = entries;

        // Verify that converting back to a standard matrix matches the original input
        assert matricesAreEqual(this.outputToMatrix(), matrix);
    }

    /**
     * Finds the most commonly occurring value in a matrix.
     * Used to determine the default value for the sparse representation.
     *
     * @param matrix The standard 2D array to analyze.
     * @return The integer value that appears most frequently in the matrix.
     */
    private int getMajorityValue(int[][] matrix) {

        // Count how many times each value appears
        HashMap<Integer, Integer> entries = new HashMap<>();

        for (int[] row : matrix) {
            for (int num : row) {
                entries.put(num, entries.getOrDefault(num, 0) + 1); // Claude
            }
        }

        // Find the value with the highest count
        int maxKey = 0;
        int maxValue = 0;

        for (Map.Entry<Integer, Integer> entry : entries.entrySet()) { // Claude
            if (entry.getValue() > maxValue) {
                maxValue = entry.getValue();
                maxKey = entry.getKey();
            }
        }

        return maxKey;
    }

    /**
     * Compares two 2D arrays for equality by checking dimensions and values.
     *
     * @param expected The first matrix to compare.
     * @param actual The second matrix to compare.
     * @return True if both matrices have the same size and contain the same values.
     */
    public static boolean matricesAreEqual(int[][] expected, int[][] actual) {

        // Check that row and column counts match
        if (expected.length != actual.length) return false;
        if (expected[0].length != actual[0].length) return false;

        // Compare each element
        for (int i = 0; i < expected.length; i++) {
            for (int j = 0; j < expected[i].length; j++) {
                if (expected[i][j] != actual[i][j]) return false;
            }
        }

        return true;
    }

    /**
     * Converts this SparseMatrix back into a standard 2D array.
     *
     * @return A 2D array reconstructed from the sparse representation.
     */
    public int[][] outputToMatrix() {

        // Fill the entire matrix with the default value
        int[][] resultMatrix = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                resultMatrix[i][j] = defaultValue;
            }
        }

        // Overwrite positions that have non-default values
        for (SparseEntry entry : nonDefaultValues) {
            resultMatrix[entry.row][entry.col] = entry.value;
        }

        return resultMatrix;
    }

    public static void main(String[] args) {

        System.out.println();

        System.out.println("=============== TEST 1 ===============");
        int[][] testMatrix1 = {
            { 5, 0, 0, 0 },
            { 0, 8, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 6, 0, 0 }
        };
        SparseMatrix sparse1 = new SparseMatrix(testMatrix1);
        System.out.println(sparseMatrixToString(sparse1));
        System.out.println();
        int[][] result1 = sparse1.outputToMatrix();
        System.out.println(matrixToString(result1));

        System.out.println("=============== TEST 2 ===============");
        int[][] testMatrix2 = {
            { 8, 8, 8, 8, 8, 8, 8, 8, 8, 9 },
            { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
            { 0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
            { 8, 8, 8, 8, 8, 8, 8, 7, 8, 8 },
            { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
            { 8, 8, 8, 8, 2, 8, 8, 8, 8, 8 },
            { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
            { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
            { 8, 8, 1, 8, 8, 8, 8, 8, 8, 8 },
            { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 }
        };
        SparseMatrix sparse2 = new SparseMatrix(testMatrix2);
        System.out.println(sparseMatrixToString(sparse2));
        System.out.println();
        int[][] result2 = sparse2.outputToMatrix();
        System.out.println(matrixToString(result2));

        System.out.println("=============== TEST 3 ===============");
        int[][] testMatrix3 = {
            { 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 876, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 53, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 43, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 873, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 21, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 213, 0, 0, 0 }
        };
        SparseMatrix sparse3 = new SparseMatrix(testMatrix3);
        System.out.println(sparseMatrixToString(sparse3));
        System.out.println();
        int[][] result3 = sparse3.outputToMatrix();
        System.out.println(matrixToString(result3));
    }

    /**
     * Returns a string representation of a SparseMatrix showing its dimensions,
     * default value, and all non-default entries.
     *
     * @param matrix The SparseMatrix to convert to a string.
     * @return A formatted string describing the sparse matrix.
     */
    static String sparseMatrixToString(SparseMatrix matrix) {

        StringBuilder sb = new StringBuilder();
        for (SparseEntry entry : matrix.nonDefaultValues) {
            sb.append("\n").append(entry);
        }

        return
            "\nRow count: " + matrix.rows +
            "\nColumn count: " + matrix.cols +
            "\nDefault value: " + matrix.defaultValue +
            "\nNon-default values: " + sb
        ;
    }

    /**
     * Returns a tab-separated string representation of a standard 2D array.
     *
     * @param matrix The 2D array to convert to a string.
     * @return A formatted string with rows separated by newlines and values separated by tabs.
     */
    static String matrixToString(int[][] matrix) {

        StringBuilder sb = new StringBuilder();

        for (int[] row : matrix) {
            for (int num : row) {
                sb.append(num).append("\t");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Represents a single non-default entry in the sparse matrix,
     * storing the value and its row/column position.
     */
    public static class SparseEntry {

        private final int value;
        private final int row;
        private final int col;

        /**
         * Constructs a SparseEntry with a value and its position in the matrix.
         *
         * @param value The non-default value.
         * @param row The row index of the value.
         * @param col The column index of the value.
         */
        public SparseEntry(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return value + "\t at [" + row + ", " + col + "]";
        }
    }
}
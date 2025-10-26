package org.example.array;

public class MatrixProbs {

    public static int diagonalSum(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int d1 = 0, d2 = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == j) {
                    d1 += mat[i][j];
                } else if (j == n - (i + 1)) {
                    d2 += mat[i][j];
                }
            }
        }

        return d1 + d2;
    }

    public static int maxSumOfSpecificPattern(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < m - 2; j++) {
                int sum = mat[i][j] + mat[i][j + 1] + mat[i][j + 2]
                        + mat[i + 1][j + 1]
                        + mat[i + 2][j] + mat[i + 2][j + 1] + mat[i + 2][j + 2];

                if (max < sum) {
                    max = sum;
                }
            }
        }

        return max;
    }

    public static int[][] transposeMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] transposeMat = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                transposeMat[j][i] = mat[i][j];
            }
        }

        return transposeMat;
    }

    public static int[][] setZeroes(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        boolean[] rows = new boolean[n];
        boolean[] cols = new boolean[m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    rows[i] = true;
                    cols[j] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rows[i] || cols[j]) {
                    mat[i][j] = 0;
                }
            }
        }

        return mat;
    }

    public static void main(String[] args) {

    }
}

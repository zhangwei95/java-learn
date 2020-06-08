package algorithm.leetcode;

import java.util.Random;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 * 矩阵
 */
public class T029_PrintMatrix {

    public static void main(String[] args) {

        int row = new Random().nextInt(9) + 1;
        int column = new Random().nextInt(9) + 1;
        int[][] test = new int[row][column];

        for (int i = 0; i < test.length; i++) {
            for (int j = 0; j < test[0].length; j++) {
                test[i][j] = test[0].length * i + j + 1;
                System.out.print(test[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
        int[] ints = printMatrix(test);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]);
            System.out.print("\t");
        }
    }

    public static int[] printMatrix(int[][] matrix) {
        if(matrix.length == 0){
            return new int[0];
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int total = rows * columns;
        int[] result = new int[total];
        int row = 0;
        int column = 0;
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int[][] moveWay = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int direction = 0;
        for (int i = 0; i < total; i++) {
            result[i] = matrix[row][column];
            visited[row][column] = true;
            int nextRow = row + moveWay[direction][0];
            int nextColumn = column + moveWay[direction][1];
            if (nextRow < 0 || nextColumn < 0 || nextRow >= rows || nextColumn >= columns || visited[nextRow][nextColumn]) {
                direction = (direction + 1) % 4;
            }
            row += moveWay[direction][0];
            column += moveWay[direction][1];
        }

        return result;
    }

}

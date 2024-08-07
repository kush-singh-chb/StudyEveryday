package org.example.l75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSeas {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;


        boolean[][] pac = new boolean[rows][cols];
        boolean[][] atl = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            dfs(0, i, rows, cols, pac, heights[0][i], heights); // 0,0 |
            dfs(rows - 1, i, rows, cols, atl, heights[cols - 1][i], heights);
        }
        for (int i = 0; i < cols; i++) {
            dfs(i, 0, rows, cols, pac, heights[i][0], heights);
            dfs(i, cols - 1, rows, cols, atl, heights[i][rows - 1], heights);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++) {
                if (pac[i][j] && atl[i][j])
                    result.add(Arrays.asList(i, j));
            }
        return result;
    }

    private void dfs(int row, int col, int rows, int cols, boolean[][] visited, int prevHeight, int[][] heights) {
        if (row < 0 || col < 0 || row >= rows || col >= cols || visited[row][col] || prevHeight > heights[row][col]) {
            return;
        }
        visited[row][col] = true;
        dfs(row + 1, col, rows, cols, visited, heights[row][col], heights);
        dfs(row - 1, col, rows, cols, visited, heights[row][col], heights);
        dfs(row, col + 1, rows, cols, visited, heights[row][col], heights);
        dfs(row, col - 1, rows, cols, visited, heights[row][col], heights);
    }

    public static void main(String[] args) {
        TwoSeas twoSeas = new TwoSeas();
        twoSeas.pacificAtlantic(new int[][]{new int[]{1, 2, 2, 3, 5}, new int[]{3, 2, 3, 4, 4}, new int[]{2, 4, 5, 3, 1}, new int[]{6, 7, 1, 4, 5}, new int[]{5, 1, 1, 2, 4}});
    }
}

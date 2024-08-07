package org.example.l75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        var ans = new ArrayList<Integer>();
        int maxRow = matrix.length;
        int maxCol = matrix[0].length;
        int row = 0;
        int col = -1;
        int direction = 1;
        while(row > 0 && col > 0){
            for(int i = 0; i < maxCol; i++){
                col+=direction;
                ans.add(matrix[row][col]);
            }
            row--;
            for (int i = 0; i < maxRow; i++) {
                row += direction;
                ans.add(matrix[row][col]);
            }
            col--;

            // Flip the direction for the next traversal
            direction *= -1;
        }
        return ans;
    }

    public static void main(String[] args) {
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        spiralMatrix.spiralOrder(new int[][]{new int[]{1,2,3,4},new int[]{5,6,7,8},new int[]{9,10,11,12}});
    }
}

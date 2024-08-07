package org.example.l75;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RotateImage {

    public void rotate(int[][] matrix){
        int n= matrix.length;
        int i=0; int j=n-1;
        while(i<j){
            for(int k=0; k<n; k++){
                int temp = matrix[i][k];
                matrix[i][k]=matrix[j][k];
                matrix[j][k]=temp;
            }
            i++; j--;
        }
        for(int m=0; m<n; m++){
            for(int l=m+1; l<n; l++){
                int temp = matrix[m][l];
                matrix[m][l]=matrix[l][m];
                matrix[l][m]=temp;
            }
        }
    }
    public static void main(String[] args) {
        RotateImage rotateImage = new RotateImage();
        rotateImage.rotate(new int[][]{new int[]{1,2,3},new int[]{4,5,6},new int[]{7,8,9}});
        Set<Character> charsSet = "test".chars()
                .mapToObj(e->(char)e).collect(Collectors.toSet());

    }
}

package org.example.l75;

import java.util.*;
import java.util.stream.Stream;

public class MergeIntervals {

    public static void quickSort(int[][] sortArr, int low, int high) {
        if (sortArr.length == 0 || low >= high) return;

        int middle = low + (high - low) / 2;
        int border = sortArr[middle][0];

        int i = low, j = high;
        while (i <= j) {
            while (sortArr[i][0] < border) i++;
            while (sortArr[j][0] > border) j--;
            if (i <= j) {
                int[] swap = sortArr[i];
                sortArr[i] = sortArr[j];
                sortArr[j] = swap;
                i++;
                j--;
            }
        }

        if (low < j) quickSort(sortArr, low, j);
        if (high > i) quickSort(sortArr, i, high);
    }

    public int[][] merge(int[][] intervals) {
        int[][] sortArr = intervals.clone();

        Arrays.sort(intervals,Comparator.comparingInt(a-> a[0]));

        int i = 1;
        int j = 0;
        int length = sortArr.length;
        while (i < length) {
            if (sortArr[j][1] >= sortArr[i][0]) {
                sortArr[j] = new int[] {sortArr[j][0], Math.max(sortArr[j][1], sortArr[i][1])};
                sortArr[i] = new int[] {-1, -1};

            } else {
                j++;
                sortArr[j] = sortArr[i];
                if (i != j) {
                    sortArr[i] = new int[]{-1, -1};
                }
            }

            i++;
        }

        int[][] ans = new int[j + 1][2];
        j = 0;
        for (int[] ints : sortArr) {
            if (ints[0] != -1) {
                ans[j] = ints;
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MergeIntervals mergeIntervals = new MergeIntervals();
        mergeIntervals.merge(new int[][]{new int[]{1,3},new int[]{2,6},new int[]{8,10},new int[]{15,18}});
    }
}

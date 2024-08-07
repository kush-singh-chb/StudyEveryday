package org.example.l75;

import java.util.HashMap;
import java.util.PriorityQueue;

public class KthClosestDistances {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Double> heap = new PriorityQueue<>();
        HashMap<Double, int[]> pointDistanceMap = new HashMap();
        for(int[] point: points){
            var euclidean = Math.sqrt(Math.pow(point[0] - point[1], 2));
            pointDistanceMap.put(euclidean,point);
            heap.add(euclidean);
        }
        int i = 0;
        int[][] ans = new int[2][k];
        while(i < k){
            ans[i] = pointDistanceMap.get(heap.poll());
            i++;
        }
        return ans;
    }
}

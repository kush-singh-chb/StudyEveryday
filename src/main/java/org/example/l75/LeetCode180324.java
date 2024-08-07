package org.example.l75;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LeetCode180324 {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int[][] aug = new int[n][2];
        for (int i = 0; i < n; ++i) {
            aug[i][0] = nums1[i];
            aug[i][1] = nums2[i];
        }
        Arrays.sort(aug, new Comparator<int[]>() {
            public int compare(int[] lhs, int[] rhs) {
                return -Integer.compare(lhs[1], rhs[1]);
            }
        });
        Arrays.copyOfRange(nums1, 0, 1);
        PriorityQueue<Integer> pq = new PriorityQueue();
        long ans = 0, total = 0;
        for (int i = 0; i < aug.length; ++i) {
            total += aug[i][0];
            pq.add(aug[i][0]);
            if (i >= k) total -= pq.poll();
            if (i >= k - 1) ans = Math.max(ans, total * aug[i][1]);
        }
        return ans;
    }

    private class Pair {
        public int[] a, b;

        Pair(int[] a, int[] b) {
            this.a = a;
            this.b = b;
        }

    }

    public long totalCost(int[] costs, int k, int candidates) {
        var totalCost = 0;
        while (k > 0) {
            int i = 0;
            int j = costs.length - 1;
            var min = Integer.MAX_VALUE;
            while (i < candidates) {
                min = Math.min(min, Math.min(costs[i], costs[j]));
                i++;
                j--;
            }
            totalCost += min;
            int finalMin = min;
            int[] finalCosts = new int[costs.length - 2];
            int[] tempCost = costs;
            var first = IntStream.range(0, costs.length)
                    .filter(x -> tempCost[x] == finalMin)
                    .findFirst().getAsInt();
            int p = -1;
            for (int y = 0; y < costs.length - 1; y++) {
                if (y != first) {
                    finalCosts[++p] = costs[y];
                }
            }
            costs = finalCosts;
            k--;
        }
        return totalCost;
    }

    public int solution(int[] D, String[] T) {
        int plasticTime = 0, glassTime = 0, metalTime = 0;
        int pp = 0, pg = 0, pm = 0;
        int prevDis = 0;
        // Iterate through each house
        for (int i = 0; i < T.length; i++) {
            String bags = T[i];
            int distance = D[i];
            // Count the number of bags of each type in the current house
            Map<Character, Integer> bagCount = new HashMap<>();
            for (char c : bags.toCharArray()) {
                bagCount.put(c, bagCount.getOrDefault(c, 0) + 1);
            }
            // Update the time for each truck based on the bags collected in the current house
            plasticTime += (distance * 2) + bagCount.getOrDefault('P', 0);
            glassTime += (distance * 2) + bagCount.getOrDefault('G', 0);
            metalTime += (distance * 2) + bagCount.getOrDefault('M', 0);
        }

        // Return the maximum time among the three trucks
        return Math.max(plasticTime, Math.max(glassTime, metalTime));
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        var list = new ArrayList<Integer>();
        for (int i = 0; i < spells.length - 1; i++) {
            int finalI = i;
            list.add((int) Arrays.stream(potions).map(x -> x * spells[finalI]).filter(x -> x >= success).count());
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
    private HashMap<Integer, Integer> points = new HashMap<>();
    private HashMap<Integer, Integer> cache = new HashMap<>();

    private int maxPoints(int num) {
        // Check for base cases
        if (num == 0) {
            return 0;
        }

        if (num == 1) {
            return points.getOrDefault(1, 0);
        }

        if (cache.containsKey(num)) {
            return cache.get(num);
        }

        // Apply recurrence relation
        int gain = points.getOrDefault(num, 0);
        cache.put(num, Math.max(maxPoints(num - 1), maxPoints(num - 2) + gain));
        return cache.get(num);
    }

    public int deleteAndEarn(int[] nums) {
        int maxNumber = 0;

        // Precompute how many points we gain from taking an element
        for (int num : nums) {
            points.put(num, points.getOrDefault(num, 0) + num);
            maxNumber = Math.max(maxNumber, num);
        }

        return maxPoints(maxNumber);
    }

    public static void main(String[] args) {
        var lc = new LeetCode180324();
        //System.out.println(lc.maxScore(new int[]{1,3,3,2}, new int[]{2,1,3,4}, 3 ));
        //System.out.println(lc.totalCost(new int[]{1,2,4,1},3,3));
//        System.out.println(lc.successfulPairs(new int[]{3,1,2},new int[]{8,5,8},16));
//        System.out.println(lc.solution(new int[]{2, 1, 1, 1, 2}, new String[]{"", "PP", "PP", "GM", ""}));
        System.out.println(lc.deleteAndEarn(new int[]{3,4,2}));
    }
}

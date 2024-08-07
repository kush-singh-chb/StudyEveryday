package org.example.l75;

import java.util.Arrays;

public class LeetCode250324 {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, -1);
        return lootHouses(nums, 0, nums.length - 1, dp);
    }
    int lootHouses(int[] nums, int s, int e, int[] dp){
        if (s > e)
            return 0;

        if (s == e)
            return nums[s];

        if (dp[s] != -1)
            return dp[s];

        return dp[s] = Math.max(nums[s] + lootHouses(nums, s + 2, e, dp),
                lootHouses(nums, s + 1, e, dp));
    }

    public static void main(String[] args) {
        LeetCode250324 lc = new LeetCode250324();
        lc.rob(new int[]{2,7,9,3,1});
    }
}

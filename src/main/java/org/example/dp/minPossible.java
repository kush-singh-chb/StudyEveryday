package org.example.dp;

import java.util.HashMap;
import java.util.Map;

public class minPossible {

    public static void main(String[] args) {
        minPossible mp = new minPossible();
        System.out.println(mp.coinChange(new int[]{1,2,5},11));
    }

    private int minPossible(int amount, int[] nums){
        var memo = new HashMap<Integer, Integer>();
        return minPossible(amount, nums, memo);
    }
    private int minPossible(int amount, int[] nums,HashMap<Integer, Integer> memo){
        if(amount == 0){
            return 0;
        }
        if(amount < 0){
            return -1;
        }
        if(memo.containsKey(amount)){
            return memo.get(amount);
        }
        int minCoin = -1;
        for(int i= 0; i < nums.length - 1; i++){
            int newAmount = amount - nums[i];
            int subCoins = minPossible(newAmount, nums);
            if(subCoins != -1){
                int numCoins = subCoins + 1;
                if(numCoins < minCoin || minCoin == -1){
                    minCoin = numCoins;
                }
            }
        }

        return minCoin;
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int target = 1; target <= amount; target++) {
            for (int coin : coins) {
                if (target - coin >= 0 && dp[target - coin] != Integer.MAX_VALUE)
                    dp[target] = Math.min(dp[target], 1 + dp[target - coin]);
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

}

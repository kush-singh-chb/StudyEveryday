package org.example.l75;

import java.util.Arrays;

public class LC1770 {
        private int[][] memo;
        private int[] nums, multipliers;
        private int n, m;

        private int dp(int i, int left) {
            if (i == m) {
                return 0; // Base case
            }

            int mult = multipliers[i];
            int right = (n - 1) - (i - left);
            System.out.println(i + 1 +", " + (left + 1));
            System.out.println(i + 1 +", " + left);
            if (memo[i][left] == 0) {
                // Recurrence relation
                memo[i][left] = Math.max(mult * nums[left] + dp(i + 1, left + 1),
                        mult * nums[right] + dp(i + 1, left));
            }

            return memo[i][left];
        }

        public int maximumScore(int[] nums, int[] multipliers) {
            n = nums.length;
            m = multipliers.length;
            this.nums = nums;
            this.multipliers = multipliers;
            this.memo = new int[m][m];
            return dp(0, 0);
        }

    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];

        for(int i=dp.length-2; i>=0; i--) {
            for(int j=dp[0].length-2; j>=0; j--) {

                int c1 = text1.charAt(i);
                int c2 = text2.charAt(j);

                if(c1==c2) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                }
                else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j + 1]);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        var lc = new LC1770();
//        System.out.println(lc.maximumScore(new int[]{-5,-3,-3,-2,7,1},new int[]{-10,-5,3,4,6}));
        System.out.println(lc.longestCommonSubsequence("abcde","ace"));
    }
}

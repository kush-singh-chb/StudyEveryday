package org.example.l75;

class MaxProfit {
    public static int maxProfit(int[] prices) {
        int lsf = Integer.MAX_VALUE;
        int maxProfit = 0;
        int profitToday;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < lsf) {
                lsf = prices[i];
            }
            profitToday = prices[i] - lsf;
            maxProfit = Math.max(maxProfit, profitToday);

        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int maxProfit = maxProfit(new int[]{1, 4, 2});
        System.out.println(maxProfit);
    }
}
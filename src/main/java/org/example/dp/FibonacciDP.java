package org.example.dp;

public class FibonacciDP {

    public static void main(String[] args) {
        FibonacciDP fibonacciDP = new FibonacciDP();
        var memo = new Integer[574];
        memo[0] = 0;
        memo[1] = 0;
        memo[2] = 1;
        System.out.println(fibonacciDP.calculateFib(5, memo));
    }

    private int calculateFib(int i, Integer[] memo) {
        if(memo[i] != null) {
            return memo[i];
        }
        memo[i] = calculateFib(i-1, memo) + calculateFib(i-2, memo) + calculateFib(i-3, memo);
        return memo[i];
    }
}

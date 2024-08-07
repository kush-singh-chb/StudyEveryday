package org.example.l75;

import java.util.*;

public class LeetCode190324 {
    String[] keypad = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0)
            return combinations;
        StringBuilder helper = new StringBuilder();
        calculate(digits, 0, helper, combinations);
        return combinations;
    }

    public void calculate(String digits, int index, StringBuilder helper, List<String> combinations) {
        if (index == digits.length()) {
            combinations.add(helper.toString());
            return;
        }
        int digit = digits.charAt(index) - '0';
        for (int i = 0; i < keypad[digit].length(); i++) {
            helper.append(keypad[digit].charAt(i));
            calculate(digits, index + 1, helper, combinations);
            helper.deleteCharAt(helper.length() - 1);
        }
    }
    Integer[] memo = new Integer[37];

    public int tribonacci(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (n == 2) {
            return 1;
        }

        var one = (memo[n-1] != null) ? memo[n-1] : tribonacci(n-1);
        var two =  (memo[n-2]!= null) ? memo[n-2] : tribonacci(n-2);
        var three =  (memo[n-3]!= null) ? memo[n-3] : tribonacci(n-3);
        if(memo[n-1]==null){
            memo[n-1]= one;
        }
        if(memo[n-2]==null){
            memo[n-2]= two;
        }
        if(memo[n-3]==null){
            memo[n-3]= three;
        }
        return one + two + three;
    }


    public static void main(String[] args) {
        var lc = new LeetCode190324();
        System.out.println(lc.letterCombinations("23"));
//        System.out.println(lc.tribonacci(4));
    }
}

package org.example.l75;

import java.util.Stack;

class Solution {
    public static void main(String... args) {
        var solution = new Solution();
        solution.convert("PAYPALISHIRING", 3);
    }

    public String convert(String s, int numRows) {
        StringBuilder sb0 = new StringBuilder(s);
        StringBuilder sb = new StringBuilder();
        int sp = 0;
        for (int i = sp; i <= numRows; i += numRows) {
            if (sb0.length() == sb.length()) {
                break;
            }
            sb.append(s.charAt(i));
            if (i + numRows >= sb0.length()) {
                sp++;
            }
            sb0.deleteCharAt(i);
        }
        System.out.println(sb);
        return sb.toString();
    }
}
package org.example.l75;

public class RepeatedSubstringPattern {
    public static boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                String substring = s.substring(0, i);
                StringBuilder repeated = new StringBuilder();
                repeated.append(substring.repeat(n / i));
                if (repeated.toString().equals(s)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RepeatedSubstringPattern.repeatedSubstringPattern("abcabc");
    }
}
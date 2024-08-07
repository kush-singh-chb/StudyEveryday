package org.example.l75;

import java.util.Arrays;

public class CheckInclusion {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] freq = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i <= s2.length() - s1.length(); i++) {
            int[] check = new int[26];
            int count = s1.length();

            for (int j = i; j < s1.length() + i; j++) {
                if (freq[s2.charAt(j) - 'a'] == 0) {
                    break;
                } else {
                    check[s2.charAt(j) - 'a']++;
                    count--;
                }
            }
            if (Arrays.equals(check, freq) && count == 0) {
                return true;
            }
        }
        return false;
    }
}

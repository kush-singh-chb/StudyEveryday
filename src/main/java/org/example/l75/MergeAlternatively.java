package org.example.l75;

import java.util.HashSet;
import java.util.Set;

public class MergeAlternatively {
    public static String mergeAlternately(String word1, String word2) {
        var gcd = new HashSet<Character>();
        var sb = new StringBuilder();
        int i = 0;
        while(i < word1.length() || i < word2.length()){
            if (i < word1.length()) {
                sb.append(word1.charAt(i));
            }
            if (i < word2.length()) {
                sb.append(word2.charAt(i));
            }
            i++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        mergeAlternately("abc","pqrs");
    }
}

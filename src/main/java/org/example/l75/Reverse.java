package org.example.l75;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Reverse {

    public static String reverseVowels(String s) {
        var vowels = new HashSet<>(List.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        var chars = s.toCharArray();
        int i = 0, j = s.length()-1;
        while (i<j){
            if(vowels.contains(s.charAt(i)) && vowels.contains(s.charAt(j))){
                var temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
                continue;
            }
            if(!vowels.contains(s.charAt(i))){
                i++;
            }
            if(!vowels.contains(s.charAt(j))){
                j--;
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
//        System.out.println(reverseVowels("Aa"));
        System.out.println(exceptItself(new int[]{1,2,3,4}));
    }

    private static String reverseWord(String s) {
        var s1 = s.split(" ");
        var res = new Stack<String>();
        for(int i=s1.length-1; i>=0; i--) {
            if(!s1[i].equals("")) res.push(s1[i].trim());
        }
        return String.join(" ", res);
    }

    private static int[] exceptItself(int[] nums){
        int numsLength = nums.length;
        int prefixProduct = 1;
        int suffixProduct = 1;
        int[] result = new int[numsLength];
        for(int i = 0; i < numsLength; i++) {
            result[i] = prefixProduct;
            prefixProduct *= nums[i];
        }
        for(int i = numsLength-1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }
        return result;
    }
}

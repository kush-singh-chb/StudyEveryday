package org.example.l75;

import java.util.Arrays;
import java.util.List;

public class Candies {
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        Boolean[] booleans = new Boolean[candies.length];
        var offerCandies = new int[candies.length];
        var max = Integer.MIN_VALUE;
        Arrays.fill(booleans,false);
        for(int i=0; i < candies.length ; i++){
            offerCandies[i] = candies[i] + extraCandies;
            max = Math.max(candies[i], max);
        }
        for(int i=0; i < offerCandies.length ; i++){
            booleans[i] = offerCandies[i] >= max;
        }
        return Arrays.asList(booleans);
    }

    public static void main(String[] args) {
        kidsWithCandies(new int[]{2,3,5,1,3},3);
    }
}

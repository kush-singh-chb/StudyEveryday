package org.example.dp;

import java.util.HashMap;

public class IsSumPossible {

    public static void main(String[] args) {
        IsSumPossible isSumPossible = new IsSumPossible();
        System.out.println(isSumPossible.checkPossible(new int[]{9,2,2}, 7, new HashMap<>()));;
    }

    private boolean checkPossible(int[] nums, int leftOver, HashMap<Integer, Boolean> memo){
        if(leftOver == 0){
            return true;
        }

        if(leftOver < 0){
            return false;
        }
        if(memo.containsKey(leftOver)){
            return memo.get(leftOver);
        }
//        if (leftOver > 0 && i > nums.length){
//            return false;
//        }
        for(int x = 0; x < nums.length; x++){
            var remaining = leftOver - nums[x];
            if (checkPossible(nums, remaining, memo)){
                memo.put(leftOver, true);
                return true;
            }
        }


        return false;
    }
}

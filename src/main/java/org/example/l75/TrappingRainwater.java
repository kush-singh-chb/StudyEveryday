package org.example.l75;

public class TrappingRainwater {

    public int trap(int[] height) {
        int sum = 0;
        int i=0,left_max=height[i]; // left 0,1,0
        int j=height.length-1,right_max=height[j]; // right 1,2,1
        while (i<j)
        {
            if(left_max <= right_max)
            {
                sum+=(left_max-height[i]);
                i++;
                left_max=Math.max(left_max,height[i]);
            }
            else
            {
                sum+=(right_max-height[j]);
                j--;
                right_max=Math.max(right_max,height[j]);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        var calcWater = new TrappingRainwater();
        calcWater.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1});
    }
}

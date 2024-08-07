package org.example.l75;

public class CountNBits {
    public int hammingWeight(long n) {
        var count = 0;
        var num = n;
        if(n<0) {
            num *= -1;
            count -= 1;
        }
        while (num != 0){
            count += num & 1;
            num = num >> 1;
        }
        return count;
    }
}

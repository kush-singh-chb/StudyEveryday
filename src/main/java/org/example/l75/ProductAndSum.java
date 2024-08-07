package org.example.l75;

public class ProductAndSum {
    public int subtractProductAndSum(int n) {
        int number = n;
        int sum = 0;
        int product = 1;
        while(number > 0){
            var temp = number%10;
            sum += temp;
            product *= temp;
            number /= 10.0;
        }
        return product - sum;
    }
}

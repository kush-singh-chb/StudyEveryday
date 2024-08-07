package org.example.l75;

public class ATOI {
    public static void main(String[] args) {
        myAtoi("   -42");
    }
    public static int myAtoi(String s) {
        String input = s.trim();
        int i = 0, n = input.length();
        while(i < n && input.charAt(i) == ' '){
            i++;
        }
        int positive=0, negative=0;
        if(i<n && input.charAt(i)  == '+'){
            positive++;
            i++;
        }
        if(i<n && input.charAt(i)  == '-'){
            negative++;
            i++;
        }
        double ans = 0;
        while(i<n && input.charAt(i)  >= '0' && input.charAt(i) <='9'){
            ans= ans*10 + (input.charAt(i) - '0');
            i++;
        }

        if(negative > 0)
            ans = -ans;
        if(positive > 0 && negative > 0)
            return 0;

        int INT_MAX = (int) Math.pow(2, 31) - 1;
        int INT_MIN = (int) Math.pow(-2, 31);
        if(ans > INT_MAX)
            ans = INT_MAX;

        if(ans < INT_MIN)
            ans = INT_MIN;

        System.out.println((int)ans);
        return (int)ans;

    }
}

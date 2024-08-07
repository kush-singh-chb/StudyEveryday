package org.example.l75;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class LowerCase {
    public static int solution(String S, int B){
        int len = S.length();
        ArrayList<Integer> holes = new ArrayList<>();
        int i = 0;
        while(i < len)
        {
            int count = 0;
            while(i < len && S.charAt(i) == 'x')
            {
                count++;
                i++;
            }
            if(count > 0)
            {
                holes.add(count);
            }
            if(count == 0)
            {
                i++;
            }
            holes.sort(Collections.reverseOrder());
        }

        i = 0;
        int result = 0;
        while(B > 1)
        {
            if(i == holes.size())
                break;

            int cost = Math.min((holes.get(i) + 1), B);
            B = B - cost;
            int temp = cost - 1;
            result = result + temp;
            holes.set(i, holes.get(i) - temp);
            if(holes.get(i) == 0)
            {
                i++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution("..xxxxx",4));
    }
}

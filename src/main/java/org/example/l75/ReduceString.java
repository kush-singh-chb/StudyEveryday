package org.example.l75;

public class ReduceString {
    int solution(String s) {
        String currentResult = reduceString(s);

        if (currentResult.length() < 2){
            return currentResult.length();
        }
        String previous = s;
        System.out.println("Current: " + currentResult);
        System.out.println("Previous: " + previous);
        currentResult = reduceString(currentResult);
        previous = currentResult;

        //  "01010"


        return currentResult.length();
    }

    String reduceString(String s){
        StringBuilder sb = new StringBuilder();
        int i = 1;

        while (i < s.length() - 1){
            char currChar = s.charAt(i);
            char prevChar = s.charAt(i-1);
            // * and *
            // * and 0
            // * and 1

            if(currChar == '*' || prevChar == '*'){
                sb.append(prevChar);
                i += 1;
                continue;
            }
            int prev = prevChar - '0';
            int curr = currChar - '0';
            // 0 0
            // 1 1
            if(prev == curr){
                sb.append(currChar);
                sb.append(prevChar);
                i += 2;
                continue;
            }
            if(prevChar != currChar){
                // both deleted
                // 1 0
                // 0 1
                i += 2;
                continue;
            }
            sb.append(currChar);

        }
        return sb.toString();
    }

}

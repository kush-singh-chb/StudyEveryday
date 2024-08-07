package org.example.l75;

import java.util.*;

public class RansomNote {

    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> occurance = new HashMap<>();
        for(int i = 0; i < ransomNote.length(); i++){
            if(occurance.containsKey(ransomNote.charAt(i))){
                char temp = ransomNote.charAt(i);
                occurance.put(temp, occurance.get(temp)+1);
            }else{
                occurance.put(ransomNote.charAt(i),1);
            }
        }
        for(int i = 0; i < magazine.length(); i++){
            char temp = magazine.charAt(i);
            if(occurance.containsKey(temp)){
                occurance.put(temp,occurance.get(temp)-1);
                if(occurance.get(temp) == 0){
                    occurance.remove(temp);
                }
            }
        }
        return occurance.keySet().isEmpty();
    }

    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> occurance = new HashMap<>();
        HashMap<Character, Integer> occurance2 = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            if(occurance.containsKey(s.charAt(i))){
                char temp = s.charAt(i);
                occurance.put(temp, occurance.get(temp)+1);
            }else{
                occurance.put(s.charAt(i),1);
            }
        }
        for(int i = 0; i < t.length(); i++){
            if(occurance2.containsKey(t.charAt(i))){
                char temp = t.charAt(i);
                occurance2.put(temp, occurance.get(temp)+1);
            }else{
                occurance2.put(t.charAt(i),1);
            }
        }
        for(Character c: occurance2.keySet()){
            if(!occurance.containsKey(c) && occurance.get(c) != occurance2.get(c)){
                return false;
            }
        }
        return true;
    }

    public int maxNumberOfBalloons(String text) {
        HashMap<Character, Integer> occurance = new HashMap<>();
        for(int i = 0; i < text.length(); i++){
            char key = text.charAt(i);
            if(occurance.containsKey(key)){
                occurance.put(key, occurance.get(key)+1);
            }else{
                occurance.put(key,1);
            }
        }
        boolean breaker = true;
        int ans = 0;
        String target = "baloon";
        while(breaker){
            for(int i = 0; i < target.length(); i++){
                char key = target.charAt(i);
                if(!occurance.containsKey(key) || occurance.get(key) == 0){
                    breaker = false;
                    ans--;
                }else{
                    occurance.put(key, occurance.get(key)-1);
                    if(occurance.get(key) == 0){
                        occurance.remove(key);
                    }
                }
            }
            ans++;
        }
        return ans;
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<Integer, List<String>> valueMap = new HashMap<>();
        for (String str : strs) {
            var total = 0;
            for (int x = 0; x < str.length(); x++) {
                total += str.charAt(x);
            }
            if (valueMap.containsKey(total)) {
                valueMap.get(total).add(str);
            } else {
                var newList = new ArrayList<String>();
                newList.add(str);
                valueMap.put(total, newList);
            }
        }
        return new ArrayList<>(valueMap.values());
    }


    public int calPoints(String[] operations) {
        LinkedList<Integer> scoreBoard = new LinkedList<>();
        for(String operation : operations){
            boolean isNumber = false;
            try{
                Integer.parseInt(operation);
                isNumber = true;
            }catch (NumberFormatException ignored){
            }
            if(isNumber){
                scoreBoard.add(Integer.valueOf(operation));
            }else{
                if(Objects.equals(operation, "C")){
                    if(!scoreBoard.isEmpty()){
                        scoreBoard.removeLast();
                    }
                } else if (Objects.equals(operation,"D")) {
                    int last = scoreBoard.peekLast() != null ? scoreBoard.peekLast() : 0;
                    scoreBoard.add(last * 2);
                }else {
                  int last = scoreBoard.removeLast();
                  int sec_last = scoreBoard.removeLast();
                    scoreBoard.add(sec_last);
                    scoreBoard.add(last);
                  scoreBoard.add(last + sec_last);
                }
            }
        }
        int total = 0;
        for(int x: scoreBoard){
            total += x;
        }
        return total;
    }
    public static void main(String[] args) {
        RansomNote note = new RansomNote();
        note.canConstruct("aa", "aab");
        note.calPoints(new String[]{"5","2","C","D","+"});
    }
}

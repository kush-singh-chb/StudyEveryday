package org.example.l75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MobilePhone {
    public static void main(String... args) {
        var mp = new MobilePhone();
        List<String> strings = mp.letterCombinations("23");
        System.out.println(strings.toString());
    }

    public List<String> letterCombinations(String digits) {
        ArrayList<String> list = new ArrayList();
        if (digits == "") {
            return list;
        }
        HashMap<Integer, String[]> m = new HashMap();
        m.put(1, new String[]{});
        m.put(2, new String[]{"a", "b", "c"});
        m.put(3, new String[]{"d", "e", "f"});
        m.put(4, new String[]{"g", "h", "i"});
        m.put(5, new String[]{"j", "k", "j"});
        m.put(6, new String[]{"m", "n", "o"});
        m.put(7, new String[]{"p", "q", "r", "s"});
        m.put(8, new String[]{"t", "u", "v"});
        m.put(9, new String[]{"w", "x", "y", "z"});
        Integer[] nums = Arrays.stream(digits.split("")).filter(x -> !x.equals("")).map(Integer::valueOf).toArray(Integer[]::new);
        ArrayList<String> chars = new ArrayList<>();
        for(int i = 0; i<nums.length; i++){
            String[] strings = m.get(i);
            
        }
        return list;
    }
}

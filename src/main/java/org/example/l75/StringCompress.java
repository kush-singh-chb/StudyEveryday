package org.example.l75;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringCompress {
    public static int compress(char[] chars) {
        int indexAns = 0, index = 0;
        while (index < chars.length) {
            char currentChar = chars[index];
            int count = 0;
            while (index < chars.length && chars[index] == currentChar) {
                index++;
                count++;
            }
            chars[indexAns++] = currentChar;
            if (count != 1) for (char c : Integer.toString(count).toCharArray())
                chars[indexAns++] = c;
        }
        return indexAns;
    }

    public static void moveZeroes(int[] nums) {
        if (nums.length == 1) {
            return;
        }

        int nonZeroIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroIndex] = nums[i];
                nonZeroIndex++;
            }
        }


        while(nonZeroIndex<nums.length){
            nums[nonZeroIndex] = 0;
            nonZeroIndex++;
        }
    }

    public static boolean isSubsequence(String s, String t) {
        if(s.length() == 0 || t.length() == 0) return false;
        int subString = s.length();
        int i = 0;
        int j = 0;
        while(i <= s.length() || j <= t.length()){
            if(s.charAt(i) == t.charAt(j)){
                subString--;
                i++;
            }
            j++;
        }
        return subString == 0;
    }

    public static int maxVowels(String s, int k) {
        int vowel=0;
        for(int i=0;i<k;i++)
            if(isVowel(s.charAt(i))) vowel++;

        int maxvowel=vowel;

        for(int i=k;i<s.length();i++){
            if(isVowel(s.charAt(i-k))) vowel--;
            if(isVowel(s.charAt(i))) vowel++;
            maxvowel= Math.max(maxvowel, vowel);
        }
        return maxvowel;
    }
    public static boolean isVowel(char c){
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    public static int maxArea(int[] height) {
        int max = Integer.MIN_VALUE;
        int sMax = Integer.MIN_VALUE;
        for (int j : height) {
            if (j > max) {
                max = j;
            } else{
                sMax = j;
            }
        }
        return (sMax != Integer.MIN_VALUE) ? sMax * sMax : max * max;
    }

    public static int maxOperations(int[] nums, int k) {
        var operations = 0;
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        while(i < j){
            var sum = (nums[i] + nums[j]);
            if(sum == k){
                operations++;
                i++;
                j--;
            }else if(sum > k) {
                j--;
            }else{
                i++;
            }
        }

        return operations;
    }

    public static double findMaxAverage(int[] nums, int k) {
        var sum = 0.0;
        for(int i = 0; i < k; i++){
            sum += nums[i];
        }
        var maxAvg = sum/k;
        for(int i = 1; i<=nums.length - k; i++){
            sum = sum - nums[i-1] + nums[i+k-1];
            maxAvg = Math.max(sum/k, maxAvg);
        }
        return maxAvg;
    }

    public static int longestOnes(int[] nums, int k) {
        int count = 0;
        var cur_count = 0;
        for(int i = 0; i < k; i++){
            if(nums[i] == 1){
                cur_count++;
            }else{
                count = Math.max(count,cur_count);
                cur_count = 0;
            }
        }
        var cur_zero = 0;
        int idx = k;
        while(idx < nums.length){
            if(nums[idx++] == 1){
                cur_count++;
            }else{
                cur_zero++;
                if(cur_zero > k){
                    count = Math.max(count,cur_count);
                    cur_count = 0;
                    cur_zero = 1;
                }
            }
        }
        count = Math.max(count,cur_count);
        return (cur_zero == k) ? count+cur_zero : count;
    }

    public static int longestSubarray(int[] nums) {
        var maxCount = 0;
        int i = 0;
        int j = nums.length -1;
        var zero = false;
        while (i<j){
            if(nums[i] == 1){
                maxCount++;
            }else if(nums[i] == 0 && !zero) {
                maxCount++;
                zero = true;
            }else {
                maxCount--;
            }
            i++;

            if(nums[j] == 1){
                maxCount++;
            }else if(nums[j] == 0 && !zero) {
                maxCount++;
                zero = true;
            }else {
                maxCount--;
            }
            j--;
        }
        return (zero) ? maxCount: maxCount - 1;
    }

    public static int pivotIndex(int[] nums) {
        var sumTotal = 0;
        var sumLeft = 0;
        for(int x : nums){
            sumTotal += x;
        }

        for(int o = 0; o < nums.length; sumLeft += nums[o++]){
            var test = sumTotal - nums[o];
            if(sumLeft * 2 == test ){
                return o;
            }
        }
        return -1;
    }

    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> n1 = IntStream.of(nums1).boxed().collect(Collectors.toList());
        List<Integer> n2 = IntStream.of(nums2).boxed().collect(Collectors.toList());
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 =  new ArrayList<>();
        for (Integer value : n1) {
            if (!n2.contains(value) && !list1.contains(value)) {
                list1.add(value);
            }
        }
        for (Integer integer : n2) {
            if (!n1.contains(integer) && !list2.contains(integer)) {
                list2.add(integer);
            }
        }
        res.add(list1);
        res.add(list2);
        return res;
    }
    public static boolean uniqueOccurrences(int[] arr) {
        var map = new HashMap<Integer, Integer>();
        var set = new HashSet<Integer>();
        for(int i : arr){
            if(set.add(i)){
                map.put(i,1);
            }else{
                var occ = map.get(i);
                map.put(i,occ+1);
            }
        }
        var unique = true;
        List<Integer> collect = map.values().stream().distinct().collect(Collectors.toList());
        if(collect.size() != map.values().size()){
            unique = false;
        }
        return unique;
    }
    public static boolean closeStrings(String word1, String word2) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (char ch : word1.toCharArray()) {
            freq1[ch - 'a']++;
        }

        for (char ch : word2.toCharArray()) {
            freq2[ch - 'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if ((freq1[i] == 0 && freq2[i] != 0) || (freq1[i] != 0 && freq2[i] == 0)) {
                return false;
            }
        }

        Arrays.sort(freq1);
        Arrays.sort(freq2);

        for (int i = 0; i < 26; i++) {
            if (freq1[i] != freq2[i]) {
                return false;
            }
        }

        return true;
    }
    private static int equalPairs(int[][] grid) {
        int n = grid.length;
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();
        for(int i = 0; i < n; i++) {
            StringBuilder s = new StringBuilder();
            for(int j = 0; j < n; j++) {
                s.append(grid[i][j]);
                s.append(":");
            }
            String str = s.toString();
            map1.put(str, map1.getOrDefault(str, 0) + 1);
        }
        for(int i = 0; i < n; i++) {
            StringBuilder s = new StringBuilder();
            for(int j = 0; j < n; j++) {
                s.append(grid[j][i]);
                s.append(":");
            }
            String str = s.toString();
            map2.put(str, map2.getOrDefault(str, 0) + 1);
        }
        int ans = 0;
        for(String s : map1.keySet()) {
            if(map2.containsKey(s)) {
                ans += map1.get(s) * map2.get(s);
            }
        }
        return ans;
    }

    public static int[] asteroidCollision(int[] asteroids) {
        var stack = new ArrayDeque<Integer>();
        Arrays.stream(asteroids).forEach(stack::push);
        while(!stack.isEmpty() && canCompare(stack)){
            var e1 = stack.pop();
            var e2 = stack.pop();
            if( e1 > 0 && e2 > 0){
                // same direction so no collision
                stack.push(e1);
                stack.push(e2);
            }else{
                // collision
                var sum = e1 + e2;
                if( sum < 0){
                    stack.push(e1);
                }else if(sum > 0) {
                    stack.push(e2);
                }
            }
        }
        var res = new int[stack.size()];
        for(int i = stack.size()-1; i >= 0; --i ){
            res[i] = stack.pop();
        }
        return res;
    }

    private static boolean canCompare(ArrayDeque<Integer> stack){
        return !(stack.stream().allMatch(x-> x<0) || stack.stream().allMatch(x-> x>0));
    }

    public static class ListNode {
     int val;
     ListNode next;
     ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        var oddList = head;
        var evenStart = head.next;
        var evenList = head.next;
        head = evenList;
        var odd = true;
        while(head != null || head.next != null) {
            if(odd){
                oddList.next = head.next;
                oddList = oddList.next;
                odd = false;
            }else{
                evenList.next = head.next;
                evenList = evenList.next;
                odd = true;
            }
            head = head.next;
        }
        // join bothList
        oddList.next = evenStart;

        // make head
        head = oddList;
        return head;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        var current = head;
        while(current != null){
            var next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }



     public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
     }

    public static int maxDepth(TreeNode root) {
        return findDepth(root,0);
    }

    public static int findDepth(TreeNode root, int prevSum){
        if(root == null) return prevSum;
        int left = findDepth(root.left, prevSum+1);
        int right = findDepth(root.right, prevSum+1);
        return Math.max(left,right);
    }

    int max = 0;
    int maxLevel = 0;
    public int maxLevelSum(TreeNode root) {
        if(root == null) return 0;
        int maxVal = Integer.MIN_VALUE; // To Store the maximum Value
        int ans = 1; // To Store Final answer
        int level = 1; // keeping track of level
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            int sum = 0;
            for(int i = 0; i < size; i++){
                TreeNode curr = q.poll();
                sum += curr.val;
                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
            if(sum > maxVal){ // updating the answer if found greater
                maxVal = sum;
                ans = level;
            }
            level++;
        }
        return ans;
    }

    public static TreeNode searchBST(TreeNode root, int val) {
        var stack = new Stack<TreeNode>();
        TreeNode ans = null;
        stack.push(root);
        while(!stack.isEmpty()){
            var node = stack.pop();
            if(node == null) {
                return ans;
            };
            if(node.val == val){
                ans = node;
            }else{
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return ans;
    }

    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Boolean[] visited = new Boolean[rooms.size()];
        Arrays.fill(visited, false);
        visited[0] = true;
        rooms.forEach(room -> {
            int index = rooms.indexOf(room);
            if(!room.isEmpty()){
                room.forEach( key -> {
                    if(key != index){
                        visited[key] = true;
                    }
                });
            }
        });
        return !Arrays.asList(visited).contains(Boolean.FALSE);
    }

    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        ArrayList<List<Integer>> l = new ArrayList<>();
        IntStream.range(0,n).forEach(i -> {
            var list = new ArrayList<Integer>();
            IntStream.range(0,isConnected[i].length).forEach(j -> {
                if(i != j && isConnected[i][j] == 1) {
                    list.add(isConnected[i][j]);
                }
            });
            l.add(list);
        });
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                // Starting BFS from unvisited node
                q.add(i);
                visited.add(i);
                while (!q.isEmpty()) {
                    int current = q.poll();
                    for (int j : l.get(current)) {
                        if (!visited.contains(j)) {
                            q.add(j);
                            visited.add(j);
                        }
                    }
                }
                count++; // Increment count for each connected component found
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(findCircleNum(new int[][]{new int[]{1,0,0},new int[]{0,1,0},new int[]{0,0,1}}));
//        System.out.println( canVisitAllRooms(List.of(List.of(1,3),List.of(3,0,1),List.of(2),List.of(0))));
//        System.out.println( canVisitAllRooms(List.of(List.of(1),List.of(2),List.of(3),Collections.emptyList())));
//        var root = new TreeNode(3,new TreeNode(9),new TreeNode(20,new TreeNode(15),new TreeNode(7)));
//        searchBST(root, 20);
//        maxDepth(root);
        // var l = new ListNode(1,new ListNode(2, new ListNode(3, new ListNode(4))));
        //reverseList(l);
        //System.out.println(Arrays.toString(asteroidCollision(new int[]{-2,-1,1,2})));
        //System.out.println(equalPairs(new int[][]{new int[]{12,12},new int[]{12,12}}));
        // System.out.println(closeStrings("abbbzcf","babzzcz"));
        //maxVowels("abciiidef",3);
        // findMaxAverage(new int[]{1,12,-5,-6,50,3},4);
        //maxOperations(new int[]{4,4,1,3,1,3,2,2,5,5,1,5,2,1,2,3,5,4}, 2);
//        System.out.println(maxArea(new int[] {1,2,1}));
// //       isSubsequence("a"," ");
//        compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'});
//        moveZeroes(new int[]{0, 1, 0, 3, 12});
    }


}

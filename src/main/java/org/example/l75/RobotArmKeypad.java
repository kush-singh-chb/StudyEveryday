package org.example.l75;

import java.util.HashMap;

public class RobotArmKeypad {

    public static String findMoves(int[] pin) {
        // Mapping of key position to its coordinates
        HashMap<Integer, int[]> keyMap = new HashMap<>();
        keyMap.put(1, new int[]{0, 0});
        keyMap.put(2, new int[]{0, 1});
        keyMap.put(3, new int[]{0, 2});
        keyMap.put(4, new int[]{1, 0});
        keyMap.put(5, new int[]{1, 1});
        keyMap.put(6, new int[]{1, 2});
        keyMap.put(7, new int[]{2, 0});
        keyMap.put(8, new int[]{2, 1});
        keyMap.put(9, new int[]{2, 2});

        // Initialize starting position of the robot arm
        int[] start = new int[]{0, 0};

        // Initialize the result string to store the sequence of moves
        StringBuilder result = new StringBuilder();

        // Start the backtracking process to find the sequence of moves for each digit in the pin
        for (int digit : pin) {
            int[] end = keyMap.get(digit);
            generateMoves(start, end, result);
            result.append("P");  // Press the key
            start = end;  // Update the current position of the robot arm
        }

        return result.toString();
    }

    // Recursive function to generate the sequence of moves using backtracking
    private static void generateMoves(int[] start, int[] end, StringBuilder result) {
        // Base case: If the current position is the same as the target position, return
        if (start[0] == end[0] && start[1] == end[1]) {
            return;
        }

        // Choose horizontal movement if possible
        if (start[1] < end[1]) {
            result.append("R");  // Move right
            start[1]++;
        } else if (start[1] > end[1]) {
            result.append("L");  // Move left
            start[1]--;
        }

        // Choose vertical movement if no horizontal movement is possible
        if (start[0] < end[0]) {
            result.append("D");  // Move down
            start[0]++;
        } else if (start[0] > end[0]) {
            result.append("U");  // Move up
            start[0]--;
        }

        generateMoves(start, end, result);  // Recur for the next move
    }

    public static void main(String[] args) {
        int[] pin = {5,4,7,9};  // Example pin
        String moves = findMoves(pin);
        System.out.println("Sequence of moves: " + moves);
    }
}
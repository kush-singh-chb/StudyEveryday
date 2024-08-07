package org.example.l75;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class LeetCode {

    int currentNum = 1;
    int currRow = 0;
    int currCol = 0;
    int[] up = new int[]{-1, 0};
    int[] down = new int[]{1, 0};
    int[] left = new int[]{0, -1};
    int[] right = new int[]{0, 1};
    int x = 0;
    int y = 0;

    int dfs(List<List<Integer>> al, boolean[] visited, int from) {
        int change = 0;
        visited[from] = true;
        for (var to : al.get(from)) {
            if (!visited[Math.abs(to)]) {
                change += dfs(al, visited, Math.abs(to)) + (to > 0 ? 1 : 0);
            }
        }
        return change;
    }

    public int minReorder(int n, int[][] connections) {
        List<List<Integer>> al = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            al.add(new ArrayList<>());
        }
        for (var c : connections) {
            al.get(c[0]).add(c[1]);
            al.get(c[1]).add(-c[0]);
        }
        return dfs(al, new boolean[n], 0);
    }

    public int bootManager(String S) {
        // Implement your solution here
        int cut = 0;
        int c = 0;
        // iterating over the string
        for (int i = 0; i < S.length(); i++) {
            // decreasing c, when we get "L"
            if (S.charAt(i) == 'L') c -= 1;
            // increasing c when we get "R"
            if (S.charAt(i) == 'R') c += 1;
            if (c == 0) cut += 1;
        }
        return cut;
    }

    public String autoArm(int[] pin) {


        List<List<Integer>> keypad = List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9));
        int[][] _keypad = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        StringBuilder result = new StringBuilder();
        searchPath2(pin, 0, _keypad, currentNum, result, new int[]{x, y});
        return result.toString();

    }

    private void searchPath(int[] pin, int idx, List<List<Integer>> keypad, int currentNum, StringBuilder result) {
        var command = new Stack<String>();
        if (idx > pin.length - 1) {
            return;
        }
        if (pin[idx] == currentNum) {
            result.append("P");
            return;
        }
        command.push("P");
        var nextRow = Math.abs(currRow - pin[idx]) / 3;
        var hopRow = nextRow + currRow;
        currRow = currRow + hopRow;
        if (hopRow > 0) {
            while (hopRow != 0) {
                command.push("D");
                hopRow--;
            }
        } else if (hopRow < 0) {
            while (hopRow != 0) {
                command.push("U");
                hopRow++;
            }
        }

        var nextCol = Math.abs(currentNum - pin[idx]) / 3;
        var hopCol = nextCol + currCol;
        currCol = currCol + hopCol;
        if (hopCol > 0) {
            for (int i = 0; i < Math.abs(hopCol); i++) {
                command.push("R");
            }
        } else if (hopCol < 0) {
            for (int i = 0; i < Math.abs(hopCol); i++) {
                command.push("L");
            }
        }

        while (!command.isEmpty()) {
            result.append(command.pop());
        }
        currentNum = pin[idx];
        searchPath(pin, idx + 1, keypad, currentNum, result);
    }

    private void searchPath2(int[] pin, int idx, int[][] keypad, int currentNum, StringBuilder result, int[] start) {
        if (idx > pin.length - 1) {
            return;
        }
        if (start[0] > 2 || start[0] < 0 || start[1] > 2 || start[1] < 0) {
            return;
        }
        if (pin[idx] == currentNum) {
            result.append("P");
            return;
        }
        var visited = new boolean[9];
        check(start, right, keypad, pin[idx], result, "R", visited);
        check(start, left, keypad, pin[idx], result, "L", visited);
        check(start, up, keypad, pin[idx], result, "U", visited);
        check(start, down, keypad, pin[idx], result, "D", visited);
        searchPath2(pin, idx + 1, keypad, pin[idx], result, new int[]{x, y});
    }

    private StringBuilder check(int[] start, int[] end, int[][] keypad, int num, StringBuilder result, String toAppend, boolean[] visited) {
        var res = new StringBuilder();
        int h = start[0] + end[0];
        int k = start[1] + end[1];
        if (h < 0 || h > 2 || k < 0 || k > 2) {
            result = new StringBuilder();
            return result;
        }
        var cur = keypad[h][k];
        if (visited[cur - 1]) {
            result = new StringBuilder();
            return result;
        } else {
            visited[cur - 1] = true;
        }
        if (cur == num) {
            x = h;
            y = k;
            result.append("P");
        } else {
            res.append(toAppend);
            check(new int[]{h, k}, right, keypad, num, res, "R", visited);
            check(new int[]{h, k}, left, keypad, num, res, "L", visited);
            check(new int[]{h, k}, up, keypad, num, res, "U", visited);
            check(new int[]{h, k}, down, keypad, num, res, "D", visited);
            if (!res.toString().isEmpty() && res.charAt(res.length() - 1) == 'P') {
                result.append(res);
                return result;
            }
        }
        return new StringBuilder();
    }

    private void appendDir(int[] dir, String res) {
        if (dir == up) {
            res += "U";
        }
        if (dir == down) {
            res += "D";
        }
        if (dir == left) {
            res += "L";
        }
        if (dir == right) {
            res += "R";
        }
    }

    public String moveArm(int[] pin) {
        int currentPosition = 1;
        StringBuilder movements = new StringBuilder();

        for (int digit : pin) {
            if (digit == 0) {
                digit = 10; // considering 0 as 10 for movement calculation
            }
            int destination = digit;

            while (currentPosition != destination) {
                if ((currentPosition - 1) / 3 > (destination - 1) / 3) {
                    movements.append('L');
                    currentPosition--;
                } else if ((currentPosition - 1) / 3 < (destination - 1) / 3) {
                    movements.append('R');
                    currentPosition++;
                }
                if (currentPosition > destination) {
                    movements.append('U');
                    currentPosition -= 3;
                } else if (currentPosition < destination) {
                    movements.append('D');
                    currentPosition += 3;
                }
            }
            movements.append('P');
        }
        return movements.toString();
    }
}

public class Runner {
    public static void main(String[] args) {
        LeetCode lc = new LeetCode();
//        System.out.println(lc.minReorder(6, new int[][]{{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}}));
        System.out.println(lc.moveArm(new int[]{5, 4, 7, 9}));
    }
}

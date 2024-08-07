package org.example.l75;


import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        productAndSum();
    }

    private static void productAndSum() {
        new ProductAndSum().subtractProductAndSum(234);
    }

    private static void countNBits() {
        out.println(new CountNBits().hammingWeight(1101));
    }

    private static void CatAndMouse() {
        int[][] graph = new int[6][];
        int[] n1 = new int[2];
        n1[0] = 2;
        n1[1] = 5;
        int[] n2 = new int[1];
        n2[0] = 3;
        int[] n3 = new int[3];
        n3[1] = 4;
        n3[2] = 5;
        int[] n4 = new int[3];
        n4[0] = 1;
        n4[1] = 4;
        n4[2] = 5;
        int[] n5 = new int[2];
        n5[0] = 2;
        n5[1] = 3;
        int[] n6 = new int[3];
        n6[1] = 2;
        n6[2] = 3;
        graph[0] = n1;
        graph[1] = n2;
        graph[2] = n3;
        graph[3] = n4;
        graph[4] = n5;
        graph[5] = n6;
        new CatAndMouse().catMouseGame(graph);
    }
}
package org.example.l75;

public class CanPlant {
        public static boolean canPlaceFlowers(int[] flowerbed, int n) {
            if (n == 0) {
                return true;
            }
            for (int i = 0; i < flowerbed.length; i++) {
                if (flowerbed[i] == 0 && (i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length-1 || flowerbed[i+1] == 0)) {
                    flowerbed[i] = 1;
                    n--;
                    if (n == 0) {
                        return true;
                    }
                }
            }
            return false;
        }

    public static void main(String[] args) {
        canPlaceFlowers(new int[]{1}, 0);
    }
    }

package org.example.l75;

public class CatAndMouse {
        public int catMouseGame(int[][] graph) {
            int mouse = 2;
            int cat = 1;
            int prevCat = -1;
            int prevMouse = -1;
            while((mouse != prevMouse || cat != prevCat)){
                mouse = graph[mouse][getRandomNumber(graph[mouse].length)];
                if(cat == mouse || mouse == 0){
                    break;
                }
                var temp = graph[cat][getRandomNumber(graph[cat].length)];
                if(temp != 0) {
                    cat = temp;
                }
                if(cat == mouse){
                    break;
                }
                prevCat = cat;
                prevMouse = mouse;
            }
            if(mouse == 0) {
                return 1;
            }
            if(mouse == prevMouse || prevCat == cat){
                return 0;
            }
            return 2;
        }

        private int getRandomNumber(int max) {
            return (int) ((Math.random() * (max)) + 0);
        }

        private boolean meetup(int cat, int mouse){
            return cat == mouse;
        }
}

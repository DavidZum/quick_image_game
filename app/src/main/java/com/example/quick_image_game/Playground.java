package com.example.quick_image_game;

public class Playground {
    private int[][] images;
    private int x;
    private int y;

    public int[][] getImages() {
        return images;
    }

    public void setImages(int[][] images) {
        this.images = images;
    }

    public Playground(int x, int y) {
        this.x = x;
        this.y = y;
        images = new int[x][y];
    }

    public void init() {
        images = new int[x][y];
        for (int i = 0; i < images.length; i++) {
            for (int j = 0; j < images[i].length; j++) {
                images[i][j] = -1;
            }
        }
        for(int i = 0; i < this.x * this.y; i++) {
            Position p = new Position(0,0);
            int x;
            int y;
            do {
                x = (int)(Math.random()*this.x);
                y = (int)(Math.random()*this.y);
            } while (images[x][y] != -1);
            images[x][y] = i;
        }
    }

    public int getImage(Position p) {
        return images[p.getX()][p.getY()];
    }


}

package com.mfitss.idletd.objects.Tiles;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.mfitss.idletd.objects.GameObject;

public class Field {

    public static final int WIDTH = 16;
    public static final int HEIGHT = 9;

    private Tile[][] field;

    public Field() {
        createEmptyField();
    }

    public Field(Tile[][] tiles) {
        setField(tiles);
    }

    public void createEmptyField() {
        field = new Tile[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                field[i][j] = new GrassTile(i, j);
            }
        }
    }

    public void setTile(int cx, int cy, Tile tile) {
        field[cx][cy] = tile;
    }

    public void setField(Tile[][] tiles) {
        field = tiles;
    }

    public void drawField(Batch batch) {
        for (Tile[] tiles : field) {
            for (Tile tile : tiles) {
                tile.draw(batch);
            }
        }
    }

    private void DrawObjects(Batch batch, GameObject[] objects) {
        for (GameObject object : objects) {
            object.draw(batch);
        }
    }
}

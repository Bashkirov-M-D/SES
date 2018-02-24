package com.mfitss.idletd.objects.Tiles;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.mfitss.idletd.objects.GameObject;

public class Map {

    public static final int WIDTH = 16;
    public static final int HEIGHT = 9;

    private Tile[][] map;

    public Map() {
        createEmptyMap();
    }

    public Map(Tile[][] tiles) {
        setMap(tiles);
    }

    public void createEmptyMap() {
        map = new Tile[WIDTH][HEIGHT];
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                map[i][j] = new GrassTile(new Rectangle(i, j, 1, 1));
            }
        }
    }

    public void setTile(int cx, int cy, Tile tile) {
        map[cx][cy] = tile;
    }

    public void setMap(Tile[][] tiles) {
        map = tiles;
    }

    public void drawMap(Batch batch) {
        for (Tile[] tiles : map) {
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

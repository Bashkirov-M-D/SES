package com.mfitss.idletd.model;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.mfitss.idletd.model.Tiles.Tile;

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

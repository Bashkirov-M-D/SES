package com.mfitss.idletd.model;


import com.mfitss.idletd.model.Tiles.Tile;

public class Map {

    private static final int SIZE_X = 16;
    private static final int SIZE_Y = 9;

    Tile[][] map;

    public Map() {
        map = new Tile[16][9];
        for (Tile[] tiles : map) {
            for (Tile tile : tiles) {
                //Tile = new Tile();
            }
        }
    }
}

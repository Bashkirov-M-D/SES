package com.mfitss.idletd.model.Tiles;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class RoadTile extends Tile {
    public RoadTile(Rectangle bounds, Sprite sprite) {
        super(bounds, sprite);
        setOccupied(true);
    }
}

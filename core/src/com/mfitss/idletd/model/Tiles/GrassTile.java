package com.mfitss.idletd.model.Tiles;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class GrassTile extends Tile {
    public GrassTile(Rectangle bounds, Sprite sprite) {
        super(bounds, sprite);
        setOccupied(false);
    }
}

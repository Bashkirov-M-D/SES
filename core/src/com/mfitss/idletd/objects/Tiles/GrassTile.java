package com.mfitss.idletd.objects.Tiles;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class GrassTile extends Tile {
    public GrassTile(Rectangle bounds) {
        super(bounds);
        Sprite sprite = new Sprite(/*set sprite*/);
        setSprite(sprite);
        setOccupied(false);
    }
}

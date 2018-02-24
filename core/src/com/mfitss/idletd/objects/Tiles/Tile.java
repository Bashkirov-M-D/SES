package com.mfitss.idletd.objects.Tiles;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mfitss.idletd.objects.GameObject;

public class Tile extends GameObject {

    private boolean occupied;

    public Tile(Rectangle bounds) {
        super(bounds);
    }

    public void setTile(Rectangle bounds, Sprite sprite) {
        super.setObject(bounds, sprite);
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}

package com.mfitss.idletd.model.Tiles;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mfitss.idletd.model.GameObject;

public class Tile extends GameObject {

    private boolean occupied;

    public Tile(Rectangle bounds, Sprite sprite) {
        super(bounds, sprite);
    }

    public Tile() {
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

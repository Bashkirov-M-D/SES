package com.mfitss.idletd.objects.Tiles;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mfitss.idletd.main.GameScreen;
import com.mfitss.idletd.objects.GameObject;

public class Tile extends GameObject {

    private boolean occupied;

    public Tile(float cX, float cY) {
        super(new Rectangle(cX, GameScreen.HEIGHT_DECREASE * cY, 1, GameScreen.HEIGHT_DECREASE));
    }

    public void setTile(float cX, float cY, Sprite sprite) {
        super.setObject(new Rectangle(cX, GameScreen.HEIGHT_DECREASE * cY, 1, GameScreen.HEIGHT_DECREASE), sprite);
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}

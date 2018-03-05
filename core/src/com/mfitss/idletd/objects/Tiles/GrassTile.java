package com.mfitss.idletd.objects.Tiles;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class GrassTile extends Tile {

    private static Sprite sprite = new Sprite(new Texture("01.jpg"));

    public GrassTile(float cX, float cY) {
        super(cX, cY);
        setSprite(sprite);
        setOccupied(false);
    }
}

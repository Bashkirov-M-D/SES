package com.mfitss.idletd.objects.Tiles;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class RoadTile extends Tile {

    private Direction direction;

    public RoadTile(Rectangle bounds) {
        super(bounds);
        Sprite sprite = new Sprite(/*set sprite*/);
        setSprite(sprite);
        setOccupied(true);
    }

    enum Direction {LEFT, TOP, RIGHT, BOTTOM}
}

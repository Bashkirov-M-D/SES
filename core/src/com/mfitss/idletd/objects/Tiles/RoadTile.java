package com.mfitss.idletd.objects.Tiles;


import com.badlogic.gdx.graphics.g2d.Sprite;

public class RoadTile extends Tile {

    private Direction direction;

    public RoadTile(float cX, float cY) {
        super(cX, cY);
        Sprite sprite = new Sprite(/*set sprite*/);
        setSprite(sprite);
        setOccupied(true);
    }

    enum Direction {LEFT, TOP, RIGHT, BOTTOM}
}

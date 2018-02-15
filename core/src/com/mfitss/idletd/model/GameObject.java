package com.mfitss.idletd.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Михаил on 15.02.2018.
 */

public class GameObject {
    protected Rectangle bounds;
    protected Sprite sprite;

    public GameObject(Rectangle bounds, Sprite sprite) {
        this.bounds = bounds;
        this.sprite = sprite;
    }

    void draw(Batch batch) {
        sprite.draw(batch);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}

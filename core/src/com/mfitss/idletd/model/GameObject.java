package com.mfitss.idletd.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class GameObject {
    private Rectangle bounds;
    private Sprite sprite;

    public GameObject() {
    }

    public GameObject(Rectangle bounds, Sprite sprite) {
        this.bounds = bounds;
        this.sprite = sprite;
    }

    public void draw(Batch batch) {
        sprite.draw(batch);
    }

    public void setObject(Rectangle bounds, Sprite sprite) {
        this.bounds = bounds;
        this.sprite = sprite;
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

package com.mfitss.idletd.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public abstract class GameObject {
    protected Sprite sprite;

    protected Rectangle bounds;

    public GameObject() {
    }

    public void setBounds(float pX, float pY, float width, float height) {
        bounds = new Rectangle(pX, pY, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void draw(SpriteBatch batch) {
        sprite.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        sprite.draw(batch);
    }
}

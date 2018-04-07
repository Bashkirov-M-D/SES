package com.mfitss.idletd.Model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public abstract class GameObject {
    protected Sprite object;

    protected Rectangle bounds;

    public GameObject(TextureRegion region, float pX, float pY, float width, float height) {
        bounds = new Rectangle(pX, pY, width, height);
        object = new Sprite(region);
    }

    public void draw(SpriteBatch batch) {
        object.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        object.draw(batch);
    }
}

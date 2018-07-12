package com.mfitss.idletd.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mfitss.idletd.controllers.BuildingManager;
import com.mfitss.idletd.objects.Planets.Planet;


public abstract class GameObject {
    protected Sprite sprite;

    protected Rectangle bounds;

    protected int maxHealth;

    protected int health;

    protected GameObject() {
        maxHealth = 1;
        health = maxHealth;
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
        sprite.setOrigin(bounds.width / 2, bounds.height / 2);
        sprite.draw(batch);
    }

    public void decreaseHealth() {
        health--;
        if (health < 1)
            destroy();
    }

    public abstract void work(float delta);

    protected void destroy() {
        if (this instanceof Planet && ((Planet) this).getMine() != null)
            BuildingManager.destroyObject(((Planet) this).getMine());
        BuildingManager.destroyObject(this);
    }

    public boolean destroyed() {
        return health < 1;
    }
}

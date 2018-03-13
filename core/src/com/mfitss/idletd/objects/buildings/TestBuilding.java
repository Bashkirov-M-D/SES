package com.mfitss.idletd.objects.buildings;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.main.GameScreen;

public class TestBuilding extends Building {
    public TestBuilding(float pX, float pY) {
        super(pX, pY, 64, 64);
        setSprite(new Sprite(new Texture("03.png")));
    }

    @Override
    public void work(float time) {
        bounds.setX(bounds.getX() + 200 * time);
        bounds.setY(bounds.getY() + 50 * time);
        if (bounds.getX() > GameScreen.WIDTH / 2 - 64)
            bounds.setX(-GameScreen.WIDTH / 2);
        if (bounds.getY() > GameScreen.HEIGHT / 2 - 64)
            bounds.setY(-GameScreen.HEIGHT / 2);
    }
}

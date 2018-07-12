package com.mfitss.idletd.objects.buildings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.controllers.BuildingManager;
import com.mfitss.idletd.main.GameScreen;

public class Core extends Building {
    public Core() {
        setBounds(-150, -150, 300, 300);
        BuildingManager.addObject(this);
        setSprite(new Sprite(new Texture(Gdx.files.internal("core.png"))));

    }

    @Override
    public void work(float delta) {
    }

    @Override
    public void destroy() {
        BuildingManager.destroyObject(this);
        GameScreen.setGameOver(true);
    }
}

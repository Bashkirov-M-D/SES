package com.mfitss.idletd.objects.Planets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mfitss.idletd.objects.GameObject;
import com.mfitss.idletd.objects.buildings.Building;

public class Planet extends GameObject {

    private Building building;

    public Planet(int x, int y) {
        setBounds(x, y, 50, 50);
        setSprite(new Sprite(new Texture(Gdx.files.internal("planet.svg"))));
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void destroyBuilding() {
        this.building = null;
    }

    public boolean isOccupied() {
        return building != null;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        //building.setBounds();
        //building.draw(batch);
    }
}

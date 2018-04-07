package com.mfitss.idletd.objects.Planets;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mfitss.idletd.objects.GameObject;
import com.mfitss.idletd.objects.buildings.Building;

public class Planet extends GameObject {

    private Building building;

    public Planet(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        setSprite(new Sprite(new Texture(Gdx.files.internal("")))); // TODO: 07.04.2018 add sprite
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

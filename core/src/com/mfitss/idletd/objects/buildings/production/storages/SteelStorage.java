package com.mfitss.idletd.objects.buildings.production.storages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.objects.buildings.BuildPrice;

public class SteelStorage extends Storage {

    private static BuildPrice buildPrice = new BuildPrice(5, 25, 250, 2);

    public SteelStorage(){
        resource = com.mfitss.idletd.objects.buildings.Building.metal;
        price = buildPrice.clone();
        resourceIncrease = 50;
        resourceName = "Steel";
        buildingName = "Steel storage";
    }

    @Override
    public boolean build(int x, int y, int width, int height) {
        if(super.build(x, y, width, height)) {
            sprite = new Sprite(new Texture(Gdx.files.internal("steel.png")));
            return true;
        }
        return false;
    }
}

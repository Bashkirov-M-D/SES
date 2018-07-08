package com.mfitss.idletd.objects.buildings.production.storages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.objects.buildings.BuildPrice;

public class IronStorage extends Storage{

    private static BuildPrice buildPrice = new BuildPrice(5, 15, 50, 0);

    public IronStorage() {
        resource = com.mfitss.idletd.objects.buildings.Building.iron;
        price = buildPrice.clone();
        resourceIncrease = 100;
        resourceName = "Iron";
        buildingName = "Iron storage";
    }

    @Override
    public boolean build(int x, int y, int width, int height) {
        if(super.build(x, y, width, height)) {
            sprite = new Sprite(new Texture(Gdx.files.internal("iron.png")));
            return true;
        }
        return false;
    }
}

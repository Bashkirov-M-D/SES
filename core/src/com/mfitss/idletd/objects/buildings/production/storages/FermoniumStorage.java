package com.mfitss.idletd.objects.buildings.production.storages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.objects.buildings.BuildPrice;

public class FermoniumStorage extends Storage {
    private static BuildPrice buildPrice = new BuildPrice(5, 15, 50, 5);

    public FermoniumStorage() {
        resource = com.mfitss.idletd.objects.buildings.Building.fermonium;
        price = buildPrice.clone();
        resourceIncrease = 10;
        resourceName = "Fermonium";
        buildingName = "Fermonium storage";
    }

    @Override
    public boolean build(int x, int y, int width, int height) {
        if(super.build(x, y, width, height)) {
            sprite = new Sprite(new Texture(Gdx.files.internal("fermonium.png")));
            return true;
        }
        return false;
    }
}

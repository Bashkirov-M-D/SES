package com.mfitss.idletd.objects.buildings.production.storages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.objects.buildings.BuildPrice;
import com.mfitss.idletd.objects.buildings.Building;

public class CoinStorage extends Storage {
    private static BuildPrice buildPrice = new BuildPrice(10, 20, 300, 2);

    public CoinStorage() {
        resource = Building.fermonium;
        price = buildPrice.clone();
        resourceIncrease = 1000;
        resourceName = "Coins";
        buildingName = "Coins storage";
    }

    @Override
    public boolean build(int x, int y, int width, int height) {
        if (super.build(x, y, width, height)) {
            sprite = new Sprite(new Texture(Gdx.files.internal("coins.png")));
            return true;
        }
        return false;
    }
}

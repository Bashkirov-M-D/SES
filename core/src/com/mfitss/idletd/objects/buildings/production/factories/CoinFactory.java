package com.mfitss.idletd.objects.buildings.production.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.objects.buildings.BuildPrice;
import com.mfitss.idletd.objects.buildings.Building;

public class CoinFactory extends GameFactory {

    private static BuildPrice buildPrice = new BuildPrice(10, 15, 50, 0);

    public CoinFactory() {
        speed = 1;
        efficiency = 0.2f;
        price = buildPrice.clone();
        resourceName = "Coin";
        buildingName = "Coin factory";
    }

    @Override
    public boolean build(int x, int y, int width, int height) {
        if (super.build(x, y, width, height)) {
            setBounds(x, y, width, height);
            sprite = new Sprite(new Texture("factory.png"));
            return true;
        }
        return false;
    }

    @Override
    public void work(float delta) {
        if (iron.decrease(speed * delta * efficiency))
            coins.add(speed * delta);
    }
}

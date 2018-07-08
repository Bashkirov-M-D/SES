package com.mfitss.idletd.objects.buildings.production.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.objects.buildings.BuildPrice;

public class MetalRefinary extends GameFactory {

    private static BuildPrice buildPrice = new BuildPrice(10, 25, 100, 0);

    public MetalRefinary() {
        speed = 0.2f;
        efficiency = 5;
        price = buildPrice.clone();
        resourceName = "Steel";
        buildingName = "Metal refinery";
    }

    @Override
    public boolean build(int x, int y, int width, int height) {
        if(super.build(x, y, width, height)) {
            setBounds(x, y, width, height);
            sprite = new Sprite(new Texture("ref.png"));
            return true;
        }
        return false;
    }

    @Override
    public void work(float delta) {
        if (iron.decrease(speed * delta * efficiency))
            metal.add(speed * delta);
    }
}

package com.mfitss.idletd.objects.buildings.production.storages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.objects.buildings.BuildPrice;
import com.mfitss.idletd.resources.ResourcePower;

public class SolarPanel extends Storage {

    private static BuildPrice buildPrice = new BuildPrice(0, 20, 100, 0);

    public SolarPanel(){
        price = buildPrice.clone();
        resource = power;
        resourceIncrease = 15;
        resourceName = "Power";
        buildingName = "Solar panel";
    }

    @Override
    public boolean build(int x, int y, int width, int height) {
        if(super.build(x, y, width, height)) {
            sprite = new Sprite(new Texture(Gdx.files.internal("power.png")));
            return true;
        }
        return false;
    }

    @Override
    public boolean upgrade() {
        if(super.upgrade()){
            ResourcePower.getResource().add(resourceIncrease/2);
            return true;
        }
        return false;
    }

    @Override
    protected void onBuild() {
        super.onBuild();
        power.add(resourceIncrease);
    }
}

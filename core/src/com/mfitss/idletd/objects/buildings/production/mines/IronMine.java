package com.mfitss.idletd.objects.buildings.production.mines;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.objects.buildings.BuildPrice;
import com.mfitss.idletd.objects.Planets.Planet;
import com.mfitss.idletd.resources.ResourceIron;

public class IronMine extends Mine {

    private static BuildPrice buildPrice = new BuildPrice(5, 10, 75, 0);

    public IronMine() {
        price = buildPrice.clone();
        resourceName = "Iron";
        workSpeed = 1;
        buildingName = "Iron mine";
    }

    @Override
    public boolean build(Planet planet) {
        if(super.build(planet)) {
            setSprite(new Sprite(new Texture(Gdx.files.internal("mine.png"))));
            return true;
        }
        return false;
    }

    @Override
    public void setBounds(float pX, float pY, float width, float height) {
        super.setBounds(pX, pY, width, height);
    }

    @Override
    public void work(float delta) {
        ResourceIron.getResource().add(workSpeed*delta);
    }
}

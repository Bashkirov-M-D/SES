package com.mfitss.idletd.objects.buildings.production.mines;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.objects.buildings.BuildPrice;
import com.mfitss.idletd.resources.ResourceFermonium;

public class FermoniumMine extends Mine {
    private static BuildPrice buildPrice = new BuildPrice(20, 40, 400, 0);

    public FermoniumMine() {
        price = buildPrice.clone();
        resourceName = "Fermonium";
        workSpeed = 0.05f;
        buildingName = "Fermonium mine";
    }

    @Override
    public boolean build(com.mfitss.idletd.objects.Planets.Planet planet) {
        if (super.build(planet)) {
            setSprite(new Sprite(new Texture(Gdx.files.internal("ferMine.png"))));
            return true;
        }
        return false;
    }

    @Override
    public void work(float delta) {
        ResourceFermonium.getResource().add(workSpeed * delta);
    }
}

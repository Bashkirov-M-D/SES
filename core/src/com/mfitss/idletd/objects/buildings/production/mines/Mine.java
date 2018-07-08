package com.mfitss.idletd.objects.buildings.production.mines;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mfitss.idletd.objects.Planets.Planet;
import com.mfitss.idletd.objects.buildings.production.ProductionBuilding;

public abstract class Mine extends ProductionBuilding {

    protected float workSpeed;

    public boolean build(Planet planet){
        planet.setMine(this);
        return super.build();
    }

    @Override
    public boolean upgrade() {
        if(super.upgrade()){
            workSpeed*=2;
            return true;
        }
        return false;
    }

    public float getWorkSpeed() {
        return workSpeed;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}

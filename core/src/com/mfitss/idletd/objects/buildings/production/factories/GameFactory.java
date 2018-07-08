package com.mfitss.idletd.objects.buildings.production.factories;


import com.mfitss.idletd.controllers.GameGestureListener;
import com.mfitss.idletd.objects.buildings.production.ProductionBuilding;

public abstract class GameFactory extends ProductionBuilding {

    protected float speed;
    protected float efficiency;

    @Override
    public boolean build(int x, int y, int width, int height) {
        GameGestureListener.addClickableObject(this);
        return super.build(x, y, width, height);
    }

    @Override
    public boolean upgrade() {
        if(super.upgrade()){
            speed*=2;
            efficiency*=2;
            return true;
        }
        return false;
    }

    public float getSpeed() {
        return speed;
    }

    public float getEfficiency() {
        return efficiency;
    }
}

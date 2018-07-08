package com.mfitss.idletd.objects.buildings.production.storages;


import com.mfitss.idletd.controllers.GameGestureListener;
import com.mfitss.idletd.resources.GameResource;
import com.mfitss.idletd.objects.buildings.production.ProductionBuilding;

public abstract class Storage extends ProductionBuilding {

    protected GameResource resource;
    protected int resourceIncrease;

    @Override
    public boolean build(int x, int y, int width, int height) {
        if(super.build(x, y, width, height)) {
            onBuild();
            GameGestureListener.addClickableObject(this);
            return true;
        }
        return false;
    }

    @Override
    public boolean upgrade() {
        if(super.upgrade()){
            resource.addMax(resourceIncrease);
            resourceIncrease*=2;
            return true;
        }
        return false;
    }

    @Override
    public void work(float delta) {}

    @Override
    public void destroy() {
        onDestroy();
    }

    protected void onBuild(){
        resource.addMax(resourceIncrease);
    }

    protected void onDestroy(){
        resource.decreaseMax(resourceIncrease);
    }

    public int getResourceIncrease() {
        return resourceIncrease;
    }
}

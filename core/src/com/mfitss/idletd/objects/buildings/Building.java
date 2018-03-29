package com.mfitss.idletd.objects.buildings;

import com.mfitss.idletd.objects.GameObject;
import com.mfitss.idletd.resources.ResourceMetal;
import com.mfitss.idletd.resources.ResourcePower;

public abstract class Building extends GameObject {
    private BuildPrice price;
    protected static ResourceMetal metal = ResourceMetal.getResource();
    protected static ResourcePower power = ResourcePower.getResource();

    public void build() {
        metal.decrease(price.getMetalPrice());
        power.decrease(price.getPowerPrice());
    }

    public boolean canBuild() {
        return metal.getAmount() > price.getMetalPrice() &&
                power.getAmount() > price.getPowerPrice();
    }

    public abstract void destroy();
}

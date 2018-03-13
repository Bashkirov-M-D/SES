package com.mfitss.idletd.objects.buildings;

import com.mfitss.idletd.objects.GameObject;

public abstract class Building extends GameObject {
    public Building(float pX, float pY, float width, float height) {
        super(pX, pY, width, height);
    }

    public abstract void work(float time);
}

package com.mfitss.idletd.objects.buildings;


public class BuildPrice {
    private int powerPrice;
    private int metalPrice;

    public BuildPrice() {
    }

    public BuildPrice(int power, int metal) {
        powerPrice = power;
        metalPrice = metal;
    }

    public int getPowerPrice() {
        return powerPrice;
    }

    public int getMetalPrice() {
        return metalPrice;
    }


}

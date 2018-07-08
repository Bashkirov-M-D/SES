package com.mfitss.idletd.objects.buildings;


import com.badlogic.gdx.Gdx;

public class BuildPrice implements Cloneable {
    private float powerPrice;
    private float metalPrice;
    private float coinPrice;
    private float fermoniumPrice;

    public BuildPrice() {
    }

    public BuildPrice(float power, float metal, float coin, float fermonium) {
        setPrice(power, metal, coin, fermonium);
    }

    public void setPrice(float power, float metal, float coin, float fermonium) {
        powerPrice = power;
        metalPrice = metal;
        coinPrice = coin;
        fermoniumPrice = fermonium;
    }

    public int getPowerPrice() {
        return (int) powerPrice;
    }

    public int getMetalPrice() {
        return (int) metalPrice;
    }

    public int getCoinPrice() {
        return (int) coinPrice;
    }

    public int getFermoniumPrice() {
        return (int) fermoniumPrice;
    }

    @Override
    public BuildPrice clone() {
        try {
            return (BuildPrice)super.clone();
        } catch (CloneNotSupportedException e){
            Gdx.app.log("clone", e.getMessage());
        }
        return null;
    }
}

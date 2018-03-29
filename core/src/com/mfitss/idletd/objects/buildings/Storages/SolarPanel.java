package com.mfitss.idletd.objects.buildings.Storages;

public class SolarPanel extends Storage {

    private int powerIncrease = 10;

    @Override
    protected void onBuild() {
        power.addMax(powerIncrease);
    }

    @Override
    protected void onDestroy() {
        power.decreaseMax(powerIncrease);
    }
}

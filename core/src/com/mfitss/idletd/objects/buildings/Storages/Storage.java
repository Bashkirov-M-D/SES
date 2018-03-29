package com.mfitss.idletd.objects.buildings.Storages;


import com.mfitss.idletd.objects.buildings.Building;

public abstract class Storage extends Building {
    @Override
    public void build() {
        super.build();
        onBuild();
    }

    @Override
    public void destroy() {
        onDestroy();
    }

    protected abstract void onBuild();

    protected abstract void onDestroy();
}

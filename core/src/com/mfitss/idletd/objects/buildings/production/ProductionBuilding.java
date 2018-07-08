package com.mfitss.idletd.objects.buildings.production;

import com.mfitss.idletd.objects.buildings.Building;

public abstract class ProductionBuilding extends Building {
    protected String resourceName;

    public String getResourceName() {
        return resourceName;
    }
}

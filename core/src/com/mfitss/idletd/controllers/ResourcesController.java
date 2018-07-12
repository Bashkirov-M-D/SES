package com.mfitss.idletd.controllers;

import com.mfitss.idletd.resources.GameResource;
import com.mfitss.idletd.resources.ResourceFermonium;
import com.mfitss.idletd.resources.ResourceIron;
import com.mfitss.idletd.resources.ResourceMoney;
import com.mfitss.idletd.resources.ResourcePower;
import com.mfitss.idletd.resources.ResourceSteel;

public class ResourcesController {
    private static GameResource[] resources = new GameResource[]{
            ResourceIron.getResource(),
            ResourcePower.getResource(),
            ResourceSteel.getResource(),
            ResourceMoney.getResource(),
            ResourceFermonium.getResource()
    };

    private static int[][] initialValues = new int[][]{
            {0, 100},
            {50, 50},
            {50, 50},
            {500, 1000},
            {0, 10}
    };

    public static void create() {
        for (int i = 0; i < resources.length; i++) {
            resources[i].setAmount(initialValues[i][0]);
            resources[i].setMaxAmount(initialValues[i][1]);
        }
    }

    public static void updateResources() {
        for (GameResource resource : resources) {
            resource.update();
        }
    }
}

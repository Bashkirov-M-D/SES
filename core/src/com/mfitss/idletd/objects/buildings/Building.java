package com.mfitss.idletd.objects.buildings;

import com.mfitss.idletd.objects.ClickableObject;
import com.mfitss.idletd.resources.ResourceFermonium;
import com.mfitss.idletd.resources.ResourceIron;
import com.mfitss.idletd.resources.ResourceMoney;
import com.mfitss.idletd.resources.ResourcePower;
import com.mfitss.idletd.resources.ResourceSteel;

public abstract class Building extends ClickableObject {
    protected BuildPrice price;
    protected static ResourceSteel metal = ResourceSteel.getResource();
    protected static ResourcePower power = ResourcePower.getResource();
    protected static ResourceIron iron = ResourceIron.getResource();
    protected static ResourceMoney coins = ResourceMoney.getResource();
    protected static ResourceFermonium fermonium = ResourceFermonium.getResource();
    protected String buildingName;
    protected float upgradeMultipler = 1.15f;

    public boolean build(int x, int y, int width, int height) {
        setBounds(x, y, width, height);
        create();
        return build();
    }

    protected boolean build() {
        if (metal.decrease(price.getMetalPrice()) && power.decrease(price.getPowerPrice()) &&
                coins.decrease(price.getCoinPrice()) && fermonium.decrease(price.getFermoniumPrice())) {
            price.setPrice(upgradeMultipler * price.getPowerPrice(), upgradeMultipler * price.getMetalPrice(),
                    upgradeMultipler * price.getCoinPrice(), upgradeMultipler * price.getFermoniumPrice());
            return true;
        }
        return false;
    }

    public boolean upgrade() {
        return build();
    }

    public boolean canBuild() {
        return metal.getAmount() >= price.getMetalPrice() &&
                power.getAmount() >= price.getPowerPrice() &&
                coins.getAmount() >= price.getCoinPrice() &&
                fermonium.getAmount() >= price.getFermoniumPrice();
    }

    public void destroy() {
        super.destroy();
        power.add(price.getPowerPrice()/upgradeMultipler);
    }

    public BuildPrice getPrice() {
        return price;
    }

    public String getBuildingName() {
        return buildingName;
    }
}

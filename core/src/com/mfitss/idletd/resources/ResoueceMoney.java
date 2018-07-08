package com.mfitss.idletd.resources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ResoueceMoney extends GameResource {
    public static ResoueceMoney resource;

    private ResoueceMoney() {
        name = "Coins";
        icon = new Sprite(new Texture("iron.png"));
        maxAmount = 1000;
        amount = 500;
    }

    public static ResoueceMoney getResource() {
        if (resource == null)
            resource = new ResoueceMoney();
        return resource;
    }
}

package com.mfitss.idletd.resources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ResourceMoney extends GameResource {
    public static ResourceMoney resource;

    private ResourceMoney() {
        name = "Coins";
        icon = new Sprite(new Texture("iron.png"));
    }

    public static ResourceMoney getResource() {
        if (resource == null)
            resource = new ResourceMoney();
        return resource;
    }
}

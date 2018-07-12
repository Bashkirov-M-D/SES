package com.mfitss.idletd.resources;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ResourcePower extends GameResource {
    private static ResourcePower resource;

    private ResourcePower() {
        name = "Power";
        icon = new Sprite(new Texture("power.png"));
    }

    public static ResourcePower getResource() {
        if(resource==null)
            resource = new ResourcePower();
        return resource;
    }
}

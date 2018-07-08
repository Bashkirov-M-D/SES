package com.mfitss.idletd.resources;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ResourceIron extends GameResource {
    private static ResourceIron resource;

    private ResourceIron() {
        name = "Iron";
        icon = new Sprite(new Texture("iron.png"));
        maxAmount = 100;
    }

    public static ResourceIron getResource() {
        if(resource==null)
            resource = new ResourceIron();
        return resource;
    }
}

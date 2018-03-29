package com.mfitss.idletd.resources;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ResourceIron extends GameResource {
    private static ResourceIron resource;

    private ResourceIron() {
        resource = new ResourceIron();
        name = "Iron";
        icon = new Sprite(new Texture("")); // TODO: 29.03.2018 add icon
    }

    public static ResourceIron getResource() {
        return resource;
    }
}

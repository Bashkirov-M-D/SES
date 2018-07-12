package com.mfitss.idletd.resources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ResourceSteel extends GameResource {

    private static ResourceSteel resource;

    private ResourceSteel() {
        name = "Steel";
        icon = new Sprite(new Texture("iron.png"));
    }

    public static ResourceSteel getResource() {
        if(resource == null)
            resource = new ResourceSteel();
        return resource;
    }
}

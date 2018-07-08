package com.mfitss.idletd.resources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ResourceFermonium extends GameResource {
    private static ResourceFermonium resource;

    private ResourceFermonium() {
        name = "Fermonium";
        icon = new Sprite(new Texture("iron.png"));
        maxAmount = 10;
    }

    public static ResourceFermonium getResource() {
        if (resource == null)
            resource = new ResourceFermonium();
        return resource;
    }
}

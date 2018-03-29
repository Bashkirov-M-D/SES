package com.mfitss.idletd.resources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ResourceMetal extends GameResource {

    private static ResourceMetal resource;

    private ResourceMetal() {
        resource = new ResourceMetal();
        name = "Metal";
        icon = new Sprite(new Texture("")); // TODO: 29.03.2018 add icon
    }

    public static ResourceMetal getResource() {
        return resource;
    }
}

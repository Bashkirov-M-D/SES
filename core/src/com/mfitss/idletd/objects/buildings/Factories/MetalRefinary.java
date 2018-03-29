package com.mfitss.idletd.objects.buildings.Factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.resources.ResourceIron;
import com.mfitss.idletd.resources.ResourceMetal;

public class MetalRefinary extends GameFactory {

    private int recyclingSpeed;
    private float recyclingModifier = 1 / 2;
    private ResourceIron iron;

    public MetalRefinary(int x, int y, int width, int height) {
        sprite = new Sprite(new Texture("")); // TODO: 29.03.2018 add sprite
        setBounds(x, y, width, height);
        iron = ResourceIron.getResource();
        metal = ResourceMetal.getResource();
    }


    @Override
    public void work(float delta) {
        if (iron.decrease(recyclingSpeed * delta))
            metal.add(recyclingSpeed * delta * recyclingModifier);
    }

    @Override
    public void destroy() {
    }
}

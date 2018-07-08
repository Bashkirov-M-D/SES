package com.mfitss.idletd.objects.Planets;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mfitss.idletd.objects.ClickableObject;
import com.mfitss.idletd.objects.buildings.production.mines.Mine;

public class Planet extends ClickableObject {

    private Mine mine;
    public static final int PLANET_TEXTURE_NUMBER = 5;

    private int rotationSpeed;

    public Planet(int x, int y, AssetManager manager) {
        setBounds(x, y, 100, 100);
        sprite = new Sprite(manager.get(choosePicture(), Texture.class));
        rotationSpeed = (int) (Math.random() * 21) - 10;
        create();
    }

    public void setMine(Mine mine) {
        this.mine = mine;
        mine.setBounds(bounds.x + 15, bounds.y + 110, bounds.width - 30, bounds.height - 30);
    }

    public Mine getMine() {
        return mine;
    }

    public void destroyMine() {
        this.mine = null;
    }

    public boolean isOccupied() {
        return mine != null;
    }

    private void rotate(float delta) {
        sprite.rotate(delta * rotationSpeed);
    }

    private String choosePicture() {
        return new StringBuilder()
                .append("planets/planet")
                .append((int) (Math.random() * PLANET_TEXTURE_NUMBER + 1))
                .append(".png")
                .toString();
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (mine != null)
            mine.draw(batch);
    }

    @Override
    public void work(float delta) {
        rotate(delta);
    }
}

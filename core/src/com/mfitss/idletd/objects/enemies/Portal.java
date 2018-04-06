package com.mfitss.idletd.objects.enemies;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.objects.GameObject;

public class Portal extends GameObject {
    private float delay = 3;

    private float timeFromLastSpawn = 0;

    private Enemy enemy;

    private int spawnAmount;

    public Portal(int x, int y, int width, int height, Enemy enemy) {
        setBounds(x, y, width, height);
        setSprite(new Sprite(new Texture(Gdx.files.internal("")))); // TODO: 06.04.2018 add sprite
        setEnemy(enemy);
    }

    public boolean spawnEnemy(float delta) {
        timeFromLastSpawn += delta;
        if (timeFromLastSpawn >= delay && spawnAmount > 0) {
            timeFromLastSpawn -= delay;
            enemy.spawn();
            spawnAmount--;
            return true;
        }
        return false;
    }

    public void setDelay(float delay) {
        this.delay = delay;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}

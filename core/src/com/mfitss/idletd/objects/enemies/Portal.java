package com.mfitss.idletd.objects.enemies;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.controllers.WaveManager;
import com.mfitss.idletd.objects.GameObject;

public class Portal extends GameObject {
    private float delay = 1;

    private int rotationSpeed = 30;

    private float timeFromLastSpawn = 0;

    private Class<Enemy> enemyType;

    private int spawnAmount;

    public Portal(float x, float y, Class<Enemy> enemy) {
        setBounds(x, y, 200, 200);
        setSprite(new Sprite(new Texture(Gdx.files.internal("portal.png"))));
        setEnemyType(enemy);
    }

    public void work(float delta){
        spawnEnemy(delta);
        rotate(delta);
    }

    private void rotate(float delta){
        sprite.rotate(delta * rotationSpeed);
    }

    private void spawnEnemy(float delta) {
        timeFromLastSpawn += delta;
        if (timeFromLastSpawn >= delay && spawnAmount > 0) {
            timeFromLastSpawn -= delay;
            try {
                Enemy enemy = enemyType.newInstance();
                WaveManager.addEnemy(enemy);
            } catch (Exception e){
                e.printStackTrace();
            }
            spawnAmount--;
            checkSpawnAmount();
        }
    }

    private void checkSpawnAmount() {
        if (spawnAmount < 1)
            close();
    }

    public void close() {
    }

    public void updateDelay() {
        delay = Math.min(1, (float)spawnAmount/10);
    }

    public void setEnemyType(Class<Enemy> enemyType) {
        this.enemyType = enemyType;
    }

    public void setSpawnAmount(int spawnAmount) {
        this.spawnAmount = spawnAmount;
    }
}

package com.mfitss.idletd.objects.enemies;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mfitss.idletd.controllers.WaveManager;
import com.mfitss.idletd.objects.GameObject;

public class Portal extends GameObject {
    private float delay = 3;
    private int rotationSpeed = 30;
    private float timeFromLastSpawn = 0;
    private Class<Enemy> enemyType;
    private int spawnAmount;
    private AssetManager assetManager;

    public Portal(float x, float y, Class<Enemy> enemy, AssetManager manager) {
        assetManager = manager;
        setBounds(x, y, 200, 200);
        setSprite(new Sprite(assetManager.get("portal.png", Texture.class)));
        setEnemyType(enemy);
    }

    public void work(float delta) {
        spawnEnemy(delta);
        rotate(delta);
    }

    private void rotate(float delta) {
        sprite.rotate(delta * rotationSpeed);
    }

    private void spawnEnemy(float delta) {
        timeFromLastSpawn += delta;
        if (timeFromLastSpawn >= delay && spawnAmount > 0) {
            timeFromLastSpawn = 0;
            try {
                Enemy enemy = enemyType.newInstance();
                enemy.setBounds(bounds.x + 50, bounds.y + 50);
                enemy.setManager(assetManager);
                WaveManager.addEnemy(enemy);
            } catch (Exception e) {
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
        delay = Math.min(3, 30f / spawnAmount);
    }

    public void setEnemyType(Class<Enemy> enemyType) {
        this.enemyType = enemyType;
    }

    public void setSpawnAmount(int spawnAmount) {
        this.spawnAmount = spawnAmount;
    }
}

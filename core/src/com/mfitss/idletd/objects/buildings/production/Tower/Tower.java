package com.mfitss.idletd.objects.buildings.production.Tower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mfitss.idletd.controllers.WaveManager;
import com.mfitss.idletd.objects.buildings.BuildPrice;
import com.mfitss.idletd.objects.buildings.Building;
import com.mfitss.idletd.objects.enemies.Enemy;

public class Tower extends Building {
    private Enemy target;
    private int delay = 3;
    private int attackRange = 500;
    private float timeLeftFromAttack = delay;
    private ShapeRenderer renderer;

    public Tower() {
        price = new BuildPrice(10, 20, 300, 0);
        buildingName = "Turret";
    }

    @Override
    public boolean build(int x, int y, int width, int height) {
        if (super.build(x, y, width, height)) {
            sprite = new Sprite(new Texture(Gdx.files.internal("tower.png")));
            return true;
        }
        return false;
    }

    @Override
    public void work(float delta) {
        if (!checkTarget(target))
            target = null;
        if (target == null || target.destroyed())
            findTarget();
        attack(delta);
    }

    private void findTarget() {
        Array<Enemy> enemies = WaveManager.getEnemies();
        Enemy enemy;
        for (int i = 0; i < enemies.size; i++) {
            enemy = enemies.get(i);
            if (checkTarget(enemy)) {
                target = enemy;
            }
        }
    }

    private boolean checkTarget(Enemy enemy) {
        if (enemy != null && !enemy.destroyed()) {
            Rectangle enemyBounds = enemy.getBounds();
            float x = enemyBounds.x + enemyBounds.width / 2;
            float y = enemyBounds.y + enemyBounds.height / 2;
            return new Vector2(x - bounds.x - bounds.width / 2, y - bounds.y - bounds.height / 2).len() <= attackRange;
        }
        return false;
    }

    private void attack(float delta) {
        timeLeftFromAttack += delta;
        if (target != null) {
            if (timeLeftFromAttack >= delay) {
                timeLeftFromAttack = 0;
                target.setAttacked(true);
            }
            if (target.isAttacked() && timeLeftFromAttack >= 0.3) {
                target.decreaseHealth();
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        if (timeLeftFromAttack < 0.3 && target != null) {
            WaveManager.getMap().drawLine(
                    new Vector2(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2),
                    new Vector2(target.getBounds().x + target.getBounds().width / 2, target.getBounds().y + target.getBounds().height / 2),
                    2, new Color(1, 0, 0, 1)
            );
        }
    }

    public int getAttackRange() {
        return attackRange;
    }

    public float getAPS() {
        return 1f / delay;
    }
}

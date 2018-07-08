package com.mfitss.idletd.objects.enemies;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mfitss.idletd.controllers.WaveManager;
import com.mfitss.idletd.objects.GameObject;

public class Enemy extends GameObject {
    private GameObject target;
    private float speed;

    public Enemy(int x, int y) {
        setBounds(x, y, 100, 100);
        setSprite(new Sprite(new Texture(Gdx.files.internal("enemy.png"))));
    }

    public void attack(float delta){
        if(!bounds.overlaps(target.getBounds()))
            move(delta);
        else {
            target.decreaseHealth();
            destroy();
        }
    }

    private void move(float delta) {
        Rectangle targetBounds = target.getBounds();
        float targetPointX = targetBounds.x + targetBounds.width / 2;
        float targetPointY = targetBounds.y + targetBounds.height / 2;
        float selfPointX = bounds.x + bounds.width / 2;
        float selfPointY = bounds.y + bounds.height / 2;
        Vector2 vector = new Vector2(targetPointX - selfPointX, targetPointY - selfPointY);
        float moveDistance = speed * delta;
        if(vector.len() > moveDistance) {
            float sin = vector.y / vector.len();
            float cos = vector.x / vector.len();
            float x = cos * moveDistance;
            float y = sin * moveDistance;
            bounds.x = x;
            bounds.y = y;
        } else {
            bounds.x = targetPointX - bounds.width/2;
            bounds.y = targetPointY - bounds.height/2;
        }
    }

    @Override
    public void work(float delta) {

    }

    @Override
    protected void destroy() {
        super.destroy();
        WaveManager.removeEnemy(this);
    }
}

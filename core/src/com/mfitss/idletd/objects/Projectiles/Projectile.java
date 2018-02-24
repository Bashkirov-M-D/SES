package com.mfitss.idletd.objects.Projectiles;


import com.badlogic.gdx.math.Rectangle;
import com.mfitss.idletd.objects.GameObject;

public class Projectile extends GameObject {

    protected GameObject target;

    private float speed;

    public Projectile(Rectangle bounds, GameObject targ) {
        super(bounds);
        target = targ;
    }

    public void move() {
        Rectangle targetPosition = target.getBounds();

    }
}

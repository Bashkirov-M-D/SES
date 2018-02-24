package com.mfitss.idletd.objects.Towers;

import com.badlogic.gdx.math.Rectangle;
import com.mfitss.idletd.objects.GameObject;
import com.mfitss.idletd.objects.Projectiles.Projectile;

public abstract class Tower extends GameObject {

    protected double damage;
    protected DamageType damageType;
    protected Projectile projectile;

    public Tower(Rectangle bounds) {
        super(bounds);
    }

    public enum DamageType {PHYSICAL, MAGIC}

}

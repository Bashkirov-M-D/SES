package com.mfitss.idletd.resources;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mfitss.idletd.controllers.SaveManager;

public abstract class GameResource {
    protected String name;

    protected Sprite icon;

    protected GameResource() {}

    protected float amount;

    protected int maxAmount;

    public strictfp void add(float quantity) {
        amount += quantity * (1 + SaveManager.getLevel() * 0.0025);
    }

    public void update(){
        if (amount > maxAmount) amount = maxAmount;
    }

    public strictfp boolean decrease(float quantity) {
        if (amount >= quantity) {
            amount -= quantity;
            return true;
        } else {
            return false;
        }
    }

    public void addMax(int quantity) {
        maxAmount += quantity;
    }

    public void decreaseMax(int quantity) throws RuntimeException {
        if (maxAmount < quantity)
            throw new RuntimeException("can't decrease max amount of " + name);
        maxAmount -= quantity;
        if(amount>maxAmount)
            amount=maxAmount;
    }

    public void setIconBounds(float x, float y, float width, float height) {
        icon.setBounds(x, y, width, height);
    }

    public void drawIcon(SpriteBatch batch) {
        icon.draw(batch);
    }

    public String getName() {
        return name;
    }

    public Sprite getIcon() {
        return icon;
    }

    public int getAmount() {
        return (int) amount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }
}

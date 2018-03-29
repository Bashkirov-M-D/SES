package com.mfitss.idletd.controllers;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Controller {
    void control(float deltaTime);

    void draw(SpriteBatch batch);
}

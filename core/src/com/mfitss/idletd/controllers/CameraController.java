package com.mfitss.idletd.controllers;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mfitss.idletd.main.GameScreen;

public class CameraController implements Controller {

    public static final int CAMERA_WIDTH = 1280;
    public static final int CAMERA_HEIGHT = 620;

    private OrthographicCamera camera;
    private float move;

    public CameraController() {
        camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    @Override
    public void control(float deltaTime) {
        move = deltaTime * 500;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) moveRight();
        if (Gdx.input.isKeyPressed(Input.Keys.A)) moveLeft();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) moveUp();
        if (Gdx.input.isKeyPressed(Input.Keys.S)) moveDown();
    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    private void moveLeft() {
        camera.position.add(-move, 0, 0);
        if (camera.position.x - CAMERA_WIDTH / 2 < -GameScreen.FIELD_WIDTH / 2)
            camera.position.x = -GameScreen.FIELD_WIDTH / 2 + CAMERA_WIDTH / 2;
    }

    private void moveRight() {
        camera.position.add(move, 0, 0);
        if (camera.position.x + CAMERA_WIDTH / 2 > GameScreen.FIELD_WIDTH / 2)
            camera.position.x = GameScreen.FIELD_WIDTH / 2 - CAMERA_WIDTH / 2;
    }

    private void moveDown() {
        camera.position.add(0, -move, 0);
        if (camera.position.y - CAMERA_HEIGHT / 2 < -GameScreen.FIELD_HEIGHT / 2)
            camera.position.y = -GameScreen.FIELD_HEIGHT / 2 + CAMERA_HEIGHT / 2;
    }

    private void moveUp() {
        camera.position.add(0, move, 0);
        if (camera.position.y + CAMERA_HEIGHT / 2 > GameScreen.FIELD_HEIGHT / 2)
            camera.position.y = GameScreen.FIELD_HEIGHT / 2 - CAMERA_HEIGHT / 2;
    }
}
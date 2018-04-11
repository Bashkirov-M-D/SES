package com.mfitss.idletd.controllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.mfitss.idletd.main.GameScreen;

public class GameGestureListener implements GestureDetector.GestureListener {

    private final int BASE_CAMERA_VP_WIDTH = 1280;
    private final int BASE_CAMERA_VP_HEIGHT = 720;

    private float initialScale = 1;

    private OrthographicCamera camera;
    private GameScreen screen;

    public GameGestureListener(OrthographicCamera camera, GameScreen gameScreen) {
        this.camera = camera;
        camera.setToOrtho(false, BASE_CAMERA_VP_WIDTH, BASE_CAMERA_VP_HEIGHT);
        screen = gameScreen;
    }

    private void checkCameraPosition() {
        if (camera.position.x - camera.viewportWidth * camera.zoom / 2 < -GameScreen.FIELD_WIDTH / 2)
            camera.position.x = -GameScreen.FIELD_WIDTH / 2 + camera.viewportWidth * camera.zoom / 2;
        if (camera.position.x + camera.viewportWidth * camera.zoom / 2 > GameScreen.FIELD_WIDTH / 2)
            camera.position.x = GameScreen.FIELD_WIDTH / 2 - camera.viewportWidth * camera.zoom / 2;
        if (camera.position.y - camera.viewportHeight * camera.zoom / 2 < -GameScreen.FIELD_HEIGHT / 2)
            camera.position.y = -GameScreen.FIELD_HEIGHT / 2 + camera.viewportHeight * camera.zoom / 2;
        if (camera.position.y + camera.viewportHeight * camera.zoom / 2 > GameScreen.FIELD_HEIGHT / 2)
            camera.position.y = GameScreen.FIELD_HEIGHT / 2 - camera.viewportHeight * camera.zoom / 2;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        initialScale = camera.zoom;
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        screen.regenerateLevel();
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        camera.translate(-deltaX * camera.zoom, deltaY * camera.zoom);
        checkCameraPosition();
        return true;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        float ratio = initialDistance / distance;
        camera.zoom = initialScale * ratio;
        if (camera.zoom > 2)
            camera.zoom = 2;
        if (camera.zoom < 0.5)
            camera.zoom = 0.5f;
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    /*private void moveLeft() {
        camera.position.add(-move, 0, 0);
        if (camera.position.x - cameraWidth / 2 < -GameScreen.FIELD_WIDTH / 2)
            camera.position.x = -GameScreen.FIELD_WIDTH / 2 + cameraWidth / 2;
    }

    private void moveRight() {
        camera.position.add(move, 0, 0);
        if (camera.position.x + cameraWidth / 2 > GameScreen.FIELD_WIDTH / 2)
            camera.position.x = GameScreen.FIELD_WIDTH / 2 - cameraWidth / 2;
    }

    private void moveDown() {
        camera.position.add(0, -move, 0);
        if (camera.position.y - cameraHeight / 2 < -GameScreen.FIELD_HEIGHT / 2)
            camera.position.y = -GameScreen.FIELD_HEIGHT / 2 + cameraHeight / 2;
    }

    private void moveUp() {
        camera.position.add(0, move, 0);
        if (camera.position.y + cameraHeight / 2 > GameScreen.FIELD_HEIGHT / 2)
            camera.position.y = GameScreen.FIELD_HEIGHT / 2 - cameraHeight / 2;
    }*/
}

package com.mfitss.idletd.controllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.mfitss.idletd.main.GameScreen;

public class GameGestureListener implements GestureDetector.GestureListener {

    private OrthographicCamera camera;

    public GameGestureListener(OrthographicCamera camera) {
        this.camera = camera;
    }

    private void checkCameraPosition() {
        if (camera.position.x - camera.viewportWidth / 2 < -GameScreen.FIELD_WIDTH / 2)
            camera.position.x = -GameScreen.FIELD_WIDTH / 2 + camera.viewportWidth / 2;
        if (camera.position.x + camera.viewportWidth / 2 > GameScreen.FIELD_WIDTH / 2)
            camera.position.x = GameScreen.FIELD_WIDTH / 2 - camera.viewportWidth / 2;
        if (camera.position.y - camera.viewportHeight / 2 < -GameScreen.FIELD_HEIGHT / 2)
            camera.position.y = -GameScreen.FIELD_HEIGHT / 2 + camera.viewportHeight / 2;
        if (camera.position.y + camera.viewportHeight / 2 > GameScreen.FIELD_HEIGHT / 2)
            camera.position.y = GameScreen.FIELD_HEIGHT / 2 - camera.viewportHeight / 2;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        System.out.println(initialPointer1.x + " " + initialPointer1.y + " " + initialPointer2.x + " " + initialPointer2.y + " " +
                pointer1.x + " " + pointer1.y + " " + pointer2.x + " " + pointer2.y);
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

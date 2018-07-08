package com.mfitss.idletd.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mfitss.idletd.UI.UI;
import com.mfitss.idletd.main.GameScreen;
import com.mfitss.idletd.objects.ClickableObject;

public class GameGestureListener implements GestureDetector.GestureListener {

    private static float cameraZoom = 1;

    private float initialScale = 1;

    private OrthographicCamera camera;

    private UI ui;

    private static Array<ClickableObject> objects;

    static {
        objects = new Array<ClickableObject>(ClickableObject.class);
    }

    public GameGestureListener(OrthographicCamera camera, UI i) {
        this.camera = camera;
        ui = i;
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        checkCameraPosition();
    }

    public static void addClickableObjects(ClickableObject[] clickableObjects) {
        objects.addAll(clickableObjects);
    }

    public static void addClickableObject(ClickableObject clickableObject) {
        objects.add(clickableObject);
    }

    public void checkCameraPosition() {
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
        x = camera.position.x - (Gdx.graphics.getWidth() / 2 - x) * camera.zoom;
        y = camera.position.y + (Gdx.graphics.getHeight() / 2 - y) * camera.zoom;
        Rectangle bounds;
        boolean flag = false;
        for (ClickableObject o : objects) {
            bounds = o.getBounds();
            if (x > bounds.x && y > bounds.y && x < bounds.x + bounds.width && y < bounds.y + bounds.width) {
                ui.objectClicked(o);
                flag = true;
            }
        }
        if (!flag)
            ui.clicked(x, y);
        return true;
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
        if (camera.zoom > 1.5)
            camera.zoom = 1.5f;
        if (camera.zoom < 0.5)
            camera.zoom = 0.5f;
        cameraZoom = camera.zoom;
        checkCameraPosition();
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }

    public static float getCameraZoom() {
        return cameraZoom;
    }
}

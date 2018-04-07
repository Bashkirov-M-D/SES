package com.mfitss.idletd.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mfitss.idletd.controllers.CameraController;
import com.mfitss.idletd.controllers.Controller;

import java.util.LinkedList;
import java.util.List;

public class GameScreen implements Screen {
    public static final int FIELD_WIDTH = 5000;
    public static final int FIELD_HEIGHT = 2400;

    private List<Controller> controllers;
    private SpriteBatch batch;
    private Camera camera;

    private Sprite sprite;

    GameMap map;

    @Override
    public void show() {
        batch = new SpriteBatch();
        controllers = new LinkedList<Controller>();
        map = new GameMap();

        CameraController cameraController = new CameraController();
        camera = cameraController.getCamera();
        controllers.add(cameraController);

        sprite = new Sprite(new Texture(Gdx.files.internal("space.jpg")));
        sprite.setBounds(-FIELD_WIDTH / 2, -FIELD_HEIGHT / 2, FIELD_WIDTH, FIELD_HEIGHT);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        for (Controller controller : controllers) {
            controller.control(delta);
        }

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        sprite.draw(batch);
        map.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}

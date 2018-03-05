package com.mfitss.idletd.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mfitss.idletd.objects.Tiles.Field;

public class GameScreen implements Screen {

    public static final float HEIGHT_DECREASE = 0.8f;

    private OrthographicCamera camera;

    private SpriteBatch batch;

    private Field field;

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Field.WIDTH, Field.HEIGHT);
        System.out.println("SHOWN");
        batch = new SpriteBatch();
        field = new Field();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        field.drawField(batch);
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

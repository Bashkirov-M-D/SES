package com.mfitss.idletd.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mfitss.idletd.objects.buildings.TestBuilding;

public class GameScreen implements Screen {

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    private OrthographicCamera camera;

    Texture texture;

    TestBuilding testBuilding;

    private SpriteBatch batch;

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        texture = new Texture("02.jpg");
        batch.setProjectionMatrix(camera.combined);
        camera.setToOrtho(false);
        testBuilding = new TestBuilding(-WIDTH, -HEIGHT);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        testBuilding.work(delta);
        batch.begin();
        batch.draw(texture, -WIDTH / 2, -HEIGHT / 2, WIDTH, HEIGHT);
        testBuilding.draw(batch);
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
        texture.dispose();
        batch.dispose();
    }

}

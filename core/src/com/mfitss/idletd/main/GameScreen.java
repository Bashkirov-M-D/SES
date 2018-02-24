package com.mfitss.idletd.main;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mfitss.idletd.objects.Tiles.Map;

public class GameScreen implements Screen {

    private OrthographicCamera camera;

    private SpriteBatch batch;

    private Texture texture;

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Map.WIDTH, Map.HEIGHT);

        batch = new SpriteBatch();
        texture = new Texture("103419216_1310118147_padme_by_taho.jpg");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
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
        texture.dispose();
    }
}

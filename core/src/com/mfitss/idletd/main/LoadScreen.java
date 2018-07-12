package com.mfitss.idletd.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class LoadScreen implements Screen {
    private Sprite background;
    private MenuScreen.PlanetController controller;
    private SpriteBatch batch;
    private Label label;
    private AssetManager manager;
    private Main main;

    public LoadScreen(Main main, AssetManager manager) {
        this.main = main;
        this.manager = manager;
    }

    @Override
    public void show() {
        background = new Sprite(new Texture(Gdx.files.internal("space2.jpg")));
        controller = new MenuScreen.PlanetController();
        controller.createPlanets();
        Skin skin = new Skin(new TextureAtlas("uiskin.atlas"));
        skin.load(Gdx.files.internal("uiskin.json"));
        label = new Label("Loading", skin.get(Label.LabelStyle.class));
        label.setFontScale(5);
        label.setBounds((Gdx.graphics.getWidth() - label.getPrefWidth())/2, (Gdx.graphics.getHeight() - label.getPrefHeight())/2,
                label.getPrefWidth(), label.getPrefHeight());
        batch = new SpriteBatch();

        manager.load("space.jpg", Texture.class);
        manager.load("enemy.png", Texture.class);
        manager.load("portal.png", Texture.class);
        for (int i = 1; i < 6; i++) {
            manager.load("planets/planet" + i + ".png", Texture.class);
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(manager.update())
            main.showGameScreen();

        batch.begin();
        background.draw(batch);
        label.draw(batch, 1);
        controller.drawPlanets(batch);
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
        background.getTexture().dispose();
        batch.dispose();
    }
}

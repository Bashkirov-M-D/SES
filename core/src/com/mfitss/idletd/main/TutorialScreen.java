package com.mfitss.idletd.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.mfitss.idletd.controllers.TutorialGestureListener;

public class TutorialScreen implements Screen {

    private Main main;
    private Texture image;
    private int imageNumber;
    private int imageAmount = 7;
    private SpriteBatch batch;

    public TutorialScreen(Main main) {
        this.main = main;
        imageNumber = 1;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new GestureDetector(new TutorialGestureListener(this)));
        batch = new SpriteBatch();
        updateImage();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(image, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        Gdx.input.setInputProcessor(null);
        dispose();
    }

    @Override
    public void dispose() {
        image.dispose();
    }

    public void next() {
        imageNumber++;
        if (imageNumber > imageAmount)
            imageNumber = imageAmount;
        else updateImage();
    }

    public void previous() {
        imageNumber--;
        if (imageNumber < 1)
            imageNumber = 1;
        else updateImage();
    }

    private void updateImage() {
        if (image != null)
            image.dispose();
        image = new Texture(Gdx.files.internal("tutorial/" + imageNumber + ".jpg"));
    }

    public void returnToMenu(){
        main.returnToMenu();
    }
}

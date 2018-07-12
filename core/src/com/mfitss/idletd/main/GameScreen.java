package com.mfitss.idletd.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.mfitss.idletd.UI.UI;
import com.mfitss.idletd.controllers.GameGestureListener;
import com.mfitss.idletd.controllers.ResourcesController;
import com.mfitss.idletd.controllers.SaveManager;
import com.mfitss.idletd.controllers.TaskManager;
import com.mfitss.idletd.controllers.WaveManager;

public class GameScreen implements Screen {
    public static final int FIELD_WIDTH = 5000;
    public static final int FIELD_HEIGHT = 2400;

    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private OrthographicCamera camera;
    private GameMap map;
    private UI ui;
    private GameGestureListener gestureListener;
    private boolean paused;
    private static boolean gameOver;
    private Main main;
    private AssetManager manager;

    private Texture background;
    private Sprite emptySprite;

    public GameScreen(Main main, AssetManager assetManager){
        this.main = main;
        gameOver = false;
        manager = assetManager;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        map = new GameMap(manager, renderer);
        ui = new UI(this);
        camera = new OrthographicCamera();

        gestureListener = new GameGestureListener(camera, ui);

        InputMultiplexer input = new InputMultiplexer();
        input.addProcessor(ui.getStage());
        input.addProcessor(new GestureDetector(gestureListener));
        Gdx.input.setInputProcessor(input);

        emptySprite = new Sprite(new Texture("iron.png"));
        emptySprite.setBounds(0, 0, 100, 100);

        background = manager.get("space.jpg");
        TaskManager.create();
        ResourcesController.create();
        WaveManager.create();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        renderer.setProjectionMatrix(camera.combined);

        if(!paused) {
            if (gameOver) {
                ui.gameOver();
                Gdx.input.setInputProcessor(ui.getStage());
                pause();
            } else {
                ui.getStage().act(delta);
                ui.update();
                map.work(delta);
                WaveManager.work(delta);
                TaskManager.manage(delta);
                ResourcesController.updateResources();
            }
        }

        batch.begin();
        renderer.begin(ShapeRenderer.ShapeType.Line);
        batch.draw(background, -FIELD_WIDTH / 2, -FIELD_HEIGHT / 2, FIELD_WIDTH, FIELD_HEIGHT);
        map.draw(batch);

        emptySprite.draw(batch);
        ui.getStage().draw();
        renderer.end();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportHeight = Gdx.graphics.getHeight();
        camera.viewportWidth = Gdx.graphics.getWidth();
        gestureListener.checkCameraPosition();
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public void resume() {
        if(!gameOver)
            paused = false;
    }

    @Override
    public void hide() {
        SaveManager.save(TaskManager.getTimePlaying());
        dispose();
    }

    @Override
    public void dispose() {
        ui.dispose();
        batch.dispose();
    }

    public static void setGameOver(boolean gameOver) {
        GameScreen.gameOver = gameOver;
    }

    public void returnToMenu(){
        main.returnToMenu();
    }
}

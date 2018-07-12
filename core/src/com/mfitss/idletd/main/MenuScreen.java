package com.mfitss.idletd.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mfitss.idletd.controllers.SaveManager;

public class MenuScreen implements Screen {
    private Stage stage;
    private Skin skin;
    private Color defaultColor;
    private Sprite background;
    private Batch batch;
    private Main main;
    private PlanetController controller;


    public MenuScreen(Main main) {
        this.main = main;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(new TextureAtlas("uiskin.atlas"));
        skin.load(Gdx.files.internal("uiskin.json"));
        defaultColor = new Color(0.7f, 0.8f, 1f, 1f);
        background = new Sprite(new Texture("space2.jpg"));
        background.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = stage.getBatch();
        createMenuWindow();
        controller = new PlanetController();
        controller.createPlanets();
    }

    private void createMenuWindow() {
        Window window = new Window("", skin.get(Window.WindowStyle.class));
        window.setMovable(false);
        int width = Gdx.graphics.getWidth() / 5;
        int height = Gdx.graphics.getHeight() / 2;
        window.setBounds((Gdx.graphics.getWidth() - width) / 2, (Gdx.graphics.getHeight() - height) / 2, width, height);
        window.setColor(defaultColor);
        stage.addActor(window);
        String buttonText = "Start game";
        TextButton button = new TextButton(buttonText, skin.get(TextButton.TextButtonStyle.class));
        button.setColor(defaultColor);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.startGame();
            }
        });
        window.add(button).height(window.getHeight() / 5).width(window.getWidth() / 5 * 4).pad(10);
        window.row();
        buttonText = "Tutorial";
        button = new TextButton(buttonText, skin.get(TextButton.TextButtonStyle.class));
        button.setColor(defaultColor);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.showTutorial();
            }
        });
        window.add(button).height(window.getHeight() / 5).width(window.getWidth() / 5 * 4);
        window.row();
        window.add(new Label("Your current score: " + SaveManager.getBestTime(), skin.get(Label.LabelStyle.class))).pad(10);
        window.row();
        window.add(new Label("Your current level: " + SaveManager.getLevel(), skin.get(Label.LabelStyle.class)));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        controller.rotatePlanets(delta);

        batch.begin();
        background.draw(batch);
        controller.drawPlanets(batch);
        batch.end();
        stage.draw();
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
        dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    public static class PlanetController{
        private Sprite[] planets;

        public void rotatePlanets(float delta) {
            for (Sprite s : planets) {
                s.rotate(delta * 20);
            }
        }

        public void drawPlanets(Batch batch) {
            for (Sprite s : planets) {
                s.draw(batch);
            }
        }

        public void createPlanets() {
            int size = Gdx.graphics.getWidth() / 15;
            int width = Gdx.graphics.getWidth();
            int height = Gdx.graphics.getHeight();
            planets = new Sprite[]{
                    new Sprite(new Texture("planets/planet1.png")),
                    new Sprite(new Texture("planets/planet2.png")),
                    new Sprite(new Texture("planets/planet3.png")),
                    new Sprite(new Texture("planets/planet4.png")),
                    new Sprite(new Texture("planets/planet5.png"))
            };

            float ppx = 0;
            float pph = 0;
            for (int i = 0; i < 5; i++) {
                switch (i) {
                    case 0:
                        ppx = width / 8;
                        pph = height / 5;
                        break;
                    case 1:
                        ppx = 7 * width / 10;
                        pph = height / 10;
                        break;
                    case 2:
                        ppx = 2 * width / 10;
                        pph = 2 * height / 5;
                        break;
                    case 3:
                        ppx = 1.5f * width / 10;
                        pph = 4 * height / 5;
                        break;
                    case 4:
                        ppx = 8 * width / 10;
                        pph = 2.5f * height / 5;
                }
                planets[i].setBounds(ppx, pph, size, size);
                planets[i].setOrigin(size/2, size/2);
            }
        }
    }
}

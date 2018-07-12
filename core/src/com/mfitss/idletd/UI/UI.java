package com.mfitss.idletd.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mfitss.idletd.controllers.BuildingManager;
import com.mfitss.idletd.controllers.SaveManager;
import com.mfitss.idletd.controllers.TaskManager;
import com.mfitss.idletd.controllers.WaveManager;
import com.mfitss.idletd.main.GameScreen;
import com.mfitss.idletd.objects.ClickableObject;
import com.mfitss.idletd.resources.GameResource;
import com.mfitss.idletd.resources.ResourceFermonium;
import com.mfitss.idletd.resources.ResourceIron;
import com.mfitss.idletd.resources.ResourceMoney;
import com.mfitss.idletd.resources.ResourcePower;
import com.mfitss.idletd.resources.ResourceSteel;

public class UI {

    private Stage stage;
    private Skin skin;
    private Color defaultColor;
    private ProductionBuildWindow buildWindow;
    private DefenceBuildWindow defenceBuildWindow;
    private InfoWindow infoWindow;
    private Array<Label> labels;
    private Label taskLabel;
    private Label waveLabel;
    private GameScreen screen;

    public UI(GameScreen gameScreen) {
        screen = gameScreen;
        create();
    }

    private void create() {
        stage = new Stage();
        labels = new Array<Label>(Label.class);

        skin = new Skin(new TextureAtlas("uiskin.atlas"));
        skin.load(Gdx.files.internal("uiskin.json"));

        defaultColor = new Color(0.7f, 0.8f, 1f, 1f);

        createLabels();
        createButtons();

        buildWindow = new ProductionBuildWindow("", stage, skin);
        infoWindow = new InfoWindow("", stage, skin);
        defenceBuildWindow = new DefenceBuildWindow("", stage, skin);
    }

    private void createLabels() {
        Label.LabelStyle labelStyle = skin.get(Label.LabelStyle.class);
        Label label;

        for (int i = 0; i < 5; i++) {
            label = new Label("", labelStyle);
            label.setBounds(40 + i * 2f / 10 * Gdx.graphics.getWidth(),
                    Gdx.graphics.getHeight() - 50,
                    50, 50);
            label.setFontScale(2);

            LabelAction action = new LabelAction(label, i);

            label.addAction(action);
            labels.add(label);
            stage.addActor(label);
        }
        label = new Label("v0.0.1 special-alpha", labelStyle);
        label.setBounds(0, 0, 100, 20);
        label.setColor(1, 1, 1, 1);
        stage.addActor(label);
        label = new Label("", labelStyle);
        label.setBounds(Gdx.graphics.getWidth() / 2 - 500, 10, 400, 50);
        label.setFontScale(2);
        stage.addActor(label);
        taskLabel = label;
        label = new Label("", labelStyle);
        label.setBounds(Gdx.graphics.getWidth() / 2 - 500, 70, 400, 50);
        label.setFontScale(2);
        stage.addActor(label);
        waveLabel = label;
    }

    private void createButtons() {
        final TextButton.TextButtonStyle buttonStyle = skin.get(TextButton.TextButtonStyle.class);

        TextButton textButton = new TextButton("Production", buttonStyle);
        textButton.setBounds(Gdx.graphics.getWidth() - 220, 20, 200, 100);
        textButton.setColor(defaultColor);
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                infoWindow.setVisible(false);
                defenceBuildWindow.hide();
                if (buildWindow.isVisible())
                    buildWindow.hide();
                else buildWindow.show();
            }
        });
        stage.addActor(textButton);

        textButton = new TextButton("Defence", buttonStyle);
        textButton.setBounds(Gdx.graphics.getWidth() - 440, 20, 200, 100);
        textButton.setColor(defaultColor);
        textButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                infoWindow.setVisible(false);
                buildWindow.hide();
                if (defenceBuildWindow.isVisible())
                    defenceBuildWindow.hide();
                else defenceBuildWindow.show();
            }
        });
        stage.addActor(textButton);
    }

    public void objectClicked(ClickableObject o) {
        buildWindow.hide();
        infoWindow.setVisible(true);
        infoWindow.addInfo(o);
    }

    public void update() {
        for (int i = 0; i < 5; i++) {
            LabelAction action = new LabelAction(labels.get(i), i);
            labels.get(i).addAction(action);
        }
        buildWindow.update();
        defenceBuildWindow.update();
        taskLabel.addAction(taskLabelAction);
        waveLabel.addAction(waveLabelAction);
    }

    public void clicked(float x, float y) {
        if (buildWindow.getCurrentBuilding() != -1) {
            BuildingManager.build(buildWindow.getBuildingByNumber(buildWindow.getCurrentBuilding()), (int) x, (int) y, 100, 100);
        } else if (defenceBuildWindow.getCurrentBuilding() != -1)
            BuildingManager.build(defenceBuildWindow.getBuildingByNumber(defenceBuildWindow.getCurrentBuilding()), (int) x, (int) y, 100, 100);
        infoWindow.setVisible(false);
    }

    public Stage getStage() {
        return stage;
    }

    private class LabelAction extends Action {

        private Label label;
        private int labelNumber;

        public LabelAction(Label label, int labelNumber) {
            this.label = label;
            this.labelNumber = labelNumber;

        }

        @Override
        public boolean act(float delta) {
            GameResource resource = null;
            switch (labelNumber) {
                case 0:
                    resource = ResourceIron.getResource();
                    break;
                case 1:
                    resource = ResourceSteel.getResource();
                    break;
                case 2:
                    resource = ResourcePower.getResource();
                    break;
                case 3:
                    resource = ResourceMoney.getResource();
                    break;
                default:
                    resource = ResourceFermonium.getResource();
            }
            label.setText(resource.getName() + ": " + resource.getAmount() + " / " +
                    resource.getMaxAmount());
            return true;
        }
    }

    private Action taskLabelAction = new Action(){
        @Override
        public boolean act(float delta) {
            Label label = (Label) actor;
            label.setText("Task: " + TaskManager.getResource().getName() + " more then " + TaskManager.getResourceNeed() + " Time left: " +
                    TaskManager.getTimeLeft()  + " days");
            return true;
        }
    };

    private Action waveLabelAction = new Action(){
        @Override
        public boolean act(float delta) {
            Label label = (Label) actor;
            label.setText("Time left before next wave: " + WaveManager.getTimeLeft() + " days");
            return true;
        }
    };

    public void gameOver(){
        Window window = new Window("", skin.get(Window.WindowStyle.class));
        window.setMovable(false);
        window.setBounds(Gdx.graphics.getWidth()/2-250, Gdx.graphics.getHeight()/2-250, 500, 500);
        Label.LabelStyle style = skin.get(Label.LabelStyle.class);
        Label label;
        addGameOverText(window, style);
        for (int i = 2; i < 4; i++) {
            label = new Label(chooseGameOverLabelText(i), style);
            label.setFontScale(2);
            window.add(label).pad(5);
            window.row();
        }
        TextButton button = new TextButton("Return to menu", skin.get(TextButton.TextButtonStyle.class));
        button.setColor(defaultColor);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                screen.returnToMenu();
            }
        });
        window.add(button).pad(5).width(window.getWidth()-20).height(50);
        stage.addActor(window);
        SaveManager.save(TaskManager.getTimePlaying());
    }

    public void addGameOverText(Window window, Label.LabelStyle style) {
        Label label;
        if (WaveManager.getCore().destroyed()) {
            label = new Label(chooseGameOverLabelText(5), style);
            label.setFontScale(2);
            window.add(label).pad(5);
            window.row();
        } else {
            for (int i = 0; i < 2; i++) {
                label = new Label(chooseGameOverLabelText(i), style);
                label.setFontScale(2);
                window.add(label).pad(5);
                window.row();
            }
        }
    }

    private String chooseGameOverLabelText(int i){
        switch (i){
            case 0:
                return "You have failed the task:";
            case 1:
                return TaskManager.getResource().getName() + " have more then " + TaskManager.getResourceNeed();
            case 2:
                return "Time played: " + TaskManager.getTimePlaying();
            case 3:
                return "Best score: " + Math.max(SaveManager.getBestTime(), TaskManager.getTimePlaying());
            case 5:
                return "Your core was destroyed";
            default:
                return null;
        }
    }

    public void dispose(){
        stage.dispose();
        skin.dispose();
    }
}

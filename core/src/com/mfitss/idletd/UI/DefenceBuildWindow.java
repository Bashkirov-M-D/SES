package com.mfitss.idletd.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.mfitss.idletd.objects.buildings.BuildPrice;
import com.mfitss.idletd.objects.buildings.Building;
import com.mfitss.idletd.objects.buildings.production.Tower.Tower;

public class DefenceBuildWindow extends Window {
    private Array<TextButton> buttons;
    private Array<Label> labels;
    private int currentBuilding = -1;
    private Color defaultColor = new Color(0.7f, 0.8f, 1f, 1f);
    private Stage stage;
    private Skin skin;


    public DefenceBuildWindow(String title, Stage stage, Skin skin) {
        super(title, skin.get(Window.WindowStyle.class));
        buttons = new Array<TextButton>(TextButton.class);
        labels = new Array<Label>(Label.class);
        this.stage = stage;
        this.skin = skin;
        create();
    }

    private void create() {
        clear();
        buttons.clear();
        labels.clear();
        setMovable(false);
        setBounds(Gdx.graphics.getWidth() - 220, 130, 220, Gdx.graphics.getHeight() - 200);
        setColor(defaultColor);

        TextButton.TextButtonStyle style = skin.get(TextButton.TextButtonStyle.class);
        Label.LabelStyle labelStyle = skin.get(Label.LabelStyle.class);

        for (int i = 0; i < 1; i++) {
            Building building = getBuildingByNumber(i);
            createButton(i, style, building);
            createCostLabel(i, labelStyle, building);
        }
        stage.addActor(this);
        setVisible(false);
    }

    private void createButton(int i, TextButton.TextButtonStyle style, Building building) {
        String buttonText = "Build " + building.getBuildingName();
        TextButton button = new TextButton(buttonText, style);
        button.setColor(defaultColor);
        button.addListener(new DefenceBuildWindow.ButtonClickListener(button, i));
        buttons.add(button);
        add(button).height(50).width(getWidth() - 20).pad(5);
        row();
    }

    private void createCostLabel(int i, Label.LabelStyle style, Building building) {
        BuildPrice price = building.getPrice();
        Label costLabel = new Label("Cost: steel: " + price.getMetalPrice() + " power: " + price.getPowerPrice() + "\n coins: " +
                price.getCoinPrice() + " fermonium: " + price.getFermoniumPrice(), style);
        add(costLabel);
        row();
        labels.add(costLabel);
    }

    public void hide() {
        currentBuilding = -1;
        for (int i = 0; i < buttons.size; i++) {
            buttons.get(i).setColor(defaultColor);
        }
        setVisible(false);
    }

    public void show() {
        update();
        setVisible(true);
    }

    public void update() {
        Building building;
        Color inactiveButtonColor = new Color(0.4f, 0.4f, 0.5f, 1);
        for (int i = 0; i < buttons.size; i++) {
            building = getBuildingByNumber(i);
            if (!building.canBuild()) {
                buttons.get(i).setColor(inactiveButtonColor);
                labels.get(i).setColor(0.7f, 0.2f, 0.2f, 1);
            } else {
                if (buttons.get(i).getColor().equals(inactiveButtonColor)) {
                    buttons.get(i).setColor(defaultColor);
                    labels.get(i).setColor(defaultColor);
                }
            }
        }
    }

    public Building getBuildingByNumber(int i) {
        Building building;
        switch (i) {
            case 0:
                building = new Tower();
                break;
            default:
                building = null;
        }
        return building;
    }

    public int getCurrentBuilding() {
        return currentBuilding;
    }

    private class ButtonClickListener extends ClickListener {
        private TextButton button;
        private int buildingNumber;
        private Color chosenColor;

        public ButtonClickListener(TextButton textButton, int num) {
            buildingNumber = num;
            button = textButton;
            chosenColor = new Color(0.7f, 0, 0, 1);
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            if (button.getColor().equals(chosenColor)) {
                currentBuilding = -1;
                button.setColor(defaultColor);
            } else {
                button.setColor(chosenColor);
                currentBuilding = buildingNumber;
                for (int i = 0; i < buttons.size; i++)
                    if (!buttons.get(i).equals(button))
                        buttons.get(i).setColor(defaultColor);
            }
            update();
        }
    }
}

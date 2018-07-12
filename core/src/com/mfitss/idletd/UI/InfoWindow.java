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
import com.mfitss.idletd.controllers.BuildingManager;
import com.mfitss.idletd.objects.ClickableObject;
import com.mfitss.idletd.objects.Planets.Planet;
import com.mfitss.idletd.objects.buildings.BuildPrice;
import com.mfitss.idletd.objects.buildings.Building;
import com.mfitss.idletd.objects.buildings.production.ProductionBuilding;
import com.mfitss.idletd.objects.buildings.production.Tower.Tower;
import com.mfitss.idletd.objects.buildings.production.factories.GameFactory;
import com.mfitss.idletd.objects.buildings.production.mines.FermoniumMine;
import com.mfitss.idletd.objects.buildings.production.mines.IronMine;
import com.mfitss.idletd.objects.buildings.production.mines.Mine;
import com.mfitss.idletd.objects.buildings.production.storages.SolarPanel;
import com.mfitss.idletd.objects.buildings.production.storages.Storage;

public class InfoWindow extends Window {
    private Color defaultColor = new Color(0.7f, 0.8f, 1f, 1f);
    private Stage stage;
    private Skin skin;
    private ClickableObject infoObject;

    public InfoWindow(String title, Stage stage, Skin skin) {
        super(title, skin.get(Window.WindowStyle.class));
        this.stage = stage;
        this.skin = skin;
        create();
    }

    private void create() {
        setMovable(false);
        setBounds(Gdx.graphics.getWidth() - 220, 130, 220, Gdx.graphics.getHeight() - 200);
        setColor(defaultColor);
        setVisible(false);
        stage.addActor(this);
    }

    public void addInfo(ClickableObject object) {
        clear();
        infoObject = object;
        if (object instanceof Planet)
            addPlanetInfo((Planet) object);
        if (object instanceof Building)
            addBuildingInfo((Building) object);
    }

    private void addPlanetInfo(Planet planet) {
        Mine mine = planet.getMine();
        if (mine == null)
            addPlanetInfo();
        else addBuildingInfo((Building) mine);
    }

    private void addPlanetInfo() {
        Label.LabelStyle labelStyle = skin.get(Label.LabelStyle.class);
        add(new Label("Building: none", labelStyle)).pad(5);
        row();
        add(new Label("Income: 0", labelStyle)).pad(5);
        row();
        TextButton.TextButtonStyle buttonStyle = skin.get(TextButton.TextButtonStyle.class);
        TextButton button;
        Building building;
        for (int i = 0; i < 2; i++) {
            button = new TextButton("", buttonStyle);
            switch (i) {
                case 0:
                    building = new IronMine();
                    break;
                case 1:
                    building = new FermoniumMine();
                    break;
                default:
                    building = null;
            }
            button.setText("Build " + building.getBuildingName());
            button.setColor(defaultColor);
            button.addListener(new BuildClickListener((Mine) building));
            add(button).pad(5).height(50).width(getWidth() - 20);
            row();
            BuildPrice price = building.getPrice();
            Label costLabel = new Label("Cost: steel: " + price.getMetalPrice() + " power: " + price.getPowerPrice() + "\n coins: " +
                    price.getCoinPrice() + " fermonium: " + price.getFermoniumPrice(), labelStyle);
            add(costLabel);
            if (!building.canBuild()) {
                button.setColor(0.4f, 0.4f, 0.5f, 1);
                costLabel.setColor(0.7f, 0.2f, 0.2f, 1);
            }
            row();
        }
    }

    private void addBuildingInfo(Building building) {
        add(new Label("Building :" + building.getBuildingName(), skin.get(Label.LabelStyle.class)));
        row();
        if (building instanceof ProductionBuilding)
            addBuildingInfo((ProductionBuilding) building);
        if (building instanceof Tower)
            addTowerInfo((Tower) building);
    }

    private void addBuildingInfo(final ProductionBuilding building) {
        Label.LabelStyle labelStyle = skin.get(Label.LabelStyle.class);
        if (building instanceof GameFactory) {
            add(new Label("Recycling speed: " + ((GameFactory) building).getSpeed(), labelStyle));
            row();
            add(new Label("Recycling efficiency: " + (1 / ((GameFactory) building).getEfficiency()) * 100 + "%", labelStyle));
        } else if (building instanceof SolarPanel) {
            add(new Label("Power increase: " + ((SolarPanel) building).getResourceIncrease(), labelStyle));
        } else if (building instanceof Mine) {
            add(new Label(building.getResourceName() + " income: " + ((Mine) building).getWorkSpeed(), labelStyle)).pad(5);
        } else if (building instanceof Storage) {
            add(new Label(building.getResourceName() + " capacity: " + ((Storage) building).getResourceIncrease(), labelStyle));
        }
        row();
        TextButton button = new TextButton("Upgrade", skin.get(TextButton.TextButtonStyle.class));
        button.setColor(defaultColor);
        add(button).pad(5).width(getWidth() - 20).height(50);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                BuildingManager.upgrade(building);
                addInfo(building);
            }
        });
        row();
        BuildPrice price = building.getPrice();
        Label costLabel = new Label("Cost: steel: " + price.getMetalPrice() + " power: " + price.getPowerPrice() + "\n coins: " +
                price.getCoinPrice() + " fermonium: " + price.getFermoniumPrice(), labelStyle);
        add(costLabel);
        if (!building.canBuild()) {
            button.setColor(0.4f, 0.4f, 0.5f, 1);
            costLabel.setColor(0.7f, 0.2f, 0.2f, 1);
        }
    }

    private void addTowerInfo(Tower tower) {
        Label.LabelStyle labelStyle = skin.get(Label.LabelStyle.class);
        add(new Label("Attack range: " + tower.getAttackRange(), labelStyle)).pad(5);
        row();
        add(new Label("Attack speed: " + String.format("%.2f", tower.getAPS()), labelStyle)).pad(5);
        row();
    }

    private class BuildClickListener extends ClickListener {

        private Mine mine;

        public BuildClickListener(Mine mine) {
            this.mine = mine;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            BuildingManager.build(mine, (Planet) infoObject);
            addInfo(mine);
        }
    }
}

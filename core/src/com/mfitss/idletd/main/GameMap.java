package com.mfitss.idletd.main;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mfitss.idletd.controllers.BuildingManager;
import com.mfitss.idletd.controllers.WaveManager;
import com.mfitss.idletd.objects.GameObject;
import com.mfitss.idletd.objects.Planets.Planet;
import com.mfitss.idletd.objects.buildings.Core;
import com.mfitss.idletd.objects.enemies.Enemy;
import com.mfitss.idletd.objects.enemies.Portal;

public class GameMap {
    private static int fieldWidthDecrease = GameScreen.FIELD_WIDTH / 5;
    private static int fieldHeightDecrease = GameScreen.FIELD_HEIGHT / 5;
    public static final int BUILDABLE_FIELD_WIDTH = GameScreen.FIELD_WIDTH - fieldWidthDecrease;
    public static final int BUILDABLE_FIELD_HEIGHT = GameScreen.FIELD_HEIGHT - fieldHeightDecrease;
    private int planetCount = 10;
    private Array<GameObject> objects;
    private AssetManager assetManager;
    private ShapeRenderer renderer;
    private Core core;

    public GameMap(AssetManager manager, ShapeRenderer renderer) {
        assetManager = manager;
        this.renderer = renderer;
        WaveManager.setMap(this);
        BuildingManager.set(this);
        objects = new Array<GameObject>(GameObject.class);
        createMap();
    }

    private void createMap() {
        core = new Core();
        WaveManager.setCore(core);
        Rectangle rectangle = new Rectangle();
        Planet[] planets = new Planet[planetCount];
        int distanceBetweenPlanets = 500;
        int cX;
        int cY;
        boolean flag;
        int fieldWidthForPlanets = BUILDABLE_FIELD_WIDTH - fieldWidthDecrease;
        int fieldHeightForPlanets = BUILDABLE_FIELD_HEIGHT - fieldHeightDecrease;
        for (int i = 0; i < planetCount; i++) {
            flag = true;
            while (flag) {
                flag = false;
                cX = (int) (Math.random() * (BUILDABLE_FIELD_WIDTH - 99) - BUILDABLE_FIELD_WIDTH / 2);
                cY = (int) (Math.random() * (BUILDABLE_FIELD_HEIGHT - 99) - BUILDABLE_FIELD_HEIGHT / 2);
                rectangle.set(cX - distanceBetweenPlanets / 2, cY - distanceBetweenPlanets / 2, distanceBetweenPlanets, distanceBetweenPlanets);
                if (rectangle.overlaps(core.getBounds()))
                    flag = true;
                if (!flag) {
                    for (Planet planet : planets) {
                        if (planet != null && rectangle.overlaps(planet.getBounds())) {
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag) {
                    Planet planet = new Planet(cX, cY, assetManager);
                    objects.add(planet);
                    planets[i] = planet;
                }
            }
        }
    }

    public void addBuilding(GameObject object) {
        objects.add(object);
    }

    public void deleteObject(GameObject object) {
        objects.removeValue(object, true);
    }

    public GameObject[] getObjects() {
        return objects.toArray();
    }

    public void work(float delta) {
        for (int i = 0; i < objects.size; i++) {
            objects.get(i).work(delta);
        }
    }

    public void draw(SpriteBatch batch) {
        for (int i = 0; i < objects.size; i++)
            objects.get(i).draw(batch);
        for (int i = 0; i < WaveManager.getPortals().size; i++)
            WaveManager.getPortals().get(i).draw(batch);
        for (int i = 0; i < WaveManager.getEnemies().size; i++)
            WaveManager.getEnemies().get(i).draw(batch);
    }

    public void createPortal() {
        Rectangle rectangle = new Rectangle();
        boolean flag = true;
        int cX;
        int cY;
        while (flag) {
            flag = false;
            cX = (int) (Math.random() * (GameScreen.FIELD_WIDTH - 199)) - GameScreen.FIELD_WIDTH / 2;
            cY = selectPortalY(cX);
            rectangle.set(cX, cY, 100, 200);
            for (int i = 0; i < WaveManager.getPortals().size; i++) {
                if (WaveManager.getPortals().get(i).getBounds().overlaps(rectangle)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                WaveManager.addPortal(new Portal(rectangle.x, rectangle.y, Enemy.class, assetManager));
            }
        }
    }

    private int selectPortalY(int x) {
        int y;
        if (x > BUILDABLE_FIELD_WIDTH / 2 || x < -BUILDABLE_FIELD_WIDTH / 2 - 200)
            y = (int) (Math.random() * (GameScreen.FIELD_HEIGHT - 199)) - GameScreen.FIELD_HEIGHT / 2;
        else {
            y = (int) (Math.random() * (fieldHeightDecrease - 399));
            if (y > (fieldHeightDecrease - 400) / 2) y += BUILDABLE_FIELD_HEIGHT / 2;
            else y = -y - 200 - BUILDABLE_FIELD_HEIGHT / 2;
        }
        return y;
    }

    public void drawLine(Vector2 start, Vector2 end, int width, Color color) {
        renderer.setColor(color);
        renderer.line(start, end);
        Gdx.gl.glLineWidth(width);
    }
}

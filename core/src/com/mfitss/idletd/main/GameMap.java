package com.mfitss.idletd.main;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mfitss.idletd.objects.Planets.Planet;

public class GameMap {
    private static int fieldWidthDecrease = GameScreen.FIELD_WIDTH / 10;
    private static int fieldHeightDecrease = GameScreen.FIELD_HEIGHT / 10;
    public static final int BUILDABLE_FIELD_WIDTH = GameScreen.FIELD_WIDTH - fieldWidthDecrease;
    public static final int BUILDABLE_FIELD_HEIGHT = GameScreen.FIELD_HEIGHT - fieldHeightDecrease;
    private int planetCount = 5;
    private Planet[] planets;

    public GameMap() {
        createPlanets();
    }

    private void createPlanets() {
        Rectangle rectangle = new Rectangle();
        planets = new Planet[planetCount];
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
                cX = (int) (Math.random() * (fieldWidthForPlanets + 1) - fieldWidthForPlanets / 2);
                cY = (int) (Math.random() * (fieldHeightForPlanets + 1) - fieldHeightForPlanets / 2);
                rectangle.set(cX - distanceBetweenPlanets / 2, cY - distanceBetweenPlanets / 2, distanceBetweenPlanets, distanceBetweenPlanets);
                for (Planet planet : planets) {
                    if (planet != null && rectangle.overlaps(planet.getBounds())) {
                        flag = true;
                        break;
                    }
                }
                if (!flag)
                    planets[i] = new Planet(cX, cY);
            }
        }
    }

    public void draw(SpriteBatch batch) {
        for (Planet planet : planets) {
            planet.draw(batch);
        }
    }
}

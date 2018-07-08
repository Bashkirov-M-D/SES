package com.mfitss.idletd.controllers;

import com.badlogic.gdx.math.Rectangle;
import com.mfitss.idletd.main.GameMap;
import com.mfitss.idletd.main.GameScreen;
import com.mfitss.idletd.objects.GameObject;
import com.mfitss.idletd.objects.Planets.Planet;
import com.mfitss.idletd.objects.buildings.Building;
import com.mfitss.idletd.objects.buildings.production.mines.Mine;

public class BuildingManager {
    private static GameScreen screen;
    private static GameMap map;

    public static boolean build(Mine mine, Planet planet) {
        if (mine.canBuild())
            if (!planet.isOccupied()) {
                mine.build(planet);
                map.addBuilding(mine);
            }
        return true;
    }

    private static boolean checkOverlapping(Rectangle rectangle, GameObject[] objects) {
        for (GameObject object : objects) {
            if (rectangle.overlaps(object.getBounds()))
                return false;
        }
        return true;
    }

    public static boolean upgrade(Building building){
        return building.upgrade();
    }

    public static boolean build(Building building, int x, int y, int width, int height) {
        Rectangle bounds = new Rectangle(x - width / 2, y - height / 2, width, height);
        if (x >= -GameMap.BUILDABLE_FIELD_WIDTH / 2 && x <= GameMap.BUILDABLE_FIELD_WIDTH / 2 - width &&
                y >= -GameMap.BUILDABLE_FIELD_HEIGHT / 2 && y <= GameMap.BUILDABLE_FIELD_HEIGHT / 2 - height &&
                checkOverlapping(bounds, map.getObjects()) &&
                building.canBuild())
        {
            building.build((int) bounds.x, (int) bounds.y, (int) bounds.width, (int) bounds.height);
            map.addBuilding(building);
            return true;
        }

        return false;
    }

    public static void set(GameScreen screen, GameMap map) {
        BuildingManager.screen = screen;
        BuildingManager.map = map;
    }

    public static void destroyObject(GameObject object){
        map.deleteObject(object);
    }
}

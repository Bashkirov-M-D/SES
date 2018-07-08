package com.mfitss.idletd.controllers;

import com.badlogic.gdx.utils.Array;
import com.mfitss.idletd.main.GameMap;
import com.mfitss.idletd.objects.enemies.Enemy;
import com.mfitss.idletd.objects.enemies.Portal;

public class WaveManager {
    private static Array<Enemy> enemies;
    private static int timeBetweenWaves = 20;
    private static float timeLeft;
    private static int enemyNumber;
    private static Array<Portal> portals;
    private static final int MAX_PORTALS_NUMBER = 8;
    private static final int ENEMIES_PER_PORTAL_NUMBER = 10;

    private static GameMap map;

    public static void create() {
        enemies = new Array<Enemy>(Enemy.class);
        portals = new Array<Portal>(Portal.class);
        timeLeft = timeBetweenWaves;
        enemyNumber = 5;
    }

    public static void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public static void addPortal(Portal portal) {
        portals.add(portal);
    }

    public static void work(float delta) {
        timeLeft -= delta;
        if (timeLeft <= 0) {
            startNewWave();
            timeLeft = timeBetweenWaves;
        }
        for (int i = 0; i < enemies.size; i++) {
            enemies.get(i).attack(delta);
        }
        for (Portal portal : portals) {
            portal.work(delta);
        }
    }

    public static void removeEnemy(Enemy enemy) {
        enemies.removeValue(enemy, true);
    }

    public static void startNewWave() {
        while ((portals.size < 1 || enemyNumber / portals.size > ENEMIES_PER_PORTAL_NUMBER) && portals.size < MAX_PORTALS_NUMBER)
            map.createPortal();
        Portal portal;
        for (int i = 0; i < portals.size; i++) {
            portal = portals.get(i);
            portal.setSpawnAmount(enemyNumber / portals.size);
            portal.setEnemyType(Enemy.class);
            portal.updateDelay();
            enemyNumber = (int)(enemyNumber * 1.2 + 2);
        }
    }

    public static Array<Enemy> getEnemies() {
        return enemies;
    }

    public static Array<Portal> getPortals() {
        return portals;
    }

    public static void setMap(GameMap map) {
        WaveManager.map = map;
    }

    public static int getTimeLeft() {
        return (int) timeLeft;
    }
}

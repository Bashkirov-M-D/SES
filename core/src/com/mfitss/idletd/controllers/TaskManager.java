package com.mfitss.idletd.controllers;

import com.mfitss.idletd.main.GameScreen;
import com.mfitss.idletd.resources.GameResource;
import com.mfitss.idletd.resources.ResourceFermonium;
import com.mfitss.idletd.resources.ResourceIron;
import com.mfitss.idletd.resources.ResourceMoney;
import com.mfitss.idletd.resources.ResourceSteel;

public class TaskManager {
    private static float timePlaying;

    private static float timeLeft;

    private static float timeForTask = 500;

    private static int resourceNeed;

    private static GameResource resource;

    private static float[] startResourceRequirements;

    public static void create(){
        startResourceRequirements = new float[]{100, 50, 300, 1};
        startNewTask();
    }

    public static void manage(float delta) {
        timePlaying += delta;
        timeLeft -= delta;
        if (timeLeft <= 0) {
            timeLeft = 0;
            checkTask();
        }
    }

    private static void checkTask() {
        if (resource.getAmount() >= resourceNeed)
            startNewTask();
        else {
            GameScreen.setGameOver(true);
        }
    }

    private static void chooseResource(int rnd) {
        switch (rnd) {
            case 0:
                resource = ResourceIron.getResource();
                break;
            case 1:
                resource = ResourceSteel.getResource();
                break;
            case 2:
                resource = ResourceMoney.getResource();
                break;
            case 3:
                resource = ResourceFermonium.getResource();
                break;
            default:
                resource = null;
        }
    }

    public static void startNewTask() {
        int rnd = (int) (Math.random() * (startResourceRequirements.length));
        chooseResource(rnd);
        resourceNeed = (int) startResourceRequirements[rnd];
        timeLeft = timeForTask;
        for (int i = 0; i < 4; i++) {
            startResourceRequirements[i] = startResourceRequirements[i] * 2;
        }
    }

    public static GameResource getResource() {
        return resource;
    }

    public static int getResourceNeed() {
        return resourceNeed;
    }

    public static int getTimePlaying() {
        return (int) timePlaying;
    }

    public static int getTimeLeft() {
        return (int) timeLeft;
    }
}

package com.mfitss.idletd.controllers;

import com.badlogic.gdx.Gdx;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveManager {
    private static int bestTime;
    private static int level;

    public static void create() {
        String path = Gdx.files.getLocalStoragePath();
        File file = new File(path + "score");
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNext())
                    bestTime = scanner.nextInt();
                if (scanner.hasNext())
                    level = scanner.nextInt();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save(int time) {
        String path = Gdx.files.getLocalStoragePath();
        File file = new File(path + "score");
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    PrintWriter writer = new PrintWriter(file);
                    writer.println(time);
                    writer.println(level);
                    writer.close();
                }
            } else {
                if (time > bestTime) {
                    bestTime = time;
                    PrintWriter writer = new PrintWriter(file);
                    writer.println(time);
                    writer.println(level);
                    writer.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getBestTime(){
        return bestTime;
    }

    public static void addLevel(int value) {
        level += value;
    }

    public static int getLevel() {
        return level;
    }
}

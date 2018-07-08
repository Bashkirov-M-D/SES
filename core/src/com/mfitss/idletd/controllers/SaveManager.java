package com.mfitss.idletd.controllers;

import com.badlogic.gdx.Gdx;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SaveManager {
    public static void saveBestTime(int time) {
        String path = Gdx.files.getLocalStoragePath();
        File file = new File(path + "score");
        int prevScore = 0;
        try {
            if (!file.exists()) {
                file.createNewFile();
                PrintWriter writer = new PrintWriter(file);
                writer.println(time);
                writer.close();
            } else {
                Scanner scanner = new Scanner(file);
                if(scanner.hasNext())
                    prevScore = scanner.nextInt();
                if(time>prevScore){
                    PrintWriter writer = new PrintWriter(file);
                    writer.println(time);
                    writer.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getBestTime(){
        String path = Gdx.files.getLocalStoragePath();
        File file = new File(path + "score");
        int score;
        try {
            if(file.exists()) {
                Scanner scanner = new Scanner(file);
                if (scanner.hasNext()) {
                    score = scanner.nextInt();
                    return score;
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return 0;
    }
}

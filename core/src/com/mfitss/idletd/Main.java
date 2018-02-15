package com.mfitss.idletd;

import com.badlogic.gdx.Game;
import com.mfitss.idletd.view.GameScreen;

public class Main extends Game {



    @Override
    public void create() {
        setScreen(new GameScreen());
    }

    @Override
    public void render() {

    }

    @Override
    public void dispose() {
        screen.dispose();
    }
}

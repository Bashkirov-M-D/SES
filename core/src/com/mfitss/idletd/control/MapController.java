package com.mfitss.idletd.control;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.mfitss.idletd.model.Map;

public class MapController {
    Map map;

    public MapController() {
        map = new Map();
    }

    public void DrawMap(Batch batch) {
        map.drawMap(batch);
    }
}

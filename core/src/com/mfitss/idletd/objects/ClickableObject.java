package com.mfitss.idletd.objects;

import com.mfitss.idletd.controllers.GameGestureListener;

public abstract class ClickableObject extends GameObject {

    protected void create(){
        GameGestureListener.addClickableObject(this);
    }

    public void onClick(){}
}

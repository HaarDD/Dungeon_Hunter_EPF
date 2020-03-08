package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.MainScreen.HPBarTexture;

public class HPBarAnimation {


    public TextureRegion[] HPBarRegions = new TextureRegion[5];
    public HPBarAnimation(){

            HPBarRegions[0]=new TextureRegion(HPBarTexture,0,0,64,12);
            HPBarRegions[1]=new TextureRegion(HPBarTexture,0,12,64,12);
            HPBarRegions[2]=new TextureRegion(HPBarTexture,0,24,64,12);
            HPBarRegions[3]=new TextureRegion(HPBarTexture,0,36,64,12);
            HPBarRegions[4]=new TextureRegion(HPBarTexture,0,48,64,12);


    }
}

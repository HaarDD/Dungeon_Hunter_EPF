package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.MainScreen.AnimationSpeed;
import static com.mygdx.game.MainScreen.bloodTexture;

public class BloodAnimation {

    public Animation bloodON,bloodOFF;



    public BloodAnimation(){

        Array<TextureRegion> frames = new Array<TextureRegion>();



        //BloodON
        for(int i = 1;i<6;i++){
            frames.add(new TextureRegion(bloodTexture,(i-1)*40,1,40,40));
        }
        bloodON = new Animation(0.03f*AnimationSpeed,frames);
        frames.clear();


        //BloodOFF
        frames.add(new TextureRegion(bloodTexture,(6-1)*40,1,40,40));
        bloodOFF = new Animation(0.03f*AnimationSpeed,frames);
        frames.clear();




    }
}

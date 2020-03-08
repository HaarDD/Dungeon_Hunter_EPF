package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.MainScreen.AnimationSpeed;
import static com.mygdx.game.MainScreen.swordTexture;

public class SwordAnimation {

    public Animation swordAttack, swordNothing;



    public SwordAnimation(){

        Array<TextureRegion> frames = new Array<TextureRegion>();



        //swordAttack
        for(int i = 1;i<20;i++){
            frames.add(new TextureRegion(swordTexture,(i-1)*64,1,64,128));
        }
        swordAttack = new Animation(0.03f*AnimationSpeed,frames);
        frames.clear();


        //swordNothing

        frames.add(new TextureRegion(swordTexture,0,1,64,128));
        swordNothing = new Animation(0.03f*AnimationSpeed,frames);
        frames.clear();






    }
}

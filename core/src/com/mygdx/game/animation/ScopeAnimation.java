package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.MainScreen.AnimationSpeed;
import static com.mygdx.game.MainScreen.scopeTexture;

public class ScopeAnimation {

    public Animation scopeAttack,scopeNothing;



    public ScopeAnimation(){

        Array<TextureRegion> frames = new Array<TextureRegion>();



        //ScopeAttack
        for(int i = 1;i<6;i++){
            frames.add(new TextureRegion(scopeTexture,(i-1)*32,1,32,32));
        }
        scopeAttack = new Animation(0.06f*AnimationSpeed,frames);
        frames.clear();


        //ScopeNothing
        frames.add(new TextureRegion(scopeTexture,(6-1)*32,1,32,32));
        scopeNothing = new Animation(0.06f*AnimationSpeed,frames);
        frames.clear();




    }
}

package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.MainScreen.AnimationSpeed;
import static com.mygdx.game.MainScreen.zombieTexture;

public class ZombieAnimation {

    public Animation zombieIdle, zombieLeft,zombieRight,zombieUp,zombieDown;



    public ZombieAnimation(){

        Array<TextureRegion> frames = new Array<TextureRegion>();



        //zombieIdle
        for(int i = 89;i<100;i++){
            frames.add(new TextureRegion(zombieTexture,(i-1)*40,1,40,40));
        }
        zombieIdle = new Animation(0.2f*AnimationSpeed,frames);
        frames.clear();


        //zombieLeft
        for(int i = 1;i<12;i++){
            frames.add(new TextureRegion(zombieTexture,(i-1)*40,1,40,40));
        }
        zombieLeft = new Animation(0.1f*AnimationSpeed,frames);
        frames.clear();
        //zombieRight
        for(int i = 12;i<23;i++){
            frames.add(new TextureRegion(zombieTexture,(i-1)*40,1,40,40));
        }
        zombieRight = new Animation(0.1f*AnimationSpeed,frames);
        frames.clear();


        //zombieUp
        for(int i = 23;i<34;i++){
            frames.add(new TextureRegion(zombieTexture,(i-1)*40,1,40,40));
        }

        zombieUp = new Animation(0.1f*AnimationSpeed,frames);
        frames.clear();

        //zombieDown
        for(int i = 34;i<45;i++){
            frames.add(new TextureRegion(zombieTexture,(i-1)*40,1,40,40));
        }

        zombieDown = new Animation(0.1f*AnimationSpeed,frames);
        frames.clear();





    }
}

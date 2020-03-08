package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import static com.mygdx.game.MainScreen.AnimationSpeed;
import static com.mygdx.game.MainScreen.playerTexture;

public class PlayerAnimation {

    public Animation playerIdle,playerRunLeft,playerRunRight,playerRunUp, playerRunDown, playerRunLeftDown,playerRunRightDown,playerRunLeftUp,playerRunRightUp;



    public PlayerAnimation(){
        Array<TextureRegion> frames = new Array<TextureRegion>();
        //runright
        for(int i = 12;i<23;i++){
            frames.add(new TextureRegion(playerTexture,(i-1)*40,1,40,40));
        }
        playerRunRight = new Animation(0.1f*AnimationSpeed,frames);
        frames.clear();
        //runleft
        for(int i = 1;i<12;i++){
            frames.add(new TextureRegion(playerTexture,(i-1)*40,1,40,40));
        }

        playerRunLeft = new Animation(0.1f*AnimationSpeed,frames);
        frames.clear();
        //runup
        for(int i = 23;i<34;i++){
            frames.add(new TextureRegion(playerTexture,(i-1)*40,1,40,40));
        }

        playerRunUp = new Animation(0.1f*AnimationSpeed,frames);
        frames.clear();
        //rundown
        for(int i = 34;i<45;i++){
            frames.add(new TextureRegion(playerTexture,(i-1)*40,1,40,40));
        }

        playerRunDown = new Animation(0.1f*AnimationSpeed,frames);
        frames.clear();
        //idle
        for(int i = 89;i<100;i++){
            frames.add(new TextureRegion(playerTexture,(i-1)*40,1,40,40));
        }

        playerIdle = new Animation(0.2f*AnimationSpeed,frames);
        frames.clear();
        //leftdown
        for(int i = 45;i<56;i++){
            frames.add(new TextureRegion(playerTexture,(i-1)*40,1,40,40));
        }

        playerRunLeftDown = new Animation(0.1f*AnimationSpeed,frames);
        frames.clear();
        //rightdown
        for(int i = 56;i<67;i++){
            frames.add(new TextureRegion(playerTexture,(i-1)*40,1,40,40));
        }

        playerRunRightDown = new Animation(0.1f*AnimationSpeed,frames);
        frames.clear();
        //leftup
        for(int i = 67;i<78;i++){
            frames.add(new TextureRegion(playerTexture,(i-1)*40,1,40,40));
        }

        playerRunLeftUp = new Animation(0.1f*AnimationSpeed,frames);
        frames.clear();
        //rightup
        for(int i = 78;i<89;i++){
            frames.add(new TextureRegion(playerTexture,(i-1)*40,1,40,40));
        }
        playerRunRightUp = new Animation(0.1f*AnimationSpeed,frames);
        frames.clear();



    }




}

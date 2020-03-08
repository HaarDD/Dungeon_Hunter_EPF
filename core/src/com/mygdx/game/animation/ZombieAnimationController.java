package com.mygdx.game.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ZombieAnimationController {
    public enum enemyStates {IDLE, RUNLEFT, RUNRIGHT, RUNUP, RUNDOWN}

    public float zombieStateTimer;
    public ZombieAnimation animation = new ZombieAnimation();


    public TextureRegion getFrameFiveStates(float velocityX, float velocityY) {
        enemyStates playerCurrentState = getEnemyStateFiveStates(velocityX, velocityY);

        zombieStateTimer += Gdx.graphics.getDeltaTime();

        TextureRegion region = new TextureRegion();
        switch (playerCurrentState) {
            case IDLE: {
                region = (TextureRegion) animation.zombieIdle.getKeyFrame(zombieStateTimer, true);
                break;
            }
            case RUNRIGHT: {
                region = (TextureRegion) animation.zombieRight.getKeyFrame(zombieStateTimer, true);
                break;
            }
            case RUNLEFT: {
                region = (TextureRegion) animation.zombieLeft.getKeyFrame(zombieStateTimer, true);
                break;
            }
            case RUNUP: {
                region = (TextureRegion) animation.zombieUp.getKeyFrame(zombieStateTimer, true);
                break;
            }
            case RUNDOWN: {
                region = (TextureRegion) animation.zombieDown.getKeyFrame(zombieStateTimer, true);
                break;
            }

        }
        return region;

    }


    public TextureRegion getIdleAnimation(){

        ZombieAnimation animation=new ZombieAnimation();
        zombieStateTimer+= Gdx.graphics.getDeltaTime();
        return (TextureRegion)animation.zombieIdle.getKeyFrame(zombieStateTimer,true);
    }


    public enemyStates getEnemyStateFiveStates(float velocityX, float velocityY) {
        enemyStates playerst = enemyStates.IDLE;

        float soothoshenieXY = velocityX / velocityY;
        float soothoshenieYX = velocityY / velocityX;
        if (velocityX > 0 && (soothoshenieXY < -1f || soothoshenieXY > 1f)) {
            return enemyStates.RUNRIGHT;
        }
        if (velocityX < 0 && (soothoshenieXY < -1f || soothoshenieXY > 1f)) {
            return enemyStates.RUNLEFT;
        }
        if (velocityY > 0 && (soothoshenieYX < -1f || soothoshenieYX > 1f)) {
            return enemyStates.RUNUP;
        }
        if (velocityY < 0 && (soothoshenieYX < -1f || soothoshenieYX > 1f)) {
            return enemyStates.RUNDOWN;
        }
        return playerst;
    }


}





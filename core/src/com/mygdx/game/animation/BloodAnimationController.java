package com.mygdx.game.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BloodAnimationController {
    public enum bloodStates {ON, OFF}

    public float bloodStateTimer;
    public BloodAnimation animation = new BloodAnimation();


    public boolean isAttack = false;

    public TextureRegion getFrameFiveStates() {
        bloodStates playerCurrentState = getEnemyStateFiveStates();

        bloodStateTimer += Gdx.graphics.getDeltaTime();

        TextureRegion region = new TextureRegion();
        switch (playerCurrentState) {
            case ON: {
                region = (TextureRegion) animation.bloodON.getKeyFrame(bloodStateTimer, true);
                break;
            }
            case OFF: {
                region = (TextureRegion) animation.bloodOFF.getKeyFrame(bloodStateTimer, true);
                break;
            }


        }
        return region;

    }


    public bloodStates getEnemyStateFiveStates() {
        bloodStates scopest = bloodStates.OFF;


        if (isAttack) {
            return bloodStates.ON;
        }
        return scopest;
    }


}





package com.mygdx.game.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SwordAnimationController {
    public enum scopeStates {ATTACK, NOTHING}

    public float swordStateTimer;
    public SwordAnimation animation = new SwordAnimation();


    public boolean isAttack = false;

    public TextureRegion getFrameFiveStates() {
        scopeStates playerCurrentState = getEnemyStateFiveStates();

        swordStateTimer += Gdx.graphics.getDeltaTime();

        TextureRegion region = new TextureRegion();
        switch (playerCurrentState) {
            case ATTACK: {
                region = (TextureRegion) animation.swordAttack.getKeyFrame(swordStateTimer, true);
                break;
            }
            case NOTHING: {
                region = (TextureRegion) animation.swordNothing.getKeyFrame(swordStateTimer, true);
                break;
            }


        }
        return region;

    }


    public scopeStates getEnemyStateFiveStates() {
        scopeStates scopest = scopeStates.NOTHING;


        if (isAttack) {
            return scopeStates.ATTACK;
        }
        return scopest;
    }


}





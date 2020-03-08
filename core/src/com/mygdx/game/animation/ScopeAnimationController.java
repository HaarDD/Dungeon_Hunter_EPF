package com.mygdx.game.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ScopeAnimationController {
    public enum scopeStates {ATTACK, NOTHING}

    public float scopeStateTimer;
    public ScopeAnimation animation = new ScopeAnimation();


    public boolean isAttack = false;

    public TextureRegion getFrameFiveStates() {
        scopeStates playerCurrentState = getEnemyStateFiveStates();

        scopeStateTimer += Gdx.graphics.getDeltaTime();

        TextureRegion region = new TextureRegion();
        switch (playerCurrentState) {
            case ATTACK: {
                region = (TextureRegion) animation.scopeAttack.getKeyFrame(scopeStateTimer, true);
                break;
            }
            case NOTHING: {
                region = (TextureRegion) animation.scopeNothing.getKeyFrame(scopeStateTimer, true);
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





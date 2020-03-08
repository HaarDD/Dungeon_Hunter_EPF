package com.mygdx.game.animation;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class HPBarController {

    HPBarAnimation hpBarAnimation = new HPBarAnimation();

    public TextureRegion getCurrentHPBar(int health) {

        TextureRegion region = new TextureRegion();
        switch (health) {
            case 1: {
                region = hpBarAnimation.HPBarRegions[0];
                break;
            }
            case 2: {
                region = hpBarAnimation.HPBarRegions[1];
                break;
            }
            case 3: {
                region = hpBarAnimation.HPBarRegions[2];
                break;
            }
            case 4: {
                region = hpBarAnimation.HPBarRegions[3];
                break;
            }
            case 5: {
                region = hpBarAnimation.HPBarRegions[4];
                break;
            }

        }
        return region;

    }



}





package com.mygdx.game.gameui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HPBar extends Actor {

TextureRegion hpbartextureregion = new TextureRegion();



    public void setTextureRegion(TextureRegion textureRegion){

        this.hpbartextureregion=textureRegion;


    }


    @Override
    public void draw(Batch batch, float parentAlpha){

        batch.draw(hpbartextureregion,this.getX(),this.getY(),this.getWidth(),this.getHeight());




    }

}

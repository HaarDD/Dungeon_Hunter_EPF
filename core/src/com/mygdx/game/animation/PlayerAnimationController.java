package com.mygdx.game.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.characters.Player;

public class PlayerAnimationController {

    public enum playerStates{IDLE,RUNLEFT,RUNRIGHT,RUNUP,RUNDOWN,RUNLEFTDOWN,RUNRIGHTDOWN,RUNLEFTUP,RUNRIGHTUP}
    public float playerStateTimer;
    public boolean start=false;
    TextureRegion region  = new TextureRegion();


    public TextureRegion getFrameNineStates(float velocityX, float velocityY, Player player, int i){

        if(i==0 || start==false){
            start=true;
            PlayerAnimation animation=new PlayerAnimation();
            playerStates playerCurrentState = getPlayerStateNineStates(velocityX,velocityY,player);
            playerStateTimer+= Gdx.graphics.getDeltaTime();
            switch(playerCurrentState){
                case IDLE:{
                    region = (TextureRegion)animation.playerIdle.getKeyFrame(playerStateTimer,true);

                    break;}
                case RUNRIGHT: {
                    region = (TextureRegion)animation.playerRunRight.getKeyFrame(playerStateTimer,true);
                    break;}
                case RUNLEFT:{
                    region = (TextureRegion)animation.playerRunLeft.getKeyFrame(playerStateTimer,true);
                    break;}
                case RUNUP:{
                    region = (TextureRegion)animation.playerRunUp.getKeyFrame(playerStateTimer,true);
                    break;}
                case RUNDOWN:{
                    region = (TextureRegion)animation.playerRunDown.getKeyFrame(playerStateTimer,true);
                    break;}
                case RUNLEFTDOWN:{
                    region = (TextureRegion)animation.playerRunLeftDown.getKeyFrame(playerStateTimer,true);
                    break;}
                case RUNRIGHTDOWN:{
                    region = (TextureRegion)animation.playerRunRightDown.getKeyFrame(playerStateTimer,true);
                    break;}
                case RUNLEFTUP:{
                    region = (TextureRegion)animation.playerRunLeftUp.getKeyFrame(playerStateTimer,true);
                    break;}
                case RUNRIGHTUP:{
                    region = (TextureRegion)animation.playerRunRightUp.getKeyFrame(playerStateTimer,true);
                    break;}
            }
        }

        return region;
    }



    public TextureRegion getIdleAnimation(){

        PlayerAnimation animation=new PlayerAnimation();
        playerStateTimer+= Gdx.graphics.getDeltaTime();
        return (TextureRegion)animation.playerIdle.getKeyFrame(playerStateTimer,true);
    }

    public playerStates getPlayerStateNineStates(float velocityX, float velocityY, Player player) {
        playerStates playerst=playerStates.IDLE;

        float ratioXY = velocityX/velocityY;
        float ratioYX = velocityY/velocityX;
        if (velocityX > 0 && (ratioXY<-1.7f || ratioXY>1.7f)) {
            return playerStates.RUNRIGHT;
        }
        if (velocityX < 0 && (ratioXY<-1.7f || ratioXY>1.7f)) {
            return playerStates.RUNLEFT;
        }
        if (velocityY > 0 && (ratioYX<-1.7f || ratioYX>1.7f)) {
            return playerStates.RUNUP;
        }
        if (velocityY < 0 && (ratioYX<-1.7f || ratioYX>1.7f)) {
            return playerStates.RUNDOWN;
        }
        if (velocityY < 0 && velocityX < 0 && ratioYX>-1.7f && ratioYX<1.7f) {
            return playerStates.RUNLEFTDOWN;
        }
        if (velocityY < 0 && velocityX > 0 && ratioYX>-1.7f && ratioYX<1.7f) {
            return playerStates.RUNRIGHTDOWN;
        }
        if (velocityY > 0 && velocityX < 0 && ratioYX>-1.7f && ratioYX<1.7f) {
            return playerStates.RUNLEFTUP;
        }
        if(velocityY > 0 && velocityX > 0 && ratioYX>-1.7f && ratioYX<1.7f){
            return playerStates.RUNRIGHTUP;
        }
        //if (player.body.getLinearVelocity().x == 0 && player.body.getLinearVelocity().y ==0) {
        //    playerst = playerStates.IDLE;
        //}
        return playerst;
    }

}

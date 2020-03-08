package com.mygdx.game.gameui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class JoystickArea extends Group {


    private JoystickInputListener joysticklistener;
    public Joystick joystick;

    private class AreaListener extends InputListener{

        private final Vector2 tmp = new Vector2();


        public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
            joystick.setCenterPosition(x,y);
            joystick.parentToLocalCoordinates(tmp.set(x,y));
            joysticklistener.touchDown(event,tmp.x,tmp.y,pointer,button);
            return true;
        }
        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
           joystick.parentToLocalCoordinates(tmp.set(x,y));
            joysticklistener.touchUp(event,tmp.x,tmp.y,pointer,button);
            joystick.setDefaultXY();
        }


        public void touchDragged (InputEvent event, float x, float y, int pointer) {
            joystick.parentToLocalCoordinates(tmp.set(x,y));
            joysticklistener.touchDragged(event,tmp.x,tmp.y,pointer);
        }
    }







    public JoystickArea(Texture circle, Texture cursorJoystick){
        joystick = new Joystick(circle, cursorJoystick);
        addActor(joystick);



        joysticklistener = new JoystickInputListener(joystick);
        addListener(new AreaListener());


        setX(0);
        setY(0);
        setWidth(Gdx.graphics.getWidth()*0.4f);
        setHeight(Gdx.graphics.getHeight()*0.7f);

        //debug();
    }

    public float getValueX(){
        return joystick.getValueX();
    }

    public float getValueY(){
        return joystick.getValueY();
    }

    public boolean isJoystickDown(){
        return joystick.isJoystickDown();
    }



}

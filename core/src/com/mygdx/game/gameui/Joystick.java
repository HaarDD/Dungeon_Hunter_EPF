package com.mygdx.game.gameui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

public class Joystick extends Actor {

    private Texture circle;
    private Texture cursorJoystick;
    private Boolean isJoystickDown = false;
    private float radius;

    private float inverseRad = 0;

    private static final float cursorRaduisOnRaduis = 0.2f;

    private float curX = 0;
    private float curY = 0;

    private float valueX = 0;
    private float valueY = 0;

    public void setCenterPosition(float x, float y){
        setPosition(x - radius, y - radius);
    }

    public float getValueX(){
        return valueX;
    }

    public float getValueY(){
        return valueY;
    }

    public boolean isJoystickDown(){
        return isJoystickDown;
    }


    private List<JoystickChangedListener> listeners = new ArrayList<JoystickChangedListener>();

    public void addJoystickChangedListener(JoystickChangedListener listener){
        listeners.add(listener);
    }
    public void removeJoystickChangedListener(JoystickChangedListener listener){
        listeners.remove(listener);
    }
    public void clearJoystickChangedListener(JoystickChangedListener listener){
        listeners.clear();
    }

    public void handleChangeListener(){
        for(JoystickChangedListener listener : listeners) {
            listener.changer(valueX,valueY);

        }
    }


    public Joystick(Texture circle, Texture cursorJoystick){
        this.circle = circle;
        this.cursorJoystick = cursorJoystick;
        setDefaultXY();
        setDefaultWH();
        addListener(new JoystickInputListener(this));

        //debug();
    }


    public void setTouched(){
        isJoystickDown = true;
    }
    public void setUnTouched(){
        isJoystickDown = false;

    }

    private void resetCut(){
        curX = 0;
        curY = 0;
    }


    public void setDefaultWH(){
        float defaultWH = Gdx.graphics.getWidth()*0.2f;
        setWidth(defaultWH);
        setHeight(defaultWH);
        radius = defaultWH/2;
        inverseRad = 1 / radius;
    }

    public void setDefaultXY(){
        setX(0.1f*Gdx.graphics.getWidth());
        setY(0.135f*(Gdx.graphics.getHeight()));
    }

    public void changeCursor(float x, float y){
        float dx = x - radius;
        float dy = y - radius;
        float length = (float)Math.sqrt(dx * dx + dy * dy);
        if(length < radius){

            this.curX = dx;
            this.curY = dy;
        } else {
            float k = radius/ length;
            this.curX = dx * k;
            this.curY = dy * k;
        }

        valueX = curX * inverseRad;
        valueY = curY * inverseRad;
    }

    public void setWidth(float width){
        super.setHeight(width);
        super.setWidth(width);
        radius = width/2;
        inverseRad = 1 / radius;
    }

    public void setHeight(float height){
        super.setHeight(height);
        super.setWidth(height);
        radius = height/2;
        inverseRad = 1 / radius;
    }


    @Override
    public Actor hit(float x, float y, boolean touchable){
        Actor actor = super.hit(x,y,touchable);
        if(actor == null) return null;
        else {
            float dx = x - radius;
            float dy = y - radius;

            return(dx*dx + dy*dy <= radius*radius) ? this:null;

        }
    }




    @Override
    public void draw(Batch batch, float parentAlpha){
        batch.draw(circle, this.getX(), this.getY(), this.getWidth(),this.getHeight());
        batch.setColor(1,0,0,1);
        if(isJoystickDown){
            batch.draw(cursorJoystick,
                    this.getX() + radius - (radius * cursorRaduisOnRaduis) + curX,
                    this.getY() + radius - (radius * cursorRaduisOnRaduis) + curY,
                    2 * radius * cursorRaduisOnRaduis,
                    2 * radius * cursorRaduisOnRaduis
                    );
        } else{
            batch.draw(cursorJoystick,
                    this.getX() + radius - (radius * cursorRaduisOnRaduis),
                    this.getY() + radius - (radius * cursorRaduisOnRaduis),
                    2 * radius * cursorRaduisOnRaduis,
                    2 * radius * cursorRaduisOnRaduis
            );

        }
    }
}

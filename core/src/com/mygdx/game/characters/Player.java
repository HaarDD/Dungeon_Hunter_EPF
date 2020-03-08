package com.mygdx.game.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.MainScreen;


public class Player {
    public Body body;
    public int health=5;
    public static final float RADIUS = 15f;




    public Player(float x, float y, World world) {

            body = createCircleBody((x*2)+1, (y*2)+1, 0.7f*RADIUS* MainScreen.UNIT_SCALE, world);
            body.setLinearDamping(0.2f);
    }

    public void setVelocity(float x, float y){
        body.setLinearVelocity(x,y);



    }




    public void draw(SpriteBatch batch, TextureRegion player_animation){
        float divR = RADIUS * MainScreen.UNIT_SCALE;
            batch.draw(player_animation,body.getPosition().x - divR-RADIUS*MainScreen.UNIT_SCALE,body.getPosition().y - 0.3f*divR-RADIUS*MainScreen.UNIT_SCALE, 4*RADIUS*MainScreen.UNIT_SCALE, 4*RADIUS*MainScreen.UNIT_SCALE);

    }

    public void applyForce(Vector2 v){
        body.applyForceToCenter(v,true);


    }



    public Body createCircleBody(float x,float y, float radius, World world){
        BodyDef def = new BodyDef();
        def.type = BodyDef.BodyType.DynamicBody;
        def.fixedRotation = true;
        Body body = world.createBody(def);

        CircleShape circle = new CircleShape();
        circle.setPosition(new Vector2(0,0));
        circle.setRadius(radius);
        Fixture f = body.createFixture(circle,0.2f);
        f.setUserData("player");
        circle.dispose();
        //f.setDensity(200000.0f);
        //MassData ms = new MassData();
       // ms.mass=200000f;
        //body.setMassData(ms);

        //groundFixture.friction=0f;
        body.setTransform(x,y,0);

        return body;





    }

}

package com.mygdx.game.physics;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {

    public Fixture fa;
    public Fixture fb;
    public String bodyA;
    public String bodyB;

    @Override
    public void beginContact(Contact contact) {
        fa = contact.getFixtureA();
        fb = contact.getFixtureB();

        if(fa == null || fb == null) return;

        if(fa.getUserData() == null|| fb.getUserData() == null) return;




        bodyA=fa.getUserData().toString();
        bodyB=fb.getUserData().toString();

        //if((fa.getUserData().toString().contains("line")&& fb.getUserData().toString().contains("wall")||(fb.getUserData().toString().contains("line")&& fa.getUserData().toString().contains("wall"))))System.out.println("стенка и линия соприкасаются");
        //System.out.println(fa.getUserData().toString());
        //System.out.println(fb.getUserData().toString());
        //System.out.println("Коллизия случилась");
    }

    @Override
    public void endContact(Contact contact) {
        fa = contact.getFixtureA();
        fb = contact.getFixtureB();

        if(fa == null || fb == null) return;

        if(fa.getUserData() == null|| fb.getUserData() == null) return;

            bodyA="null";
            bodyB="null";


       //System.out.println("Коллизия закончилась");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }


    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


}

package com.mygdx.game.physics;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static com.mygdx.game.MainScreen.map;
import static com.mygdx.game.MainScreen.world;



public class WallsCreate {


    public static final TiledMapTileLayer walls = (TiledMapTileLayer) map.getLayers().get("walls");


    BodyDef walldef = new BodyDef();
    public Body wall_rect;




    public void WallsCreate(){

        walldef.type = BodyDef.BodyType.StaticBody;
        walldef.fixedRotation = true;
        wall_rect = world.createBody(walldef);

        createWalls();




    }



    public void createWalls(){


        for(int i=0;i<walls.getWidth();i++){
            for(int l=0;l<walls.getHeight();l++){

                // S+=Integer.toString(i)+","+Integer.toString(l)+";";
                if(walls.getCell(l,i)!=null){createRect(l,i);}

            }
        }
    }





    public void createRect(float x,float y){

        PolygonShape wallshape = new PolygonShape();
        wallshape.setAsBox(1,1,new Vector2((x*2)+1,(y*2)+1),0);

        Fixture wallFixture = wall_rect.createFixture(wallshape,0);

        wallFixture.setUserData("wall");
        wallshape.dispose();

    }

    public int[][] pointsToGoFinder(){
        int[][] massiveOfWalls= new int[50][50];
        for(int i=0;i<50;i++){
            for(int l=0;l<50;l++){
                // S+=Integer.toString(i)+","+Integer.toString(l)+";";
                if(walls.getCell(l,i)!=null){
                    massiveOfWalls[l][i]=-1;


                }else{
                    massiveOfWalls[l][i]=99999;

                }
            }
        }

        return massiveOfWalls;
    }

}

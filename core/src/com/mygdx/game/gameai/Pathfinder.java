package com.mygdx.game.gameai;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.physics.WallsCreate;
import static com.mygdx.game.physics.WallsCreate.walls;


public class Pathfinder {

    WallsCreate wallsCreate=new WallsCreate();

    public int[][] massiveOfPointsToGo;

    public void Pathfinder(){




    }



    public void debugMas(int[][] massive){

        for(int i=0;i<50;i++){
            String vivod = "";
            for(int l=0;l<50;l++){


                if(massive[l][i]!=99999)vivod+=Integer.toString(massive[l][i])+"\t";
                else vivod+="-\t";
            }
            System.out.println(vivod);
        }


    }





    public Vector2 pathfinder(Vector2 start, Vector2 end){
        start = new Vector2((int)Math.floor((start.x)/2),(int)Math.floor((start.y)/2));
        end = new Vector2((int)Math.floor((end.x)/2),(int)Math.floor((end.y)/2));

        massiveOfPointsToGo=new int[50][50];
        massiveOfPointsToGo=wallsCreate.pointsToGoFinder();
        int[][] mas = massiveOfPointsToGo;
        mas[(int)start.x][(int)start.y]=0;
        Vector2[] ochered = new Vector2[3000];
        int size = 1;
        int schetchik = 0;
        ochered[0]=start;

        int obr_schetchik;

        while((mas[(int)end.x][(int)end.y]==99999) && (size>schetchik)){
            if(mas[(int)ochered[schetchik].x+1][(int)ochered[schetchik].y]!=-1 && (mas[(int)ochered[schetchik].x+1][(int)ochered[schetchik].y]>mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y]+1)){
                mas[(int)ochered[schetchik].x+1][(int)ochered[schetchik].y]=mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y]+1;
                ochered[size] = new Vector2(ochered[schetchik].x+1,ochered[schetchik].y);
                size++;
            }
            if(mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y-1]!=-1 && (mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y-1]>mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y]+1)){
                mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y-1]=mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y]+1;
                ochered[size] = new Vector2(ochered[schetchik].x,ochered[schetchik].y-1);
                size++;
            }
            if(mas[(int)ochered[schetchik].x-1][(int)ochered[schetchik].y]!=-1 && (mas[(int)ochered[schetchik].x-1][(int)ochered[schetchik].y]>mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y]+1)){
                mas[(int)ochered[schetchik].x-1][(int)ochered[schetchik].y]=mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y]+1;
                ochered[size] = new Vector2(ochered[schetchik].x-1,ochered[schetchik].y);
                size++;
            }
            if(mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y+1]!=-1 && (mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y+1]>mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y]+1)){
                mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y+1]=mas[(int)ochered[schetchik].x][(int)ochered[schetchik].y]+1;
                ochered[size] = new Vector2(ochered[schetchik].x,ochered[schetchik].y+1);
                size++;
            }

            schetchik++;



        }

        // debugMas(mas);


        Vector2[] put = new Vector2[mas[(int)end.x][(int)end.y]+1];
        put[mas[(int)end.x][(int)end.y]] = new Vector2((int)end.x,(int)end.y);
        obr_schetchik=mas[(int)end.x][(int)end.y];

        while (obr_schetchik!=0){

            if(put[obr_schetchik]!=null){


            if( (mas[(int)put[obr_schetchik].x+1][((int)put[obr_schetchik].y)])==obr_schetchik-1){
                put[obr_schetchik-1]=new Vector2((int)put[obr_schetchik].x+1,(int)put[obr_schetchik].y);
            }
            if( (mas[(int)put[obr_schetchik].x][((int)put[obr_schetchik].y-1)])==obr_schetchik-1){
                put[obr_schetchik-1]=new Vector2((int)put[obr_schetchik].x,(int)put[obr_schetchik].y-1);
            }
            if( (mas[(int)put[obr_schetchik].x-1][((int)put[obr_schetchik].y)])==obr_schetchik-1){
                put[obr_schetchik-1]=new Vector2((int)put[obr_schetchik].x-1,(int)put[obr_schetchik].y);
            }
            if( (mas[(int)put[obr_schetchik].x][((int)put[obr_schetchik].y+1)])==obr_schetchik-1){
                put[obr_schetchik-1]=new Vector2((int)put[obr_schetchik].x,(int)put[obr_schetchik].y+1);
            }


            }
            obr_schetchik--;


        }

        Vector2 toPlayerFirst = new Vector2();

        if(put.length!=1){
            toPlayerFirst=put[1];
        }
        else {
            toPlayerFirst=put[0];
        }


        return toPlayerFirst;



    }

    public boolean viewfinder(Vector2 start_before,Vector2 end_before){
        Vector2 start = new Vector2(start_before.x,start_before.y);
        Vector2 end = new Vector2(end_before.x,end_before.y);
        boolean isWall=false;
        float del=0.1f;
        float line = (float)Math.sqrt(Math.pow((double)(end.x-start.x),2)+Math.pow((double)(end.y-start.y),2));
        Vector2 where = new Vector2();
        where.x=start.x;
        where.y=start.y;
        for(float i=1;i<line/del;i++){
            for(float l=1;l<line/del;l++){


                if(start.y-end.y>0 && start.x-end.x<0){
                    if(end.x>where.x)where.x-=(start.x-end.x)*(del/line);
                    if(end.y<where.y)where.y-=(start.y-end.y)*(del/line);
                }
                if(start.y-end.y<0 && start.x-end.x<0){
                    if(end.x>where.x)where.x-=(start.x-end.x)*(del/line);
                    if(end.y>where.y)where.y-=(start.y-end.y)*(del/line);
                }
                if(start.y-end.y>0 && start.x-end.x>0){
                    if(end.x<where.x)where.x-=(start.x-end.x)*(del/line);
                    if(end.y<where.y)where.y-=(start.y-end.y)*(del/line);
                }
                if(start.y-end.y<0 && start.x-end.x>0){
                    if(end.x<where.x)where.x-=(start.x-end.x)*(del/line);
                    if(end.y>where.y)where.y-=(start.y-end.y)*(del/line);
                }

                if(walls.getCell((int)Math.floor(where.x)/2,(int)Math.floor(where.y)/2)!=null){
                    isWall=true;
                }
                //batch.draw(point,0.2f+where.x,0.2f+(where.y),0.4f,0.4f);
                //System.out.println((int)Math.floor(where.x)/2+"_"+(int)Math.floor(where.y)/2);
            }
        }
        return  isWall;
    }

}

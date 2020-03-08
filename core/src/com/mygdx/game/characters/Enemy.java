package com.mygdx.game.characters;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.MainScreen;
import com.mygdx.game.animation.BloodAnimationController;
import com.mygdx.game.animation.ScopeAnimationController;
import com.mygdx.game.animation.ZombieAnimationController;
import com.mygdx.game.gameai.Pathfinder;



import java.util.Comparator;

import static com.mygdx.game.MainScreen.scopeTexture;


public class Enemy {
    public Body body;

    public Pathfinder pathfinder=new Pathfinder();

    public long startGame=TimeUtils.millis();

    public long lastTime=0;
    public long lastAttack=0;
    public boolean isPlayerAttackNow=false;

    public int health=2;

    public float velocityX = 0;
    public float velocityY = 0;
    public boolean start=false;


    public Vector2 wherePlayer = new Vector2();

    public ScopeAnimationController scopeAnimationController = new ScopeAnimationController();
    public Sprite scopeSprite;


    public static final float RADIUS = 15f;
    ZombieAnimationController zombieAnimationController = new ZombieAnimationController();
    BloodAnimationController bloodAnimationController = new BloodAnimationController();







    public Enemy(float x, float y, World world) {

        scopeSprite = new Sprite(new TextureRegion(scopeTexture,1,1,32,32));
        scopeSprite.setSize(2,2);
        scopeSprite.setOrigin(1,0);

        body = createCircleBody((x*2), (y*2), 0.7f*RADIUS*MainScreen.UNIT_SCALE, world);
        body.setLinearDamping(0.2f);
    }



    public static final Comparator<Enemy> COMPARE_BY_Y = new Comparator<Enemy>() {
        public int compare(Enemy lhs, Enemy rhs) {
            return (int)(rhs.getPositionY()*100 - lhs.getPositionY()*100);
        }
    };



    public float getPositionY(){


        return body.getPosition().y;
    }


    public void attacked(SpriteBatch batch){
        if(isPlayerAttackNow){

            if(body.getPosition().sub(wherePlayer).len()<3.3f){

                if(TimeUtils.millis()-lastAttack>850){
                    bloodAnimationController.isAttack=true;
                    batch.draw(bloodAnimationController.getFrameFiveStates(),body.getPosition().x-1.5f,body.getPosition().y-1.5f,3,3);
                }


                if(TimeUtils.millis()-lastAttack>1000){
                    bloodAnimationController.bloodStateTimer=0;
                    bloodAnimationController.isAttack=false;
                    health-=1;
                    lastAttack=TimeUtils.millis();
                }
            }
            isPlayerAttackNow=false;
        }



    }



    public void setVelocity(float x, float y){
        body.setLinearVelocity(x,y);


    }


    public void draw(SpriteBatch batch, Player player){
        float divR = RADIUS * MainScreen.UNIT_SCALE;
            TextureRegion animation;
            animation = zombieAnimationController.getFrameFiveStates(velocityX,velocityY);

            if(startGame+5000<TimeUtils.millis())goToPlayer(player,8,1.5f,5);
            wherePlayer = player.body.getPosition();

            batch.draw(animation,body.getPosition().x - divR*2,body.getPosition().y - divR, 4*divR, 4*divR);
            if(startGame+5000<TimeUtils.millis())attacked(batch);
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

        Fixture f = body.createFixture(circle,1f);
        f.setUserData("ball");
        f.setRestitution(0.5f);
        circle.dispose();

        body.setTransform(x,y,0);

        return body;

    }

    public void goToPlayer(Player player,float rad_start,float rad_end, float speed){

        float where_x=0,where_y=0;
        rad_start*=2;
        rad_end*=2;
        //float dlina_vektora = (float)Math.sqrt(Math.pow((double)(bodywhere.body.getPosition().x-bodywho.body.getPosition().x),2)+Math.pow((double)(bodywhere.body.getPosition().y-bodywho.body.getPosition().y),2));
        Vector2 toPlayerFirst = new Vector2();
        toPlayerFirst = pathfinder.pathfinder(body.getPosition(),player.body.getPosition());
        if(toPlayerFirst==null)toPlayerFirst=new Vector2(body.getPosition());

        //System.out.println(viewfinder(bodywho.body.getPosition(),bodywhere.body.getPosition()));
        //System.out.println(kolvo_shagov);
        //System.out.println(kolvo_shagov_view);



        //if((dlina_vektora)<rad_start && (dlina_vektora)>rad_end){
        float dlina_vektora_tela = (float)Math.sqrt(Math.pow((double)(player.body.getPosition().x-body.getPosition().x),2)+Math.pow((double)(player.body.getPosition().y-body.getPosition().y),2));
        float dlina_vektora_tochka = (float)Math.sqrt(Math.pow((double)(toPlayerFirst.x*2-body.getPosition().x),2)+Math.pow((double)(toPlayerFirst.y*2-body.getPosition().y),2));
        if(dlina_vektora_tela>rad_end && dlina_vektora_tela<rad_start){


            if(pathfinder.viewfinder(body.getPosition(),player.body.getPosition())==false){



                where_x = ((player.body.getPosition().x)-(body.getPosition().x))*(speed/dlina_vektora_tela);
                where_y = ((player.body.getPosition().y)-(body.getPosition().y))*(speed/dlina_vektora_tela);
                setVelocity(5*((float)Math.floor(body.getPosition().x))/2,5*((float)Math.floor(body.getPosition().y))/2);
                setVelocity((where_x),(where_y));
            } else{


                where_x = ((toPlayerFirst.x*2)-(body.getPosition().x-0.75f))*(speed/dlina_vektora_tochka);
                where_y = ((toPlayerFirst.y*2)-(body.getPosition().y-0.75f))*(speed/dlina_vektora_tochka);
                setVelocity((where_x),(where_y));

            }
        }else setVelocity(0,0);





        velocityX=where_x;
        velocityY=where_y;

        //positionEnemy[number]= new Vector2(where_x,where_y);

    }


}

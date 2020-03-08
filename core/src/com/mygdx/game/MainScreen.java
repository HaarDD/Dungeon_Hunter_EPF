    package com.mygdx.game;

    import com.badlogic.gdx.Game;
    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.InputMultiplexer;
    import com.badlogic.gdx.InputProcessor;
    import com.badlogic.gdx.Screen;
    import com.badlogic.gdx.graphics.Color;
    import com.badlogic.gdx.graphics.GL20;
    import com.badlogic.gdx.graphics.OrthographicCamera;
    import com.badlogic.gdx.graphics.Texture;
    import com.badlogic.gdx.graphics.g2d.BitmapFont;
    import com.badlogic.gdx.graphics.g2d.Sprite;
    import com.badlogic.gdx.graphics.g2d.SpriteBatch;
    import com.badlogic.gdx.graphics.g2d.TextureAtlas;
    import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
    import com.badlogic.gdx.maps.tiled.TiledMap;
    import com.badlogic.gdx.maps.tiled.TiledMapTile;
    import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
    import com.badlogic.gdx.maps.tiled.TmxMapLoader;
    import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
    import com.badlogic.gdx.math.Vector2;
    import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
    import com.badlogic.gdx.physics.box2d.World;
    import com.badlogic.gdx.scenes.scene2d.InputEvent;
    import com.badlogic.gdx.scenes.scene2d.Stage;
    import com.badlogic.gdx.scenes.scene2d.ui.Button;
    import com.badlogic.gdx.scenes.scene2d.ui.Label;
    import com.badlogic.gdx.scenes.scene2d.ui.Skin;
    import com.badlogic.gdx.scenes.scene2d.ui.Table;
    import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
    import com.badlogic.gdx.utils.Align;
    import com.badlogic.gdx.utils.TimeUtils;
    import com.mygdx.game.animation.HPBarController;
    import com.mygdx.game.animation.PlayerAnimationController;
    import com.mygdx.game.animation.SwordAnimationController;
    import com.mygdx.game.characters.Enemy;
    import com.mygdx.game.characters.Player;
    import com.mygdx.game.gameui.HPBar;
    import com.mygdx.game.gameui.JoystickArea;
    import com.mygdx.game.physics.ContactListener;
    import com.mygdx.game.physics.WallsCreate;

    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.Random;

    public class MainScreen implements Screen, InputProcessor {

        private Game game;

        SpriteBatch batch;
        Random random = new Random();
        int count;

        public static final float UNIT_SCALE = 1f / 16f;
        public static final float AnimationSpeed=1f;

        ContactListener contactListener = new ContactListener();

        boolean pauseBool = false;

        ////ANIMATION////ANIMATION////ANIMATION////ANIMATION////ANIMATION////ANIMATION////ANIMATION////ANIMATION////ANIMATION////ANIMATION
        public static final Texture zombieTexture=new Texture("ZombieSprite.png");
        public static final Texture playerTexture=new Texture("PlayerSprite.png");
        public static final Texture swordTexture = new Texture("sword-sheet.png");
        public static final Texture scopeTexture = new Texture("scope.png");
        public static final Texture HPBarTexture = new Texture("hpbar.png");

        public static final Texture bloodTexture = new Texture("blood.png");

        public static final TiledMap map = new TmxMapLoader().load("Map.tmx");
        public static final World world = new World(new Vector2(),false);


        public HPBar hpBar = new HPBar();
        public  Sprite sword;

        public boolean swordStartAnimation = false;
        public long swordCurrentTime = 0;
        public long currentGameTime;
        public long sinceGameTime;
        SwordAnimationController swordAnimationController = new SwordAnimationController();
        PlayerAnimationController playerAnimationController = new PlayerAnimationController();
        HPBarController hpBarController = new HPBarController();


        ////ANIMATION////ANIMATION////ANIMATION////ANIMATION////ANIMATION////ANIMATION////ANIMATION////ANIMATION////ANIMATION////ANIMATION
        private OrthographicCamera camera=new OrthographicCamera();
        private OrthogonalTiledMapRenderer renderer;

        TiledMapTileLayer walls;
        TiledMapTileLayer walls_z;
        TiledMapTileLayer decoration;
        TiledMapTile skeleton;

        private Stage stage= new Stage();

        Player player;

        //СОЗДАНИЕ СТЕН

        WallsCreate wallsCreate = new WallsCreate();

        ArrayList<Enemy> enemy = new ArrayList<>();

        ///ТЕКСТ///ТЕКСТ///ТЕКСТ///ТЕКСТ///ТЕКСТ///ТЕКСТ///ТЕКСТ///ТЕКСТ

        public final Label.LabelStyle labelStyle = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("pixel.fnt"));

        public int countKilledZombies=0;

        public long startPlay;

        ///ТЕКСТ///ТЕКСТ///ТЕКСТ///ТЕКСТ///ТЕКСТ///ТЕКСТ///ТЕКСТ///ТЕКСТ

        public Label pauseLabel;
        public Label pauseLabel2;
        public Label timeStart;


        boolean playOnce;

        public long startGAME;

        private JoystickArea joystickArea;

        public float playerVelocityX;
        public float playerVelocityY;

        ///ТЕЛА///ТЕЛА///ТЕЛА///ТЕЛА///ТЕЛА
        Box2DDebugRenderer box2drenderer; //отладка тел



        ///ТЕЛА///ТЕЛА///ТЕЛА///ТЕЛА///ТЕЛА

        //ГЕЙМПЛЕЙ//ГЕЙМПЛЕЙ//ГЕЙМПЛЕЙ//ГЕЙМПЛЕЙ//ГЕЙМПЛЕЙ
        public boolean isAttack=false;
        public boolean playerAttackNow=false;
        //ГЕЙМПЛЕЙ//ГЕЙМПЛЕЙ//ГЕЙМПЛЕЙ//ГЕЙМПЛЕЙ//ГЕЙМПЛЕЙ

        public ShapeRenderer shapeRenderer = new ShapeRenderer();

        public MainScreen(Game game){

            this.game=game;


            batch = new SpriteBatch();
            renderer = new OrthogonalTiledMapRenderer(map, UNIT_SCALE,batch);
            camera.setToOrtho(false,Gdx.graphics.getWidth()/50,Gdx.graphics.getHeight()/50);


            startPlay=TimeUtils.millis();
            startGAME=TimeUtils.millis();
            sinceGameTime=TimeUtils.millis();

            //box2drenderer = new Box2DDebugRenderer(); //отладка тел
            sword = new Sprite(swordTexture);
            sword.setSize(4,8);
            sword.setOrigin(2,1.5f);
            sword.rotate(45f);

            world.setContactListener(contactListener);

            wallsCreate.WallsCreate();

            count=random.nextInt(5)+15;

            labelStyle.font = myFont;
            labelStyle.fontColor = Color.BLACK;


            pauseLabel = new Label("ПАУЗА",labelStyle);
            pauseLabel.setFontScale(7);
            pauseLabel.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            pauseLabel.setPosition(0,0);
            pauseLabel.setAlignment(Align.center);

            pauseLabel2 = new Label("\n\n\n\n\n\n\n\nпродумывать движения нельзя ;)",labelStyle);
            pauseLabel2.setFontScale(2);
            pauseLabel2.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
            pauseLabel2.setPosition(0,0);
            pauseLabel2.setAlignment(Align.center);

            timeStart = new Label("time",labelStyle);
            timeStart.setFontScale(4);
            timeStart.setSize(Gdx.graphics.getWidth()*0.5f,Gdx.graphics.getHeight()*0.2f);
            timeStart.setPosition((int)(Gdx.graphics.getWidth()-timeStart.getWidth())/2,Gdx.graphics.getHeight()*0.75f);
            timeStart.setAlignment(Align.center);


            walls = (TiledMapTileLayer) map.getLayers().get("walls");
            walls_z = (TiledMapTileLayer) map.getLayers().get("walls_z");
            decoration = (TiledMapTileLayer) map.getLayers().get("decoration");
            skeleton = decoration.getCell(1,47).getTile();


            player = new Player(26-0.5f,25-0.5f,world);
            randomGenerationEnemy();


            Skin skin = new Skin();


            TextureAtlas playButtonsAtlas = new TextureAtlas("attackButton.pack");

            TextureAtlas pauseAtlas = new TextureAtlas("pause.pack");

            skin.addRegions(playButtonsAtlas);
            skin.addRegions(pauseAtlas);
            skin.load(Gdx.files.internal("skin.json"));

            final Table playButtons = new Table();

            hpBar.setPosition(0,Gdx.graphics.getHeight()-120);
            hpBar.setWidth(640);
            hpBar.setHeight(120);

            final Button pauseButton = new Button(skin,"pause");

            pauseButton.setTransform(true);
            pauseButton.setWidth(Gdx.graphics.getHeight()*0.1f);
            pauseButton.setHeight(Gdx.graphics.getHeight()*0.1f);
            pauseButton.setX(Gdx.graphics.getWidth()-(Gdx.graphics.getWidth()*0.1f));
            pauseButton.setY(Gdx.graphics.getHeight()-(Gdx.graphics.getWidth()*0.1f));



            pauseButton.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent e, float x, float y){


                    if (pauseBool == false) {
                        stage.clear();

                        playOnce=true;
                        stage.addActor(pauseLabel);
                        stage.addActor(pauseLabel2);
                        stage.addActor(pauseButton);
                        pauseButton.setChecked(true);
                        pauseBool=true;

                    }else{
                        stage.clear();

                        stage.addActor(timeStart);
                        stage.addActor(joystickArea);
                        stage.addActor(playButtons);
                        stage.addActor(hpBar);
                        stage.addActor(pauseButton);
                        pauseButton.setChecked(false);
                        pauseBool=false;
                    }


                }



            });


            Button attackButton = new Button(skin,"attack");

            attackButton.setTransform(true);
            attackButton.setWidth(Gdx.graphics.getHeight()*0.1f);
            attackButton.setHeight(Gdx.graphics.getHeight()*0.1f);

            attackButton.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent e, float x, float y){
                    isAttack=true;
                }



            });


            //Создание джойстика
            Texture circle = new Texture("skinjoysticknew.png");//область джойстика
            Texture cursorJoystick = new Texture("skinknobnew.png");//кнопка
            joystickArea = new JoystickArea(circle, cursorJoystick);//созагрузка области и кнопки

            //ДОБАВЛЕНИЕ АКТЁРОВ К СЦЕНЕ

            stage.addActor(timeStart);
            stage.addActor(joystickArea);
            stage.addActor(playButtons);
            stage.addActor(hpBar);
            stage.addActor(pauseButton);

            playButtons.setX(0.8f*Gdx.graphics.getWidth());
            playButtons.setY(0.3f*Gdx.graphics.getHeight());


            playButtons.add(attackButton).width(Gdx.graphics.getWidth()*0.2f).height(Gdx.graphics.getWidth()*0.2f);

            InputMultiplexer multiplexer = new InputMultiplexer();
            multiplexer.addProcessor(stage);

            multiplexer.addProcessor(this);

            Gdx.input.setInputProcessor(multiplexer);

        }


        @Override
        public void show() { }


        public void randomGenerationEnemy(){
            for(int i=0;i<count;i++){
                int randomX=random.nextInt(46)+2,randomY=random.nextInt(46)+2;
                while(walls.getCell(randomX,randomY)!=null || ((randomX>18 && randomX<32) && (randomY>18 && randomY<32))){
                    randomX=random.nextInt(46)+2;
                    randomY=random.nextInt(46)+2;

                }
                enemy.add(new Enemy(randomX-0.5f+1,randomY+0.5f,world));

            }
        }


        public void rotateSword(Enemy nearestenemy){
            sword.setRotation(player.body.getPosition().sub(nearestenemy.body.getPosition()).angle()+90);
            if(sword.getRotation()>360){
                sword.setRotation(sword.getRotation()-360);
            }
            if(sword.getRotation()<360){
                sword.setRotation(sword.getRotation()+360);
            }
            if(isAttack==true){
                if(TimeUtils.millis()>swordCurrentTime+1001 || swordStartAnimation==false){
                    swordStartAnimation=true;
                    swordAnimationController.swordStateTimer=0;
                    swordCurrentTime=TimeUtils.millis();
                }
                isAttack=false;
            }else{
                if(TimeUtils.millis()>swordCurrentTime+570){
                    playerAttackNow=false;
                    swordAnimationController.isAttack=false;
                    sword.setRegion(swordAnimationController.getFrameFiveStates());
                }else{
                    playerAttackNow=true;
                    swordAnimationController.isAttack=true;
                    sword.setRegion(swordAnimationController.getFrameFiveStates());
                }
            }
        }


        public void scopeController(SpriteBatch batch,int i){
            if((float)Math.sqrt(Math.pow((double)(player.body.getPosition().x-enemy.get(i).body.getPosition().x),2)+Math.pow((double)(player.body.getPosition().y-enemy.get(i).body.getPosition().y),2))<3){
                if (TimeUtils.millis() > enemy.get(i).lastTime || enemy.get(i).start == false){
                    enemy.get(i).scopeSprite.setPosition(enemy.get(i).body.getPosition().x-1,enemy.get(i).body.getPosition().y);
                    enemy.get(i).scopeAnimationController.scopeStateTimer=0;
                    enemy.get(i).scopeAnimationController.isAttack=true;
                    enemy.get(i).scopeSprite.setRegion(enemy.get(i).scopeAnimationController.getFrameFiveStates());
                    enemy.get(i).scopeSprite.setRotation((enemy.get(i).body.getPosition().sub(player.body.getPosition()).angle()-270));
                    while(enemy.get(i).scopeSprite.getRotation()>360)enemy.get(i).scopeSprite.setRotation(enemy.get(i).scopeSprite.getRotation()-360);
                    while(enemy.get(i).scopeSprite.getRotation()<0)enemy.get(i).scopeSprite.setRotation(enemy.get(i).scopeSprite.getRotation()+360);
                    enemy.get(i).scopeSprite.draw(batch);
                    if(TimeUtils.millis()>startGAME+5000) player.health-=1;//ИГРОК ПОЛУЧАЕТ УРОН ТУТ
                    enemy.get(i).lastTime = TimeUtils.millis() + 2000;
                    enemy.get(i).start = true;
                } else {
                    if (enemy.get(i).lastTime-TimeUtils.millis()>1500){
                        enemy.get(i).scopeSprite.setRegion(enemy.get(i).scopeAnimationController.getFrameFiveStates());
                        enemy.get(i).scopeSprite.draw(batch);
                    }
                }
            }
        }



        public Enemy nearestEnemy(){
            float line=10000;
            Enemy nearest = enemy.get(0);
            for(int i=0;i<enemy.size();i++){
                if(line>enemy.get(i).body.getPosition().sub(player.body.getPosition()).len()){
                    line=enemy.get(i).body.getPosition().sub(player.body.getPosition()).len();
                    nearest=enemy.get(i);
                }
            }
            return nearest;
        }


        public void sortEnemies(){
            Collections.sort(enemy, Enemy.COMPARE_BY_Y);
        }

        public void renderPlayer(int i){

            if(sword.getRotation()<90 || sword.getRotation()>270){
                sword.draw(batch);
                player.draw(batch,playerAnimationController.getFrameNineStates(playerVelocityX,playerVelocityY,player,i));
            }else{
                player.draw(batch,playerAnimationController.getFrameNineStates(playerVelocityX,playerVelocityY,player,i));
                sword.draw(batch);
            }
        }

        public void renderEnemies(int i){
            if(enemy.get(i).scopeSprite.getRotation()<90 || enemy.get(i).scopeSprite.getRotation()>270){
                scopeController(batch,i);
                enemy.get(i).draw(batch,player);
            }else{
                enemy.get(i).draw(batch,player);
                scopeController(batch,i);
            }



        }

        public void PLAY(){
            Gdx.gl.glClearColor(0,0,0,1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            currentGameTime+=TimeUtils.timeSinceMillis(sinceGameTime);
            sinceGameTime=TimeUtils.millis();
            if(startGAME+5000>currentGameTime+startGAME){

                timeStart.setText("Начало через: "+(int)Math.floor(5000-currentGameTime)/1000);
            }else timeStart.setText("");

            if((TimeUtils.millis()>startGAME+5000) && joystickArea.isJoystickDown()){
                playerVelocityX = joystickArea.getValueX();
                playerVelocityY = joystickArea.getValueY();
                player.setVelocity(playerVelocityX*10,playerVelocityY*10);
            }else{playerVelocityX=0;
                playerVelocityY=0;
                player.setVelocity(0,0);
            }

            camera.position.x = player.body.getPosition().x; //Слежение за шаром
            camera.position.y = player.body.getPosition().y; //Слежение за шаром
            camera.update();
            renderer.setView(camera);
            renderer.render();
            batch.setProjectionMatrix(camera.combined);

            nearestEnemy().isPlayerAttackNow=playerAttackNow;

            sortEnemies();
            rotateSword(nearestEnemy());
            sword.setPosition(player.body.getPosition().x-2,player.body.getPosition().y-1);
            batch.begin();

            //box2drenderer.render(world,camera.combined);//ОТЛАДКА ТЕЛ//ОТЛАДКА ТЕЛ//ОТЛАДКА ТЕЛ//ОТЛАДКА ТЕЛ//ОТЛАДКА ТЕЛ//ОТЛАДКА ТЕЛ

            int sch=0;
            for(int i=0;i<enemy.size();i++){
                if(enemy.get(i).body.getPosition().y<player.body.getPosition().y+0.3f){
                    sch++;
                }
            }

            if (sch==enemy.size())renderPlayer(0);
            for(int i=0;i<enemy.size();i++){

                if(enemy.get(i).body.getPosition().y<player.body.getPosition().y){
                    renderEnemies(i);
                }else{
                    renderEnemies(i);
                    renderPlayer(i);
                }
                if(enemy.get(i).health<1){
                    decoration.getCell((int)Math.floor(enemy.get(i).body.getPosition().x/2),(int)Math.floor(enemy.get(i).body.getPosition().y/2)).setTile(skeleton);
                    world.destroyBody(enemy.get(i).body);
                    enemy.remove(i);

                    count-=1;
                    countKilledZombies+=1;
                }
            }
            if(enemy.size()==0 || player.health<1){


                for (int i=0;i<enemy.size();i++){
                    world.destroyBody(enemy.get(i).body);

                }
                world.destroyBody(player.body);
                world.destroyBody(wallsCreate.wall_rect);
                if(player.health<0)player.health=0;
                this.game.setScreen(new EndScreen(this.game,countKilledZombies,player.health,TimeUtils.timeSinceMillis(startPlay)));
            }
            //renderer.renderTileLayer(decoration);
            renderer.renderTileLayer(walls_z);

            if(player.health>0)hpBar.setTextureRegion(hpBarController.getCurrentHPBar(player.health));

            batch.end();

        }


        @Override
        public void render(float delta) {

            if(!pauseBool){
                PLAY();
                world.step(delta,4,4);

            }else{
                Gdx.gl.glClearColor(1,1,1,1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            }

            stage.act(delta);
            stage.draw();
        }


        @Override
        public void resize(int width, int height) {
            stage.getViewport().update(width,height,true);
        }

        @Override
        public void pause() {

        }

        @Override
        public void resume() {

        }

        @Override
        public void hide() {

        }

        @Override
        public void dispose() {
            game.dispose();
            map.dispose();
            world.dispose();
            myFont.dispose();
            zombieTexture.dispose();
            playerTexture.dispose();
            swordTexture.dispose();
            scopeTexture.dispose();
            HPBarTexture.dispose();
            bloodTexture.dispose();
            batch.dispose();
            shapeRenderer.dispose();
            renderer.dispose();
            stage.dispose();
        }

        @Override
        public boolean keyDown(int keycode) {
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }

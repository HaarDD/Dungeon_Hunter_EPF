package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.animation.PlayerAnimationController;
import com.mygdx.game.animation.ZombieAnimationController;

public class EndScreen implements Screen, InputProcessor {


    public Game game;
    public Label helloLabel;
    public Label helloLabel2;

    Skin skin = new Skin();

    final static public Texture background = new Texture("background.png");
    SpriteBatch batch = new SpriteBatch();

    PlayerAnimationController playerAnimationController=new PlayerAnimationController();
    ZombieAnimationController zombieAnimationController=new ZombieAnimationController();

    public final Label.LabelStyle labelStyle = new Label.LabelStyle();
    BitmapFont myFont = new BitmapFont(Gdx.files.internal("pixel.fnt"));

    private Stage startStage = new Stage();

    public EndScreen(final Game game, int countKilledZombies, int countCurrentHP, long timePlay) {


        this.game = game;
        labelStyle.font = myFont;
        labelStyle.fontColor = Color.BLACK;

        helloLabel = new Label("Ещё раз?", labelStyle);
        helloLabel.setFontScale(5);
        helloLabel.setSize(Gdx.graphics.getWidth(), 200);
        helloLabel.setPosition(0, Gdx.graphics.getWidth() * 0.15f);
        helloLabel.setAlignment(Align.center);

        helloLabel2 = new Label("\n\nУбито: "+ Integer.toString(countKilledZombies)+"\nЗдоровье: "+Integer.toString(countCurrentHP)+"\nВремя игры: "+Integer.toString((int)Math.floor(timePlay/1000))+" сек.", labelStyle);
        helloLabel2.setFontScale(5);
        helloLabel2.setSize(Gdx.graphics.getWidth(), 200);
        helloLabel2.setPosition(Gdx.graphics.getHeight()*0.1f, Gdx.graphics.getHeight() * 0.8f);
        helloLabel2.setAlignment(Align.left);


        TextureAtlas atlas = new TextureAtlas("buttonstart.pack");
        skin.addRegions(atlas);
        skin.load(Gdx.files.internal("buttonstart.json"));

        TextButton.TextButtonStyle textButtonStyle=new TextButton.TextButtonStyle();
        textButtonStyle.font=myFont;
        TextButton helloButton = new TextButton("ИГРАТЬ",skin,"start");

        helloButton.getLabel().setFontScale(3);

        helloButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){

                game.setScreen(new MainScreen(game));
            }
        });




        helloButton.setWidth((int)(Gdx.graphics.getWidth()*0.33f));
        helloButton.setHeight((int)(helloButton.getWidth()/4));
        helloButton.setX((int)(Gdx.graphics.getWidth()-helloButton.getWidth())/2);
        helloButton.setY((int)(Gdx.graphics.getHeight()*0.2f));

        helloButton.setX((int) (Gdx.graphics.getWidth() - helloButton.getWidth()) / 2);
        helloButton.setY((int) (Gdx.graphics.getHeight() * 0.1f));

        startStage.addActor(helloLabel);
        startStage.addActor(helloLabel2);
        startStage.addActor(helloButton);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(startStage);
        multiplexer.addProcessor(this);
        Gdx.input.setInputProcessor(multiplexer);
    }







    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(playerAnimationController.getIdleAnimation(),-Gdx.graphics.getWidth()*0.05f,-Gdx.graphics.getWidth()*0.02f,600,600);
        batch.draw(zombieAnimationController.getIdleAnimation(),Gdx.graphics.getWidth()*1.05f-600,-Gdx.graphics.getWidth()*0.02f,600,600);
        batch.end();
        startStage.act(delta);
        startStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        startStage.getViewport().update(width,height,true);
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
        startStage.dispose();
        game.dispose();
        skin.dispose();
        myFont.dispose();

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

package com.mygdx.game;

import com.badlogic.gdx.Game;





public class HaarDD extends Game {

	@Override
	public void create () {
	setScreen(new StartScreen(this));
	}

	@Override
	public void render () {


	super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
	}
}
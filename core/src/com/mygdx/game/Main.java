package com.mygdx.game;

import com.badlogic.gdx.Game;


/**
 * Manages the game logic.
 *
 * @author Yifei, Steven
 * @version 2024
 */

public class Main extends Game {
	/**
	 * Sets the initial screen of the game to a {@link StartScreen} instance.
	 */
	@Override
	public void create() {
		this.setScreen(new StartScreen(this));
	}
}

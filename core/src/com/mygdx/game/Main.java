package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * Manages the game logic.
 *
 * @author Yifei, Steven
 * @version 2024
 */

public class Main extends Game {
	@Override
	public void create() {
		this.setScreen(new StartScreen(this));
	}
}

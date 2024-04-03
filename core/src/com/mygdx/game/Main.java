package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture imagePlayer;
	Texture imageLaser;
	Texture imageTieFighter;
	Player player;
	TieFighter[] tieFighter;
	int widthCount = 4;
	int heightCount = 3;
	int spacingTieFighter = 50;
	int minXTieFighter;
	int minYTieFighter;
	int maxXTieFighter;
	int maxYTieFighter;
	int directionTieFighter = 1;
	float speedTieFighter = 100;

	Vector2 offsetTieFighter;
}

package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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

	@Override
	public void create () {
		offsetTieFighter = Vector2.Zero;
		batch = new SpriteBatch();
		imagePlayer = new Texture("player.png");
		imageLaser = new Texture("bullet.png");
		imageTieFighter = new Texture("alien.png");
		player = new Player(imagePlayer, imageLaser);
		tieFighter = new TieFighter[widthCount * heightCount];
		int i = 0;
		for (int y = 0; y < heightCount; y++) {
			for (int x = 0; x < widthCount; x++) {
				Vector2 position = new Vector2(x * spacingTieFighter, y * spacingTieFighter);
				position.x += (float) Gdx.graphics.getWidth() / 2;
				position.y += Gdx.graphics.getHeight();
				position.x -= ((float) widthCount / 2) * spacingTieFighter;
				position.y -= (heightCount) * spacingTieFighter;

				tieFighter[i] = new TieFighter(position, imageTieFighter, Color.GREEN);
				i++;
			}
		}
	}
}

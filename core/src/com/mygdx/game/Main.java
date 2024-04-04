package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
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
	int amountTieFighterAlive = 0;
	int minXTieFighter;
	int minYTieFighter;
	int maxXTieFighter;
	int maxYTieFighter;
	int directionTieFighter = 1;
	float speedTieFighter = 100;

	Vector2 offsetTieFighter;

	/**
	 * Initializes the game resources and objects.
	 */
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

	/**
	 * Updates and renders the game state.
	 */
	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		player.Draw(batch);


		int alien_length = tieFighter.length;
        for (TieFighter fighter : tieFighter) {
            if (fighter.Alive) {
                if (player.spriteBullet.getBoundingRectangle().overlaps(fighter.sprite.getBoundingRectangle())) {
                    player.positionBullet.y = 1000;
                    fighter.Alive = false;
                    break;
                }
            }
        }


		minXTieFighter = 10000;
		minYTieFighter = 10000;
		maxXTieFighter = 0;
		maxYTieFighter = 0;

		amountTieFighterAlive = 0;
		for (int i = 0; i < alien_length; i++) {
			if (tieFighter[i].Alive) {
				int IndexX = i % widthCount;
				int IndexY = i / widthCount;

				if (IndexX > maxXTieFighter) {
					maxXTieFighter = IndexX;
				}
				if (IndexX < minXTieFighter) {
					minXTieFighter = IndexX;
				}
				if (IndexY > maxYTieFighter) {
					maxYTieFighter = IndexY;
				}
				if (IndexY < minYTieFighter) {
					minYTieFighter = IndexY;
				}
				amountTieFighterAlive++;
			}
		}

		if (amountTieFighterAlive == 0) {
			speedTieFighter += 20;
			for (int i = 0; i < alien_length; i++) {
				tieFighter[i].Alive = true;
			}
			offsetTieFighter = new Vector2(0,0);
			batch.end();
			return;
		}

		offsetTieFighter.x += directionTieFighter * deltaTime * speedTieFighter;
		if (tieFighter[maxXTieFighter].position.x >= Gdx.graphics.getWidth()) {
			directionTieFighter = -1;

			offsetTieFighter.y -= tieFighter[0].sprite.getHeight() * tieFighter[0].sprite.getScaleY() * 0.25f;
		}
		if (tieFighter[minXTieFighter].position.x <= 0) {
			directionTieFighter = 1;
			offsetTieFighter.y -= tieFighter[0].sprite.getHeight() * tieFighter[0].sprite.getScaleY() * 0.25f;
		}

		if (tieFighter[minYTieFighter].position.y <= 0) {
			Gdx.app.exit();
		}

		for (int i = 0; i < alien_length; i++) {
			tieFighter[i].position = new Vector2 (tieFighter[i].position_initial.x + offsetTieFighter.x, tieFighter[i].position_initial.y + offsetTieFighter.y);
			if (tieFighter[i].Alive) {
				tieFighter[i].Draw(batch);

				if (tieFighter[i].sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())) {
					Gdx.app.exit();
				}
			}
		}
		batch.end();
    }

	/**
	 * Releases resources when the application is destroyed.
	 */
	@Override
	public void dispose () {
		batch.dispose();
		imagePlayer.dispose();
	}
}

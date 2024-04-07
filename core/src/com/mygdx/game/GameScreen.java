package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Represents Game Screen.
 *
 * @author Yifei, Steven
 * @version 2024
 */

public class GameScreen extends ScreenAdapter {
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
    int score = 0;
    float speedTieFighter = 100;
    private final BitmapFont font;
    private final Game game;

    Vector2 offsetTieFighter;

    public GameScreen(Game game) {
        this.game = game;
        font = new BitmapFont();
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

    public int getScore() {
        return this.score;
    }

    @Override
    public void render (float delta) {
        int count = 0;
        try {
            float deltaTime = Gdx.graphics.getDeltaTime();
            ScreenUtils.clear(0, 0, 0, 1);
            batch.begin();
            font.draw(batch, "Score " + this.score, Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f - 50);
            player.Draw(batch);


            int tieFighterLength = tieFighter.length;
            for (TieFighter fighter : tieFighter) {
                if (fighter.Alive) {
                    if (player.spriteBullet.getBoundingRectangle().overlaps(fighter.sprite.getBoundingRectangle())) {
                        player.positionBullet.y = 1000;
                        fighter.Alive = false;
                        this.score += 100;
                        font.draw(batch, "Score " + this.score, Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f - 50);
                        break;
                    }
                }
            }


            minXTieFighter = 10000;
            minYTieFighter = 10000;
            maxXTieFighter = 0;
            maxYTieFighter = 0;

            amountTieFighterAlive = 0;
            for (int i = 0; i < tieFighterLength; i++) {
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
                count ++;
                if (count == 1) {
                    this.game.setScreen(new EndScreen(game, this.score));
                }
                speedTieFighter += 20;
                for (int i = 0; i < tieFighterLength; i++) {
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
                this.game.setScreen(new EndScreen(game, this.score));
            }

            for (int i = 0; i < tieFighterLength; i++) {
                tieFighter[i].position = new Vector2 (tieFighter[i].position_initial.x + offsetTieFighter.x, tieFighter[i].position_initial.y + offsetTieFighter.y);
                if (tieFighter[i].Alive) {
                    tieFighter[i].Draw(batch);

                    if (tieFighter[i].sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())) {
                        this.game.setScreen(new EndScreen(game, this.score));
                    }
                }
            }
            batch.end();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        imagePlayer.dispose();
    }
}

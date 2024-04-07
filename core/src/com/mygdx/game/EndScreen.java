package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;

/**
 * Represents End Screen.
 *
 * @author Yifei, Steven
 * @version 2024
 */

public class EndScreen extends ScreenAdapter {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final int score;

    public EndScreen(Game game, int score) {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.score = score;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Game Finished! Press ESC to exit.", Gdx.graphics.getWidth() / 2f - 90, Gdx.graphics.getHeight() / 2f + 50);
        font.draw(batch, "Score: " + this.score, Gdx.graphics.getWidth() / 2f - 90, Gdx.graphics.getHeight() / 2f - 50);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
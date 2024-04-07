package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;

/**
 * Represents the screen displayed after the game ends.
 *
 * @author Yifei, Steven
 * @version 2024
 */

public class EndScreen extends ScreenAdapter {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final int score;

    /**
     * Constructs a new EndScreen instance with the provided game reference and player's score.
     *
     * @param score The player's final score achieved during the game.
     */
    public EndScreen(int score) {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.score = score;
    }

/**
 * Renders the game screen visuals, handles player input, updates game state, and checks for win/lose conditions.
 *
 * @param delta The time elapsed since the last frame, in seconds.
 */
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

/**
 * Disposes of resources used by the EndScreen object.
 */
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
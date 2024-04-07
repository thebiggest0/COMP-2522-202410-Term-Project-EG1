package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;

/**
 * Represents Start Screen of the game.
 *
 * @author Yifei, Steven
 * @version 2024
 */

public class StartScreen extends ScreenAdapter {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final Game game;

    /**
     * Constructs a new StartScreen instance associated with the provided game instance.
     *
     * @param game The main game instance this StartScreen belongs to.
     */
    public StartScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    /**
     * Renders the Start Screen's visuals and handles user input for transitioning to the game screen.
     *
     * @param delta The time elapsed since the last frame, in seconds.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Movement: A = Right, D = Left, SPACE = Shoot", Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f + 50);
        font.draw(batch, "Click Enter to start game", Gdx.graphics.getWidth() / 2f - 75, Gdx.graphics.getHeight() / 2f - 50);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            this.game.setScreen(new GameScreen(game));
        }
    }

    /**
     * Frees resources used by the StartScreen instance.
     */
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

/**
 * Represents Start Screen.
 *
 * @author Yifei, Steven
 * @version 2024
 */

public class StartScreen extends ScreenAdapter {
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final Game game;

    public StartScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

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

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
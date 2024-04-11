package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.audio.Sound;

/**
 * Represents the player's ship in the game world.
 *
 * @author Yifei, Steven
 * @version 2024
 */

public class Player {
    /**
     * The current position of the player's ship in the game world.
     */
    public Vector2 position;

    /**
     * The current position of the player's bullet, initially off-screen.
     */
    public Vector2 positionBullet;

    /**
     * The visual representation of the player's ship as a Sprite object.
     */
    public Sprite sprite;

    /**
     * The visual representation of the player's bullet as a Sprite object.
     */
    public Sprite spriteBullet;

    /**
     * The movement speed of the player's ship in units per second.
     */
    public float speed = 500;

    /**
     * The movement speed of the player's bullet in units per second.
     */
    public float speedBullet = 1000;
    private Sound firingSound;

    /**
     * Constructs a new Player instance with the specified textures for the ship and bullet.
     *
     * @param image The Texture object representing the player's ship's appearance.
     * @param imageBullet The Texture object representing the player's bullet's appearance.
     */
    public Player(Texture image, Texture imageBullet) {
        sprite = new Sprite(image);
        spriteBullet = new Sprite(imageBullet);
        sprite.setScale(4);
        spriteBullet.setScale(4);
//        firingSound = Gdx.audio.newSound(Gdx.files.internal("bullet.wav"));
        position = new Vector2((float) Gdx.graphics.getWidth() /2,sprite.getScaleY()*sprite.getHeight()/2);
        positionBullet = new Vector2(0, 1000);
    }

    /**
     * Updates the player's movement and bullet position based on user input and elapsed time.
     * @param deltaTime The time elapsed since the last frame, in seconds.
     */
    public void Update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && positionBullet.y >= Gdx.graphics.getHeight()) {
//            firingSound.play();
            positionBullet.x = position.x + 5;
            positionBullet.y = 0;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= deltaTime*speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += deltaTime*speed;
        }

        if (position.x - (sprite.getWidth() * sprite.getScaleX()/2) <= 0) {
            position.x = sprite.getWidth() * sprite.getScaleX()/2;
        }
        if (position.x + (sprite.getWidth() * sprite.getScaleX()/2) >= Gdx.graphics.getWidth()) {
            position.x = Gdx.graphics.getWidth() - sprite.getWidth() * sprite.getScaleX()/2;
        }

        positionBullet.y += deltaTime * speedBullet;
    }

    /**
     * Draws the player's ship and bullet on the provided SpriteBatch.
     * @param batch The SpriteBatch object used for rendering 2D graphics.
     */
    public void Draw(SpriteBatch batch) {
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
        spriteBullet.setPosition(positionBullet.x, positionBullet.y);
        spriteBullet.draw(batch);
    }
}

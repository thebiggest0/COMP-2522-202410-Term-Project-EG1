package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents the boss enemy encountered at a later stage of the game.
 *
 * @author Yifei, Steven
 * @version 2024
 */

public class Boss implements Enemy{
    /**
     * The current position of the boss on the screen.
     */
    public Vector2 position;

    /**
     * The texture used to render the boss visually.
     */
    public final Texture texture;

    /**
     * The current health points (HP) of the boss.
     */
    private int hp;

    /**
     * The bounding rectangle used for collision detection between the boss and other objects.
     */
    private final Rectangle boundingBox;

    /**
     * Scaling factor applied to the boss texture during rendering.
     */
    private final int scale = 20;

    /**
     * Constructs a new Boss instance at the specified position.
     *
     * @param position The initial position of the boss on the screen (center of the top edge).
     */
    public Boss(Vector2 position) {
        this.texture = new Texture("boss2.png"); // Assume you have a boss texture
        this.position = new Vector2(Gdx.graphics.getWidth() / 2f - texture.getWidth() / 2f, Gdx.graphics.getHeight());
        this.hp = 15;
        this.boundingBox = new Rectangle(position.x, position.y, texture.getWidth() * scale, texture.getHeight() * scale);
    }

    /**
     * Draws the boss on the provided SpriteBatch.
     *
     * @param batch The SpriteBatch object used for 2D drawing.
     */
    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, texture.getWidth() * scale, texture.getHeight() * scale);
    }

    /**
     * Retrieves the current health points (HP) of the boss.
     *
     * @return The current HP value of the boss.
     */
    public int getHp() {
        return this.hp;
    }

    /**
     * Reduces the boss's HP by 1 point, simulating damage taken.
     */
    public void hit() {
        hp -= 1;
    }

    /**
     * Retrieves the bounding rectangle used for collision detection with the boss.
     *
     * @return The bounding rectangle representing the boss's collision area.
     */
    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    /**
     * Updates the boss's position and bounding box based on its movement speed and elapsed time.
     *
     * @param deltaTime The time elapsed since the last frame, in seconds.
     */
    public void update(float deltaTime) {
        float speed = 50;
        position.y -= speed * deltaTime;
        updateBoundingBox();
    }

    /**
     * Updates the bounding box position to match the boss's current position.
     */
    private void updateBoundingBox() {
        boundingBox.setPosition(position.x, position.y);
    }

}

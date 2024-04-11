package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;

/**
 * Represents a Tie Fighter in the game world.
 *
 * @author Yifei, Steven
 * @version 2024
 */

public class TieFighter implements Enemy {

    /**
     * The current position of the Tie Fighter in the game world.
     */
    public Vector2 position;

    /**
     * The initial position of the Tie Fighter, used for potential respawning or movement reset.
     */
    public Vector2 position_initial;

    /**
     * The visual representation of the Tie Fighter as a Sprite object.
     */
    public Sprite sprite;

    /**
     * A flag indicating whether the Tie Fighter is currently alive and active in the game.
     */
    public Boolean Alive = true;

    /**
     * Constructs a new Tie Fighter instance with a specified position, texture, and color.
     *
     * @param _position The initial position of the Tie Fighter.
     * @param image The Texture object representing the Tie Fighter's visual appearance.
     * @param color The Color object defining the Tie Fighter's tint.
     */
    public TieFighter(Vector2 _position, Texture image, Color color) {
        position = _position;
        position_initial = position;
        sprite = new Sprite(image);
//        sprite.setColor(color);
        sprite.setScale(4);
    }

    /**
     * Draws the Tie Fighter's sprite on the provided SpriteBatch.
     *
     * @param batch The SpriteBatch object used for rendering 2D graphics.
     */
    public void draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}

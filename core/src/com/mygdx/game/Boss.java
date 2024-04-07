package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * Manages the gameplay logic.
 *
 * @author Yifei, Steven
 * @version 2024
 */
public class Boss {
    public Vector2 position;
    private Texture texture;
    private int hp;
    private Rectangle boundingBox;
    private int scale = 20;
    private float speed = 50;

    public Boss(Vector2 position) {
//        this.position = position;
        this.texture = new Texture("alien.png"); // Assume you have a boss texture
        this.position = new Vector2(Gdx.graphics.getWidth() / 2f - texture.getWidth() / 2f, Gdx.graphics.getHeight());
        this.hp = 15;
        this.boundingBox = new Rectangle(position.x, position.y, texture.getWidth() * scale, texture.getHeight() * scale);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, texture.getWidth() * scale, texture.getHeight() * scale);
    }

    public int getHp() {
        return this.hp;
    }

    public void hit() {
        hp -= 1;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public void update(float deltaTime) {
        position.y -= speed * deltaTime;
        updateBoundingBox();
    }

    private void updateBoundingBox() {
        boundingBox.setPosition(position.x, position.y);
    }

}

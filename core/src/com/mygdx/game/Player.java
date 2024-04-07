package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Represents Player.
 *
 * @author Yifei, Steven
 * @version 2024
 */

public class Player {
    public Vector2 position;
    public Vector2 positionBullet;
    public Sprite sprite;
    public Sprite spriteBullet;
    public float speed = 500;
    public float speedBullet = 1000;
    public Player(Texture image, Texture imageBullet) {
        sprite = new Sprite(image);
        spriteBullet = new Sprite(imageBullet);
        sprite.setScale(4);
        spriteBullet.setScale(4);
        position = new Vector2((float) Gdx.graphics.getWidth() /2,sprite.getScaleY()*sprite.getHeight()/2);
        positionBullet = new Vector2(0, 1000);
    }

    public void Update(float deltaTime) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && positionBullet.y >= Gdx.graphics.getHeight()) {
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

    public void Draw(SpriteBatch batch) {
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
        spriteBullet.setPosition(positionBullet.x, positionBullet.y);
        spriteBullet.draw(batch);
    }
}

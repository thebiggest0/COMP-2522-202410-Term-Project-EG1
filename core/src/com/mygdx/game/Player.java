package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position;
    public Vector2 positionLaser;
    public Sprite sprite;
    public Sprite spriteLaser;
    public float speed = 500;
    public float speedBullet = 1000;

    public Player(Texture image, Texture imageLaser) {
        sprite = new Sprite(image);
        spriteLaser = new Sprite(imageLaser);
        sprite.setScale(4);
        spriteLaser.setScale(4);
        position = new Vector2(Gdx.graphics.getWidth()/2,sprite.getScaleY()*sprite.getHeight()/2);
        positionLaser = new Vector2(0, 1000);
    }

    public void update (int deltaTime) {
        // bullet clicking
        if (Gdx.input.isButtonJustPressed(0) && positionLaser.y >= Gdx.graphics.getHeight()) {
            positionLaser.x = position.x + 5;
            positionLaser.y = 0;
        }
        // player movement speed
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= deltaTime*speed;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += deltaTime*speed;
        }

        // player stop move at wall
        if (position.x - (sprite.getWidth() * sprite.getScaleX()/2) <= 0) {
            position.x = sprite.getWidth() * sprite.getScaleX()/2;
        }
        if (position.x + (sprite.getWidth() * sprite.getScaleX()/2) >= Gdx.graphics.getWidth()) {
            position.x = Gdx.graphics.getWidth() - sprite.getWidth() * sprite.getScaleX()/2;
        }
        positionLaser.y += deltaTime * speedBullet;
    }

    public void Draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}

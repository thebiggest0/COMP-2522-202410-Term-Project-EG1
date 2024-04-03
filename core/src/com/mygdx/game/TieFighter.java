package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;

public class TieFighter {
    public Vector2 position;
    public Vector2 position_initial;
    public Sprite sprite;
    public Boolean Alive = true;
    // 5:34
    public TieFighter(Vector2 _position, Texture image, Color color) {
        position = _position;
        position_initial = position;
        sprite = new Sprite(image);
        sprite.setColor(color);
        sprite.setScale(4);
    }

    public void Draw(SpriteBatch batch) {
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }
}

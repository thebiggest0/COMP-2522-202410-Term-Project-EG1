package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Represents the main game screen.
 *
 * @author Yifei, Steven
 * @version 2024
 */
public class GameScreen extends ScreenAdapter {
    SpriteBatch batch;
    Texture imagePlayer;
    Texture imageLaser;
    Texture imageTieFighter;
    Texture imageBackground;
    Player player;
    TieFighter[] tieFighter;
    int widthCount = 4;
    int heightCount = 3;
    int spacingTieFighter = 50;
    int amountTieFighterAlive = 0;
    int minXTieFighter;
    int minYTieFighter;
    int maxXTieFighter;
    int maxYTieFighter;
    int directionTieFighter = 1;
    int score = 0;
    int level = 1;
    boolean bossStage = false;
    float speedTieFighter = 100;
    private final BitmapFont font;
    private final Game game;
    private final Boss boss;
    private int count = 0;
//    private Music backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music.ogg"));


    Vector2 offsetTieFighter;

    /**
     * Constructs a new GameScreen instance associated with the provided game instance.
     *
     * @param game The main game instance this GameScreen belongs to.
     */
    public GameScreen(Game game) {
        this.game = game;
        font = new BitmapFont();
        offsetTieFighter = Vector2.Zero;
        batch = new SpriteBatch();
        imagePlayer = new Texture("player2.png");
        imageLaser = new Texture("bullet.png");
        imageTieFighter = new Texture("alien2.png");
        imageBackground = new Texture("background.png");
        player = new Player(imagePlayer, imageLaser);
        tieFighter = new TieFighter[widthCount * heightCount];
        int i = 0;
        for (int y = 0; y < heightCount; y++) {
            for (int x = 0; x < widthCount; x++) {
                Vector2 position = new Vector2(x * spacingTieFighter, y * spacingTieFighter);
                position.x += (float) Gdx.graphics.getWidth() / 2;
                position.y += Gdx.graphics.getHeight();
                position.x -= ((float) widthCount / 2) * spacingTieFighter;
                position.y -= (heightCount) * spacingTieFighter;

                tieFighter[i] = new TieFighter(position, imageTieFighter, Color.GREEN);
                i++;
            }
        }
        boss = new Boss(new Vector2(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f));
    }

/**
 * Renders the game screen visuals, handles player input, updates game state, and checks for win/lose conditions.
 *
 * @param delta The time elapsed since the last frame, in seconds.
 */
    @Override
    public void render (float delta) {
//        backgroundMusic.setLooping(true);
//        backgroundMusic.play();

        try {
            float deltaTime = Gdx.graphics.getDeltaTime();
//            ScreenUtils.clear(0, 0, 0, 1);
            batch.begin();
            batch.draw(imageBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            font.draw(batch, "Score " + this.score, Gdx.graphics.getWidth() / 2f + 200, Gdx.graphics.getHeight() / 2f - 200);
            if (!this.bossStage) {
                font.draw(batch, "Level " + this.level, Gdx.graphics.getWidth() / 2f - 200, Gdx.graphics.getHeight() / 2f - 200);
            }
            player.Draw(batch);


            int tieFighterLength = tieFighter.length;
            for (TieFighter fighter : tieFighter) {
                if (fighter.Alive) {
                    if (player.spriteBullet.getBoundingRectangle().overlaps(fighter.sprite.getBoundingRectangle())) {
                        player.positionBullet.y = 1000;
                        fighter.Alive = false;
                        this.score += 100;
                        break;
                    }
                }
            }


            minXTieFighter = 10000;
            minYTieFighter = 10000;
            maxXTieFighter = 0;
            maxYTieFighter = 0;

            amountTieFighterAlive = 0;
            for (int i = 0; i < tieFighterLength; i++) {
                if (tieFighter[i].Alive) {
                    int IndexX = i % widthCount;
                    int IndexY = i / widthCount;

                    if (IndexX > maxXTieFighter) {
                        maxXTieFighter = IndexX;
                    }
                    if (IndexX < minXTieFighter) {
                        minXTieFighter = IndexX;
                    }
                    if (IndexY > maxYTieFighter) {
                        maxYTieFighter = IndexY;
                    }
                    if (IndexY < minYTieFighter) {
                        minYTieFighter = IndexY;
                    }
                    amountTieFighterAlive++;
                }
            }

            if (amountTieFighterAlive == 0) {
                this.count++;
                if (this.count < 5) {
                    this.level++;
                    speedTieFighter += 50;
                    for (int i = 0; i < tieFighterLength; i++) {
                        tieFighter[i].Alive = true;
                    }
                    offsetTieFighter = new Vector2(0, 0);
                    batch.end();
                    return;
                } else if (this.count > 5) {
                    this.bossStage = true;
                    font.draw(batch, "Final Boss", Gdx.graphics.getWidth() / 2f - 250, Gdx.graphics.getHeight() / 2f - 200);
                    if (boss.getHp() > 0) {
                        boss.update(delta);
                        boss.draw(batch);
                        if (boss.getBoundingBox().overlaps(player.sprite.getBoundingRectangle())) {
                            this.game.setScreen(new EndScreen(this.score));
                        } else if (boss.position.y <= 0) {
                            this.game.setScreen(new EndScreen(this.score));
                        }
                        if (player.spriteBullet.getBoundingRectangle().overlaps(boss.getBoundingBox())) {
                            boss.hit();
                            player.positionBullet.y = 1000; // Reset the bullet position or disable it
                            this.score += 100;
                        }
                    } else {
                        this.score += 1000;
                        this.game.setScreen(new EndScreen(this.score));
                    }

                }
            }


            if (amountTieFighterAlive != 0) {
                offsetTieFighter.x += directionTieFighter * deltaTime * speedTieFighter;
                if (tieFighter[maxXTieFighter].position.x >= Gdx.graphics.getWidth()) {
                    directionTieFighter = -1;

                    offsetTieFighter.y -= tieFighter[0].sprite.getHeight() * tieFighter[0].sprite.getScaleY() * 0.25f;
                }
                if (tieFighter[minXTieFighter].position.x <= 0) {
                    directionTieFighter = 1;
                    offsetTieFighter.y -= tieFighter[0].sprite.getHeight() * tieFighter[0].sprite.getScaleY() * 0.25f;
                }

                if (tieFighter[minYTieFighter].position.y <= 0) {
                    this.game.setScreen(new EndScreen(this.score));
                }

                for (int i = 0; i < tieFighterLength; i++) {
                    tieFighter[i].position = new Vector2 (tieFighter[i].position_initial.x + offsetTieFighter.x, tieFighter[i].position_initial.y + offsetTieFighter.y);
                    if (tieFighter[i].Alive) {
                        tieFighter[i].draw(batch);

                        if (tieFighter[i].sprite.getBoundingRectangle().overlaps(player.sprite.getBoundingRectangle())) {
                            this.game.setScreen(new EndScreen(this.score));
                        }
                    }
                }
            }


            batch.end();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Frees resources used by the StartScreen instance.
     */
    @Override
    public void dispose() {
        batch.dispose();
        imagePlayer.dispose();
    }
}

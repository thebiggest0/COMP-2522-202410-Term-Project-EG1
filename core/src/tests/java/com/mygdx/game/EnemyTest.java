package tests.java.com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Enemy;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EnemyTest {
    @Test
    public void draw_ShouldDrawWithSpriteBatch() {
        // Arrange
        SpriteBatch spriteBatchMock = Mockito.mock(SpriteBatch.class);
        Enemy enemy = batch -> {
        };

        // Act
        enemy.draw(spriteBatchMock);

        // Assert
        Mockito.verify(spriteBatchMock).begin();
        Mockito.verify(spriteBatchMock).end();
    }
}

package tests.java.com.mygdx.game;
import com.mygdx.game.TieFighter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class TieFighterTest {

    @Mock
    private Texture mockImage;

    @BeforeEach
    public void setUp() {
        Mockito.lenient().when(mockImage.getWidth()).thenReturn(100);
        Mockito.lenient().when(mockImage.getHeight()).thenReturn(50);
    }

    @Test
    public void constructor_ShouldSetAliveToTrue() {
        Vector2 initialPosition = new Vector2(100, 200);
        Color color = Color.RED;

        TieFighter tieFighter = new TieFighter(initialPosition, mockImage, color);

        assertTrue(tieFighter.Alive);
    }

    @Test
    public void constructor_ShouldCreateSprite() {
        Vector2 initialPosition = new Vector2(100, 200);
        Color color = Color.RED;

        TieFighter tieFighter = new TieFighter(initialPosition, mockImage, color);

        assertNotNull(tieFighter.sprite);
    }
}
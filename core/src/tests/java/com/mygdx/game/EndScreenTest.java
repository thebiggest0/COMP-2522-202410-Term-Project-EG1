package tests.java.com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EndScreen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class EndScreenTest {

    @Mock
    private SpriteBatch mockBatch;

    @Mock
    private BitmapFont mockFont;

    @Mock
    private Graphics mockGraphics;

    @BeforeEach
    public void setUp() {
        Gdx.app = (Application) Mockito.mock(ApplicationListener.class);
        Gdx.graphics = mockGraphics;
    }

    @Test
    public void render_ShouldDrawEndScreenMessage() {
        int score = 1000;
        EndScreen endScreen = new EndScreen(score);

        when(mockFont.draw(any(SpriteBatch.class), anyString(), anyFloat(), anyFloat())).thenReturn(null);

        endScreen.render(1f);

        verify(mockFont).draw(eq(mockBatch), eq("Game Finished! Press ESC to exit."), eq(180f), eq(275f));
        verify(mockFont).draw(eq(mockBatch), eq("Score: " + score), eq(180f), eq(225f));
    }

    @Test
    public void render_ShouldExitOnEscape() {
        EndScreen endScreen = new EndScreen(0);

        when(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)).thenReturn(true);

        endScreen.render(1f);

        verify(Gdx.app).exit();
    }

    @Test
    public void dispose_ShouldDisposeBatchAndFont() {
        EndScreen endScreen = new EndScreen(0);

        endScreen.dispose();

        verify(mockBatch).dispose();
        verify(mockFont).dispose();
    }
}

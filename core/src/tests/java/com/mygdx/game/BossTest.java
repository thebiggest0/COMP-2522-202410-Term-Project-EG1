package tests.java.com.mygdx.game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Boss;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BossTest {
    private Boss boss;
    private Files filesMock;
    private FileHandle fileHandleMock;

    @BeforeEach
    public void setUp() {
        // Create mocks
        filesMock = mock(Files.class);
        fileHandleMock = mock(FileHandle.class);

        // Mock the behavior of Gdx.files.internal
        when(filesMock.internal(Mockito.anyString())).thenReturn(fileHandleMock);

        // Set the static Gdx.files to our mock
        Gdx.files = filesMock;

        // Now the constructor of Boss should not throw a NullPointerException
        boss = new Boss(new Vector2(0, 0));
    }

    @Test
    public void bossInitialHealth_ShouldBeFifteen() {
        Assertions.assertEquals(15, boss.getHp());
    }

    @Test
    public void hit_ShouldDecreaseHealthByOne() {
        boss.hit();
        assertEquals(14, boss.getHp());
    }

    @Test
    public void update_ShouldMoveBoss() {
        // Save initial position
        float initialY = boss.position.y;
        // Call update with a deltaTime, e.g., 1 second
        boss.update(1f);
        // Check if the position has been decreased by 50 (speed * deltaTime)
        assertEquals(initialY - 50, boss.position.y);
    }
}


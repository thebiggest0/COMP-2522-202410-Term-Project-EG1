package tests.java.com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Boss;
import org.mockito.Mockito;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    // ... (Your test methods) ...
}


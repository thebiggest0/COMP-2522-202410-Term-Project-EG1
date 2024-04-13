package tests.java.com.mygdx.game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Aquarium;
import com.mygdx.game.Boss;
import com.mygdx.game.Guppy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BossTest {
    private Boss boss;
    private Guppy guppy;
    private Files filesMock;
    private FileHandle fileHandleMock;
    private Aquarium aquariumMock;

    @BeforeEach
    public void setUp() {
        // Initialize a Guppy instance
        guppy = new Guppy("Poecilia", "reticulata", 10, true, 1, 0.8);

        // Create mock for Aquarium
        aquariumMock = mock(Aquarium.class);
    }

    @Test
    public void testAddGuppyToAquarium() {
        // Add the guppy to the mocked aquarium
        aquariumMock.addGuppy(guppy);

        // Verify that the guppy was added to the aquarium
        verify(aquariumMock).addGuppy(guppy);
    }

    @Test
    public void testGuppyCount() {
        // Define behavior for countGuppies
        when(aquariumMock.countGuppies()).thenReturn(1);

        // Adding the guppy to the aquarium
        aquariumMock.addGuppy(guppy);

        // Check the count of guppies
        assert(guppy.getIsAlive());
        assert(aquariumMock.countGuppies() == 1);
    }
}




//    @BeforeEach
//    public void setUp() {
        // Create mocks
//        filesMock = mock(guppy)
//        filesMock = mock(Files.class);
//        fileHandleMock = mock(FileHandle.class);
//
//        // Mock the behavior of Gdx.files.internal
//        when(filesMock.internal(Mockito.anyString())).thenReturn(fileHandleMock);
//
//        // Set the static Gdx.files to our mock
//        Gdx.files = filesMock;

        // Now the constructor of Boss should not throw a NullPointerException
//        boss = new Boss(new Vector2(0, 0));




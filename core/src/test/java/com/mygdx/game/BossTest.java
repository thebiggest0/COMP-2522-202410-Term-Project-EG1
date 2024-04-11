package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BossTest {
    private Boss boss;

    @BeforeEach
    public void setUp() {
        boss = new Boss(new Vector2(0, 0));
    }

    @Test
    public void testInitialBossHealth() {
        assertEquals(15, boss.getHp(), "Initial boss health should be 15");
    }

    @Test
    public void testBossHit() {
        boss.hit();
        assertEquals(14, boss.getHp(), "Boss health should decrease by 1 after hit");
    }

    @Test
    public void testUpdateBossPosition() {
        float initialY = boss.position.y;
        boss.update(1.0f); // Simulate 1 second elapsed time
        assertEquals(initialY - 50, boss.position.y, "Boss position should decrease by speed * deltaTime");
    }

    @Test
    public void testBossHpCannotGoBelowZero() {
        for (int i = 0; i < 20; i++) {
            boss.hit();
        }
        assertTrue(boss.getHp() >= 0, "Boss HP should not go below 0");
    }

    @Test
    public void testBossPositionCannotGoBelowZero() {
        for (int i = 0; i < 1000; i++) {
            boss.update(1.0f);
        }
        assertTrue(boss.position.y >= 0, "Boss position y-coordinate should not go below 0");
    }
}
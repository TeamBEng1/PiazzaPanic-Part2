package com.mygdx.tests.ScreenTests;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Cook;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * This test class will test all methods in GameScreen class
 */
@RunWith(GdxTestRunner.class)
public class GameScreenTests {
    PiazzaPanic piazzaPanic;
    FitViewport viewport;
    GameScreen gameScreen;

    /**
     * Creating the initial instance of gameScreen to use in the tests
     */
    @Before
    public void setUp() {
        piazzaPanic = new PiazzaPanic();
        viewport = new FitViewport(800, 480);
        gameScreen = new GameScreen(piazzaPanic, viewport, 3, 1);
    }

    /**
     * This test does not work.
     * The code is correct but due to the structure of the GameScreen class and lack of lazy evaluation it will not run
     */
    @Test
    public void testSpawnCooks() {
        gameScreen.spawnCooks();

        //check if the number of cooks added to array is equal to the cookCount
        assertEquals(gameScreen.getCookCount(), gameScreen.cooks.size);

        //check if each cook has been added to the stage as an actor
        for (Cook cook : gameScreen.cooks) {
            assertNotNull(cook.CookBody.getParent());
        }

        //check if each cooks height and width have been set correctly, as well as their scale
        for (Cook cook : gameScreen.cooks) {
            assertEquals(16,cook.CookBody.getWidth(),0.0);
            assertEquals(23,cook.CookBody.getHeight(),0.0);
        }
    }
}

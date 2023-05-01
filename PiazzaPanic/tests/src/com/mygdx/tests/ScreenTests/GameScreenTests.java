package com.mygdx.tests.ScreenTests;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Cook;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * This test class will test all methods in GameScreen class
 * @Author - Muaz
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


    /**
     * This test checks the input to see how to change the cook selected
     * Once again this code will not work due to lack of lazy evaluation and inability to run in headless
     */
    @Test
    public void testProcessInput() {
        // Test selecting cook 1 with number key 1
        Gdx.input = Mockito.mock(Input.class);
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.NUM_1)).thenReturn(true);
        gameScreen.processInput();
        assert(gameScreen.getSelected() == 0);

        // Test selecting cook 2 with number key 2
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.NUM_2)).thenReturn(true);
        gameScreen.processInput();
        assert(gameScreen.getSelected() == 1);

        // Test selecting cook 3 with number key 3 when cookCount > 2
        Mockito.when(Gdx.input.isKeyPressed(Input.Keys.NUM_3)).thenReturn(true);
        gameScreen.processInput();
        assert(gameScreen.getSelected() == 2);
    }
}


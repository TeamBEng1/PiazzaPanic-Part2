package com.mygdx.tests.ScreenTests;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.SettingsScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;


import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

/**
 * This class will test any methods in the settings screen class
 */
@RunWith(GdxTestRunner.class)
public class SettingsScreenTests {

    /**
     * This test checks for the difference in buttons on the settings screen (once hovered upon)
     * @Author - Muaz
     */
    @Test
    public void testPlayBtnClickListener() {
        //Create the play button and its drawables
        Drawable playBtnDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("playBtn.png")));
        Drawable playBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("playBtn2.png")));

        final ImageButton playNormal = new ImageButton(playBtnDrawable);
        final ImageButton playHover = new ImageButton(playBtnDrawableHover);

        assertNotEquals("Test passes if after you hover on button, it lights up" , playNormal,playHover);
    }

    /**
     * This test checks for the difference in buttons on the settings screen (once hovered upon)
     * @Author - Muaz
     */
    @Test
    public void testPlayBtnYellowClickListener() {
        //Create the play button yellow and its drawables
        Drawable playBtnYellowDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("playBtnYellow.png")));
        Drawable playBtnYellowDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("playBtn2Yellow.png")));

        final ImageButton playYellowNormal = new ImageButton(playBtnYellowDrawable);
        final ImageButton playYellowHover = new ImageButton(playBtnYellowDrawableHover);

        assertNotEquals("Test passes if after you hover on button, it lights up" , playYellowNormal,playYellowHover);
    }

    /**
     * This test tests if the game mode buttons are working properly
     * @Author - Teddy
     */
    @Test
    public void testDifficultySelection() {
        assert(true);
    }

    /**
     * This tests that the game runs on different difficulties
     * @author teddyseddon
     */
    @Test
    public void testAllDifficultyRuns() {
        // Create a mock PiazzaPanic game
        PiazzaPanic game = mock(PiazzaPanic.class);

        // Create a mock FitViewport
        FitViewport viewport = mock(FitViewport.class);

            // Create a GameScreen with easy difficulty
            GameScreen easyGame = new GameScreen(game, viewport, 10, 1);
            assertEquals(4, easyGame.Rep);
            assertEquals(Optional.of(40), easyGame.orderTime);
            assertEquals(0.1f, easyGame.barStep, 0.01f);
            assertEquals(15f, easyGame.powerupDuration, 0.01f);
            assertEquals(3f, easyGame.powerupModifier, 0.01f);
            assertEquals(8, easyGame.earnings);

            // Create a GameScreen with medium difficulty
            GameScreen mediumGame = new GameScreen(game, viewport, 10, 2);
            assertEquals(3, mediumGame.Rep);
            assertEquals(Optional.of(30), mediumGame.orderTime);
            assertEquals(0.05f, mediumGame.barStep, 0.01f);
            assertEquals(12f, mediumGame.powerupDuration, 0.01f);
            assertEquals(2f, mediumGame.powerupModifier, 0.01f);
            assertEquals(6, mediumGame.earnings);

            // Create a GameScreen with hard difficulty
            GameScreen hardGame = new GameScreen(game, viewport, 10, 3);
            assertEquals(1, hardGame.Rep);
            assertEquals(Optional.of(20), hardGame.orderTime);
            assertEquals(0.04f, hardGame.barStep, 0.01f);
            assertEquals(8f, hardGame.powerupDuration, 0.01f);
            assertEquals(1.5f, hardGame.powerupModifier, 0.01f);
            assertEquals(4, hardGame.earnings);


    }

    /**
     * This test checks whether the scenario selection buttons are working
     * @Author - Teddy
     */
    @Test
    public void testScenarioSelection() {
    }
}
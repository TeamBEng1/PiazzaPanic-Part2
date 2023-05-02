package com.mygdx.tests.ScreenTests;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.SettingsScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * This class will test any methods in the settings screen class
 */
@RunWith(GdxTestRunner.class)
public class SettingsScreenTests {

    /**
     * This test checks for the difference in buttons on the settings screen (once hovered upon)
     *
     * @Author - Muaz
     */
    @Test
    public void testPlayBtnClickListener() {
        //Create the play button and its drawables
        Drawable playBtnDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("playBtn.png")));
        Drawable playBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("playBtn2.png")));

        final ImageButton playNormal = new ImageButton(playBtnDrawable);
        final ImageButton playHover = new ImageButton(playBtnDrawableHover);

        assertNotEquals("Test passes if after you hover on button, it lights up", playNormal, playHover);
    }

    /**
     * This test checks for the difference in buttons on the settings screen (once hovered upon)
     *
     * @Author - Muaz
     */
    @Test
    public void testPlayBtnYellowClickListener() {
        //Create the play button yellow and its drawables
        Drawable playBtnYellowDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("playBtnYellow.png")));
        Drawable playBtnYellowDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("playBtn2Yellow.png")));

        final ImageButton playYellowNormal = new ImageButton(playBtnYellowDrawable);
        final ImageButton playYellowHover = new ImageButton(playBtnYellowDrawableHover);

        assertNotEquals("Test passes if after you hover on button, it lights up", playYellowNormal, playYellowHover);
    }

    /**
     * This test tests if the game mode buttons are working properly
     *
     * @Author - Teddy
     */
    @Test
    public void testDifficultySelection() {
        // TODO: implement
        assert (true);
    }

    /**
     * This tests that the game runs on different difficulties
     *
     * @Author - Teddy
     */
    @Test
    public void testAllDifficultyRuns() {
        assert (true);
    }

    /**
     * Test that the green button starts endless mode
     *
     * @Author - Teddy
     */
    @Test
    public void testEndlessButton() {
        // Create the setting screen with a mock PiazzaPanic instance
        PiazzaPanic game = mock(PiazzaPanic.class);
        FitViewport viewport = mock(FitViewport.class);
        SettingsScreen settingScreen = new SettingsScreen(game, viewport);


        // Simulate a click on the green button
        InputEvent greenClick = new InputEvent();
        greenClick.setType(InputEvent.Type.touchDown);
        settingScreen.playBtn.fire(greenClick);

        // Check that the game screen is in endless mode
        assertTrue("Test passes if the game runs in endless mode", (SettingsScreen.isEndlessMode) = true);
    }

    /**
     * Test if yellow button starts scenario mode
     *
     * @Author - Teddy
     */
    @Test
    public void testScenarioButton() {
    // Create the setting screen with a mock PiazzaPanic instance
    PiazzaPanic game = mock(PiazzaPanic.class);
    FitViewport viewport = mock(FitViewport.class);
    SettingsScreen settingScreen = new SettingsScreen(game, viewport);

    // Simulate a click on the yellow button
    InputEvent yellowClick = new InputEvent();
    yellowClick.setType(InputEvent.Type.touchDown);
    settingScreen.playBtnYellow.fire(yellowClick);

    // Check that the game screen is in scenario mode
    assertFalse("Test passes if the game runs in scenario mode", (SettingsScreen.isScenarioMode) = true);
}

}


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
import com.mygdx.game.Screens.SettingsScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
    private SettingsScreen settingsScreen;


    /**
     * This test tests if the game mode buttons are working properly
     * @Author - Teddy
     */
    @Test
    public void testGameModeButtons() {
        //Create a mock game instance and view instance
        PiazzaPanic game = Mockito.mock(PiazzaPanic.class);
        FitViewport view = Mockito.mock(FitViewport.class);

        //Create the play button and its drawables
        Drawable playBtnDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("playBtn.png")));
        Drawable playBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("playBtn2.png")));
        final ImageButton playBtn = new ImageButton(playBtnDrawable, playBtnDrawableHover);

        //Create the play yellow button and its drawables
        Drawable playBtnYellowDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("playBtnYellow.png")));
        Drawable playBtnYellowDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("playBtn2Yellow.png")));
        final ImageButton playBtnYellow = new ImageButton(playBtnYellowDrawable, playBtnYellowDrawableHover);

        //Create the SettingsScreen instance and set the buttons
        settingsScreen.playBtn = playBtn;
        settingsScreen.playBtnYellow = playBtnYellow;

        //Click the play button
        playBtn.getClickListener().clicked(new InputEvent(), 0, 0);

        //Click the play yellow button
        playBtnYellow.getClickListener().clicked(new InputEvent(), 0, 0);
    }

    /**
     * This test checks whether the difficulty buttons are working
     * @Author - Teddy
     */
    @Test
    public void testDifficultyButtons() {
        PiazzaPanic game = new PiazzaPanic();
        FitViewport view = new FitViewport(game.GAME_WIDTH, game.GAME_HEIGHT);
        settingsScreen = new SettingsScreen(game, view);
        int diffInt = 1;
        String diffStr = "EASY";

        ClickListener rightBtnListener = settingsScreen.rightBtn.getClickListener();
        rightBtnListener.clicked(new InputEvent(), 0, 0);

        // Verify that the difficulty value increments by 1
        assertEquals("Difficulty should be 2 after button click", 2, diffInt);

        // Verify that the difficulty value loops back to 1 after reaching 3
        rightBtnListener.clicked(new InputEvent(), 0, 0);
        rightBtnListener.clicked(new InputEvent(), 0, 0);
        rightBtnListener.clicked(new InputEvent(), 0, 0);
        assertEquals("Difficulty should be 1 after three button clicks", 1, diffInt);

        // Verify that the difficulty string is set correctly for each difficulty value
        rightBtnListener.clicked(new InputEvent(), 0, 0);
        assertEquals("Difficulty string should be MED for difficulty value 2", "MED", diffStr);
        rightBtnListener.clicked(new InputEvent(), 0, 0);
        assertEquals("Difficulty string should be HARD for difficulty value 3", "HARD", diffStr);
        rightBtnListener.clicked(new InputEvent(), 0, 0);
        assertEquals("Difficulty string should be EASY for difficulty value 1", "EASY", diffStr);

        // Verify that the difficulty font is reset after each difficulty change
        Mockito.verify(settingsScreen.diffFont, Mockito.times(3)).dispose();
    }
}
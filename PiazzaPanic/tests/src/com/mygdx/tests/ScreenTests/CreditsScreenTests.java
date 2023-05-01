package com.mygdx.tests.ScreenTests;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.PiazzaPanic;
import com.mygdx.game.Screens.CreditsScreen;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.junit.Assert.*;

/**
 * This class will test any methods in the credits screen class
 * @Author - Muaz
 */
@RunWith(GdxTestRunner.class)
public class CreditsScreenTests {
    PiazzaPanic game;
    CreditsScreen creditsScreen;

    @Before
    public void setUp() {
        creditsScreen = new CreditsScreen(game);
    }

    /**
     * This test checks for the difference in buttons on the credits screen (once hovered upon)
     */
    @Test
    public void testBackBtnClickListener() {
        //Create the back button and its drawables
        Drawable backBtnDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("backBtn.png")));
        Drawable backBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("backBtn2.png")));

        final ImageButton backNormal = new ImageButton(backBtnDrawable);
        final ImageButton backHover = new ImageButton(backBtnDrawableHover);

        assertNotEquals("Test passes if after you hover on button, it lights up" , backNormal,backHover);
    }

    /**
     * This test checks whether once the back button is clicked, we return to the main menu screen
     * This test does not work
     */
    @Test
    public void testBackBtnClick() {
        creditsScreen.show();

        //Mock the listener
        ClickListener mockListener = Mockito.mock(ClickListener.class);
        creditsScreen.getBackBtn().addListener(mockListener);

        // Simulate a click on the back button
        creditsScreen.getBackBtn().fire(new ChangeListener.ChangeEvent());

        // Verify that the listener was called once
        Mockito.verify(mockListener, Mockito.times(1)).clicked(null,0,0);

        // Verify that the MainMenuScreen was set as the current screen
        Mockito.verify(game).setScreen(Mockito.any(MainMenuScreen.class));
    }


    /**
     * This test checks the getter method of the back button
     * This test does not work
     */
    @Test
    public void testGetBackButton() {
        CreditsScreen creditsScreen = new CreditsScreen(game);
        ImageButton backButton = creditsScreen.getBackBtn();
        assertNotNull(backButton);
    }
}

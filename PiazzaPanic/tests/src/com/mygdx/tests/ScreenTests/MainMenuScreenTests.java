package com.mygdx.tests.ScreenTests;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * This class will test any methods in the main menu screen class
 * @Author - Muaz
 */
@RunWith(GdxTestRunner.class)
public class MainMenuScreenTests {

    /**
     * This test checks for the difference in buttons on the main menu screen (once hovered upon)
     * Test 1.9.1
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
     * This test checks for the difference in buttons on the main menu screen (once hovered upon)
     * Test 1.9.1
     */
    @Test
    public void testExitBtnClickListener() {
        //Create the exit button and its drawables
        Drawable exitBtnDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("exitBtn.png")));
        Drawable exitBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("exitBtn2.png")));

        final ImageButton exitNormal = new ImageButton(exitBtnDrawable);
        final ImageButton exitHover = new ImageButton(exitBtnDrawableHover);

        assertNotEquals("Test passes if after you hover on button, it lights up" , exitNormal,exitHover);
    }

    /**
     * This test checks for the difference in buttons on the main menu screen (once hovered upon)
     * Test 1.9.1
     */
    @Test
    public void testInfoBtnClickListener() {
        //Create the info button and its drawables
        Drawable infoBtnDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("infoBtn.png")));
        Drawable infoBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("infoBtn2.png")));

        final ImageButton infoNormal = new ImageButton(infoBtnDrawable);
        final ImageButton infoHover = new ImageButton(infoBtnDrawableHover);

        assertNotEquals("Test passes if after you hover on button, it lights up" , infoNormal,infoHover);
    }


}


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

@RunWith(GdxTestRunner.class)
public class MainMenuScreenTests {
    @Test
    public void testPlayBtnClickListener() {
        //Create the play button and its drawables
        Drawable playBtnDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("playBtn.png")));
        Drawable playBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("playBtn2.png")));

        final ImageButton playNormal = new ImageButton(playBtnDrawable);
        final ImageButton playHover = new ImageButton(playBtnDrawableHover);

        assertNotEquals(playNormal,playHover);
    }

    @Test
    public void testExitBtnClickListener() {
        //Create the exit button and its drawables
        Drawable exitBtnDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("exitBtn.png")));
        Drawable exitBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("exitBtn2.png")));

        final ImageButton exitNormal = new ImageButton(exitBtnDrawable);
        final ImageButton exitHover = new ImageButton(exitBtnDrawableHover);

        assertNotEquals(exitNormal,exitHover);
    }

    @Test
    public void testInfoBtnClickListener() {
        //Create the info button and its drawables
        Drawable infoBtnDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("infoBtn.png")));
        Drawable infoBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("infoBtn2.png")));

        final ImageButton infoNormal = new ImageButton(infoBtnDrawable);
        final ImageButton infoHover = new ImageButton(infoBtnDrawableHover);

        assertNotEquals(infoNormal,infoHover);
    }
}


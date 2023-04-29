package com.mygdx.tests.ScreenTests;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotEquals;

@RunWith(GdxTestRunner.class)
public class SettingsScreenTests {
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
    public void testPlayBtnYellowClickListener() {
        //Create the play button yellow and its drawables
        Drawable playBtnYellowDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("playBtnYellow.png")));
        Drawable playBtnYellowDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("playBtn2Yellow.png")));

        final ImageButton playYellowNormal = new ImageButton(playBtnYellowDrawable);
        final ImageButton playYellowHover = new ImageButton(playBtnYellowDrawableHover);

        assertNotEquals(playYellowNormal,playYellowHover);
    }
}

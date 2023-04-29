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
public class CreditsScreenTests {
    @Test
    public void testBackBtnClickListener() {
        //Create the back button and its drawables
        Drawable backBtnDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("backBtn.png")));
        Drawable backBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("backBtn2.png")));

        final ImageButton backNormal = new ImageButton(backBtnDrawable);
        final ImageButton backHover = new ImageButton(backBtnDrawableHover);

        assertNotEquals(backNormal,backHover);
    }
}

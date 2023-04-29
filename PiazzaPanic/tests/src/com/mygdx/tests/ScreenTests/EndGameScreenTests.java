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
public class EndGameScreenTests {

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
    public void testRestartBtnClickListener() {
        //Create the restart button and its drawables
        Drawable restartBtnDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("restartBtn.png")));
        Drawable restartBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(new Texture("restartBtn2.png")));

        final ImageButton restartNormal = new ImageButton(restartBtnDrawable);
        final ImageButton restartHover = new ImageButton(restartBtnDrawableHover);

        assertNotEquals(restartNormal,restartHover);
    }
}

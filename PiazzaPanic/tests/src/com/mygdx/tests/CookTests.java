package com.mygdx.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Cook;

@RunWith(GdxTestRunner.class)
public class CookTests {

    /**
     * This test is always true
     */
    @Test
    public void alwaysTrueTest() {
        assertTrue(true);
    }

    //TODO Add location of cook asset
    @Test
    public void testCookAssets() {

        // Load a texture for the cook's skin
        try{
            findTextureAdam();
            findTextureAlex();
            findTextureAmelia();
            findTextureBob();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Create an Image actor using the texture
        Image cookSkin = new Image(cookTexture);

        // Create a new Cook instance using the cookSkin actor
        Cook cook = new Cook(cookSkin);

        // Add your test cases and assertions here
    }

    private void findTextureAdam() {
        Texture cookTextureBase = new Texture("Cook Animations/Adam.png");
        Texture cookAnimaton = new Texture("Cook Animations/Adam_16x16.png");
    }

    private void findTextureAlex() {
        Texture cookTextureBase = new Texture("Cook Animations/Alex.png");
        Texture cookTexture16x16 = new Texture("Cook Animations/Alex_16x16.png");
    }

    private void findTextureAmelia() {
        Texture cookTextureBase = new Texture("Cook Animations/Amelia.png");
        Texture cookTexture16x16 = new Texture("Cook Animations/Amelia_16x16.png");
    }

    private void findTextureBob() {
        Texture cookTextureBase = new Texture("Cook Animations/Bob.png");
        Texture cookTexture16x16 = new Texture("Cook Animations/Bob_16x16.png");
    }


}
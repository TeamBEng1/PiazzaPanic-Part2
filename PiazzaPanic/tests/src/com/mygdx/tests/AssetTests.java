package com.mygdx.tests;

import com.badlogic.gdx.Gdx;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class AssetTests {

    /**
     * This test is always true
     */
    @Test
    public void alwaysTrueTest() {
        assertTrue(true);
    }


    /**
     * This test checks for the cooked patty asset in the assets folder
     */
    @Test
    public void cookedPattyAssetTest() {
        assertTrue(Gdx.files.internal("../assets/prepdPatty.png").exists());
    }

    /**
     * This test checks for all assets (ingredients and final) related to salad
     */
    @Test
    public void SaladTest() {
        String[] saladAssetPaths = {"lettuce", "prepdLettuce", "tomato", "prepdTomato", "salad"};
        boolean saladAssetsExist = true;
        for (String path : saladAssetPaths) {
            if (!Gdx.files.internal("../assets/" + path + ".png").exists()) {
                saladAssetsExist = false;
                break;
            }
        }
        assertTrue(saladAssetsExist);
    }

    /**
     * This test checks for all assets (ingredients and final) related to burger
     */
    @Test
    public void BurgerTest() {
        String[] burgerAssetPaths = {"lettuce", "prepdLettuce", "buns", "rawPatty", "prepdPatty", "burger"};
        boolean burgerAssetsExist = true;
        for (String path : burgerAssetPaths) {
            if (!Gdx.files.internal("../assets/" + path + ".png").exists()) {
                burgerAssetsExist = false;
                break;
            }
        }
        assertTrue(burgerAssetsExist);
    }
}


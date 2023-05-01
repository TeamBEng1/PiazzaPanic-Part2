package com.mygdx.tests;

import com.badlogic.gdx.Gdx;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * These tests check for everything to do with the assets used in the game
 */
@RunWith(GdxTestRunner.class)
public class AssetTests {

    /**
     * This test is always true
     */
    @Test
    public void alwaysTrueTest() {
        assertTrue("This test will always pass" , true);
    }

    /**
     * This test checks for all assets (ingredients and final) related to salad
     */
    @Test
    public void SaladTest() {
        String[] saladAssetPaths = {"lettuce", "prepdLettuce", "tomato", "prepdTomato", "salad"};
        boolean saladAssetsExist = true;
        for (String path : saladAssetPaths) {
            if (!Gdx.files.internal(path + ".png").exists()) {
                saladAssetsExist = false;
                break;
            }
        }
        assertTrue("This test will pass if all assets related to salad exist" , saladAssetsExist);
    }

    /**
     * This test checks for all assets (ingredients and final) related to burger
     */
    @Test
    public void BurgerTest() {
        String[] burgerAssetPaths = {"lettuce", "prepdLettuce", "buns", "rawPatty", "prepdPatty", "burger"};
        boolean burgerAssetsExist = true;
        for (String path : burgerAssetPaths) {
            if (!Gdx.files.internal(path + ".png").exists()) {
                burgerAssetsExist = false;
                break;
            }
        }
        assertTrue("This test will pass if all assets related to burger will pass" , burgerAssetsExist);
    }

    /**
     * This test checks for all assets (ingredients and final) related to pizza--
     */
    @Test
    public void PizzaTest() {
        String[] pizzaAssetPaths = {"tomato", "prepdTomato", "cheese", "prepdCheese", "pizzaBase", "pizza"};
        boolean pizzaAssetsExist = true;
        for (String path : pizzaAssetPaths) {
            if (!Gdx.files.internal(path + ".png").exists()) {
                pizzaAssetsExist = false;
                break;
            }
        }
        assertTrue("This test will pass if all assets related to pizza will pass" , pizzaAssetsExist);
    }

    /**
     * This test checks for all assets (ingredients and final) related to jacket potato
     */
    @Test
    public void JacketPotatoTest() {
        String[] jacketPotatoAssetPaths = {"cheese", "prepdCheese", "rawPotato", "prepdPotato", "jacketPotato"};
        boolean jacketPotatoAssetsExist = true;
        for (String path : jacketPotatoAssetPaths) {
            if (!Gdx.files.internal(path + ".png").exists()) {
                jacketPotatoAssetsExist = false;
                break;
            }
        }
        assertTrue("This test will pass if all assets related to jacket potato will pass" , jacketPotatoAssetsExist);
    }


    /**
     * This test checks for all assets relating to ordering any of the above items
     */
    @Test
    public void orderAssetTest() {
        String[] orderAssetPaths = {"Burger", "BurgerBubble", "JacketPotato", "JacketPotatoBubble", "Pizza", "PizzaBubble", "Salad", "SaladBubble"};
        boolean orderAssetsExist = true;
        for (String path : orderAssetPaths) {
            if (!Gdx.files.internal(path + ".png").exists()) {
                orderAssetsExist = false;
                break;
            }
        }
        assertTrue("This test will pass if all assets related to order will pass" , orderAssetsExist);
    }

    /**
     * This test checks for the music asset
     */
    @Test
    public void musicAssetTest() {
        assertTrue("This test will pass if the asset for the music file is present" ,
                Gdx.files.internal("Alien_Jazz_Ridley_Coyte.mp3").exists());
    }

    /**
     * This test checks for all the assets relating to powerups
     */
    @Test
    public void powerUpAssetTests() {
        String[] powerUpAssetPaths = {"", "Blue", "Green", "Purple", "Red", "Red2", "Yellow"};
        boolean powerUpAssetsExist = true;
        for (String path : powerUpAssetPaths) {
            if (!Gdx.files.internal("powerUp" + path + ".png").exists()) {
                powerUpAssetsExist = false;
                break;
            }
        }
        assertTrue("This test will pass if all assets related to order will pass" , powerUpAssetsExist);
    }

    /**
     * This test checks for other general assets used within the game
     */
    @Test
    public void otherAssetTests() {
        assertTrue("This test will pass if the bin asset exists" ,
                Gdx.files.internal("bin.png").exists());
        assertTrue("This test will pass if the reputation points asset exists" ,
                Gdx.files.internal("REPHeart.png").exists());
        assertTrue("This test will pass if the cook cursor asset exists" ,
                Gdx.files.internal("selected.png").exists());
        assertTrue("This test will pass if the money asset exists" ,
                Gdx.files.internal("money.png").exists());
    }
}



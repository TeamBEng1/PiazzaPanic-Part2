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

    /**
     * This test checks for all assets (ingredients and final) related to pizza
     */
    @Test
    public void PizzaTest() {
        String[] pizzaAssetPaths = {"tomato", "prepdTomato", "cheese", "prepdCheese", "pizzaBase", "pizza"};
        boolean pizzaAssetsExist = true;
        for (String path : pizzaAssetPaths) {
            if (!Gdx.files.internal("../assets/" + path + ".png").exists()) {
                pizzaAssetsExist = false;
                break;
            }
        }
        assertTrue(pizzaAssetsExist);
    }

    /**
     * This test checks for all assets (ingredients and final) related to jacket potato
     */
    @Test
    public void JacketPotatoTest() {
        String[] jacketPotatoAssetPaths = {"cheese", "prepdCheese", "rawPotato", "prepdPotato", "jacketPotato"};
        boolean jacketPotatoAssetsExist = true;
        for (String path : jacketPotatoAssetPaths) {
            if (!Gdx.files.internal("../assets/" + path + ".png").exists()) {
                jacketPotatoAssetsExist = false;
                break;
            }
        }
        assertTrue(jacketPotatoAssetsExist);
    }


    /**
     * This test checks for all assets relating to ordering any of the above items
     */
    @Test
    public void orderAssetTest() {
        String[] orderAssetPaths = {"Burger", "BurgerBubble", "JacketPotato", "JacketPotatoBubble", "Pizza", "PizzaBubble", "Salad", "SaladBubble"};
        boolean orderAssetsExist = true;
        for (String path : orderAssetPaths) {
            if (!Gdx.files.internal("../assets/order" + path + ".png").exists()) {
                orderAssetsExist = false;
                break;
            }
        }
        assertTrue(orderAssetsExist);
    }
}



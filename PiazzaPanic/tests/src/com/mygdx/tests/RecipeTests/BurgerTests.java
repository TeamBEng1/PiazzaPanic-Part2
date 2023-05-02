package com.mygdx.tests.RecipeTests;

import com.mygdx.game.Food.Burger;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * These tests check for everything to do with the Burger class
 * @Author - Muaz
 * */
@RunWith(GdxTestRunner.class)
public class BurgerTests {

    /**
     * This test checks for the number of ingredients required to make a burger
     * For a burger, there are 3 ingredients (buns, patty and lettuce) and we assertEquals to 3
     */
    @Test
    public void testGetRecipe() {
        Burger burger = new Burger();
        assertEquals("Passes if the number of ingredients needed for burger is 3" ,
                3, burger.getRecipe().size());
    }

    /**
     * This test checks that the texture for the burger is present, and thus not null
     */
    @Test
    public void testGetTexture() {
        Burger burger = new Burger();
        assertNotNull("Passes if the texture of the burger exists" ,
                burger.getTexture());
    }

    /**
     * This test checks that the texture for ordering of the burger is present, and thus not null
     */
    @Test
    public void testGetSpeechBubbleTexture() {
        Burger burger = new Burger();
        assertNotNull("Passes if the texture of the burger order speech bubble exists" ,
                burger.getSpeechBubbleTexture());
    }

    /**
     * This test checks that the three ingredients combined (buns, patty and lettuce) make a burger
     * We also checked to see if they are pushed in a different order (patty, buns, lettuce) and if it would
     * still assemble the burger
     */
    @Test
    public void testHasBurger() {
        Burger burger = new Burger();
        Stack<Ingredient> ingredients = new Stack<>();
        Ingredient buns = new Ingredient("buns",null,null);
        buns.prepare();
        Ingredient patty = new Ingredient("patty",null,null);
        patty.prepare();
        Ingredient lettuce = new Ingredient("lettuce",null,null);
        lettuce.prepare();

        ingredients.push(buns);
        ingredients.push(patty);
        ingredients.push(lettuce);
        assertTrue("Passes if the burger contains the above ingredients" ,
                burger.has(ingredients));

        //add another test with ingredients in different order
        ingredients.clear();
        ingredients.push(patty);
        ingredients.push(lettuce);
        ingredients.push(buns);
        assertTrue("Passes if the burger contains the above ingredients" ,
                burger.has(ingredients));

    }
}
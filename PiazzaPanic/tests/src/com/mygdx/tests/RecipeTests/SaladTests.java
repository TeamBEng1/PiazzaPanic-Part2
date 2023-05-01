package com.mygdx.tests.RecipeTests;

import com.mygdx.game.Food.Salad;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * These tests check for everything to do with the Salad class
 */
@RunWith(GdxTestRunner.class)
public class SaladTests {

    /**
     * This test checks for the number of ingredients required to make a salad
     * For a salad, there are 2 ingredients (tomato and lettuce) and we assertEquals to 2
     */
    @Test
    public void testGetRecipe() {
        Salad salad = new Salad();
        assertEquals(2, salad.getRecipe().size());
    }

    /**
     * This test checks that the texture for the salad is present, and thus not null
     */
    @Test
    public void testGetTexture() {
        Salad salad = new Salad();
        assertNotNull(salad.getTexture());
    }

    /**
     * This test checks that the texture for ordering of the salad is present, and thus not null
     */
    @Test
    public void testGetSpeechBubbleTexture() {
        Salad salad = new Salad();
        assertNotNull((salad.getSpeechBubbleTexture()));
    }

    /**
     * This test checks that the two ingredients combined (tomato and lettuce) make a salad
     * We also checked to see if they are pushed in a different order (lettuce and tomato) and if it would
     * still assemble the salad
     */
    @Test
    public void testHasSalad() {
        Salad salad = new Salad();
        Stack<Ingredient> ingredients = new Stack<>();
        Ingredient tomato = new Ingredient("tomato",null,null);
        tomato.prepare();
        Ingredient lettuce = new Ingredient("lettuce",null,null);
        lettuce.prepare();

        ingredients.push(tomato);
        ingredients.push(lettuce);
        assertTrue(salad.has(ingredients));

        //add another test with ingredients in different order
        ingredients.clear();
        ingredients.push(lettuce);
        ingredients.push(tomato);
        assertTrue(salad.has(ingredients));
    }
}
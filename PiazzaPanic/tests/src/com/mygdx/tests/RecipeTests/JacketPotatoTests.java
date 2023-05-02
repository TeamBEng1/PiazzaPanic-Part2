package com.mygdx.tests.RecipeTests;

import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.JacketPotato;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * These tests check for everything to do with the Jacket Potato class
 * @Author - Muaz
 */
@RunWith(GdxTestRunner.class)
public class JacketPotatoTests {

    /**
     * This test checks for the number of ingredients required to make a jacket potato
     * For a jacket potato, there are 2 ingredients (cheese and potato) and we assertEquals to 2
     */
    @Test
    public void testGetRecipe() {
        JacketPotato jacketPotato = new JacketPotato();
        assertEquals("Passes if the number of ingredients needed for jacket potato is 2" ,
                2, jacketPotato.getRecipe().size());
    }


    /**
     * This test checks that the texture for the jacket potato is present, and thus not null
     */
    @Test
    public void testGetTexture() {
        JacketPotato jacketPotato = new JacketPotato();
        assertNotNull("Passes if the texture of the jacket potato exists" ,
                jacketPotato.getTexture());
    }

    /**
     * This test checks that the texture for ordering of the jacket potato is present, and thus not null
     */
    @Test
    public void testGetSpeechBubbleTexture() {
        JacketPotato jacketPotato = new JacketPotato();
        assertNotNull("Passes if the texture of the jacket potato order speech bubble exists" ,
                jacketPotato.getSpeechBubbleTexture());
    }

    /**
     * This test checks that the two ingredients combined (cheese and potato) make a jacket potato
     * We also checked to see if they are pushed in a different order (potato and cheese) and if it would
     * still assemble the jacket potato
     */
    @Test
    public void testHasJacketPotato() {
        JacketPotato jacketPotato = new JacketPotato();
        Stack<Ingredient> ingredients = new Stack<>();
        Ingredient cheese = new Ingredient("cheese",null,null);
        cheese.prepare();
        Ingredient potato = new Ingredient("potato",null,null);
        potato.prepare();

        ingredients.push(cheese);
        ingredients.push(potato);
        assertTrue("Passes if the jacket potato contains the above ingredients" ,
                jacketPotato.has(ingredients));

        //add another test with ingredients in different order
        ingredients.clear();
        ingredients.push(potato);
        ingredients.push(cheese);
        assertTrue("Passes if the jacket potato contains the above ingredients" ,
                jacketPotato.has(ingredients));
    }
}

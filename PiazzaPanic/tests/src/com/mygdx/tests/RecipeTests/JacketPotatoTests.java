package com.mygdx.tests.RecipeTests;

import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.JacketPotato;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class JacketPotatoTests {
    @Test
    public void testGetRecipe() {
        JacketPotato jacketPotato = new JacketPotato();
        assertEquals(2, jacketPotato.getRecipe().size());
    }

    @Test
    public void testGetTexture() {
        JacketPotato jacketPotato = new JacketPotato();
        assertNotNull(jacketPotato.getTexture());
    }

    @Test
    public void testGetSpeechBubbleTexture() {
        JacketPotato jacketPotato = new JacketPotato();
        assertNotNull((jacketPotato.getSpeechBubbleTexture()));
    }

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
        assertTrue(jacketPotato.has(ingredients));

        //add another test with ingredients in different order
        ingredients.clear();
        ingredients.push(cheese);
        ingredients.push(potato);
        assertTrue(jacketPotato.has(ingredients));
    }
}

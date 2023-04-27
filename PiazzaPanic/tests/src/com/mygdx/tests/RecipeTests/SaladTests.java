package com.mygdx.tests.RecipeTests;

import com.mygdx.game.Food.Salad;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class SaladTests {

    @Test
    public void testGetRecipe() {
        Salad salad = new Salad();
        assertEquals(2, salad.getRecipe().size());
    }

    @Test
    public void testGetTexture() {
        Salad salad = new Salad();
        assertNotNull(salad.getTexture());
    }

    @Test
    public void testGetSpeechBubbleTexture() {
        Salad salad = new Salad();
        assertNotNull((salad.getSpeechBubbleTexture()));
    }

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
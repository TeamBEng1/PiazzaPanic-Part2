package com.mygdx.tests.RecipeTests;

import com.mygdx.game.Food.Burger;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class BurgerTests {

    @Test
    public void testGetRecipe() {
        Burger burger = new Burger();
        assertEquals(3, burger.getRecipe().size());
    }

    @Test
    public void testGetTexture() {
        Burger burger = new Burger();
        assertNotNull(burger.getTexture());
    }

    @Test
    public void testGetSpeechBubbleTexture() {
        Burger burger = new Burger();
        assertNotNull((burger.getSpeechBubbleTexture()));
    }

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
        assertTrue(burger.has(ingredients));

        //add another test with ingredients in different order
        ingredients.clear();
        ingredients.push(patty);
        ingredients.push(lettuce);
        ingredients.push(buns);
        assertTrue(burger.has(ingredients));

    }
}
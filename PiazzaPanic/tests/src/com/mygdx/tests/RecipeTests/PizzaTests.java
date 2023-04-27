package com.mygdx.tests.RecipeTests;

import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.JacketPotato;
import com.mygdx.game.Food.Pizza;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class PizzaTests {
    @Test
    public void testGetRecipe() {
        Pizza pizza = new Pizza();
        assertEquals(3, pizza.getRecipe().size());
    }

    @Test
    public void testGetTexture() {
        Pizza pizza = new Pizza();
        assertNotNull(pizza.getTexture());
    }

    @Test
    public void testGetSpeechBubbleTexture() {
        Pizza pizza = new Pizza();
        assertNotNull((pizza.getSpeechBubbleTexture()));
    }

    @Test
    public void testHasPizza() {
        Pizza pizza = new Pizza();
        Stack<Ingredient> ingredients = new Stack<>();
        Ingredient cheese = new Ingredient("cheese",null,null);
        cheese.prepare();
        Ingredient tomato = new Ingredient("tomato",null,null);
        tomato.prepare();
        Ingredient base = new Ingredient("pizzaBase",null,null);
        base.prepare();

        ingredients.push(cheese);
        ingredients.push(tomato);
        ingredients.push(base);
        assertTrue(pizza.has(ingredients));

        //add another test with ingredients in different order
        ingredients.clear();
        ingredients.push(cheese);
        ingredients.push(tomato);
        ingredients.push(base);
        assertTrue(pizza.has(ingredients));
    }
}


package com.mygdx.tests.RecipeTests;

import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.Pizza;
import com.mygdx.tests.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Stack;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * These tests check for everything to do with the Pizza class
 * @Author - Muaz
 */
@RunWith(GdxTestRunner.class)
public class PizzaTests {

    /**
     * This test checks for the number of ingredients required to make a pizza
     * For a pizza, there are 3 ingredients (cheese, tomato and pizzaBase) and we assertEquals to 3
     */
    @Test
    public void testGetRecipe() {
        Pizza pizza = new Pizza();
        assertEquals("Passes if the number of ingredients needed for pizza is 3" ,
                3, pizza.getRecipe().size());
    }

    /**
     * This test checks that the texture for the pizza is present, and thus not null
     */
    @Test
    public void testGetTexture() {
        Pizza pizza = new Pizza();
        assertNotNull("Passes if the texture of the pizza exists" ,
                pizza.getTexture());
    }

    /**
     * This test checks that the texture for ordering of the pizza is present, and thus not null
     */
    @Test
    public void testGetSpeechBubbleTexture() {
        Pizza pizza = new Pizza();
        assertNotNull("Passes if the texture of the pizza order speech bubble exists" ,
                pizza.getSpeechBubbleTexture());
    }

    /**
     * This test checks that the three ingredients combined (cheese, tomato and pizzaBase) make a pizza
     * We also checked to see if they are pushed in a different order (pizzaBase, tomato and cheese) and if it would
     * still assemble the pizza
     */
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
        Ingredient potato = new Ingredient("potato",null,null);
        potato.prepare();

        ingredients.push(cheese);
        ingredients.push(tomato);
        ingredients.push(base);
        assertTrue("Passes if the pizza contains the above ingredients" ,
                pizza.has(ingredients));

        //add another test with ingredients in different order
        ingredients.clear();
        ingredients.push(base);
        ingredients.push(tomato);
        ingredients.push(cheese);
        assertTrue("Passes if the pizza contains the above ingredients" ,
                pizza.has(ingredients));

        //add incorrect ingredients and see if desired item is still produced
        ingredients.clear();
        ingredients.push(base);
        ingredients.push(tomato);
        ingredients.push(potato);
        assertFalse("Passes if the pizza is not made from the above ingredients",
                pizza.has(ingredients));

        //add some correct ingredients but not all and see if desired item is still produced
        ingredients.clear();
        ingredients.push(base);
        ingredients.push(tomato);
        assertFalse("Passes if the pizza is not made from only the above ingredients",
                pizza.has(ingredients));

        //add none of the ingredients and check if the desired item is produced
        ingredients.clear();
        assertFalse("Passes if the pizza is not made, due to no ingredients",
                pizza.has(ingredients));
    }
}


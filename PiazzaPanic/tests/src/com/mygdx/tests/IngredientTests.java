package com.mygdx.tests;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Food.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class IngredientTests {

    @Test
    public void testIngredientPreparation() {
        //Create a new Ingredient object with a not prepared texture and a prepared texture
        Texture notPreparedTexture = new Texture("lettuce.png");
        Texture preparedTexture = new Texture("prepdLettuce.png");
        Ingredient ingredient = new Ingredient("lettuce", notPreparedTexture, preparedTexture);

        //Check that the initial state is not prepared
        assertFalse(ingredient.getState());

        //Prepare the ingredient and check that the state is now prepared
        ingredient.prepare();
        assertTrue(ingredient.getState());

        //Update the current texture and check that it is the prepared texture
        ingredient.updateCurrentTexture();
        assertEquals(preparedTexture, ingredient.getCurrentTexture());
    }


    @Test
    public void testIngredientEquality() {
        //Create two ingredient objects with the same name and not prepared texture
        Texture notPreparedTexture = new Texture("lettuce.png");
        Texture preparedTexture = new Texture("prepdLettuce.png");
        Ingredient ingredient1 = new Ingredient("lettuce", notPreparedTexture, preparedTexture);
        Ingredient ingredient2 = new Ingredient("lettuce", notPreparedTexture, preparedTexture);

        //Check that the two ingredients are equal
        assertTrue(ingredient1.equals(ingredient2));

        //Prepare one of the ingredients and check that they are no longer equal
        ingredient1.prepare();
        assertFalse(ingredient1.equals(ingredient2));
    }
}

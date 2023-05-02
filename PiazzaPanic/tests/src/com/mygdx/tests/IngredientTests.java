package com.mygdx.tests;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Food.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * This class tests all methods in the ingredient class
 * @Author - Muaz
 */
@RunWith(GdxTestRunner.class)
public class IngredientTests {

    /**
     * This test checks both prepare() method and updateCurrentTexture() methods
     * It checks to see whether the ingredient can be prepared by getting its state before and after
     * It also compares the textures and checks whether the notPreparedTexture is converted to the preparedTexture
     * after preparation
     * Test 1.7.1
     */
    @Test
    public void testPrepare() {
        //Create a new Ingredient object with a not prepared texture and a prepared texture
        Texture notPreparedTexture = new Texture("lettuce.png");
        Texture preparedTexture = new Texture("prepdLettuce.png");
        Ingredient ingredient = new Ingredient("lettuce", notPreparedTexture, preparedTexture);

        //Check that the initial state is not prepared
        assertFalse("Test passes if the initial state is not prepared" , ingredient.getState());

        //Prepare the ingredient and check that the state is now prepared
        ingredient.prepare();
        assertTrue("Test passes if the current state is now prepared" , ingredient.getState());

        //Update the current texture and check that it is the prepared texture
        ingredient.updateCurrentTexture();
        assertEquals("Test passes if the texture updates accordingly after preparation" ,
                preparedTexture, ingredient.getCurrentTexture());
    }


    /**
     * This test checks that two of the same ingredients when one is prepared and the other is not are not equal
     * Test 1.7.2
     */
    @Test
    public void testEquals() {
        //Create two ingredient objects with the same name and not prepared texture
        Texture notPreparedTexture = new Texture("lettuce.png");
        Texture preparedTexture = new Texture("prepdLettuce.png");
        Ingredient ingredient1 = new Ingredient("lettuce", notPreparedTexture, preparedTexture);
        Ingredient ingredient2 = new Ingredient("lettuce", notPreparedTexture, preparedTexture);

        //Check that the two ingredients are equal
        assertTrue("Test passes if both ingredients are equal" , ingredient1.equals(ingredient2));

        //Prepare one of the ingredients and check that they are no longer equal
        ingredient1.prepare();
        assertFalse("Test passes if both ingredients are now not equal" , ingredient1.equals(ingredient2));
    }
}

package com.mygdx.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.Customer;

@RunWith(GdxTestRunner.class)
public class CustomerTests {

    /**
     * This test is always true
     */
    @Test
    public void alwaysTrueTest() {
        assertTrue(true);
    }

        @Test
        public void testCustomer() {
            // Load a texture for the customer's skin
            Texture customerTexture = new Texture("path/to/your/texture.png");

            // Create an Image actor using the texture
            Image customerSkin = new Image(customerTexture);

            // Create a new Customer instance using the customerSkin actor
            Customer customer = new Customer(customerSkin);

            // Add your test cases and assertions here
        }
    }
}
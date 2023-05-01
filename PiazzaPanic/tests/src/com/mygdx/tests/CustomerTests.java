package com.mygdx.tests;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Customer;
import com.mygdx.game.Food.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.List;

import static org.junit.Assert.*;

/**
 * These tests check for everything to do with the Customer class
 * @Author - Muaz
 */
@RunWith(GdxTestRunner.class)
public class CustomerTests {

    private Actor skin;
    private Customer customer;

    /**
     * Before the tests we are creating a new customer
     */
    @Before
    public void setUp()  {
        skin = new Actor();
        customer = new Customer(skin,10);
    }

    /**
     * This test checks the constructor method in the customer class
     * It makes sure that the customer object is actually created
     * Also that all of its elements, such as customerOrder and customerBody and customerName, are not null
     * It checks that the order is not complete at time of creation and that the customer is not yet at counter
     * @Author - Muaz
     */
    @Test
    public void testConstructor() {
        assertNotNull("The customer object should not be empty" , customer);
        assertNotNull("The customer should be assigned an order" , customer.customerOrder);
        assertFalse("At the start, the customer order should not be complete" , customer.orderComplete);
        assertFalse("At the start, the customer should not already be at the counter" , customer.atCounter);
        assertNotNull("The customer body should not be empty" , customer.body);
        assertNotNull("The customer name should not be null" , customer.name);
        assertTrue("The customer should have one of the following names: Blue, Red, White or Yellow" ,
                customer.name.equals("Blue") || customer.name.equals("Red") ||
                customer.name.equals("White") || customer.name.equals("Yellow"));
    }

    /**
     * This test checks whether the customer actually reaches the counter after the move method is implemented
     */
    @Test
    public void testMoveToCounter() {
        //move the customer to the counter
        while (!customer.atCounter) {
            customer.move();
        }

        //check the customer is at the counter
        assertEquals("Passes if the customer is correctly at the counter" ,
                128,customer.body.getX(),0.01);
    }


    /**
     * This test checks to see whether after the order is complete, the customer will actually move off the screen
     * We have assumed 500 as an arbitrary value for moving off the screen
     */
    @Test
    public void testMoveOffScreen() {
        //move the customer to the counter
        while (!customer.atCounter) {
            customer.move();
        }

        //complete the customer order
        if (!customer.orderComplete) {
            //customer.customerOrder.complete();
            customer.orderComplete = true;
        }

        //move the customer offscreen. 400 is an arbitrary value and if exceeded we assume the customer is offscreen
        while (customer.body.getX() < 500) {
            customer.move();
        }

        //check that the customer is offscreen
        assertTrue("Passes if the customers x position is greater than the arbitrary value of 500" ,
                customer.body.getX() >= 500);
    }


    /**
     * This test checks if the GenerateOrder method of the customer object returns a valid order
     */
    @Test
    public void testGenerateOrder() {
        List<Order> orderOptions = customer.getOrderOptions();

        //Call the generateOrder method several times and check if returned number is within range
        for (int i = 0; i < 10; i++) {
            Order order = customer.generateOrder();
            assertTrue("Test passes if returned order contained in orderOptions list" ,
                    orderOptions.contains(order));
        }
    }
}

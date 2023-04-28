package com.mygdx.tests;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Customer;
import com.mygdx.game.Food.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.List;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class CustomerTests {

    private Actor skin;
    private Customer customer;

    @Before
    public void setUp() throws Exception {
        skin = new Actor();
        customer = new Customer(skin,10);
    }

    @Test
    public void testConstructor() {
        assertNotNull(customer);
        assertNotNull(customer.customerOrder);
        assertFalse(customer.orderComplete);
        assertFalse(customer.atCounter);
        assertNotNull(customer.body);
        assertNotNull(customer.name);
        assertTrue(customer.name.equals("Blue") || customer.name.equals("Red") ||
                customer.name.equals("White") || customer.name.equals("Yellow"));
    }

    @Test
    public void testMoveToCounter() {
        //move the customer to the counter
        while (!customer.atCounter) {
            customer.move();
        }

        //check the customer is at the counter
        assertEquals(128,customer.body.getX(),0.01);
    }


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
        while (customer.body.getX() < 400) {
            customer.move();
        }

        //check that the customer is offscreen
        assertTrue(customer.body.getX() >= 400);
    }


    @Test
    public void testGenerateOrder() {
        List<Order> orderOptions = customer.getOrderOptions();

        //Get the size of the orderOptions list
        int size = orderOptions.size();

        //Call the generateOrder method several times and check if returned number is within range
        for (int i = 0; i < 10; i++) {
            Order order = customer.generateOrder();
            assertTrue(orderOptions.contains(order));
        }
    }
}

package com.mygdx.tests;

import com.mygdx.game.Screens.GameScreen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Cook;
import com.mygdx.game.Food.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * These tests check for everything to do with the Cook class
 */
@RunWith(GdxTestRunner.class)
public class CookTests {

    /**
     * This test checks the constructor method in the cook class
     * It makes sure that the cook object is actually created
     * Also that all of its elements, such as CookBody and CookStack, are not null
     * It checks that at the start (when there are no orders), the cook is not busy
     * Test 1.5.1
     * @Author - Muaz
     */
    @Test
    public void testCookConstructor() {
        Actor actor = new Actor();
        Cook cook = new Cook(actor);
        assertNotNull("The cook object should not be empty" , cook);
        assertEquals("The actor should be the same as the cookBody element of the cook" , actor, cook.CookBody);
        assertNotNull("The cook stack should not be empty" , cook.CookStack);
        assertFalse("The cook, once created, should not be busy" , cook.isBusy);
    }

    /**
     * This test checks that the cook moves to the right place
     * The variable index is hardcoded as 3, to see if the cook will move to the third station
     * At the end an assert statement is used to check if the cook is roughly in the right place
     * Test 1.5.2
     * @Author - Muaz
     */
    @Test
    public void testCookMove() {
        Actor actor = new Actor();
        Cook cook = new Cook(actor);
        ArrayList<Integer> stations = new ArrayList<Integer>();
        stations.add(1);
        stations.add(2);
        stations.add(3);
        int index = 3; //check to see if cook properly moves to third station

        //set initial position of cook
        actor.setX(5);
        actor.setY(5);

        //set speedmodifier
        Float speedmodifier = 1F;

        //move the cook towards target station
        boolean hasReached = false;
        while (!hasReached) {
            cook.move(index,actor,stations,speedmodifier);
            double distance = Math.sqrt(Math.pow(actor.getX() - cook.getLocations()[index][0],2 +
                    Math.pow(actor.getY() - cook.getLocations()[index][1],2)));
            if (distance <= 0.5) {
                hasReached = true;
            }
        }

        //check if the cook has reached the target station
        assertEquals(28f, actor.getY(),speedmodifier);
        assertEquals(0f,actor.getX(),speedmodifier);
    }


    /**
     * This test does not work. It is meant to test whether the flip button on the frying station works
     * This is due to deprecated Gradle features
     * Test 1.5.3
     * @Author - Teddy
     */
//    @Test
//    public void testFlipButton() {
//        // set up the cook and frying station
//        Actor actor = new Actor();
//        Cook cook = new Cook(actor);
//        actor.setX(32f);
//        actor.setY(64f);
//        ArrayList<Integer> stations = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
//        int stationIndex = 1; // frying station
//        float speedmodifier = 1f;
//
//        // move the cook to the frying station
//        boolean hasReached = false;
//        while (!hasReached) {
//            cook.move(stationIndex, actor, stations, speedmodifier);
//            if (Math.abs(actor.getX() - cook.getLocations()[stationIndex][0]) <= 0.5 &&
//                    Math.abs(actor.getY() - cook.getLocations()[stationIndex][1]) <= 0.5) {
//                hasReached = true;
//            }
//        }
//
//        // verify that the cook is not busy and there are no ingredients in the stack
//        assertFalse(cook.isBusy);
//        assertTrue(cook.CookStack.isEmpty());
//
//        // wait for the flip button to appear
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // simulate clicking the flip button
//        GameScreen.fryingClicked++;
//
//        // wait for the patty to cook
//        long startTime = System.currentTimeMillis();
//        hasReached = false;
//        while (!hasReached && System.currentTimeMillis() - startTime < 10000) { // loop for at most 10 seconds
//            try {
//                Thread.sleep(100); // sleep for 100 milliseconds before checking again
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (!cook.isBusy && !cook.CookStack.isEmpty()) {
//                hasReached = true;
//            }
//        }
//        //assertTrue(hasReached); // verify that the patty has been cooked
//        Ingredient cookedPatty = cook.CookStack.peek();
//        assertEquals("prepdPatty.png", cookedPatty.getCurrentTexture().toString());
//    }
}
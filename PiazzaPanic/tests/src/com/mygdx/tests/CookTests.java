package com.mygdx.tests;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Cook;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Screens.GameScreen;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(GdxTestRunner.class)
public class CookTests {

    @Test
    public void testCookConstructor() {
        Actor actor = new Actor();
        Cook cook = new Cook(actor);
        assertNotNull(cook);
        assertEquals(actor, cook.CookBody);
        assertNotNull(cook.CookStack);
        assertFalse(cook.isBusy);
    }

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
            if (Math.abs(actor.getX() - cook.getLocations()[index][0]) <= 0.5 &&
                Math.abs(actor.getY() - cook.getLocations()[index][1]) <= 0.5) {
                hasReached = true;
            }
        }

        //check if the cook has reached the target station
        assertEquals(32f, actor.getY(),1f);
        assertEquals(0f,actor.getX(),1f);
    }

    @Test
    public void testFlipButton() {
        // set up the cook and frying station
        Actor actor = new Actor();
        Cook cook = new Cook(actor);
        actor.setX(32f);
        actor.setY(64f);
        ArrayList<Integer> stations = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
        int stationIndex = 1; // frying station
        float speedmodifier = 1f;

    // move the cook to the frying station
        boolean hasReached = false;
        while (!hasReached) {
            cook.move(stationIndex, actor, stations, speedmodifier);
            if (Math.abs(actor.getX() - cook.getLocations()[stationIndex][0]) <= 0.5 &&
                    Math.abs(actor.getY() - cook.getLocations()[stationIndex][1]) <= 0.5) {
                hasReached = true;
            }
        }

    // verify that the cook is not busy and there are no ingredients in the stack
        assertFalse(cook.isBusy);
        assertTrue(cook.CookStack.isEmpty());

    // wait for the flip button to appear
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    // simulate clicking the flip button
        fryingClickable.getListeners().get(0).clicked(null, 0, 0);

    // wait for the patty to cook
        hasReached = false;
        while (!hasReached) {
            cook.updateCookStack();
            if (!cook.isBusy && !cook.CookStack.isEmpty()) {
                hasReached = true;
            }
        }

    // verify that the cook is not busy and the patty has been cooked
        assertFalse(cook.isBusy);
        assertFalse(cook.CookStack.isEmpty());
        Ingredient cookedPatty = cook.CookStack.peek();
        assertEquals("patty", cookedPatty.getName());
        assertEquals("prepdPatty.png", cookedPatty.getCurrentTexture().toString());
    }
}
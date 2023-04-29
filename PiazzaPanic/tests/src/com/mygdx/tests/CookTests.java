package com.mygdx.tests;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Cook;
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

        //move the cook towards target station
        boolean hasReached = false;
        while (!hasReached) {
            cook.move(index,actor,stations);
            if (Math.abs(actor.getX() - cook.getLocations()[index][0]) <= 0.5 &&
                Math.abs(actor.getY() - cook.getLocations()[index][1]) <= 0.5) {
                hasReached = true;
            }
        }

        //check if the cook has reached the target station
        assertEquals(32f, actor.getY(),1f);
        assertEquals(0f,actor.getX(),1f);
    }
}






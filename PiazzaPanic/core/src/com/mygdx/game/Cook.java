package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Screens.GameScreen;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Stack;

// it would probably be a good idea to move a lot of the code from GameScreen to here
/**
 * The class Cook represents the playable characters in the game
 */

public class Cook {
    public Stack<Ingredient> CookStack;
    public Actor CookBody;
    public float[][] locations = {{0, 64}, {32, 64}, {64, 64}, {0, 32}, {48, 28}, {80, 48}};
    public boolean isBusy = false;
    private boolean hasReached = false;

    /**
     * Cook constructor
     */
    public Cook(Actor skin) {
        this.CookBody = skin;
        this.CookStack = new Stack<>();
    }

    /**
     * move method moves the cook between the workstations
     */
    public void move(int index, Actor cook, ArrayList<Integer> stations, Float speedModifier) {
        for (int station : stations) {
            if (index != station) {
                // method to move a cook from their current position to a station
                if (cook.getX() != locations[index][0] || cook.getY() != locations[index][1]) {
                    // calculate the difference between 2 points to move the sprite towards
                    float pathX = locations[index][0] - cook.getX();
                    float pathY = locations[index][1] - cook.getY();
                    // use Pythagoras to find the distance between current position and final position
                    float distance = (float) Math.sqrt(pathX * pathX + pathY * pathY);
                    float directionX = pathX / distance;
                    float directionY = pathY / distance;
                    // stop cook from moving once close enough to the station
                    float speed;
                    if (distance < 1) {
                        speed = 0f;
                        //hasReached = true;
                    }
                    else {
                        speed = 50f * speedModifier;
                    }

                    cook.setX(cook.getX() + directionX * (speed * Gdx.graphics.getDeltaTime()));
                    cook.setY(cook.getY() + directionY * (speed * Gdx.graphics.getDeltaTime()));
                }
            }
        }
    }
    public float[][] getLocations() {
        return locations;
    }
}

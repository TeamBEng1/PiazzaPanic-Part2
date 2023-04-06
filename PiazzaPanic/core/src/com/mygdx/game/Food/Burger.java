package com.mygdx.game.Food;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The class Burger represents the burger item in the game, which inherits from the recipe class
 */
public class Burger implements Recipe {
    ArrayList<Ingredient> burgerRecipe;
    Texture burgerTexture;
    Texture speechBubble;

    /**
     * Constructor for the Burger class
     */
    public Burger() {
        this.burgerRecipe = new ArrayList<Ingredient>();
        Ingredient patty = new Ingredient("patty", new Texture("rawPatty.png"), new Texture("prepdPatty.png"));
        patty.prepare();
        burgerRecipe.add(patty);

        Ingredient buns = new Ingredient("buns", new Texture("buns.png"), new Texture("buns.png"));
        buns.prepare();
        burgerRecipe.add(buns);

        Ingredient lettuce = new Ingredient("lettuce", new Texture("lettuce.png"), new Texture("prepdLettuce.png"));
        lettuce.prepare();
        burgerRecipe.add(lettuce);

        this.burgerTexture = new Texture("burger.png");
        this.speechBubble = new Texture("orderBurgerBubble.png");
    }

    /**
     * Getter function for the burgerRecipe attribute
     * @return the recipe array for the recipe
     */
    @Override
    public ArrayList<Ingredient> getRecipe() {
        return burgerRecipe;
    }

    /**
     * Getter function for the burgerTexture attribute
     * @return the texture
     */
    @Override
    public Texture getTexture() {
        return burgerTexture;
    }

    /**
     * Getter function for the speechBubble attribute
     * @return the speech bubble
     */
    @Override
    public Texture getSpeechBubbleTexture() {
        return speechBubble;
    }

    /**
     * Function for checking if a chef has all the ingredients to make a burger
     * @return the boolean true or false for if the chef has all ingredients
     */
    @Override
    public Boolean has(Stack<Ingredient> ingredients) {
        boolean foundBuns = false;
        boolean foundPatty = false;
        boolean foundLettuce = false;
        Ingredient buns = new Ingredient("buns", null, null);
        buns.prepare();
        Ingredient patty = new Ingredient("patty", null, null);
        patty.prepare();
        Ingredient lettuce = new Ingredient("lettuce", null, null);
        lettuce.prepare();
        for (Ingredient ingredient : ingredients) {
            if (ingredient.equals(buns)) {
                foundBuns = true;
            }
            if (ingredient.equals(patty)) {
                foundPatty = true;
            }
            if (ingredient.equals(lettuce)) {
                foundLettuce = true;
            }
        }
        return foundBuns && foundLettuce && foundPatty;
    }
}

package com.mygdx.game.Food;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Stack;


/**
 * TODO:
 * Add ability to grate cheese?
 * Cook pizza (currently you just collect all raw ingredients)
 */

public class Pizza implements Recipe{
    ArrayList<Ingredient> pizzaRecipe;
    Texture pizzaTexture;
    Texture speechBubble;

    /**
     * Constructor for Pizza class
     */

    public Pizza() {
        this.pizzaRecipe = new ArrayList<Ingredient>();
        Ingredient cheese = new Ingredient("cheese", new Texture("cheese.png"), new Texture("cheese.png"));
        cheese.prepare();
        pizzaRecipe.add(cheese);
        Ingredient tomato = new Ingredient("tomato", new Texture("tomato.png"), new Texture("prepdTomato.png"));
        tomato.prepare();
        pizzaRecipe.add(tomato);
        Ingredient base = new Ingredient("pizzaBase", new Texture("pizzaBase.png"), new Texture("pizzaBase.png"));
        base.prepare();
        pizzaRecipe.add(base);
        this.pizzaTexture = new Texture("pizza.png");
        this.speechBubble = new Texture("orderPizzaBubble.png");
    }

    /**
     * Getter function for burgerRecipe
     * @return recipe array
     */
    @Override
    public ArrayList<Ingredient> getRecipe() { return pizzaRecipe; }

    /**
     * Setter for burgerTexture
     * @return texture
     */

    @Override
    public Texture getTexture() { return pizzaTexture; }

    /**
     * Getter function for the speechBubble attribute
     * @return the speech bubble
     */
    @Override
    public Texture getSpeechBubbleTexture() {
        return speechBubble;
    }

    /**
     * Checks if chef has all ingredients to make pizza
     * @return True if so, otherwise false
     */
    @Override
    public Boolean has(Stack<Ingredient> ingredients) {
        boolean foundCheese = false;
        boolean foundTomato = false;
        boolean foundBase = false;
        Ingredient cheese = new Ingredient("cheese", null, null);
        cheese.prepare();
        Ingredient tomato = new Ingredient("tomato", null, null);
        tomato.prepare();
        Ingredient base = new Ingredient("pizzaBase", null, null);
        base.prepare();

        for(Ingredient ingredient : ingredients) {
            if(ingredient.equals(cheese)) {
                foundCheese = true;
            }
            if(ingredient.equals(tomato)) {
                foundTomato = true;
            }
            if(ingredient.equals(base)) {
                foundBase = true;
            }
        }
        return foundCheese && foundTomato && foundBase;
    }
}

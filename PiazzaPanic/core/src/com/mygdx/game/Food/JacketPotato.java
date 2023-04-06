package com.mygdx.game.Food;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.Stack;

public class JacketPotato implements Recipe {
    ArrayList<Ingredient> jPotatoRecipe;
    Texture jPotatoTexture;
    Texture speechBubble;

    /**
     * Constructor for JacketPotato class
     */

    public JacketPotato() {
        this.jPotatoRecipe = new ArrayList<Ingredient>();
        Ingredient potato = new Ingredient("potato", new Texture("rawPotato.png"), new Texture("prepdPotato.png"));
        potato.prepare();
        jPotatoRecipe.add(potato);

        Ingredient cheese = new Ingredient("cheese", new Texture("cheese.png"), new Texture("cheese.png"));
        cheese.prepare();
        jPotatoRecipe.add(cheese);

        this.jPotatoTexture = new Texture("jacketPotato.png");
        this.speechBubble = new Texture("orderJacketPotatoBubble.png");
    }

    /**
     * Getter function for jPotatoRecipe
     * @return recipe array
     */
    @Override
    public ArrayList<Ingredient> getRecipe() { return jPotatoRecipe; }

    /**
     * Setter for jPotatoTexture
     * @return texture
     */

    @Override
    public Texture getTexture() { return jPotatoTexture; }

    /**
     * Getter function for the speechBubble attribute
     * @return the speech bubble
     */
    @Override
    public Texture getSpeechBubbleTexture() {
        return speechBubble;
    }

    /**
     * Checks if chef has all ingredients to make jacket potato
     * @return True if so, otherwise false
     */

    @Override
    public Boolean has(Stack<Ingredient> ingredients) {
        boolean foundPotato = false;
        boolean foundCheese = false;

        Ingredient cheese = new Ingredient("cheese", null, null);
        cheese.prepare();

        Ingredient potato = new Ingredient("potato", new Texture("rawPotato.png"), new Texture("prepdPotato.png"));
        potato.prepare();

        for(Ingredient ingredient : ingredients) {
            if (ingredient.equals(cheese)) {
                foundCheese = true;
            }
            if (ingredient.equals(potato)) {
                foundPotato = true;
            }
        }
        return foundPotato && foundCheese;
    }
}

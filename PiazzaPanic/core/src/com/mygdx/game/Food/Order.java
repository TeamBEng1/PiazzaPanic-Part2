package com.mygdx.game.Food;

import com.badlogic.gdx.graphics.Texture;

public class Order {
    Texture orderTex;
    Recipe orderRecipe;
    String name;
    //default order time, 40 seconds
    public Integer orderTime = 40;

    /**
     * Constructor for the Order class
     */
    public Order(String name,Texture orderTexture, Recipe orderRecipe){
        this.name = name;
        this.orderTex = orderTexture;
        this.orderRecipe = orderRecipe;

    }

    /**
     * Getter function to return the current texture of the order
     * @return the texture for order
     */
    public Texture getOrderTexture(){
        return this.orderTex;
    }

    /**
     * Getter function to return the recipe of the order
     * @return the recipe for order
     */
    public Recipe getRecipe(){
        return this.orderRecipe;
    }

    /**
     * Getter function to return the name of the order
     * @return the name for order
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter function to return the order time of the order
     * @return the order time for order
     */
    public Integer getOrderTime(){
        return this.orderTime;
    }

    /**
     * Setter function to set the order time of the order
     * @param orderTime the integer value to set the order time to
     */
    public void setOrderTime(Integer orderTime){
        this.orderTime = orderTime;
    }
}

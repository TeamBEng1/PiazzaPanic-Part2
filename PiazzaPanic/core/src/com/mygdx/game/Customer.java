package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.Food.Burger;
import com.mygdx.game.Food.Order;
import com.mygdx.game.Food.Salad;
import com.mygdx.game.Food.Pizza;
import com.mygdx.game.Food.JacketPotato;
import com.mygdx.game.Screens.GameScreen;

import java.util.ArrayList;
import java.util.List;

/**
 * The Customer class represents the non-playable customer character
 */
public class Customer {
    // god we love using arrays for dealing with this stuff
    private final ArrayList<Order> orderOptions = new ArrayList<>();
    private final float targetY = MathUtils.random(16, 48);
    public boolean orderComplete = false;
    public boolean atCounter = false;
    public Actor body;
    public String name;
    public Order customerOrder;

    /**
     * Customer constructor
     */
    public Customer(Actor skin, int orderTime) {
        String[] names = {"Blue", "Red", "White", "Yellow"};
        this.name = names[MathUtils.random(0, 3)];
        this.body = skin;
        this.body.setWidth(16);
        this.body.setHeight(23);
        this.body.setX(144);
        this.body.setY(80);
        if(GameScreen.burgerBought){
        orderOptions.add(new Order("burger", new Texture("orderBurger.png"), new Burger(), orderTime));}
        orderOptions.add(new Order("salad", new Texture("orderSalad.png"), new Salad(), orderTime));
        if(GameScreen.pizzaBought){
        orderOptions.add(new Order("pizza", new Texture("orderPizza.png"), new Pizza(), orderTime));}
        if(GameScreen.jacketPotatoBought){
        orderOptions.add(new Order("jacketPotato", new Texture("orderJacketPotato.png"), new JacketPotato(), orderTime));}

        this.customerOrder = generateOrder();
    }
/**
 * The Move method is the same move method from Cook. It sets the target destination and moves the customer there in a straight line
 */
    public void move() {
        // method to move a cook from their current position to a station
        if (!atCounter) {
            if (body.getY() != targetY) {
                body.setY(body.getY() - 50 * Gdx.graphics.getDeltaTime());
                if (Math.abs(body.getY() - targetY) < 1) {
                    body.setY(targetY);
                }
            } else {
                // once close enough to the target positions snap the customer to it
                if (body.getX() != 128) {
                    body.setX(body.getX() - 1);
                    if (Math.abs(body.getX() - 128) < 1) {
                        body.setX(128);
                    }
                } else {
                    atCounter = true;
                }
            }
        } else if (orderComplete) {
            // if an order is complete, move the customer offscreen to the right
            body.setX(body.getX() + 50 * Gdx.graphics.getDeltaTime());
        }
    }
/**
 * Generates a random number for the order
 * @return the random number
 */
    public Order generateOrder() {
        return orderOptions.get(MathUtils.random(0, orderOptions.size() - 1));
    }

    public List<Order> getOrderOptions() {
        return orderOptions;
    }
}



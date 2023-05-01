package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.Cook;
import com.mygdx.game.Customer;

import com.mygdx.game.Food.Burger;
import com.mygdx.game.Food.Ingredient;
import com.mygdx.game.Food.Order;
import com.mygdx.game.Food.Salad;
import com.mygdx.game.Food.Pizza;
import com.mygdx.game.Food.JacketPotato;

import com.mygdx.game.GameManager;
import com.mygdx.game.PiazzaPanic;
import jdk.internal.net.http.common.SequentialScheduler;

import java.io.Console;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.Random;

/**
 * The class GameScreen represents the screen and all displayed assets within the game, which inherits from the Screen interface
 */
public class GameScreen implements Screen {

    public GameManager manager;
    // if customer number = 0, then endless mode
    private final int customerNumber;
    private final int difficulty;
    PiazzaPanic game;
    FitViewport view;
    Stage gameStage;
    // map and camera stuff
    TmxMapLoader mapLoader;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera gameCam;

    //Start the game with 3 reputation points
    int Rep = 3;
    Texture RepLabel = new Texture("REP.png");
    Texture RepPoint = new Texture("REPHeart.png");
    // customer number determines how many customers will spawn over the course of the game
    // 0 means infinite
    public final Array<Cook> cooks;
    private final Array<Customer> customers;
    Integer orderTime;

    // sprite handling
    Sprite alex;
    Sprite amelia;
    Sprite adam;
    FileHandle charIdles;
    Skin skin;
    Skin customerSkins;
    ArrayList<Sprite> idles = new ArrayList<>();

    // cook and customer control variables
    int selected = 0;
    ArrayList<Integer> stationSelected = new ArrayList<>();

    // control the number of cooks
    int cookCount = 3; // control how many cooks spawn -> update to allow for the value to increase

    // take the time at the start of the game to display the time taken to complete the round
    Instant gameTime = Instant.now();

    // controls how fast the timer bar goes down (eg for chopping/baking)
    float barStep;

    // list of active orders
    ArrayList<Order> orders = new ArrayList<>();
    //used to count how much time has passed after an order is placed
    float timeCount = 0;
    //order timer font
    BitmapFont font = new BitmapFont();
    // progress bars
    HashMap<ProgressBar, Cook> bars;
    // music
    Music alienJazz = Gdx.audio.newMusic(Gdx.files.internal("Alien_Jazz_Ridley_Coyte.mp3"));
    // stations
    ImageButton pantryClickable;
    ImageButton fryingClickable;
    int fryingClicked = 0;
    boolean pattyAtFrying = false;
    boolean flippedTwice = false;
    Texture flipBtn = new Texture("flipBtn.png");
    ImageButton bakingClickable;
    ImageButton cuttingClickable;
    ImageButton binClickable;
    ImageButton servingClickable;
    //pantry and serving screen frames
    TextureRegion pantryScreenFrameRegion;
    ImageButton pantryScreenFrame;
    TextureRegion servingScreenFrameRegion;
    ImageButton servingScreenFrame;
    //clickables
    ImageButton XbtnClickable;
    ImageButton lettuceClickable;
    ImageButton tomatoClickable;
    ImageButton bunsClickable;
    ImageButton pattyClickable;
    ImageButton cheeseClickable;
    ImageButton baseClickable;
    ImageButton potatoClickable;

    //powerup clickables - Oli
    ImageButton powerupBlue;
    ImageButton powerupGreen;
    ImageButton powerupPurple;
    ImageButton powerupRed;
    ImageButton powerupRed2;
    ImageButton powerupYellow;
    ImageButton[] powerups = {powerupBlue, powerupGreen, powerupPurple, powerupRed, powerupRed2, powerupYellow};
    Random rand = new Random();
    int upperbound = 15;
    int randomSpawnNumber;
    float powerupDuration;
    float powerupModifier;
    Boolean bonusMS = false;
    Boolean bonusPoints = false;
    Boolean freezeActive = false;
    Boolean bonusHaste = false;
    Boolean Invulnerability = false;
    boolean burgerFlipped = false;

    //Texture ashes = new Texture("ashes.png");
    float powerupLeft= 0;
    Boolean powerupSpawned = false;
    float powerupSpawnTime = 30f;

    //points
    int money = 0;
    int earnings;


    ImageButton burgerClickable;
    ImageButton saladClickable;
    ImageButton pizzaClickable;
    ImageButton jacketPotatoClickable;

    /**
     * when you hover over a clickable it changes the cursor to a hand this listener is added to all clickables
     */
    ClickListener cursorHovering = new ClickListener() {
        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            Gdx.graphics.setSystemCursor(SystemCursor.Hand);
        }

        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
        }
    };
    //UI elements
    Texture plateTex = new Texture("plate.png");
    Texture cookStackTitle = new Texture("cookStackTitle.png");
    Texture selectedCook = new Texture("selected.png");
    Boolean showPantryScreen = false;
    Boolean showServingScreen = false;
    private int customerCount = 0;
    // counts customers on screen

    /**
     * The initialiser and loader for the game
     * @param game the file to initialise the game window
     * @param port the viewport within the window to fit the game to the window size
     * @param  customerNumber is the gamemode. 0 = infinite, otherwise the number of customers
     *                        needed to serve to win
     * @param difficulty how hard the game is. 1=easy, 2=medium, 3=hard
     */
    public GameScreen(PiazzaPanic game, FitViewport port, int customerNumber, int difficulty) {
        this.customerNumber = customerNumber;

        this.difficulty = difficulty;
        if (this.difficulty == 1) {
            // EASY MODE!
            Rep = 4;
            orderTime = 40;
            barStep = 0.1f;
            powerupDuration = 15f;
            powerupModifier = 3f;
            earnings = 8;
        } else if (this.difficulty == 2) {
            // MEDIUM MODE!
            Rep = 3;
            orderTime = 30;
            barStep = 0.05f;
            powerupDuration = 12f;
            powerupModifier = 2f;
            earnings = 6;
        } else {
            // HARD MODE!
            Rep = 1;
            cookCount = 2;
            orderTime = 20;
            barStep = 0.04f;
            powerupDuration = 8f;
            powerupModifier = 1.5f;
            earnings = 4;
        }

        // initialise the game
        this.game = game;
        this.view = port;
        gameStage = new Stage(view, game.batch);

        // load the map and camera
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("KitchenMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gameCam = new OrthographicCamera();
        view.setCamera(gameCam);
        view.setWorldSize(192, 144);
        gameCam.position.set(view.getWorldWidth() / 2, view.getWorldHeight() / 2, 0);

        //set order timer font color
        font.setColor(Color.BLACK);
        font.getData().setScale(0.5f);

        // sprite information from the texture atlas
        TextureAtlas atlasIdle = new TextureAtlas(Gdx.files.internal("charIdle.txt"));
        TextureAtlas customersLeft = new TextureAtlas(Gdx.files.internal("customersLeft.txt"));
        skin = new Skin();
        skin.addRegions(atlasIdle);
        customerSkins = new Skin();
        customerSkins.addRegions(customersLeft);
        charIdles = Gdx.files.internal("charIdle.txt");
        adam = skin.getSprite("Adam");
        alex = skin.getSprite("Alex");
        amelia = skin.getSprite("Amelia");
        idles.add(adam);
        idles.add(alex);
        idles.add(amelia);

        // music control
        // music composed by Ridley Coyte
        alienJazz.setLooping(true);
        alienJazz.play();

        // create the instances of the cooks and first customer.
        cooks = new Array<Cook>();
        spawnCooks();
        customers = new Array<Customer>();
        customers.add(new Customer(new Actor(), orderTime));


        // array of all progressbars created (used to update all of them in updateProgressBars function)
        bars = new HashMap<ProgressBar, Cook>();

        //powerups - Oli
        powerupBlue = createImageClickable(new Texture("powerupBlue.png"),24, 24);
        powerupGreen = createImageClickable(new Texture("powerupGreen.png"),24, 24);
        powerupPurple = createImageClickable(new Texture("powerupPurple.png"), 24, 24);
        powerupRed = createImageClickable(new Texture("powerupRed.png"),24, 24);
        powerupRed2 = createImageClickable(new Texture("powerupRed2.png"),24, 24);
        powerupYellow = createImageClickable(new Texture("powerupYellow.png"),24, 24);
        powerups[0] = powerupBlue;
        powerups[1] = powerupGreen;
        powerups[2] = powerupPurple;
        powerups[3] = powerupRed;
        powerups[4] = powerupRed2;
        powerups[5] = powerupYellow;

        // blue = Freeze
        // green = bonus points
        // purple = bonus MS
        // red = faster move
        // red2 = regain heart
        // yellow = invincible



        powerupBlue.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                hidePowerup(powerupBlue);
                freezeActive = true;
                powerupLeft = 15f;
            }
        });
        gameStage.addActor(powerupBlue);

        powerupGreen.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                hidePowerup(powerupGreen);
                bonusPoints = true;
                powerupLeft = 15f;
            }
        });
        gameStage.addActor(powerupGreen);

        powerupPurple.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                hidePowerup(powerupPurple);
                bonusMS = true;
                powerupLeft = 15f;
            }
        });
        gameStage.addActor(powerupPurple);

        powerupRed.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                hidePowerup(powerupRed);
                bonusHaste = true;
                powerupLeft = 15f;
            }
        });
        gameStage.addActor(powerupRed);

        powerupRed2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //When clicked gives rep to a max of 4
                hidePowerup(powerupRed2);
                if(Rep<4){
                    Rep+=1;
                }
            }
        });
        gameStage.addActor(powerupRed2);

        powerupYellow.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                hidePowerup(powerupYellow);
                Invulnerability = true;
                powerupLeft = 15f;
            }
        });
        gameStage.addActor(powerupYellow);



        // pantry station
        pantryClickable = createImageClickable(32, 32);
        // function executes when you press on the pantry on screen
        // it sets the pantry as the currently selected station - this moves the cook to the pantry
        // when the cook arrives the pantry screen is shown
        pantryClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stationSelected.set(selected, 0);
                if ((Math.abs(cooks.get(selected).CookBody.getY() - 64f) < 2) && (Math.abs(cooks.get(selected).CookBody.getX() - 0f) < 2)) {
                    showPantryScreen = true;
                    cooks.get(selected).isBusy = true;
                }
            }
        });
        gameStage.addActor(pantryClickable);

        // frying station
        fryingClickable = createImageClickable(32, 32);
        // function executes when you press on the frying station on screen
        // it sets the frying station as the currently selected station - this moves the cook to the frying station
        fryingClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stationSelected.set(selected, 1);
                //boolean ingredientAtStation = false;
                Ingredient cookedPatty = new Ingredient("patty", new Texture("rawPatty.png"), new Texture("prepdPatty.png"));
                cookedPatty.prepare();
                cookedPatty.updateCurrentTexture();
                if ((Math.abs(cooks.get(selected).CookBody.getY() - 64f) < 2) && (Math.abs(cooks.get(selected).CookBody.getX() - 32f) < 2)) {
                    if (!(cooks.get(selected).isBusy)) {
                        // used to limit to preping only one ingredient per press
                        boolean ingredientDone = false;
                        Ingredient selectedIngredient = null;
                        // preps the first vegetable in the current cook's stack after pressing the station again
                        // while busy creates a progress bar to indicate when the cook can move again
                        for (Ingredient ingredient : cooks.get(selected).CookStack) {
                            if ((ingredient.name == "patty") && (!ingredient.getState()) && (!ingredientDone)) {
                                selectedIngredient = ingredient;
                            }
                        }
                        if (!(selectedIngredient == null)) {
                            cooks.get(selected).isBusy = true;
                            flippedTwice = false;
                            createProgressBar(24, 86, cooks.get(selected));
                            fryingClicked++;
                            // used for the flipping mechanism (the station has to be pressed twice for the patty to be prepared)
                            if ((fryingClicked) % 2 == 0) {
                                burgerFlipped = true;
                                ingredientDone = true;
                                cooks.get(selected).CookStack.push(cookedPatty);
                                pattyAtFrying = false;
                            } else {
                                cooks.get(selected).CookStack.remove(selectedIngredient);
                                pattyAtFrying = true;
                            }
                        } else {
                            // create message to indicate that there are no ingredients in the current cook's stack to be prepared

                            if (pattyAtFrying) {
                                flippedTwice = true;
                                //System.out.println("Patty flipped!");
                                burgerFlipped = true;

                                cooks.get(selected).isBusy = true;
                                createProgressBar(24, 86, cooks.get(selected));
                                fryingClicked++;

                                cooks.get(selected).CookStack.push(cookedPatty);
                                pattyAtFrying = false;
                            }
                        }
                    }
                }
            }
        });
        gameStage.addActor(fryingClickable);

        // baking station
        bakingClickable = createImageClickable(32, 32);
        // function executes when you press on the baking station on screen
        // it sets the baking station as the currently selected station - this moves the cook to the baking station
        bakingClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stationSelected.set(selected, 2);

                Ingredient cookedPotato = new Ingredient("potato", new Texture("rawPotato.png"), new Texture("prepdPotato.png"));
                cookedPotato.prepare();
                cookedPotato.updateCurrentTexture();

                if ((Math.abs(cooks.get(selected).CookBody.getY() - 64f) < 2) && (Math.abs(cooks.get(selected).CookBody.getX() - 64f) < 2)) {
                    // limit only preparing one ingredient per click
                    boolean ingredientDone = false;
                    Ingredient selectedIngredient = null;

                    // prepares first valid thing in cuurent cook's stack after clicking station
                    // while busy creates status bar
                    for (Ingredient ingredient : cooks.get(selected).CookStack) {
                        if ((ingredient.name == "potato") && (!ingredient.getState()) && (!ingredientDone)) {
                            selectedIngredient = ingredient;
                        }
                    }

                    if(!(selectedIngredient == null)) {
                        cooks.get(selected).isBusy = true;
                        createProgressBar(64, 86, cooks.get(selected));
                        selectedIngredient.prepare();
                        selectedIngredient.updateCurrentTexture();
                        ingredientDone = true;
                    }
                }
            }
        });
        gameStage.addActor(bakingClickable);

        // bin station
        binClickable = createImageClickable(32, 32);
        // function exectutes when you press on the bin station on screen
        // it sets the bin station as the currently selected station - this moves the cook to the bin station
        // if the cook is by the bin and presses on the bin it deletes the top ingredient on the current cook's stack
        binClickable.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {

                stationSelected.set(selected, 3);
                // if statement that checks if the current cook is at the bin
                if ((Math.abs(cooks.get(selected).CookBody.getY() - 32f) < 2) && (Math.abs(cooks.get(selected).CookBody.getX() - 0f) < 2)) {
                    if (cooks.get(selected).CookStack.size() > 0) {
                        cooks.get(selected).CookStack.pop();
                    }
                }
            }
        });
        gameStage.addActor(binClickable);

        // cutting station
        cuttingClickable = createImageClickable(64, 32);
        // function exectutes when you press on the cutting station on screen
        // it sets the cutting station as the currently selected station - this moves the cook to the cutting station
        cuttingClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stationSelected.set(selected, 4);
                if ((Math.abs(cooks.get(selected).CookBody.getY() - 28f) < 2) && (Math.abs(cooks.get(selected).CookBody.getX() - 48f) < 2)) {
                    // Can only prep if the cook is not busy
                    if (!(cooks.get(selected).isBusy)) {
                        // used to limit to preping only one ingredient per press
                        boolean ingredientDone = false;
                        // preps the first vegetable in the current cook's stack after pressing the station again
                        // while busy creates a progress bar to indicate when the cook can move again
                        Ingredient selectedIngredient = null;
                        for (Ingredient ingredient : cooks.get(selected).CookStack) {
                            if ((ingredient.name == "lettuce") && (!ingredient.getState()) && (!ingredientDone)) {
                                selectedIngredient = ingredient;
                            } else if ((ingredient.name == "tomato") && (!ingredient.getState()) && (!ingredientDone)) {
                                selectedIngredient = ingredient;
                            }
                            else if ((ingredient.name == "cheese")&&(!ingredient.getState())&&(!ingredientDone)){
                                selectedIngredient = ingredient;
                            }
                        }
                        if (!(selectedIngredient == null)) {
                            cooks.get(selected).isBusy = true;
                            createProgressBar(40, 50, cooks.get(selected));
                            selectedIngredient.prepare();
                            selectedIngredient.updateCurrentTexture();
                            ingredientDone = true;
                        }
                    }
                }
            }
        });
        gameStage.addActor(cuttingClickable);

        // serving station
        servingClickable = createImageClickable(32, 56);
        // function exectutes when you press on the serving station on screen
        // it sets the serving station as the currently selected station - this moves the cook to the serving station
        // when the cook arrives the serving screen is shown
        servingClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                stationSelected.set(selected, 5);
                if ((Math.abs(cooks.get(selected).CookBody.getY() - 48f) < 2) && (Math.abs(cooks.get(selected).CookBody.getX() - 80f) < 2)) {
                    showServingScreen = true;
                    cooks.get(selected).isBusy = true;
                }
            }
        });
        gameStage.addActor(servingClickable);

        // adding the station clickables to the screen
        pantryClickable.setPosition(0, 64);
        fryingClickable.setPosition(32, 64);
        bakingClickable.setPosition(64, 64);
        binClickable.setPosition(0, 0);
        cuttingClickable.setPosition(32, 0);
        servingClickable.setPosition(96, 16);

        // adding powerups to the screen - Oli
        powerupBlue.setPosition(0, -1200);
        powerupGreen.setPosition(24,-1200);
        powerupPurple.setPosition(48,-1020);
        powerupRed.setPosition(72,-1020);
        powerupRed2.setPosition(96,-1020);
        powerupYellow.setPosition(130,-1020);

        // close button for station pop ups
        XbtnClickable = createImageClickable(new Texture("Xbtn.png"), 16, 16);
        // function executes after clicking on the close button
        // hides any menus that pop up
        XbtnClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                hidePantryScreen();
                hideServingScreen();
                cooks.get(selected).isBusy = false;
            }
        });

        // pantry screen frame
        pantryScreenFrameRegion = new TextureRegion(new Texture("pantryFrame.png"));
        pantryScreenFrame = new ImageButton(new TextureRegionDrawable(pantryScreenFrameRegion));
        pantryScreenFrame.setSize(140, 92);

        /* Pantry screen buttons
         The functions executes after clicking on any of the ingredient buttons on the pantry screen
         Addes the ingredient to the current cook's stack (if it's less than 5 items) */

        // unprepared lettuce button
        lettuceClickable = createImageClickable(new Texture("lettuce.png"), 24, 24);
        lettuceClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (cooks.get(selected).CookStack.size() < 5) {
                    cooks.get(selected).CookStack.push(new Ingredient("lettuce", new Texture("lettuce.png"), new Texture("prepdLettuce.png")));
                }
            }
        });

        // unprepared tomato button
        tomatoClickable = createImageClickable(new Texture("tomato.png"), 24, 24);
        tomatoClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (cooks.get(selected).CookStack.size() < 5) {
                    cooks.get(selected).CookStack.push(new Ingredient("tomato", new Texture("tomato.png"), new Texture("prepdTomato.png")));
                }
            }
        });

        // unprepared buns button
        bunsClickable = createImageClickable(new Texture("buns.png"), 24, 24);
        bunsClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (cooks.get(selected).CookStack.size() < 5) {
                    Ingredient buns = new Ingredient("buns", new Texture("buns.png"), new Texture("buns.png"));
                    buns.prepare();
                    cooks.get(selected).CookStack.push(buns);
                }
            }
        });

        // unprepared patty button
        pattyClickable = createImageClickable(new Texture("rawPatty.png"), 24, 24);
        pattyClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (cooks.get(selected).CookStack.size() < 5) {
                    cooks.get(selected).CookStack.push(new Ingredient("patty", new Texture("rawPatty.png"), new Texture("prepdPatty.png")));
                }
            }
        });

        // unprepared cheese button
        cheeseClickable = createImageClickable(new Texture("cheese.png"), 24, 24);
        cheeseClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (cooks.get(selected).CookStack.size() < 5) {
                    cooks.get(selected).CookStack.push(new Ingredient("cheese", new Texture("cheese.png"), new Texture("prepdCheese.png")));
                }
            }
        });

        //Pizza Base Ingredient button
        baseClickable = createImageClickable(new Texture("pizzaBase.png"), 24, 24);
        baseClickable.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if (cooks.get(selected).CookStack.size() < 5){
                    Ingredient base = new Ingredient("pizzaBase", new Texture("pizzaBase.png"), new Texture("pizzaBase.png"));
                    base.prepare();
                    cooks.get(selected).CookStack.push(base);
                }
            }
        });

        // unprepared potato button
        potatoClickable = createImageClickable(new Texture("rawPotato.png"), 24, 24);
        potatoClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (cooks.get(selected).CookStack.size() < 5) {
                    cooks.get(selected).CookStack.push(new Ingredient("potato", new Texture("rawPotato.png"), new Texture("prepdPotato.png")));
                }
            }
        });

        // serving screen frame
        servingScreenFrameRegion = new TextureRegion(new Texture("servingFrame.png"));
        servingScreenFrame = new ImageButton(new TextureRegionDrawable(servingScreenFrameRegion));
        servingScreenFrame.setSize(140, 92);

         /* Serving screen buttons
         The functions executes after clicking on any of the ingredient buttons on the serving screen
         Serves the item if all the required prepared ingredients are in the current cook's stack */

        // burger button
        burgerClickable = createImageClickable(new Texture("burger.png"), 24, 24);
        burgerClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Burger recipe = new Burger();
                Ingredient buns = new Ingredient("buns", null, null);
                buns.prepare();
                Ingredient patty = new Ingredient("patty", null, null);
                patty.prepare();
                Ingredient lettuce = new Ingredient("lettuce", null, null);
                lettuce.prepare();
                if (recipe.has(cooks.get(selected).CookStack)) {
                    if (customers.get(customerCount).customerOrder.getName() == "burger") {
                        cooks.get(selected).CookStack.remove(buns);
                        cooks.get(selected).CookStack.remove(patty);
                        cooks.get(selected).CookStack.remove(lettuce);
                        customers.get(customerCount).orderComplete = true;
                        awardMoney();
                        hideServingScreen();
                        cooks.get(selected).isBusy = false;
                    }
                } else {
                    // some or all ingredients are not in the current cook's stack
                }
            }
        });

        // salad button
        saladClickable = createImageClickable(new Texture("salad.png"), 24, 24);
        saladClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Salad recipe = new Salad();
                Ingredient tomato = new Ingredient("tomato", null, null);
                tomato.prepare();
                Ingredient lettuce = new Ingredient("lettuce", null, null);
                lettuce.prepare();
                if (recipe.has(cooks.get(selected).CookStack)) {
                    if (customers.get(customerCount).customerOrder.getName() == "salad") {
                        cooks.get(selected).CookStack.remove(tomato);
                        cooks.get(selected).CookStack.remove(lettuce);
                        customers.get(customerCount).orderComplete = true;
                        awardMoney();
                        hideServingScreen();
                        cooks.get(selected).isBusy = false;
                    }
                } else {
                    // some or all ingredients are not in the current cook's stack
                }
            }
        });

        // pizza button
        pizzaClickable = createImageClickable(new Texture("pizza.png"), 24, 24);
        pizzaClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Pizza recipe = new Pizza();
                Ingredient tomato = new Ingredient("tomato", null, null);
                tomato.prepare();
                Ingredient cheese = new Ingredient("cheese", null, null);
                cheese.prepare();
                Ingredient base = new Ingredient("pizzaBase", null, null);
                base.prepare();
                if (recipe.has(cooks.get(selected).CookStack)) {
                    if (customers.get(customerCount).customerOrder.getName() == "pizza") {
                        cooks.get(selected).CookStack.remove(tomato);
                        cooks.get(selected).CookStack.remove(cheese);
                        cooks.get(selected).CookStack.remove(base);
                        customers.get(customerCount).orderComplete = true;
                        awardMoney();
                        hideServingScreen();
                        cooks.get(selected).isBusy = false;
                    }
                } else {
                    // some or all ingredients are not in the current cook's stack
                }
            }
        });


        // jacket potato button
        jacketPotatoClickable = createImageClickable(new Texture("jacketPotato.png"), 24, 24);
        jacketPotatoClickable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                JacketPotato recipe = new JacketPotato();
                Ingredient potato = new Ingredient("potato", null, null);
                potato.prepare();
                Ingredient cheese = new Ingredient("cheese", null, null);
                cheese.prepare();

                if (recipe.has(cooks.get(selected).CookStack)) {
                    if (customers.get(customerCount).customerOrder.getName() == "jacketPotato") {
                        cooks.get(selected).CookStack.remove(potato);
                        cooks.get(selected).CookStack.remove(cheese);
                        awardMoney();
                        

                        customers.get(customerCount).orderComplete = true;
                        hideServingScreen();
                        cooks.get(selected).isBusy = false;
                    }
                } else {
                    // some or all ingredients are not in the current cook's stack
                }
            }
        });
    }

    private static TextureRegionDrawable getColoredDrawable(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
        pixmap.dispose();
        return drawable;
    }

    // Used when the clickable region has a texture
    private ImageButton createImageClickable(Texture texture, float width, float height) {
        TextureRegion region = new TextureRegion(texture);
        ImageButton clickable = new ImageButton(new TextureRegionDrawable(region));
        clickable.setSize(width, height);
        clickable.addListener(cursorHovering);
        return clickable;
    }

    // Used to create an invisible clickable region
    private ImageButton createImageClickable(int width, int height) {
        Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
        TextureRegion region = new TextureRegion(new Texture(pixmap));
        ImageButton clickable = new ImageButton(new TextureRegionDrawable(region));
        clickable.setSize(width, height);
        clickable.addListener(cursorHovering);
        return clickable;
    }

    @Override
    public void render(float delta) {
        gameCam.update();
        renderer.setView(gameCam);

        ScreenUtils.clear(0, 0, 0, 0);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.input.setInputProcessor(gameStage);
        renderer.render();

        // call functions which determine key gameplay elements
        gameStage.act();
        updateProgressBars();
        updatePowerups();
        powerupSpawnTimer();
        updateBatch();
        showCookStack();
        showStationScreens();
        showOrders(delta);
        showRepPoints();
        customerOperations();
        processInput();
        gameStage.draw();

        if (pattyAtFrying) {
            game.batch.begin();
            game.batch.draw(flipBtn, 30, 80);
            game.batch.end();
        }

        for (int i = 0; i < cookCount; i++) {
            if (!cooks.get(i).isBusy) {
                if(bonusMS){
                    cooks.get(i).move(stationSelected.get(i), cooks.get(i).CookBody, stationSelected, powerupModifier);
                }
                else{
                cooks.get(i).move(stationSelected.get(i), cooks.get(i).CookBody, stationSelected, 1f);
            }
            }
        }
    }
    private void showRepPoints(){
        game.batch.begin();
        int x = 146;
        game.batch.draw(RepLabel,130,134);
        for(int i = 0; i<Rep; i++){
            game.batch.draw(RepPoint,x,135);
            x += 6;
        }
        game.batch.end();
    }
    private void customerOperations() {
        // move the customers to the counter
        if (!customers.get(customerCount).atCounter) {
            customers.get(customerCount).move();
        } else if (customers.get(customerCount).orderComplete) {
            // make the customer leave
            customers.get(customerCount).move();

            if (customers.get(customerCount).body.getX() > 148) {
                customers.get(customerCount).body.remove();


                if (customerNumber != 0) {



                    // check if the game is not in endless mode
                    if (customerCount != customerNumber - 1) {
                        // spawn new customer
                        customers.add(new Customer(new Actor(), orderTime));
                        customerCount += 1;

                    } else {
                        // end game by taking the time at the game end and going to the time screen
                        Duration timeTaken = Duration.between(gameTime, Instant.now());
                        alienJazz.stop();
                        game.setScreen(new EndGameScreen(game, timeTaken,Rep));

                    }

                } else {
                    // I am going to be honest idk when ths code runs
                    customers.add(new Customer(new Actor(), orderTime));
                    System.out.println("One customer added");
//                    customers.add(new Customer(new Actor(), orderTime));
//                    System.out.println("Two customers added");
                    customerCount += 1;
                }
            }
        }
    }

    // generate the cooks
    public void spawnCooks() {
        for (int i = 0; i < cookCount; i++) {
            Cook cook = new Cook(new Actor());
            cook.CookBody.setWidth(16);
            cook.CookBody.setHeight(23);

            // scale information
            cook.CookBody.setScaleX(game.GAME_WIDTH / 16f);
            cook.CookBody.setScaleY(game.GAME_HEIGHT / 23f);
            // cooks are stored in an array to make it easier to keep track of all things relating to them
            // I love arrays so much
            cooks.add(cook);
            gameStage.addActor(cook.CookBody);
            stationSelected.add(i);
        }
    }

    //process user input
    private void processInput() {
        // number keys are used to select which cook is being controlled currently
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            selected = 0;
        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            selected = 1;
        }
        if (cookCount > 2 && Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
            selected = 2;
        }

        for (int i = 0; i < cooks.size; i++) {
            if (cooks.get(i).CookBody.isTouchFocusTarget()) {
                selected = i;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            // debug option to mark the current customers order as complete, moving them on
            customers.get(customerCount).orderComplete = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
              // return to main menu and save game
            /**
             * zac fix this
             * manager.setSaveGame(this);
             **/
            game.setScreen(new MainMenuScreen(game));
            alienJazz.dispose();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            // used for debugging
            // prepares all ingredients in current cook's stack
            for (Ingredient ingredient : cooks.get(selected).CookStack) {
                ingredient.prepare();
                ingredient.updateCurrentTexture();
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.N)){
            //debug - Oli
            spawnPowerup();
        }
    }

    //update the cooks on the screen
    private void updateBatch() {
        // this section assigns each cook a sprite from the list idles
        // you could potentially update this to allow for animations for the cooks when they move
        game.batch.begin();
        int index = 0;
        for (Cook cook : cooks) {
            game.batch.draw(idles.get(index), cook.CookBody.getX(), cook.CookBody.getY());
            index++;
        }
        game.batch.draw(plateTex, 164, 25);
        game.batch.draw(cookStackTitle, 164, 120);
        game.batch.draw(idles.get(selected), 168, 1);
        game.batch.draw(customerSkins.getSprite(customers.get(customerCount).name), customers.get(customerCount).body.getX(), customers.get(customerCount).body.getY());
        game.batch.draw(selectedCook, cooks.get(selected).CookBody.getX(), cooks.get(selected).CookBody.getY() + 26);
        game.batch.end();
    }

    private void showOrders(float dt) {
        // displays the orders at the top of the screen
        int x = 1;
        int y = 112;
        for (Customer customer : customers) {


            if ((customer.atCounter) && (!customer.orderComplete)) {
                timeCount += dt;

                //one second has passed
                if(timeCount >= 1){
                    //update order timer
                    if(customer.customerOrder.getOrderTime() >= 0 && freezeActive == false){
                        customer.customerOrder.orderTime --;
                    }
                    timeCount = 0;
                    if(customer.customerOrder.getOrderTime()==0){
                        Rep--;
                        customers.get(customerCount).orderComplete = true;

                        if (Rep == 0) {
                            alienJazz.stop();
                            Duration timeSurvived = Duration.between(gameTime, Instant.now());
                            game.setScreen(new EndGameScreen(game, timeSurvived,Rep));
                        }
                    }
                }
                game.batch.begin();
                game.batch.draw(customer.customerOrder.getOrderTexture(), x, y);
                game.batch.draw(customer.customerOrder.getRecipe().getSpeechBubbleTexture(), customer.body.getX() - 10, customer.body.getY() + 17);
                //order timer sets to 0 when it reaches -1

                if(customer.customerOrder.getOrderTime()>-1){
                    font.draw(game.batch, Integer.toString(customer.customerOrder.getOrderTime()), x+30, y+10);
                } else {
                    font.draw(game.batch, "0", x+30, y+10);
                }
                
                game.batch.end();
                // increase x value if there is more than one current order
                x += 41;
            }
        }
    }


    private void showCookStack() {
        // display the stack of ingredients being held by the current cook
        float x = 164;
        float y = 32;
        game.batch.begin();
        for (Ingredient ingredient : cooks.get(selected).CookStack) {
            game.batch.draw(ingredient.getCurrentTexture(), x, y);
            y += 18;
        }
        game.batch.end();
    }

    private void showStationScreens() {
        for (Cook cook : cooks) {
            if ((Math.abs(cook.CookBody.getY() - 64f) < 2) && (Math.abs(cook.CookBody.getX() - 0f) < 2)) {
                showPantryScreen();
            }
            if ((Math.abs(cook.CookBody.getY() - 48f) < 2) && (Math.abs(cook.CookBody.getX() - 80f) < 2)) {
                showServingScreen();
            }
        }
    }

    private void showServingScreen() {
        if (showServingScreen) {
            gameStage.addActor(servingScreenFrame);
            gameStage.addActor(XbtnClickable);
            XbtnClickable.toFront();
            gameStage.addActor(burgerClickable);
            gameStage.addActor(saladClickable);
            gameStage.addActor(pizzaClickable);
            gameStage.addActor(jacketPotatoClickable);

            servingScreenFrame.setPosition(10, 10);
            XbtnClickable.setPosition(7, 88);
            burgerClickable.setPosition(25, 66);
            saladClickable.setPosition(53, 66);
            pizzaClickable.setPosition(25, 36);
            jacketPotatoClickable.setPosition(53, 40);

            showServingScreen = false;
        }
    }

    private void showPantryScreen() {
        if (showPantryScreen) {
            gameStage.addActor(pantryScreenFrame);
            gameStage.addActor(XbtnClickable);
            XbtnClickable.toFront();
            gameStage.addActor(lettuceClickable);
            gameStage.addActor(tomatoClickable);
            gameStage.addActor(bunsClickable);
            gameStage.addActor(pattyClickable);
            gameStage.addActor(cheeseClickable);
            gameStage.addActor(baseClickable);
            gameStage.addActor(potatoClickable);

            pantryScreenFrame.setPosition(10, 10);
            XbtnClickable.setPosition(7, 88);
            lettuceClickable.setPosition(25, 66);
            tomatoClickable.setPosition(53, 66);
            bunsClickable.setPosition(81, 66);
            pattyClickable.setPosition(110, 72);
            cheeseClickable.setPosition(25, 34);
            baseClickable.setPosition(53, 34);
            potatoClickable.setPosition(81, 34);

            showPantryScreen = false;
        }
    }

    private void hidePantryScreen() {
        // moves pantry screen offscreen
        pantryScreenFrame.setPosition(10000, -1);
        XbtnClickable.setPosition(10000, -1);
        lettuceClickable.setPosition(10000, -1);
        tomatoClickable.setPosition(10000, -1);
        bunsClickable.setPosition(10000, -1);
        pattyClickable.setPosition(10000, -1);
        cheeseClickable.setPosition(10000, -1);
        baseClickable.setPosition(10000, -1);
        potatoClickable.setPosition(10000, -1);
    }

    private void hideServingScreen() {
        // moves serving screen offscreen
        servingScreenFrame.setPosition(10000, -1);
        XbtnClickable.setPosition(10000, -1);
        burgerClickable.setPosition(10000, -1);
        saladClickable.setPosition(10000, -1);
        pizzaClickable.setPosition(10000, -1);
        jacketPotatoClickable.setPosition(10000, -1);
    }

    public void hidePowerup(ImageButton powerup){
        //hides powerup on click and starts timer for the next one to spawn - Oli
        powerup.setPosition(0, -1000);
        powerupSpawnTime = rand.nextInt(upperbound) + 15;
        powerupSpawned = false;
    }

    public void spawnPowerup(){
        //spawns Powerup
        powerupSpawned = true;
        int numberOfPowerups = powerups.length;
        int randomInt = rand.nextInt(numberOfPowerups - 1);
        ImageButton powerup = powerups[randomInt];
        powerup.setPosition(48,120);
    }

    public void clearPowerups(){
        //clears all powerup effects in one because im lazy
        bonusMS = false;
        bonusPoints = false;
        freezeActive = false;
        bonusHaste = false;
        Invulnerability = false;
    }

    public void updatePowerups(){
        if (powerupLeft > 0){
            powerupLeft = powerupLeft - 0.017f;
            //System.out.println(String.valueOf(powerupLeft));
        }
        else{
            clearPowerups();
            //im like 99% convinced this is possibly the worst way of doing this but oh well
        }
    }

    public void awardMoney(){
        if(bonusPoints){
            money += earnings * powerupModifier;
        }
        else{
            money += earnings;
        }
        System.out.println(money);
    }

    public void powerupSpawnTimer(){
        if (powerupSpawnTime> 0){
            powerupSpawnTime = powerupSpawnTime - 0.017f;
        }
        else if(!powerupSpawned){
            spawnPowerup();
        }
    }

    public void createProgressBar(float x, float y, Cook selectedCook) {
        ProgressBarStyle style = new ProgressBarStyle();
        style.background = getColoredDrawable(20, 5, Color.GREEN);
        style.knob = getColoredDrawable(0, 5, Color.WHITE);
        style.knobAfter = getColoredDrawable(20, 5, Color.WHITE);
        ProgressBar bar = new ProgressBar(0, 7, 0.05f, false, style);
        bar.setWidth(30);
        bar.setHeight(5);
        bar.setValue(15f);
        bar.setX(x);
        bar.setY(y);
        gameStage.addActor(bar);
        bars.put(bar, selectedCook);
    }

    private void checkBurning() {
        float flipTimer = 1;
        boolean flippedinTime = false;

        System.out.println("FlipTimerStart");
        while (flipTimer > 0f) {
            if (burgerFlipped) {
                System.out.println("Flipped!");
                flippedinTime = true;
                flippedTwice = true;
                flipTimer = -5f;
            } else {
                flipTimer -= 0.0000001f;
                //System.out.println(flipTimer);
            }
        }

        if (!flippedinTime && !flippedTwice) {
            System.out.println("Burger burnt! In theory it is removed");

            Ingredient ashes = new Ingredient("ashes", new Texture("ashes.png"), new Texture("ashes.png"));

            for (Ingredient ingredient : cooks.get(selected).CookStack) {
                if (ingredient.name.equals("patty")) {
                    System.out.println("Burger removed from inventory");
                    cooks.get(selected).CookStack.remove(ingredient);
                    //cooks.get(selected).CookStack.add(ashes);
                }
            }

            cooks.get(selected).isBusy = false;
            pattyAtFrying = false;
            fryingClicked++;

            cooks.get(selected).CookStack.add(ashes);
        }
    }

    private void updateProgressBars() {
        if (!bars.isEmpty()) {
            for (ProgressBar bar : bars.keySet()) {

                if(bonusHaste){
                    bar.setValue((bar.getValue() - barStep * powerupModifier));
                }
                else{
                bar.setValue((bar.getValue() - barStep));}

                if (bar.getValue() == 0) {

                    if (pattyAtFrying && !Invulnerability) {
                        System.out.println("Checking burning");
                        checkBurning();
                    }

                    gameStage.getActors().removeValue(bar, false);
                    //unbusy the cook
                    bars.get(bar).isBusy = false;
                    bars.remove(bar);
                }
            }
        }

    }

    public int getCookCount() {
        return cookCount;
    }

    @Override
    public void resize(int width, int height) {
        view.update(width, height);
    }

    @Override
    public void show() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        game.batch.dispose();
        gameStage.dispose();
        alienJazz.dispose();
    }
}
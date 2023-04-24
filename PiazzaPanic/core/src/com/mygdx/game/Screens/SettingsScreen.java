package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.PiazzaPanic;

import java.util.concurrent.TimeUnit;

// Screen to switch between Endless and Scenario Mode
// shows after Play clicked on MainMenuScreen

public class SettingsScreen implements Screen {
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Odin Rounded - Bold.otf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont font;
    BitmapFont smallFont;

    int customerNum = 5;

    PiazzaPanic game;
    FitViewport view;
    Stage settingsStage;
    Texture optionsFrame = new Texture("OptionsFrame.png");

    // playBtn is green, playBtnYellow is yellow
    // the green buttons control endless mode,
    // the yellow ones (+ up+down) for scenario mode
    // colour coded just to make them look different
    Texture playBtnTex;
    Texture playBtnTexHover;
    Texture playBtnYellowTex;
    Texture playBtnYellowTexHover;
    Texture upBtnTex;
    Texture downBtnTex;


    // image buttons for Not hovering on them
    TextureRegion playBtnRegion;
    TextureRegionDrawable playBtnDrawable;
    ImageButton playBtn;

    TextureRegion playBtnYellowRegion;
    TextureRegionDrawable playBtnYellowDrawable;
    ImageButton playBtnYellow;

    TextureRegion upBtnRegion;
    TextureRegionDrawable upBtnDrawable;
    ImageButton upBtn;

    TextureRegion downBtnRegion;
    TextureRegionDrawable downBtnDrawable;
    ImageButton downBtn;

    // image buttons for hovering over
    TextureRegion playBtnRegionHover;
    TextureRegionDrawable playBtnDrawableHover;
    ImageButton playBtnHover;

    TextureRegion playBtnYellowRegionHover;
    TextureRegionDrawable playBtnYellowDrawableHover;
    ImageButton playBtnYellowHover;


    public SettingsScreen(PiazzaPanic game, FitViewport view) {
        this.game = game;

        parameter.size = 48;
        parameter.color = Color.BLACK;
        font = generator.generateFont(parameter);

        parameter.size = 24;
        smallFont = generator.generateFont(parameter);
    }

    /**
     * Creates listeners for play, playYellow, and the up+down buttons
     */
    @Override
    public void show() {
        // Normal buttons
        playBtnTex = new Texture("playBtn.png");
        playBtnYellowTex = new Texture("playBtnYellow.png");
        upBtnTex = new Texture("upBtn.png");
        downBtnTex = new Texture("downBtn.png");

        // Buttons being hovered over
        playBtnTexHover = new Texture("playBtn2.png");
        playBtnYellowTexHover = new Texture("playBtn2Yellow.png");

        view = new FitViewport(game.GAME_WIDTH, game.GAME_HEIGHT);
        view.getCamera().position.set(game.GAME_WIDTH / 2, game.GAME_HEIGHT / 2, 1f);
        settingsStage = new Stage(view, game.batch);


        // Regions, drawables, buttons for normal buttons
        playBtnRegion = new TextureRegion(playBtnTex);
        playBtnDrawable = new TextureRegionDrawable(playBtnRegion);
        playBtn = new ImageButton(playBtnDrawable);

        playBtnYellowRegion = new TextureRegion(playBtnYellowTex);
        playBtnYellowDrawable = new TextureRegionDrawable(playBtnYellowRegion);
        playBtnYellow = new ImageButton(playBtnYellowDrawable);

        upBtnRegion = new TextureRegion(upBtnTex);
        upBtnDrawable = new TextureRegionDrawable(upBtnRegion);
        upBtn = new ImageButton(upBtnDrawable);

        downBtnRegion = new TextureRegion(downBtnTex);
        downBtnDrawable = new TextureRegionDrawable(downBtnRegion);
        downBtn = new ImageButton(downBtnDrawable);

        // as above but for hovered buttons
        playBtnRegionHover = new TextureRegion(playBtnTexHover);
        playBtnDrawableHover = new TextureRegionDrawable(playBtnRegionHover);

        playBtnYellowRegionHover = new TextureRegion(playBtnYellowTexHover);
        playBtnYellowDrawableHover = new TextureRegionDrawable(playBtnYellowRegionHover);

        // listeners for hovering on buttons
        playBtn.addListener(new ClickListener() {
            final ImageButton playNormal = new ImageButton(playBtnDrawable);
            final ImageButton playHover = new ImageButton(playBtnDrawableHover);

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playBtn.setStyle(playHover.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                playBtn.setStyle(playNormal.getStyle());
            }
        });

        playBtnYellow.addListener(new ClickListener() {
            final ImageButton playYellowNormal = new ImageButton(playBtnYellowDrawable);
            final ImageButton playYellowHover = new ImageButton(playBtnYellowDrawableHover);

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playBtnYellow.setStyle(playYellowHover.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                playBtnYellow.setStyle(playYellowNormal.getStyle());
            }
        });

       upBtn.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

        downBtn.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }
        });

    }

    /**
     * Draws + renders settings menu screen
     */
    @Override
    public void render(float delta) {
        // this is the default, can be changed with
        // up and down buttons


        settingsStage.act();

        ScreenUtils.clear(1,1,1,1);
        view.apply();

        game.batch.setProjectionMatrix(view.getCamera().combined);
        game.batch.begin();
        game.batch.draw(optionsFrame, ((game.GAME_WIDTH / 2) - (optionsFrame.getWidth() / 2)), 10);

        font.draw(game.batch, "ENDLESS\nMODE:", 420, 580);
        font.draw(game.batch, "SCENARIO\nMODE:", 420, 380);

        GlyphLayout sF = smallFont.draw(game.batch, "Serve           " + customerNum + " people as fast as possible!", 420, 250);

        game.batch.end();

        settingsStage.getViewport().apply();

        settingsStage.addActor(playBtn);
        playBtn.setHeight(117);
        playBtn.setPosition(530, 480);

        settingsStage.addActor(playBtnYellow);
        playBtnYellow.setHeight(117);
        playBtnYellow.setPosition(530, 280);

        settingsStage.addActor(upBtn);
        upBtn.setHeight(30);
        upBtn.setPosition(420, 250);

        settingsStage.addActor(downBtn);
        downBtn.setHeight(30);
        downBtn.setPosition(416, 210);


        // code for rest of buttons etc


        if (playBtn.isPressed()){
            // Play endless mode
            game.setScreen(new GameScreen(game, view, 0));
        }

        if (playBtnYellow.isPressed()) {
            // play scenario mode
            game.setScreen(new GameScreen(game, view, customerNum));
        }


        if (upBtn.isPressed()) {

            customerNum ++;
            sF.reset();

            System.out.println("customerNum is now " + customerNum);

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("oops");
            }

        }

        if (downBtn.isPressed()) {
            customerNum --;
            sF.reset();

            if (customerNum <= 0) {
                customerNum = 1;
            }

            System.out.println("customerNum is now " + customerNum);

            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException ie) {
                System.out.println("oops");
            }

        }


        Gdx.input.setInputProcessor(settingsStage);
        settingsStage.draw();

    }


    /**
     * The resize method resizes the settingsStage to fit the screen
     */
    @Override
    public void resize(int width, int height) {
        settingsStage.getViewport().update(width, height);
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }
    /**
     * The dispose method releases all resources from the settingsStage objects
     */
    @Override
    public void dispose() {
        settingsStage.dispose();
    }


}

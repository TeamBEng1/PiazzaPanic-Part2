package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.PiazzaPanic;
import java.time.Duration;

// screen which displays after the game finishes
/**
 * The EndGameScreen class inherits from Screen and represents the screen that displays after the game is finished
 */
public class EndGameScreen implements Screen {
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Odin Rounded - Bold.otf"));
    FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    BitmapFont font;
    public PiazzaPanic game;
    FitViewport view;
    Stage screenStage;
    Texture levelCompleteFrame = new Texture("LevelCompleteFrame.png");
    Texture exitBtnTex;
    Texture exitBtnTexHover;
    ImageButton exitBtn;
    Texture restartBtnTex;
    Texture restartBtnTexHover;
    ImageButton restartBtn;
    TextureRegionDrawable exitBtnDrawable;
    TextureRegionDrawable exitBtnDrawableHover;
    TextureRegionDrawable restartBtnDrawable;
    TextureRegionDrawable restartBtnDrawableHover;
    int Rep;
    int customersServed;
    int gameMode;
    String levelTimeString;
/**
 * The EndGameScreen constructor
 */
    public EndGameScreen(PiazzaPanic game, Duration levelCompletedIn, int RepPoints, int customersServed, int gameMode) {
        // generate the styling information for the data given to this screen
        this.game = game;
        parameter.size = 48;
        parameter.color = Color.BLACK;
        font = generator.generateFont(parameter);
        levelTimeString = humanReadableFormat(levelCompletedIn);
        this.Rep = RepPoints;
        this.customersServed = customersServed;
        this.gameMode = gameMode;
    }

    /**
     * The show method creates listeners for the restart and exit buttons after loading their textures
     */
    @Override
    public void show() {
        // size information
        view = new FitViewport(game.GAME_WIDTH, game.GAME_HEIGHT);
        view.getCamera().position.set(game.GAME_WIDTH / 2, game.GAME_HEIGHT / 2, 1f);
        screenStage = new Stage(view, game.batch);

        // draw buttons
        exitBtnTex = new Texture("exitBtn.png");
        exitBtnTexHover = new Texture("exitBtn2.png");
        restartBtnTex = new Texture("RestartBtn.png");
        restartBtnTexHover = new Texture("RestartBtn2.png");

        exitBtnDrawable = new TextureRegionDrawable(new TextureRegion(exitBtnTex));
        exitBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(exitBtnTexHover));
        exitBtn = new ImageButton(exitBtnDrawable);
        exitBtn.addListener(new ClickListener() {
            final ImageButton normal = new ImageButton(exitBtnDrawable);
            final ImageButton hover = new ImageButton(exitBtnDrawableHover);

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                exitBtn.setStyle(hover.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                exitBtn.setStyle(normal.getStyle());
            }
        });

        restartBtnDrawable = new TextureRegionDrawable(new TextureRegion(restartBtnTex));
        restartBtnDrawableHover = new TextureRegionDrawable(new TextureRegion(restartBtnTexHover));
        restartBtn = new ImageButton(restartBtnDrawable);
        restartBtn.addListener(new ClickListener() {
            final ImageButton normal = new ImageButton(restartBtnDrawable);
            final ImageButton hover = new ImageButton(restartBtnDrawableHover);

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                restartBtn.setStyle(hover.getStyle());
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                restartBtn.setStyle(normal.getStyle());
            }
        });

        screenStage.addActor(exitBtn);
        exitBtn.setPosition(game.GAME_WIDTH / 2 - exitBtn.getWidth() / 2, game.GAME_HEIGHT / 2 - exitBtn.getHeight() / 2 - 250);
        screenStage.addActor(restartBtn);
        restartBtn.setPosition(game.GAME_WIDTH / 2 - restartBtn.getWidth() / 2, game.GAME_HEIGHT / 2 - restartBtn.getHeight() / 2 - 100);
    }

    /**
     * The render method draws/renders the credit screen
     */
    @Override
    public void render(float delta) {
        screenStage.act();

        ScreenUtils.clear(1, 1, 1, 1);
        view.apply();
        Gdx.input.setInputProcessor(screenStage);

        game.batch.setProjectionMatrix(view.getCamera().combined);
        game.batch.begin();
        game.batch.draw(levelCompleteFrame, ((game.GAME_WIDTH / 2) - (levelCompleteFrame.getWidth() / 2)), 10);

        if (gameMode == 0) {
            // endless mode
            // display time survived for and number of customers served
            font.draw(game.batch, "SURVIVED FOR " + levelTimeString, 420, 480);
            font.draw(game.batch, "PEOPLE SERVED: " + customersServed, 420, 425);
        } else {
            // scenario mode
            // display how fast game was completed in, and remaining lives
            font.draw(game.batch, "COMPLETED IN " + levelTimeString, 420, 480);
            font.draw(game.batch, "REPUTATION:" + Rep, 420, 425);
        }

        game.batch.end();
        screenStage.getViewport().apply();

        if (restartBtn.isPressed()) {
            game.setScreen(new SettingsScreen(game, view));
        }

        if (exitBtn.isPressed()) {
            dispose();
            game.setScreen(new MainMenuScreen(game));
        }

        screenStage.draw();
    }
    /**
     * The resize method resizes the gameStage to fit the screen
     */
    @Override
    public void resize(int width, int height) {
        screenStage.getViewport().update(width, height);
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
     * The dispose method releases all resources from the levelCompleteFrame and screenStage objects
     */
    @Override
    public void dispose() {
        levelCompleteFrame.dispose();
        screenStage.dispose();
    }
/**
 * Formats the time information
 */
    private String humanReadableFormat(Duration duration) {
        // format the time information
        String returnValue;
        /*
        return (String.format("%sm %ss",
                duration.getSeconds(),
                duration.toMinutes()));*/
        Long seconds = duration.getSeconds();
        if(seconds >= 60){
            Long minutes = seconds / 60;
            seconds = seconds % 60;
            returnValue = (Long.toString(minutes) + "m" + Long.toString(seconds) + "s");
        }
        else{
            returnValue = (Long.toString(seconds) + "s");
        }
        return (returnValue);

    }

}


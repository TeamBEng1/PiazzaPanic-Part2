package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LeaderboardScreen implements Screen {
    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Odin Rounded - Bold.otf"));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    BitmapFont endlessFont;
    BitmapFont scenarioFont;

    PiazzaPanic game;
    FitViewport view;
    Stage leaderStage;
    Texture leaderFrame = new Texture("leaderboard.png");

    Texture backBtnTex = new Texture("backBtn.png");
    Texture backBtnTexHover = new Texture("backBtn2.png");

    TextureRegion backBtnRegion;
    TextureRegionDrawable backBtnDrawable;
    ImageButton backBtn;

    TextureRegion backBtnRegionHover;
    TextureRegionDrawable backBtnDrawableHover;

    public LeaderboardScreen(PiazzaPanic game){
        this.game = game;

        parameter.size = 48;
        parameter.color = Color.BLACK;
        endlessFont = generator.generateFont(parameter);
        scenarioFont = generator.generateFont(parameter);
    }

    @Override
    public void show() {
        view = new FitViewport(game.GAME_WIDTH, game.GAME_HEIGHT);
        view.getCamera().position.set(game.GAME_WIDTH / 2, game.GAME_HEIGHT / 2, 1f);
        leaderStage = new Stage(view, game.batch);

        //Buttons
        backBtnRegion = new TextureRegion(backBtnTex);
        backBtnDrawable = new TextureRegionDrawable(backBtnRegion);
        backBtn = new ImageButton(backBtnDrawable);

        //hovered button
        backBtnRegionHover = new TextureRegion(backBtnTexHover);
        backBtnDrawableHover = new TextureRegionDrawable(backBtnRegionHover);

        backBtn.addListener(new ClickListener(){
            final ImageButton backNormal = new ImageButton(backBtnDrawable);
            final ImageButton backHover = new ImageButton(backBtnDrawableHover);
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                backBtn.setStyle(backHover.getStyle());
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                backBtn.setStyle(backNormal.getStyle());
            }
        });

        //place buttons on stage
        leaderStage.addActor(backBtn);
        backBtn.setPosition(0,game.GAME_HEIGHT-backBtn.getHeight());
    }

    public void render(float delta) {
        leaderStage.act();

        ScreenUtils.clear(1, 1, 1, 1);
        view.apply();

        game.batch.setProjectionMatrix(view.getCamera().combined);
        game.batch.begin();

        game.batch.draw(leaderFrame, ((game.GAME_WIDTH / 2) - (leaderFrame.getWidth() / 2)), 60);

        // get highscore info from files created in EndGameScreen

        String endlessHi = "";
        String scenarioHi = "";

        try {
            FileReader reader = new FileReader("endlessHighscore.txt");
            endlessHi = Integer.toString(reader.read());
            //System.out.println(reader.read());
            reader.close();

            FileReader reader2 = new FileReader("scenarioHighscore.txt");
            scenarioHi = Integer.toString(reader2.read());
            reader2.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }


        endlessFont.draw(game.batch,
                "Most customers\nserved in\nendless mode: " + endlessHi,
                430, 620);
        scenarioFont.draw(game.batch,
                "Fastest time\ncompleting scenario\nmode: " + scenarioHi + "s",
                430, 380);

        game.batch.end();
    }

    /**
     * The resize method resizes the settingsStage to fit the screen
     */
    @Override
    public void resize(int width, int height) {
        leaderStage.getViewport().update(width, height);
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
        leaderStage.dispose();
    }
}

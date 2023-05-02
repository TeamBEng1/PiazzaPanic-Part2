package com.mygdx.game.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.PiazzaPanic;
public class InstructionsScreen implements Screen {
    PiazzaPanic game;
    Texture instructions = new Texture("instructions.png");
    Texture backBtnTex = new Texture("backBtn.png");
    Texture backBtnTexHover = new Texture("backBtn2.png");

    FitViewport view;
    Stage gameStage;

    TextureRegion backBtnRegion;
    TextureRegionDrawable backBtnDrawable;
    ImageButton backBtn;

    TextureRegion backBtnRegionHover;
    TextureRegionDrawable backBtnDrawableHover;

    /**
     * Constructor for Instrcutions Screen
     */
    public InstructionsScreen(PiazzaPanic game){
        this.game = game;
    }

    @Override
    public void show() {
        view = new FitViewport(game.GAME_WIDTH, game.GAME_HEIGHT);
        view.getCamera().position.set(game.GAME_WIDTH / 2, game.GAME_HEIGHT / 2, 1f);
        gameStage = new Stage(view, game.batch);

        //Buttons
        backBtnRegion = new TextureRegion(backBtnTex);
        backBtnDrawable = new TextureRegionDrawable(backBtnRegion);
        backBtn = new ImageButton(backBtnDrawable);

        //hovered button
        backBtnRegionHover = new TextureRegion(backBtnTexHover);
        backBtnDrawableHover = new TextureRegionDrawable(backBtnRegionHover);

        //listen for hover
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
        gameStage.addActor(backBtn);
        backBtn.setPosition(0,game.GAME_HEIGHT-backBtn.getHeight());
    }

    /**
     * The render method draws/renders the credit screen
     */
    @Override
    public void render(float delta) {
        gameStage.act();

        ScreenUtils.clear(1, 1, 1, 1);
        view.apply();

        game.batch.setProjectionMatrix(view.getCamera().combined);
        game.batch.begin();
        game.batch.draw(instructions, ((game.GAME_WIDTH/2) - (instructions.getWidth()/2)), 60);
        game.batch.end();

        gameStage.getViewport().apply();

        if (backBtn.isPressed()){
            game.setScreen(new MainMenuScreen(game));
        }
        Gdx.input.setInputProcessor(gameStage);
        gameStage.draw();
    }

    public ImageButton getBackBtn() {
        return backBtn;
    }

    /**
     * The resize method resizes the gameStage to fit the screen
     */
    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height);
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
     * The dispose method releases all resources from the credits and gameStage objects
     */
    @Override
    public void dispose() {
        instructions.dispose();
        gameStage.dispose();
    }

}

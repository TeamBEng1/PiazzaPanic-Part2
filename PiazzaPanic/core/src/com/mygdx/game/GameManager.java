package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Json;
import com.mygdx.game.Screens.GameScreen;

public class GameManager {
    private static GameScreen Game_Screen = null;
    public String userID;
    private Preferences PREFS;
    public static final GameManager INSTANCE = new GameManager();
    String gameJson;
    private GameManager() {
        PREFS = Gdx.app.getPreferences("piazzaSave");
    }

    public void setSaveGame(GameScreen Game_Screen) {
        Json json = new Json();
        gameJson = json.toJson(GameScreen.class, Game_Screen.getClass());
        PREFS.putString("gameStore", gameJson);
        PREFS.flush();
    }

    public GameScreen loadSaveGame() {
        Json json = new Json();
        PREFS.getString("gameStore", gameJson);
        Game_Screen = json.fromJson(GameScreen.class, PREFS.getString("gameStore"));
        return Game_Screen;
    }
}

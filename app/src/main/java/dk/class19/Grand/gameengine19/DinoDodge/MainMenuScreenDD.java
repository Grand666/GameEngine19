package dk.class19.Grand.gameengine19.DinoDodge;

import android.graphics.Bitmap;

import dk.class19.Grand.gameengine19.GameEngine;
import dk.class19.Grand.gameengine19.Screen;

public class MainMenuScreenDD extends Screen
{
    Bitmap background;
    Bitmap startGame;
    float passedTime = 0;
    long startTime;

    public MainMenuScreenDD(GameEngine gameEngine)
    {
        super(gameEngine);
        background = gameEngine.loadBitmap("DinoDodge/MainMenuDD.png");
        startGame = gameEngine.loadBitmap("DinoDodge/StartGame.png");
        startTime = System.nanoTime();
    }

    @Override
    public void update(float deltaTime)
    {
        if (gameEngine.isTouchDown(0) && (passedTime) > 0.5f)
        {
            gameEngine.setScreen(new GameScreenDD(gameEngine));
            return;
        }
        gameEngine.drawBitmap(background, 0, 0);
        passedTime = passedTime + deltaTime;
        if ( (passedTime - (int)passedTime) > 0.5f)
        {
            gameEngine.drawBitmap(startGame, 960 - startGame.getWidth()/2, 540);
        }
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }
}

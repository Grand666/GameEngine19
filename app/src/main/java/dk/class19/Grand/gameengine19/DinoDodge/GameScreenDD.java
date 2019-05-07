package dk.class19.Grand.gameengine19.DinoDodge;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.List;

import dk.class19.Grand.gameengine19.GameEngine;
import dk.class19.Grand.gameengine19.Screen;
import dk.class19.Grand.gameengine19.Sound;
import dk.class19.Grand.gameengine19.TouchEvent;

public class GameScreenDD extends Screen
{
    enum State
    {
        Paused,
        Running,
        GameOver
    }

    World world = null;
    WorldRenderer renderer = null;

    State state = State.Running;
    Bitmap backGround;
    Bitmap backGroundTop;
    Bitmap gameOver;
    Bitmap resume;
    float backGroundX = 0;
    float backGroundTopX = 0;
    Sound bounceSound;
    Sound crashSound;
    Sound gameoverSound;
    int roadSpeed = 350;

    public GameScreenDD(GameEngine gameEngine)
    {
        super(gameEngine);
        backGround = gameEngine.loadBitmap("DinoDodge/GameScreenDD.png");
        backGroundTop = gameEngine.loadBitmap("DinoDodge/GameScreenTopDD.png");
        gameOver = gameEngine.loadBitmap("CarScroller/gameover.png");
        resume = gameEngine.loadBitmap("CarScroller/resume.png");
        bounceSound = gameEngine.loadSound("CarScroller/bounce.wav");
        crashSound = gameEngine.loadSound("CarScroller/blocksplosion.wav");
        gameoverSound = gameEngine.loadSound("CarScroller/gameover.wav");
        world = new World(gameEngine, new CollisionListener()
        {
            @Override
            public void collisionEdge()
            {
                bounceSound.play(1);
            }

            @Override
            public void collisionMonster()
            {
                crashSound.play(1);
            }

            @Override
            public void gameover()
            {
                gameoverSound.play(1);
            }

            @Override
            public void coin()
            {

            }
        }, roadSpeed);
        renderer = new WorldRenderer(gameEngine, world);

    }

    @Override
    public void update(float deltaTime)
    {
        if (world.gameOver)
        {
            state = State.GameOver;
            pause();
        }

        if(state == State.Paused && gameEngine.getTouchEvents().size() > 0)
        {
            Log.d("GameScreen:", "Starting the game.");
            state = State.Running;
            resume();
        }

        if(state == State.GameOver)
        {
            Log.d("GameSceen", "GameOver!");
            List<TouchEvent> touchEvents = gameEngine.getTouchEvents();
            for (int i = 0; i < touchEvents.size(); i++)
            {
                if(touchEvents.get(i).type == TouchEvent.TouchEventType.Up)
                {
                    gameEngine.setScreen(new MainMenuScreenDD(gameEngine));
                    return;
                }
            }
        }

        if (state == State.Running && gameEngine.getTouchY(0) < 40
                && gameEngine.getTouchX(0) > 320 - 40)
        {
            Log.d("GameScreen", "Pausing the game");
            state = State.Paused;
            pause();
        }

        if (state == State.Running)
        {
            backGroundX = backGroundX + roadSpeed * deltaTime;
            backGroundTopX = backGroundTopX + (roadSpeed/3) * deltaTime;
            if (backGroundX > 4920 - 1920)
            {
                backGroundX = 0;
            }
            if (backGroundTopX > 4920 - 1920)
            {
                backGroundTopX = 0;
            }
            world.update(deltaTime);
        }
        gameEngine.drawBitmap(backGround, 0, 0, (int)backGroundX, 0, 1920, 1080);
        gameEngine.drawBitmap(backGround, 0, 0, (int)backGroundTopX, 0, 1920, 360);
        renderer.render();

        if (state == State.Paused)
        {
            gameEngine.drawBitmap(resume, 960 - resume.getWidth()/2, 540 - resume.getHeight()/2);
        }

        if(state == State.GameOver)
        {
            gameEngine.drawBitmap(gameOver, 960 - gameOver.getWidth()/2, 540 - gameOver.getHeight()/2);
        }
    }

    @Override
    public void pause()
    {
        if (state == State.Running)
        {
            state = State.Paused;
            gameEngine.music.pause();
        }
    }

    @Override
    public void resume()
    {
        if (state == State.Paused)
        {
            state = State.Running;
            gameEngine.music.play();
        }
    }

    @Override
    public void dispose()
    {

    }
}

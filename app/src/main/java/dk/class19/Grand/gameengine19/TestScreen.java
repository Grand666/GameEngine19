package dk.class19.Grand.gameengine19;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestScreen extends Screen
{
    List<Bob> mobSpawner = new ArrayList<>();
    Bob bob = new Bob();
    Random random = new Random();
    int x = 0;
    int touchX = 0;
    int y = 0;
    int touchY = 0;
    Bitmap bitmap;
    Sound sound;
    Music backgroundMusic;
    Bitmap red;
    Bitmap green;
    Bitmap button;
    boolean isPlaying = false;
    List<TouchEvent> touchEventBuffer;



    public TestScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        red = gameEngine.loadBitmap("red.png");
        green = gameEngine.loadBitmap("green.png");
        button = gameEngine.loadBitmap("Duck.png");
        sound = gameEngine.loadSound("Breakout/blocksplosion.wav");
        backgroundMusic = gameEngine.loadMusic("Breakout/Glorious_morning.mp3");
        isPlaying = true;
    }

    @Override
    public void update(float deltaTime)
    {
        //Log.d("Testscreen", "FPS: " + gameEngine.getFramesPerSecond());
        gameEngine.clearFrameBuffer(Color.GRAY);


        gameEngine.drawBitmap(button, 100, 800);
        gameEngine.drawBitmap(button, 1700, 800);

        boolean pressed = false;
        boolean pressed1 = false;

        touchEventBuffer = gameEngine.getTouchEvents();

        for (int i = 0; i < touchEventBuffer.size(); i++)
        {
            if (touchEventBuffer.get(i).type == TouchEvent.TouchEventType.Down)
            {
                Log.d("", "Down");
                if (gameEngine.isTouchDown(0))
                {
                    if (gameEngine.getTouchX(0) < 200 && gameEngine.getTouchY(0) > 800)
                    {
                        if (gameEngine.getTouchX(0) > 100 && gameEngine.getTouchY(0) < 900)
                        {
                            pressed = true;
                        }
                    }
                    if (gameEngine.getTouchX(0) < 1800 && gameEngine.getTouchY(0) > 800)
                    {
                        if (gameEngine.getTouchX(0) > 1700 && gameEngine.getTouchY(0) < 900)
                        {
                            pressed1 = true;
                        }
                    }
                }
            }
            if (touchEventBuffer.get(i).type == TouchEvent.TouchEventType.Up)
            {
                Log.d("", "Up");
            }
            if (touchEventBuffer.get(i).type == TouchEvent.TouchEventType.ActionUp)
            {
                Log.d("", "Action_Up");
            }
            if (touchEventBuffer.get(i).type == TouchEvent.TouchEventType.ActionDown)
            {
                Log.d("", "ActionDown_X");
                if (gameEngine.getTouchX(0) < 200 && gameEngine.getTouchY(0) > 800)
                {
                    if (gameEngine.getTouchX(0) > 100 && gameEngine.getTouchY(0) < 900)
                    {
                        pressed1 = true;
                    }
                }
                if (gameEngine.getTouchX(0) < 1800 && gameEngine.getTouchY(0) > 800)
                {
                    if (gameEngine.getTouchX(0) > 1700 && gameEngine.getTouchY(0) < 900)
                    {
                        pressed = true;
                    }
                }
            }
        }

        if (pressed)
        {
            gameEngine.drawBitmap(green, 100, 400);
        }else
        {
            gameEngine.drawBitmap(red, 100, 400);
        }

        if (pressed1)
        {
            gameEngine.drawBitmap(green, 1700, 400);
        }else
        {
            gameEngine.drawBitmap(red, 1700, 400);
        }

        //On action up do jump player 2





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

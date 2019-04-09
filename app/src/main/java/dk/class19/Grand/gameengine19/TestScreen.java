package dk.class19.Grand.gameengine19;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

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
    boolean isPlaying = false;

    public TestScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        bitmap = gameEngine.loadBitmap("bob.png");
        sound = gameEngine.loadSound("Breakout/blocksplosion.wav");
        backgroundMusic = gameEngine.loadMusic("Breakout/Glorious_morning.mp3");
        isPlaying = true;
    }

    @Override
    public void update(float deltaTime)
    {
        Log.d("Testscreen", "FPS: " + gameEngine.getFramesPerSecond());

        if(x < 1080 + 64)
        {
            x += 100 * deltaTime;
        }
        else
        {
            x = 0;
        }


/*
        if (mobSpawner.size() <= 10)
        {

            x = random.nextInt(900);
            y = random.nextInt(1700);

            bob.setBitmap(gameEngine.loadBitmap("bob.png"));
            bob.setX(x);
            bob.setY(y);
            mobSpawner.add(bob);

            gameEngine.drawBitmap(bob.getBitmap(), x, y);

        }
*/
        if (gameEngine.isTouchDown(0))
        {
            x = gameEngine.getTouchX(0);
            y = gameEngine.getTouchY(0);
            sound.play(1);

            if(backgroundMusic.isPlaying())
            {
                backgroundMusic.pause();
                isPlaying = false;
            }
            else
            {
                backgroundMusic.play();
                isPlaying = true;
            }

            /*
            if (bob.hitbox().contains(touchX,touchY))
            {
                for (int i = 0; i < mobSpawner.size(); i++)
                {
                    if (mobSpawner.get(i).hitbox() == bob.hitbox())
                    {
                        mobSpawner.remove(i);
                    }
                }
            }

*/
        }


//        float x = gameEngine.getAccelerometer()[0];
//        float y = -1 * gameEngine.getAccelerometer()[1];


//        x = gameEngine.getFrameBufferWidth()/2 + x/10 * gameEngine.getFrameBufferWidth()/2;
//        y = gameEngine.getFrameBufferHeight()/2 + y/10 * gameEngine.getFrameBufferHeight()/2;
//        gameEngine.drawBitmap(bitmap, x , y );

        gameEngine.clearFrameBuffer(Color.GRAY);
        gameEngine.drawBitmap(bitmap, (int)x - 64, (int)y - 64);
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

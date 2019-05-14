package dk.class19.Grand.gameengine19.DinoDodge;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dk.class19.Grand.gameengine19.GameEngine;
import dk.class19.Grand.gameengine19.TouchEvent;

public class World
{
    public static final float MIN_X = 0;
    public static final float MAX_X = 1919;
    public static final float MIN_Y = 150;
    public static final float MAX_Y = 807;

    //Car car = new Car();
    Dino dino = new Dino();
    Dino dino1 = new Dino();
    List<BlockEnemy> blockEnemyList = new ArrayList<>();
    public int maxEnemies = 4;

    GameEngine gameEngine;
    CollisionListener listener;

    boolean gameOver = false;
    int points = 0;
    int lives = 1;
    int roadSpeed = 0;
    boolean topReached = false;
    boolean ducking = false;
    boolean jumping = false;
    int jumpingSpeed = 0;
    int jumpingSpeed1 = 0;
    boolean topReached1 = false;
    boolean ducking1 = false;
    boolean jumping1 = false;
    int help = 0;
    List<TouchEvent> touchEventBuffer;

    public World(GameEngine gameEngine, CollisionListener listener, int roadSpeed)
    {
        this.roadSpeed = roadSpeed;
        this.gameEngine = gameEngine;
        this.listener = listener;
        initializeEnemies();
        dino1.x = dino1.x + 220;

    }

    public void update(float deltaTime)
    {
        touchEventBuffer = gameEngine.getTouchEvents();

        //Player 1 jumping movement
        ducking = false;

        if(jumping)
        {
            dino.y -= jumpingSpeed * deltaTime;
            jumpingSpeed -= 70;
        }

        if(dino.y + Dino.HEIGHT > MAX_Y - 1)
        {
            jumping = false;
            topReached = false;
            dino.y = (int) MAX_Y - Dino.HEIGHT;
        }

        //Player 2 Jump movement
        ducking1 = false;

        if(jumping1)
        {
            dino1.y -= jumpingSpeed1 * deltaTime;
            jumpingSpeed1 -= 70;
        }

        if(dino1.y + Dino.HEIGHT > MAX_Y - 1)
        {
            jumping1 = false;
            topReached1 = false;
            dino1.y = (int) MAX_Y - Dino.HEIGHT;
        }


        for (int i = 0; i < touchEventBuffer.size(); i++)
        {
            if(touchEventBuffer.get(i).type == TouchEvent.TouchEventType.Down)
            {
                Log.d("Down", "Pointer 0");

            }
            if(touchEventBuffer.get(i).type == TouchEvent.TouchEventType.Up)
            {
                Log.d("Up", "Pointer 0");

            }
            if(touchEventBuffer.get(i).type == TouchEvent.TouchEventType.ActionDown)
            {
                Log.d("ActionDown", "Pointer 1");

            }
            if(touchEventBuffer.get(i).type == TouchEvent.TouchEventType.ActionUp)
            {
                Log.d("ActionUp", "Pointer 1");

            }
        }

        //Player 1

        if (gameEngine.volDown)
        {
            ducking = true;
        }
        if (gameEngine.volUp && !jumping)
        {
            jumpingSpeed = dino.jumpSpeed;
            jumping = true;
            topReached = false;
        }

        if (gameEngine.isTouchDown(0) && !gameEngine.isTouchDown(1) && !jumping && gameEngine.getTouchX(0) < 400)
        {
            if (gameEngine.getTouchX(0) < 200 && gameEngine.getTouchY(0) > 900)
            {
                if (gameEngine.getTouchX(0) > 100 && gameEngine.getTouchY(0) < 1000)
                {
                    jumpingSpeed = dino.jumpSpeed;
                    jumping = true;
                    topReached = false;
                }
            }

            if (gameEngine.getTouchX(0) < 350 && gameEngine.getTouchY(0) > 900)
            {
                if (gameEngine.getTouchX(0) > 250 && gameEngine.getTouchY(0) < 1000)
                {
                    ducking = true;
                }
            }
        }

        //Player 2

        if (gameEngine.isTouchDown(0) && !jumping1 )
        {
            if(!gameEngine.isTouchDown(1))
            {
                if (gameEngine.getTouchX(0) < 1820 && gameEngine.getTouchY(0) > 900)
                {
                    if (gameEngine.getTouchX(0) > 1720 && gameEngine.getTouchY(0) < 1000)
                    {
                        jumpingSpeed1 = dino1.jumpSpeed;
                        jumping1 = true;
                        topReached1 = false;
                    }
                }

                if (gameEngine.getTouchX(0) < 1670 && gameEngine.getTouchY(0) > 900)
                {
                    if (gameEngine.getTouchX(0) > 1570 && gameEngine.getTouchY(0) < 1000)
                    {
                        ducking1 = true;
                    }
                }
            }
        }


        BlockEnemy blockEnemy;
        BlockEnemy prevBlockEnemy;
        for (int i = 0; i < maxEnemies; i++)
        {
            blockEnemy = blockEnemyList.get(i);

            if (i == 0)
            {
                prevBlockEnemy = blockEnemyList.get(i + maxEnemies - 1);
            }else
            {
                prevBlockEnemy = blockEnemyList.get(i-1);
            }


            blockEnemy.x -= roadSpeed * deltaTime;
            if (blockEnemy.x < 0 - BlockEnemy.WIDTH)
            {
                Random random = new Random();
                boolean isItABird = random.nextBoolean();
                int randX = random.nextInt(200) + 100;
                int randY = random.nextInt(220) + 20;

                if(!isItABird)
                {
                    if (prevBlockEnemy.x < 1920)
                    {
                        blockEnemy.x = (1920 + randX) + random.nextInt(100) + BlockEnemy.WIDTH;
                        blockEnemy.y = 807 - BlockEnemy.HEIGHT;

                    } else
                    {
                        blockEnemy.x = (1920 + randX) + (prevBlockEnemy.x - 1920) + 200 + BlockEnemy.WIDTH;
                        blockEnemy.y = 807 - BlockEnemy.HEIGHT;
                    }
                }else
                {
                    if (prevBlockEnemy.x < 1920)
                    {
                        blockEnemy.x = (1920 + randX) + random.nextInt(100) + BlockEnemy.WIDTH;
                        blockEnemy.y = 807 - BlockEnemy.HEIGHT - randY;

                    } else
                    {
                        blockEnemy.x = (1920 + randX) + (prevBlockEnemy.x - 1920) + 200 + BlockEnemy.WIDTH;
                        blockEnemy.y = 807 - BlockEnemy.HEIGHT - randY;
                    }
                }

            }
        }

        //collideDinoEnemy();

    }

    private boolean checkTouchEvents()
    {
        List<TouchEvent> events = gameEngine.getTouchEvents();
        for (int i = 0; i < events.size(); i++)
        {
            if (events.get(i).type == TouchEvent.TouchEventType.Down)
            {

                return true;
            }
        }
        return false;
    }

    private boolean rectCollision(float x, float y, float width, float height,
                                  float x2, float y2, float width2, float height2)
    {
        if(x < x2 + width2 && x + width > x2 && y < y2 + height2 && y + height > y2)
        {
            return true;
        }
        return false;
    }

    public void collideDinoEnemy()
    {
        BlockEnemy blockEnemy;
        for (int i = 0; i < maxEnemies; i++)
        {
            blockEnemy = blockEnemyList.get(i);

            if(ducking)
            {
                if (rectCollision(dino.x, dino.y + 100, Dino.WIDTH, Dino.HEIGHT,
                        blockEnemy.x, blockEnemy.y, BlockEnemy.WIDTH, BlockEnemy.HEIGHT))
                {
                    gameOver = true;
                    Log.d("World", "collideCarMonster: GameOver");
                }
            }else
            {
                if (rectCollision(dino.x + (Dino.WIDTH / 3), dino.y, (Dino.WIDTH / 3), Dino.HEIGHT,
                        blockEnemy.x, blockEnemy.y, BlockEnemy.WIDTH, BlockEnemy.HEIGHT))
                {
                    gameOver = true;
                    Log.d("World", "collideCarMonster: GameOver");
                }
            }

        }
    }

    private void initializeEnemies()
    {
        Random random = new Random();


        for (int i = 0; i < maxEnemies; i++)
        {
            boolean isItABird = random.nextBoolean();
            Log.d("", "" + isItABird);

            if(!isItABird)
            {
                int randX = random.nextInt(300) + 100;
                BlockEnemy blockEnemy = new BlockEnemy((1920 + randX) + i * 450 + BlockEnemy.WIDTH, 807 - BlockEnemy.HEIGHT);

                blockEnemyList.add(blockEnemy);
            }else
            {
                int randX = random.nextInt(300) + 100;
                int randY = random.nextInt(220) + 20;
                BlockEnemy blockEnemy = new BlockEnemy((1920 + randX) + i * 450 + BlockEnemy.WIDTH, 807 - BlockEnemy.HEIGHT - randY);

                blockEnemyList.add(blockEnemy);
            }
        }

    }




}

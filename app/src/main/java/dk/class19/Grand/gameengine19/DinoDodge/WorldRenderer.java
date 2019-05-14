package dk.class19.Grand.gameengine19.DinoDodge;

import android.graphics.Bitmap;
import android.util.Log;

import dk.class19.Grand.gameengine19.GameEngine;

public class WorldRenderer
{
    GameEngine gameEngine;
    World world;
    Bitmap playerImg;
    Bitmap playerImg1;
    Bitmap player1Img;
    Bitmap player1Img1;
    Bitmap blockImg;
    Bitmap jumpImg;
    Bitmap jumpuImg;
    Bitmap duckImg;
    float startTime;
    float test;
    int idx = 0;

    //int worldSize = world.blocks.size();

    public WorldRenderer (GameEngine gameEngine, World world, float deltaTime)
    {
        this.gameEngine = gameEngine;
        this.world = world;
        playerImg = gameEngine.loadBitmap("DinoDodge/Run1.png");
        playerImg1 = gameEngine.loadBitmap("DinoDodge/Duck1.png");
        player1Img = gameEngine.loadBitmap("DinoDodge/Run1.2.png");
        player1Img1 = gameEngine.loadBitmap("DinoDodge/Duck1.2.png");
        jumpImg = gameEngine.loadBitmap("DinoDodge/Jump.png");
        jumpuImg = gameEngine.loadBitmap("DinoDodge/Jumpu.png");
        duckImg = gameEngine.loadBitmap("DinoDodge/Duck.png");
        blockImg = gameEngine.loadBitmap("DinoDodge/BlockF.png");
        startTime = deltaTime;
    }

    public void render(float deltaTime)
    {
        //Player 1
        test += deltaTime;

        if(!world.ducking && !world.jumping)
        {

            /*if(idx > 0)
            {
                gameEngine.drawBitmap(jumpuImg, world.dino.x, world.dino.y, 0, 0 + (180 * idx) - 20, 472, 170);
            }
            else
            {
                gameEngine.drawBitmap(jumpuImg, world.dino.x, world.dino.y, 0, 0 + (180 * idx) - 10, 472, 170);
            }

            if(test - startTime > 0.3)
            {
                idx++;
                if (idx == 3)
                {
                    idx = 0;
                }

                test = 0;

            }*/
            gameEngine.drawBitmap(playerImg, world.dino.x, world.dino.y);



        }
        if(world.ducking && !world.jumping)
        {
            gameEngine.drawBitmap(playerImg1, world.dino.x, world.dino.y + 85);
        }
        if(!world.ducking && world.jumping)
        {
            if (world.jumpingSpeed > 0 && world.jumpingSpeed < 500)
            {
                gameEngine.drawBitmap(jumpuImg, world.dino.x, world.dino.y, 0, (180 * 2) - 20, 472, 170);
            }
            if (world.jumpingSpeed > 0 && world.jumpingSpeed > 500)
            {
                gameEngine.drawBitmap(jumpuImg, world.dino.x, world.dino.y, 0, 0 - 10, 472, 170);
            }
            if (world.jumpingSpeed < 0 && world.jumpingSpeed > -500)
            {
                gameEngine.drawBitmap(jumpuImg, world.dino.x, world.dino.y, 0, (180 * 2) - 20, 472, 170);
            }
            if (world.jumpingSpeed < 0 && world.jumpingSpeed < -500)
            {
                gameEngine.drawBitmap(jumpuImg, world.dino.x, world.dino.y, 0, 180 - 20, 472, 170);
            }
        }

        //Player 2
        if(!world.ducking1 && !world.jumping1)
        {
            gameEngine.drawBitmap(player1Img, world.dino1.x, world.dino1.y);
        }
        if(world.ducking1 && !world.jumping1)
        {
            gameEngine.drawBitmap(player1Img1, world.dino1.x, world.dino1.y + 85);
        }
        if(!world.ducking1 && world.jumping1)
        {
            if (world.jumpingSpeed1 > 0 && world.jumpingSpeed1 < 500)
            {
                gameEngine.drawBitmap(jumpuImg, world.dino1.x, world.dino1.y, 0, (180 * 2) - 20, 472, 170);
            }
            if (world.jumpingSpeed1 > 0 && world.jumpingSpeed1 > 500)
            {
                gameEngine.drawBitmap(jumpuImg, world.dino1.x, world.dino1.y, 0, - 10, 472, 170);
            }
            if (world.jumpingSpeed1 < 0 && world.jumpingSpeed1 > -500)
            {
                gameEngine.drawBitmap(jumpuImg, world.dino1.x, world.dino1.y, 0, (180 * 2) - 20, 472, 170);
            }
            if (world.jumpingSpeed1 < 0 && world.jumpingSpeed1 < -500)
            {
                gameEngine.drawBitmap(jumpuImg, world.dino1.x, world.dino1.y, 0, 180 - 20, 472, 170);
            }
        }

        gameEngine.drawBitmap(jumpImg, 100, 900);
        gameEngine.drawBitmap(duckImg, 250, 900);
        gameEngine.drawBitmap(jumpImg, 1720, 900);
        gameEngine.drawBitmap(duckImg, 1570, 900);


        for (int i = 0; i < world.blockEnemyList.size(); i++)
        {
            gameEngine.drawBitmap(blockImg, world.blockEnemyList.get(i).x, world.blockEnemyList.get(i).y);
        }
    }

}

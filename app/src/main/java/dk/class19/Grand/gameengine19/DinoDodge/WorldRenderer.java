package dk.class19.Grand.gameengine19.DinoDodge;

import android.graphics.Bitmap;

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
    Bitmap duckImg;

    //int worldSize = world.blocks.size();

    public WorldRenderer (GameEngine gameEngine, World world)
    {
        this.gameEngine = gameEngine;
        this.world = world;
        playerImg = gameEngine.loadBitmap("DinoDodge/Run1.png");
        playerImg1 = gameEngine.loadBitmap("DinoDodge/Duck1.png");
        player1Img = gameEngine.loadBitmap("DinoDodge/Run1.2.png");
        player1Img1 = gameEngine.loadBitmap("DinoDodge/Duck1.2.png");
        jumpImg = gameEngine.loadBitmap("DinoDodge/Jump.png");
        duckImg = gameEngine.loadBitmap("DinoDodge/Duck.png");
        blockImg = gameEngine.loadBitmap("DinoDodge/BlockF.png");
    }

    public void render()
    {
        //Player 1
        if(!world.ducking && !world.jumping)
        {
            gameEngine.drawBitmap(playerImg, world.dino.x, world.dino.y);
        }
        if(world.ducking && !world.jumping)
        {
            gameEngine.drawBitmap(playerImg1, world.dino.x, world.dino.y + 85);
        }
        if(!world.ducking && world.jumping)
        {
            gameEngine.drawBitmap(playerImg, world.dino.x, world.dino.y);
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
            gameEngine.drawBitmap(player1Img, world.dino1.x, world.dino1.y);
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

package dk.class19.Grand.gameengine19.PhilipMiniQuiz;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dk.class19.Grand.gameengine19.DinoDodge.BlockEnemy;
import dk.class19.Grand.gameengine19.DinoDodge.CollisionListener;
import dk.class19.Grand.gameengine19.DinoDodge.Dino;
import dk.class19.Grand.gameengine19.GameEngine;
import dk.class19.Grand.gameengine19.TouchEvent;

public class World
{
    public static final float MIN_X = 0;
    public static final float MAX_X = 1079;
    public static final float MIN_Y = 0;
    public static final float MAX_Y = 1920;



    GameEngine gameEngine;


    public World(GameEngine gameEngine, CollisionListener listener, int roadSpeed)
    {
        this.gameEngine = gameEngine;
    }

    public void update(float deltaTime)
    {


    }





}

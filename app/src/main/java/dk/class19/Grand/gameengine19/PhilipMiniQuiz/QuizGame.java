package dk.class19.Grand.gameengine19.PhilipMiniQuiz;


import dk.class19.Grand.gameengine19.GameEngine;
import dk.class19.Grand.gameengine19.Screen;

public class QuizGame extends GameEngine
{
    @Override
    public Screen createStartScreen()
    {
        return new QuizScreen(this);
    }
}

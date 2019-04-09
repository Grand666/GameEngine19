package dk.class19.Grand.gameengine19.Breakout;

import dk.class19.Grand.gameengine19.GameEngine;
import dk.class19.Grand.gameengine19.Screen;

public class Breakout extends GameEngine
{


    @Override
    public Screen createStartScreen()
    {
        music = this.loadMusic("Breakout/Glorious_morning.mp3");
        return new MainMenuScreen(this);
    }

    public void onResume()
    {
        super.onResume();
        music.play();
        music.setLooping(true);
    }

    public void onPause()
    {
        super.onPause();
        music.pause();
    }
}

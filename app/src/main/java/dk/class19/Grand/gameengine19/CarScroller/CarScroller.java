package dk.class19.Grand.gameengine19.CarScroller;

import dk.class19.Grand.gameengine19.GameEngine;
import dk.class19.Grand.gameengine19.Screen;

public class CarScroller extends GameEngine
{
    @Override
    public Screen createStartScreen()
    {
        music = this.loadMusic("CarScroller/music.ogg");
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

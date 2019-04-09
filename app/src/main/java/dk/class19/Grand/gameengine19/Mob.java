package dk.class19.Grand.gameengine19;

import android.graphics.Bitmap;
import android.graphics.Rect;

public interface Mob
{
    int hp();
    Rect hitbox();
    String filename();
}

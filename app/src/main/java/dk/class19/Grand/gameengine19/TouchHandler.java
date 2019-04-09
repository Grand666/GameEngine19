package dk.class19.Grand.gameengine19;

public interface TouchHandler
{
    boolean isTouchDown(int pointer);
    int getTouchX(int pointer);
    int getTouchY(int pointer);
}

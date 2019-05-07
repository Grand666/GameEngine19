package dk.class19.Grand.gameengine19;

import android.view.MotionEvent;
import android.view.View;

import java.util.List;

public class MultiTouchHandler implements TouchHandler, View.OnTouchListener
{
    private boolean[] isTouched =  new boolean[20]; //Store the first 20 touches
    private int[] touchX = new int[20];
    private int[] touchY = new int[20];
    private int[] touchRawX = new int[20];
    private int[] touchRawY = new int[20];

    private List<TouchEvent> touchEventBuffer;  //Buffer with touch
    private TouchEventPool touchEventPool;      //Pool with  re-usable TouchEvent objects

    public MultiTouchHandler(View view, List<TouchEvent> touchEventBuffer, TouchEventPool touchEventPool)
    {
        view.setOnTouchListener(this);
        this.touchEventBuffer = touchEventBuffer;
        this.touchEventPool = touchEventPool;
    }
    public MultiTouchHandler(View view)
    {
        view.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        TouchEvent touchEvent = null;
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >>
                MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        int pointerId = event.getPointerId(pointerIndex);


        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                touchEvent = touchEventPool.obtains();
                touchEvent.type = TouchEvent.TouchEventType.Down;
                touchEvent.pointer = pointerId;

                touchEvent.rawX = (int) event.getRawX();
                touchRawX[pointerId] = touchEvent.rawX;

                touchEvent.rawY = (int) event.getRawY();
                touchRawY[pointerId] = touchEvent.rawY;

                touchEvent.x = (int)event.getX();
                touchX[pointerId] = touchEvent.x;

                touchEvent.y = (int)event.getY();
                touchY[pointerId] = touchEvent.y;

                isTouched[pointerId] = true;

                synchronized (touchEventBuffer)
                {
                    touchEventBuffer.add(touchEvent);
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                touchEvent = touchEventPool.obtains();
                touchEvent.type = TouchEvent.TouchEventType.ActionDown;
                touchEvent.pointer = pointerId;

                touchEvent.rawX = (int) event.getRawX();
                touchRawX[pointerId] = touchEvent.rawX;

                touchEvent.rawY = (int) event.getRawY();
                touchRawY[pointerId] = touchEvent.rawY;

                touchEvent.x = (int)event.getX();
                touchX[pointerId] = touchEvent.x;

                touchEvent.y = (int)event.getY();
                touchY[pointerId] = touchEvent.y;

                isTouched[pointerId] = true;

                synchronized (touchEventBuffer)
                {
                    touchEventBuffer.add(touchEvent);
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                touchEvent = touchEventPool.obtains();
                touchEvent.type = TouchEvent.TouchEventType.ActionUp;
                touchEvent.pointer = pointerId;

                touchEvent.x = (int)event.getX();
                touchX[pointerId] = touchEvent.x;

                touchEvent.y = (int)event.getY();
                touchY[pointerId] = touchEvent.y;

                isTouched[pointerId] = false;

                synchronized (touchEventBuffer)
                {
                    touchEventBuffer.add(touchEvent);
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                touchEvent = touchEventPool.obtains();
                touchEvent.type = TouchEvent.TouchEventType.Up;
                touchEvent.pointer = pointerId;

                touchEvent.x = (int)event.getX();
                touchX[pointerId] = touchEvent.x;

                touchEvent.y = (int)event.getY();
                touchY[pointerId] = touchEvent.y;

                isTouched[pointerId] = false;

                synchronized (touchEventBuffer)
                {
                    touchEventBuffer.add(touchEvent);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int pointerCount = event.getPointerCount();
                synchronized (touchEventBuffer)
                {
                    for (int i = 0; i < pointerCount; i++)
                    {
                        touchEvent = touchEventPool.obtains();
                        touchEvent.type = TouchEvent.TouchEventType.Dragged;
                        touchEvent.pointer = pointerId;

                        touchEvent.x = (int)event.getX();
                        touchX[pointerId] = touchEvent.x;

                        touchEvent.y = (int)event.getY();
                        touchY[pointerId] = touchEvent.y;

                        isTouched[pointerId] = true;

                        touchEventBuffer.add(touchEvent);
                    }
                }
                break;

        }
        return true; //Telling the Android system I did handle this onTouch event!
    }

    @Override
    public boolean isTouchDown(int pointer)
    {
        return isTouched[pointer];
    }

    @Override
    public int getTouchX(int pointer)
    {
        return touchX[pointer];
    }

    @Override
    public int getTouchY(int pointer)
    {
        return touchY[pointer];
    }
    @Override
    public int getRawTouchX(int pointer)
    {
        return touchRawX[pointer];
    }

    @Override
    public int getRawTouchY(int pointer)
    {
        return touchRawY[pointer];
    }
}


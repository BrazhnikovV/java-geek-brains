package com.example.jcore.lesson_5.paint_elements_threads;

import java.awt.*;

public class BallRunnable implements Runnable
{
    private Ball ball;
    private Component component;
    public static final int STEPS = 1000;
    public static final int DELAY = 4;

    public BallRunnable ( Ball aBall, Component aComponent ) {
        ball = aBall;
        component = aComponent;
    }

    public void run () {
        try {
            for ( int i = 1; i <= STEPS; i++ ) {
                ball.move( component.getBounds() );
                component.repaint();
                Thread.sleep( DELAY );
            }
        }
        catch ( InterruptedException e ) {

        }
    }
}

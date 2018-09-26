package jcore.lesson_5.paint_elements_threads;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BounceFrame extends JFrame
{
    private BallComponent comp;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;

    public BounceFrame () {
        setTitle( "Bounce" );

        comp = new BallComponent();
        add( comp, BorderLayout.CENTER );
        JPanel buttonPanel = new JPanel();
        addButton( buttonPanel, "Start", new ActionListener() {
            public void actionPerformed( ActionEvent event ) {
                addBall();
            }
        });

        addButton( buttonPanel, "Close", new ActionListener() {
            public void actionPerformed( ActionEvent event ) {
                System.exit( 0 );
            }
        });

        add( buttonPanel, BorderLayout.SOUTH );
        pack();
    }

    public void addButton ( Container c, String title, ActionListener listener ) {
        JButton button = new JButton( title );
        c.add( button );
        button.addActionListener( listener );
    }

    public void addBall () {
        Ball ball = new Ball();
        comp.add( ball );

        Runnable r = new BallRunnable( ball, comp );
        Thread t = new Thread( r );
        t.start();
    }
}

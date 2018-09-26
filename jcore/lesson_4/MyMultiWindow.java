package jcore.lesson_4;

import javax.swing.*;
import java.awt.*;

public class MyMultiWindow extends JFrame {

    public MyMultiWindow () {
        setBounds( 500, 200, 800, 600 );
        setTitle( "Gui Demo" );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setLayout( new GridLayout( 2, 2 ) );
        JPanel[] jp = new JPanel[4];

        for ( int i = 0; i < 4; i++ ) {
            jp[i] = new JPanel();
            add( jp[i] );
        }

        setVisible( true );
    }
}

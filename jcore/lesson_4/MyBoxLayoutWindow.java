package jcore.lesson_4;

import javax.swing.*;
import java.awt.*;

public class MyBoxLayoutWindow extends JFrame {
    public MyBoxLayoutWindow () {
        setTitle( "Новое окно" );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setBounds( 500, 500, 400, 300 );

        JButton[] jbs = new JButton[10];
        //setLayout(  new BoxLayout( getContentPane() , BoxLayout.Y_AXIS ) );
        //setLayout(  new FlowLayout());
        setLayout( new GridLayout( 4, 3 ) );

        for ( int i = 0; i < 10; i++ ) {
            jbs[i] = new JButton( "#" + i );
            jbs[i].setAlignmentX( CENTER_ALIGNMENT );
            add( jbs[i] );
        }

        setVisible( true );
    }
}

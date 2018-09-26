package jcore.lesson_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame {

    private static final int window_x = 300;
    private static final int window_y = 300;
    private static final int window_width = 400;
    private static final int window_height = 400;

    public MyWindow () {
        setTitle( "Месенджер" );
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        setBounds( window_x, window_y, window_width, window_height );

        JButton[] jbs = new JButton[5];
        JMenuBar main_menu = new JMenuBar();

        JMenu m_file = new JMenu( "file" );
        JMenu m_edit = new JMenu( "edit" );

        JMenuItem mi_file_new  = new JMenuItem( "New" );
        JMenuItem mi_file_exit = new JMenuItem( "Exit" );

        setJMenuBar( main_menu );
        main_menu.add( m_file );
        main_menu.add( m_edit );

        m_file.add( mi_file_new );
        m_file.addSeparator();
        m_file.add( mi_file_exit );

        mi_file_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        for ( int i = 0; i < 5; i++ ) {
            jbs[i] = new JButton( "#" + 1 );
        }

        // выбор компоновщика элементов
        setLayout( new BorderLayout());

        // Добавление кнопки на форму
        add( jbs[0], BorderLayout.EAST );
        add( jbs[1], BorderLayout.WEST );
        add( jbs[2], BorderLayout.SOUTH );
        add( jbs[3], BorderLayout.NORTH );
        add( jbs[4], BorderLayout.CENTER );

        setVisible( true );
    }
}

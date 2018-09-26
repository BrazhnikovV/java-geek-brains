package jprof.lesson_2.client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Messenger extends JFrame {

    /**
     *  @access private
     *  @var IController controller
     */
    private IController controller;

    /**
     *  @access private static
     *  @var integer window_width
     */
    private static final int window_width = 400;

    /**
     *  @access private static
     *  @var integer window_height
     */
    private static final int window_height = 400;

    /**
     *  @access private static
     *  @var JButton jbutton
     */
    private static final JButton jbutton = new JButton( "Отправить" );

    /**
     *  @access private static
     *  @var JTextArea text_area
     */
    private static final JTextArea text_area = new JTextArea( 10, 35 );

    /**
     *  @access private static
     *  @var JTextField text_field
     */
    private static final JTextField text_field = new JTextField(35 );

    /**
     *  @access private static
     *  @var String all_messages
     */
    private static String all_messages = "";

    /**
     * constructor
     *
     * @param controller
     */
    public Messenger( IController controller ) {
        // устанавливаем титульный заголовок окна
        setTitle( "Месенджер" );
        // подписываемся на событие клика по кнопки закрыть окна
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
        // устанавливаем параметры формы
        setSize( window_width, window_height );
        setResizable( false );

        // Jpanel panel_top_buttons
        JPanel panel = new JPanel();
        panel.setBackground( Color.lightGray );
        // выбор компоновщика элементов
        panel.setLayout( new FlowLayout() );

        // Создаем текстовую область для отображения переписки
        text_area.setBorder( new LineBorder( Color.GRAY, 1 ) );
        text_area.setEditable( false );
        text_area.setLineWrap( true );

        // Добавление элементов к панели
        panel.add( text_field );
        panel.add( text_area );
        panel.add( jbutton );

        // подписываемся на события клика по кнопке отправить
        jbutton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String message = text_field.getText();
                controller.sendMessage( message );
            }
        });

        // подписываемся на события нажатия кнопки Enter
        text_field.addKeyListener( new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            public void keyPressed(KeyEvent e) {
                Integer key_code = e.getKeyCode();

                if ( key_code == 10 ) {
                    String message = text_field.getText();
                    controller.sendMessage( message );
                }
            }
        });

        // подписываемся на события закрытия окна
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e ) {
                super.windowClosing( e );
                controller.closeConnection();
            }
        });

        // Добавляем панель к окну
        add( panel );
        setVisible( true );
    }

    /**
     * insertMessage - вставить сообщение пользователя в
     * текстовую область для отображения переписки
     *
     * @param text - текст сообщения
     */
    public void insertMessage ( String text )  {
        text_field.setText( "" );
        text_area.setText( all_messages += text + "\n" );
    }
}

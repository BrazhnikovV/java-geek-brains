package com.example.jcore.lesson_7.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * ClientHandler -
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_7.server
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class ClientHandler {

    /**
     *  @access private
     *  @var Server server
     */
    private Server server;

    /**
     *  @access private
     *  @var PrintWriter pw
     */
    private PrintWriter pw;

    /**
     *  @access private
     *  @var Scanner sc
     */
    private Scanner sc;

    /**
     * constructor
     *
     * @param socket -
     * @param server -
     */
    public ClientHandler( Socket socket, Server server ) {

        this.server = server;

        try {
            sc = new Scanner( socket.getInputStream() );
            pw = new PrintWriter( socket.getOutputStream(), true );

            new Thread(() -> {
                System.out.println( "Клиент подключен!" );

                while ( socket.isConnected() ) {
                    String s = sc.nextLine();
                    sendMessage( s );
                }
            }).start();
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * sendMessage - отправить сообщение
     *
     * @param msg - текст сообщения
     */
    private void sendMessage( String msg ) {
        pw.println(msg);
    }
}

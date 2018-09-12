package com.example.jcore.lesson_7.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

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
     *  @access public
     *  @var String name
     */
    public String name;

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
     * @access public
     * @param socket -
     * @param server -
     */
    public ClientHandler( Socket socket, Server server ) {

        this.server = server;

        try {
            sc = new Scanner( socket.getInputStream() );
            pw = new PrintWriter( socket.getOutputStream(), true );

            // Присваиваем имя клиенту и подписываемся на сервере
            this.name = UUID.randomUUID().toString();
            server.subscribe(this );

            new Thread(() -> {
                while ( socket.isConnected() ) {
                    String s = sc.nextLine();
                    //sendMessage( s );
                    if (s != null && !s.isEmpty())
                        server.sendBroadcastMessage(this.name + " : " + s);
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
     * @access public
     * @param msg - текст сообщения
     */
    public void sendMessage( String msg ) {
        System.out.println( "ClientHandler => sendMessage" );
        pw.println( msg );
    }
}

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

            // выполняем авторизацию польлзователя
            auth();

            // цикл получения пользовательских сообщений
            new Thread(() -> {
                while ( socket.isConnected() ) {
                    String s = sc.nextLine();
                    System.out.println( "ClientHandler => Thread(() -> II" );
                    if ( s != null && s.equals( "/exit" ) ) {
                        server.unsubscribe(this );
                    }

                    if ( s != null && !s.isEmpty() ) {
                        System.out.println( "s != null && !s.isEmpty()" );
                        server.sendBroadcastMessage( this.name, this.name + " : " + s );
                    }
                    System.out.println( "Дальше" );
                }
            }).start();
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * auth - Wait for command: "/auth login1 pass1"
     */
    private void auth() {
        while ( true ) {

            if ( !sc.hasNextLine() ) {
                continue;
            }

            String s = sc.nextLine();
            if ( s.startsWith( "/auth" ) ) {

                // получаем параметры из текстового сообщения и выполняем проверки авторизации
                String[] commands = s.split(" " );
                if ( commands.length >= 3 ) {
                    String login = commands[1];
                    String password = commands[2];

                    String name = server.getAuthService().getNickByLoginPass( login, password );

                    if ( name == null ) {
                        String msg = "Неверный логин или пароль";
                        pw.println( msg );
                    }
                    else if ( name == this.name ) {
                        String msg = "Учетная запись уже используется!";
                        pw.println( msg );
                    }
                    else {
                        this.name = name;
                        String msg = "Auth ok!";
                        pw.println( msg );
                        server.subscribe(this );
                        break;
                    }
                }
            }
        }
    }

    /**
     * sendMessage - отправить сообщение
     *
     * @access public
     * @param msg - текст сообщения
     */
    public void sendMessage( String msg ) {
        pw.println( msg );
    }
}

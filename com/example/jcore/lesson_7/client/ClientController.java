package com.example.jcore.lesson_7.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * ClientController -
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_7.client
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class ClientController {

    /**
     *  @access private
     *  @var String SERVER_ADDR
     */
    private final static String SERVER_ADDR = "localhost";

    /**
     *  @access private
     *  @var integer SERVER_PORT
     */
    private final static int SERVER_PORT = 8189;

    /**
     *  @access private
     *  @var Socket sock
     */
    private Socket sock;

    /**
     *  @access private
     *  @var Scanner in
     */
    private Scanner in;

    /**
     *  @access private
     *  @var PrintWriter out
     */
    private PrintWriter out;

    /**
     * constructor
     *
     */
    public ClientController() {
        initConnection();
    }

    /**
     * initConnection
     *
     * @access private
     */
    private void initConnection() {
        try {
            sock = new Socket( SERVER_ADDR, SERVER_PORT );
            in   = new Scanner( sock.getInputStream() );
            out  = new PrintWriter( sock.getOutputStream(), true );
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                while ( true ) {
                    if ( in.hasNext() ) {

                        String w = in.nextLine();

                        if ( w.startsWith( "end session" ) ) break;
                    }
                }
            }
            catch ( Exception e ) {}
        }).start();
    }
}

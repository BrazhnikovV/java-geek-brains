package com.example.jcore.lesson_7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server -
 *
 * @version 1.0.1
 * @package com.example.jcore.lesson_7.server
 * @author  Vasya Brazhnikov
 * @copyright Copyright (c) 2018, Vasya Brazhnikov
 */
public class Server {

    /**
     *  @access private
     *  @var integer SERVER_PORT
     */
    private final static int SERVER_PORT = 8189;

    /**
     *  @access private
     *  @var ServerSocket server
     */
    private ServerSocket server;

    /**
     * constructor
     */
    public Server() {

        try {
            server = new ServerSocket( SERVER_PORT );
            System.out.println("Сервер запущен, ожидаем подключения...");
        }
        catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
            close();
        }
    }

    /**
     * main -
     * @param args -
     */
    public static void main( String[] args ) {
        Server server = new Server();
        server.start();
    }

    /**
     * close - закрыть
     *
     * @access private
     */
    private void close() {
        try {
            server.close();
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
        System.exit(0 );
    }

    /**
     * start - Запустить сервер
     *
     * @access private
     */
    private void start() {
        while ( true ) {
            try {
                Socket client_socket = server.accept();
                ClientHandler clientHandler = new ClientHandler( client_socket, this );
            }
            catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }
}

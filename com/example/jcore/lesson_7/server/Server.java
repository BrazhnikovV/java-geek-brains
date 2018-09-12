package com.example.jcore.lesson_7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

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
     *  @access private
     *  @var ClientList client_list
     */
    private ClientList client_list;

    /**
     * constructor
     */
    public Server() {

        try {
            server      = new ServerSocket( SERVER_PORT );
            client_list = new ClientList();
            System.out.println( "Сервер запущен, ожидаем подключения..." );
        }
        catch ( IOException e ) {
            System.out.println( "Ошибка инициализации сервера" );
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
     * subscribe -
     * @access public
     * @param client_handler -
     */
    public void subscribe( ClientHandler client_handler ) {
        System.out.println( client_handler.name + ": подключен!" );
        client_list.add( client_handler );
        //String msg = "Клиент подключился";
        //sendBroadcastMessage(msg);
    }

    /**
     * sendBroadcastMessage - реализация широкополосного оповещения клиентов
     * @access public
     * @param msg - текст сообщения
     */
    public void sendBroadcastMessage( String msg ) {
        System.out.println( "Server => sendBroadcastMessage" );
        // получаем список клиентов для обхода в цикле
        List<ClientHandler> inner_cl_list = client_list.get();

        for ( ClientHandler client : inner_cl_list ) {
            client.sendMessage( msg );
        }
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

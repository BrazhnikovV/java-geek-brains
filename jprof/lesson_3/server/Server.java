package jprof.lesson_3.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Server - класс реализующий сервер
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
     *  @access private
     *  @var AuthService auth_service
     */
    private IAuthService auth_service;

    /**
     * constructor
     */
    public Server( IAuthService auth_service ) {

        this.auth_service = auth_service;

        try {
            server      = new ServerSocket( SERVER_PORT );
            client_list = new ClientList();
            System.out.println( "Сервер запущен, ожидаем подключения..." );
            checkСlientssLoyalty();
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
        IAuthService base_auth_service = new BaseAuthService();
        Server server = new Server( base_auth_service );
        server.start();
    }

    /**
     * subscribe -
     * @access public
     * @param client_handler -
     */
    public synchronized void subscribe( ClientHandler client_handler ) {
        client_list.add( client_handler );
        sendBroadcastMessage( client_handler.name, client_handler.name + ": подключен!" );
        broadcastClientsList();
    }

    /**
     * unsubscribe -
     * @access public
     * @param client_handler -
     */
    public synchronized void unsubscribe( ClientHandler client_handler ) {
        String msg = "Клиент " + client_handler.name + " отключился";
        sendBroadcastMessage( client_handler.name, msg );
        client_list.remove( client_handler );
        broadcastClientsList();

        // получаем список клиентов
        List<ClientHandler> inner_cl_list = client_list.get();
        if ( inner_cl_list.size() == 0 ) {
            //close();
        }
    }

    /**
     * getAuthService -
     * @access public
     */
    public IAuthService getAuthService() {
        return this.auth_service;
    }

    /**
     * sendBroadcastMessage - реализация широкополосного оповещения клиентов
     * @access public
     * @param name - имя
     * @param msg - текст сообщения
     */
    public synchronized void sendBroadcastMessage( String name, String msg ) {
        // получаем список клиентов для обхода в цикле
        List<ClientHandler> inner_cl_list = client_list.get();

        for ( ClientHandler client : inner_cl_list ) {
            client.sendMessage( msg );
        }
    }

    /**
     * broadcastClientsList -
     * @access public
     */
    public synchronized void broadcastClientsList() {
        // получаем список клиентов для обхода в цикле
        List<ClientHandler> inner_cl_list = client_list.get();

        String client_list = "/clients";
        for ( ClientHandler client : inner_cl_list ) {
            client_list += " " + client.name;
        }

        for ( ClientHandler client : inner_cl_list ) {
            client.sendMessage( client_list );
        }
    }

    /**
     * sendPrivateMessage - реализация личного сообщения для клиента
     * @access public
     * @param name - имя клиента
     * @param msg - текст сообщения
     */
    public synchronized void sendPrivateMessage( String name, String msg ) {
        // получаем список клиентов для обхода в цикле
        List<ClientHandler> inner_cl_list = client_list.get();

        for ( ClientHandler client : inner_cl_list ) {
            if ( name.trim().equals( client.name.trim() ) ) {
                client.sendMessage( msg );
            }
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
        System.out.println( "Сервер остановлен." );
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

    /**
     * checkСlientssLoyalty - запускает реализацию setInterval
     * для опроса клиентов на предмет их активности
     *
     * @access private
     */
    private synchronized void checkСlientssLoyalty() {
        new Thread(() -> {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    // получаем список клиентов для обхода в цикле
                    List<ClientHandler> inner_cl_list = client_list.get();

                    for ( ClientHandler client : inner_cl_list ) {
                        long time_act = client.getLastActionTime();
                        long time_now = System.currentTimeMillis();
                        long difference = time_now - time_act;

                        if ( difference > 120000 ) {
                            unsubscribe( client );
                        }
                    }
                }
            };
            timer.schedule( task, 0L ,1000L );
        }).start();
    }
}

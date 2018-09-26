package jprof.lesson_2.server;

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
     * 
     * @param auth_service
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
        if ( inner_cl_list.isEmpty() ) {
            //close();
        }
    }

    /**
     * getAuthService -
     * @access public
     * @return IAuthService
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

        inner_cl_list.forEach( ( client ) -> {
            client.sendMessage( msg );
        });
    }

    /**
     * broadcastClientsList -
     * @access public
     */
    public synchronized void broadcastClientsList() {
        // получаем список клиентов для обхода в цикле
        List<ClientHandler> inner_cl_list = client_list.get();

        String str_clients = "/clients";
        
        str_clients = inner_cl_list.stream()
            .map( ( client ) -> " " + client.name )
            .reduce( str_clients, String::concat );

        for ( ClientHandler client : inner_cl_list ) {
            client.sendMessage( str_clients );
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

        inner_cl_list.stream()
            .filter((client) -> ( name.trim().equals( client.name.trim() ) ))
            .forEachOrdered(( client ) -> {
                client.sendMessage( msg );
            });
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
            //e.printStackTrace();
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
                //e.printStackTrace();
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
                
                @Override
                public void run() {
                    // получаем список клиентов для обхода в цикле
                    List<ClientHandler> inner_cl_list = client_list.get();

                    inner_cl_list.forEach(( client ) -> {
                        long time_act = client.getLastActionTime();
                        long time_now = System.currentTimeMillis();
                        long difference = time_now - time_act;
                        if (difference > 120000) {
                            unsubscribe( client );
                        }
                    });
                }
            };
            timer.schedule( task, 0L ,1000L );
        }).start();
    }
}

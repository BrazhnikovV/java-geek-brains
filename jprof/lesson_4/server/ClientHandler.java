package jprof.lesson_4.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ClientHandler - класс обработчик клиентских подключений и обработки сообщений
 *
 * @version 1.0.1
 * @package jprof.lesson_4.server
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
     *  @access private
     *  @var long last_action_time
     */
    private long last_action_time;

    /**
     *  @access private
     *  @var ExecutorService executor
     */
    private ExecutorService executor = Executors.newFixedThreadPool(10 );

    /**
     *  @access private
     *  @var Future<Integer> future
     */
    private Future<Integer> future;

    /**
     * constructor
     *
     * @access public
     * @param socket - сокет подлючения
     * @param server - серверный сокет
     */
    public ClientHandler( Socket socket, Server server ) {

        this.server = server;

        try {
            sc = new Scanner( socket.getInputStream() );
            pw = new PrintWriter( socket.getOutputStream(), true );

            this.future = executor.submit(() -> {
                this.handler( socket, server );
                return 1;
            });
            //new Thread(() -> {
            //    this.handler( socket, server );
            //}).start();
        }
        catch ( IOException e ) {
            e.printStackTrace();
        }
    }

    /**
     * getLastActionTime -
     *
     * @access public
     */
    public long getLastActionTime( ) {
        return this.last_action_time;
    }

    /**
     * getFuture - получить объект Future
     *
     * @access public
     */
    public Future<Integer> getFuture() {
        return this.future;
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

    /**
     * handler -
     *
     * @param socket - сокет подлючения
     * @param server - серверный сокет
     */
    private void handler( Socket socket, Server server ) {
        // выполняем авторизацию польлзователя
        this.auth();

        // цикл получения пользовательских сообщений
        while ( socket.isConnected() ) {

            String s = sc.nextLine();
            if (s != null && s.equals( "/exit" )) {
                server.unsubscribe(this );
            }
            // получение личных сообщений
            else if (s.startsWith( "/w" )) {
                // получаем параметры из текстового сообщения и
                // выполняем проверки авторизации
                String[] commands = s.split(" " );
                if ( commands.length >= 2 ) {
                    String login = commands[1];

                    if ( login != null && !login.isEmpty() ) {
                        String msg = "Привет";
                        server.sendPrivateMessage( login, msg );
                    }
                }
            }
            // получение общих сообщений
            else if ( s != null && !s.isEmpty() ) {
                server.sendBroadcastMessage( this.name, this.name + " : " + s );
            }
            else {
                String msg = "Что-то пошло не так :(";
                server.sendPrivateMessage( this.name, msg );
            }
            // делаем пометку что пользователь производил
            // действие ( попытка авторизации )
            last_action_time = System.currentTimeMillis();
        }
    }

    /**
     * auth - авторизацию по текстовому сообщению: "/auth login1 pass1"
     */
    private void auth() {
        while ( true ) {

            if ( !sc.hasNextLine() ) {
                continue;
            }

            String s = sc.nextLine();
            if ( s.startsWith( "/auth" ) ) {

                // делаем пометку что пользователь производил
                // действие ( попытка авторизации )
                last_action_time = System.currentTimeMillis();

                // получаем параметры из текстового сообщения и выполняем проверки авторизации
                String[] commands = s.split(" " );
                if ( commands.length >= 3 ) {
                    String login = commands[1];
                    String password = commands[2];

                    String name = server.getAuthService().getLoginByLoginPass( login, password );

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
}

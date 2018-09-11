package com.example.jcore.lesson_6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        ServerSocket serv = null;

        Socket sock = null;

        try {
            serv = new ServerSocket(8189 );
            System.out.println(" Сервер запущен и ожидает подключения.");

            sock = serv.accept();
            System.out.println(" Клиент подключился.");
            Scanner sc = new Scanner( sock.getInputStream() );
            PrintWriter pw = new PrintWriter( sock.getOutputStream() );

            Scanner console = new Scanner( System.in );
            Thread thread_console = new Thread(() -> {
                while ( true ) {
                    System.out.println( console.nextLine() );
                }
            });

            thread_console.start();

            while ( true ) {
                String str = sc.nextLine();

                if ( str.equals( "end" ) ) {
                    break;
                }

                System.out.println( "Эхо:" + str );
                //pw.println( "Эхо:" + str );
                //pw.flush();
            }
        }
        catch ( IOException e ) {
            System.out.println(" Ошибка инициализации сервера.");
        }
        finally {
            try {
                serv.close();
            }
            catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }
}

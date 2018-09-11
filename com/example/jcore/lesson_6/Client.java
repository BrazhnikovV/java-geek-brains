package com.example.jcore.lesson_6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client  {

    private final static String SERVER_ADDR = "localhost";
    private final static int SERVER_PORT = 8189;

    private static Socket sock;
    private static Scanner in;
    private static Scanner in_serv;
    private static PrintWriter out;

    public static void main(String[] args) {
        initConnection();
    }

    private static void initConnection() {
        try {
            sock = new Socket( SERVER_ADDR, SERVER_PORT );
            in   = new Scanner( System.in );
            in_serv = new Scanner( sock.getInputStream() );
            out  = new PrintWriter( sock.getOutputStream(), true );
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                while ( true ) {
                    if ( in.hasNext() ) {
                        String w = in.nextLine();
                        if ( w.startsWith("end session")) {
                            try {
                                out.println("end");
                                sock.close();
                                out.close();
                                in.close();
                            }
                            catch (IOException exc) {}
                            break;
                        }
                        out.println(w);
                    }
                    if ( in_serv.hasNext() ) {
                        String serv_str = in_serv.nextLine();
                        System.out.println( serv_str );
                    }
                }
            }
            catch (Exception e) {}
        }).start();
    }
}

package com.example.jcore.lesson_5.send_message_threads;

import java.util.*;

public class Consumer implements Runnable {

    Producer producer;

    public Consumer( Producer producer ) {
        this.producer = producer;
    }

    public static void main(String[] args) {
        Producer producer = new Producer();
        new Thread( producer ).start();

        Consumer consumer = new Consumer( producer );
        new Thread( consumer ).start();
    }

    @Override
    public void run() {
        while ( true ) {
            String message = producer.getMessage();
            System.out.println( "Got message: " + message );

            try {
                Thread.sleep( 2000 );
            }
            catch ( InterruptedException e ) {

            }
        }
    }
}

package jcore.lesson_5.send_message_threads;

import java.util.ArrayList;
import java.util.List;

public class Producer implements Runnable {

    public static final int MAXQUEUE = 5;

    private List messages = new ArrayList();

    @Override
    public void run() {
        while ( true ) {
            putMessage();

            try {
                Thread.sleep( 1000 );
            }
            catch ( InterruptedException e ) {}
        }
    }

    public synchronized String getMessage () {
        while ( messages.size() == 0 )
        try {
            notify();
            wait();
        }
        catch ( InterruptedException e ) {}

        String message = ( String ) messages.remove(0 );
        notify();

        return message;
    }

    private synchronized void putMessage() {
        while ( messages.size() >= MAXQUEUE )
        try {
            wait();
        }
        catch ( InterruptedException e ) {}

        messages.add( new java.util.Date().toString() );
        notify();
    }
}

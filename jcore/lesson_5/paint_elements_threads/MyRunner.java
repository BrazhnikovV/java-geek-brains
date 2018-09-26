package jcore.lesson_5.paint_elements_threads;

import java.lang.*;

public class MyRunner implements Runnable {

    @Override
    public void run () {
        for ( int i = 0; i < 10; i++ ) {
            try {
                Thread.sleep( 100 );
                System.out.println( "tread " + i );
            }
            catch ( InterruptedException e ) {
                e.printStackTrace();
            }
        }
    }
}

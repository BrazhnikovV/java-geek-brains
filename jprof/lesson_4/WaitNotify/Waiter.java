package jprof.lesson_4.WaitNotify;

public class Waiter implements Runnable{

    private final Monitor monitor;

    private static String[] chars = { "A", "B", "C" };

    private static int countRivalry = 0;

    private static int countIterations = 0;

    public Waiter( Monitor m ){
        this.monitor = m;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        synchronized ( this.monitor ) {
            while ( this.countIterations < 5 ) {
                try {
                    if ( this.chars[this.countRivalry] == name ) {

                        this.countRivalry++;
                        if ( countRivalry == this.chars.length ) {
                            this.countRivalry = 0;
                        }

                        if ( name == this.chars[this.chars.length - 1] ) {
                            this.countIterations++;
                        }
                        monitor.notifyAll();
                        System.out.println( name );
                        monitor.wait();
                    }
                    else {
                        monitor.wait();
                    }
                }
                catch ( InterruptedException e ) {
                    e.printStackTrace();
                }
            }
        }
    }
}
